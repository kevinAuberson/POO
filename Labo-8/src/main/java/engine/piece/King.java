package engine.piece;

import chess.*;
import engine.movement.*;
import engine.Cell;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file King.java
 * @brief Contains the King class that represents the King chess piece.
 * @date 10-01-2024
 * <p>
 * The King class extends the Piece class.
 * It defines the movement capabilities of the King piece.
 * </p>
 */
public class King extends Piece {

    private boolean firstMove = true;
    /**
     * Constructs a new King with the specified color.
     *
     * @param color The color of the King piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public King(PlayerColor color) {
        super(color, PieceType.KING, 1, new Movement[]{
                new Horizontal(Direction.RIGHT),
                new Horizontal(Direction.LEFT),
                new Vertical(Direction.UP),
                new Vertical(Direction.DOWN),
                new Diagonal(Direction.DIAG_UP_RIGHT),
                new Diagonal(Direction.DIAG_UP_LEFT),
                new Diagonal(Direction.DIAG_DOWN_RIGHT),
                new Diagonal(Direction.DIAG_DOWN_LEFT),
        });
    }

    /**
     * Validates the King's movement and checks for castling conditions.
     *
     * @param board The chessboard represented by a 2D array of cells.
     * @param x     The destination X-coordinate.
     * @param y     The destination Y-coordinate.
     * @param turn  The current turn number.
     * @return The type of movement (MovementType) after validation.
     */
    public MovementType validateMov(Cell[][] board, int x, int y, int turn){
        // Validate the King's standard movement
        MovementType movementType = super.validateMov(board, x, y, turn);

        // Check for castling conditions if the standard movement is denied
        if(movementType == MovementType.DENIED && firstMove){
            if(smallCastling(board, x, y)){
                movementType = MovementType.SMALL_CASTLING;
            } else if(bigCastling(board, x, y)){
                movementType = MovementType.BIG_CASTLING;
            }
        }

        return movementType;
    }

    /**
     * Checks if castling is possible and returns the type of castling (small or big).
     *
     * @param board The chessboard represented by a 2D array of cells.
     * @param cell  The Rook cell involved in castling.
     * @param movement The horizontal movement type (right or left) for castling.
     * @return True if castling is possible, otherwise false.
     */
    public boolean castle(Cell[][] board, Cell cell, Movement movement){
        // Check if the cell is empty or contains a Rook
        if(cell.isEmpty() ||  cell.getPiece().getType() != PieceType.ROOK){
            return false;
        }

        // Retrieve the Rook piece
        Rook rook = (Rook) cell.getPiece();

        // Check if the Rook has not moved
        if(!rook.getFirstMove()){
            return false;
        }

        // Check if the horizontal movement for castling is valid
        return movement.canMove(board, getX(), getY(), cell.getX(), cell.getY(), 8, getColor());
    }

    /**
     * Checks and performs small castling if conditions are met.
     *
     * @param board The chessboard represented by a 2D array of cells.
     * @param x     The destination X-coordinate.
     * @param y     The destination Y-coordinate.
     * @return True if small castling is possible, otherwise false.
     */
    private boolean smallCastling(Cell[][] board, int x, int y){
        // Check if it's the King's first move and the destination is correct
        if(!firstMove && (x != 6 || y != getY())){
            return false;
        }

        // Retrieve the Rook cell for small castling
        Cell cell = board[7][getY()];
        Movement right = new Horizontal(Direction.RIGHT);

        // Check and perform castling
        return castle(board, cell, right);
    }

    /**
     * Checks and performs big castling if conditions are met.
     *
     * @param board The chessboard represented by a 2D array of cells.
     * @param x     The destination X-coordinate.
     * @param y     The destination Y-coordinate.
     * @return True if big castling is possible, otherwise false.
     */
    private boolean bigCastling(Cell[][] board, int x, int y){
        // Check if it's the King's first move and the destination is correct
        if(!firstMove && (x != 3 || y != getY())){
            return false;
        }

        // Retrieve the Rook cell for big castling
        Cell cell = board[0][getY()];
        Movement left = new Horizontal(Direction.LEFT);

        // Check and perform castling
        return castle(board, cell, left);
    }

    /**
     * Sets the firstMove flag to false, indicating that the King has moved.
     */
    public void setFirstMove() {
        this.firstMove = false;
    }

    /**
     * Returns a string representation of the King piece.
     *
     * @return A string representing the King piece.
     */
    public String toString() {
        return "King";
    }
}

package engine.piece;


import chess.*;
import engine.Cell;
import engine.movement.Movement;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Piece.java
 * @brief Provides common functionalities and properties for all chess pieces.
 * @date 10-01-2024
 * <p>
 * The Piece class is an abstract class representing chess pieces.
 * It provides common functionalities and properties for all chess pieces.
 * </p>
 */
public abstract class Piece implements ChessView.UserChoice {
    private final PlayerColor color;
    private final PieceType type;
    private Cell cell;
    int distance;
    Movement[] movements;

    /**
     * Constructs a new Piece with the specified color, type, distance, and movements.
     *
     * @param color     The color of the piece (PlayerColor.WHITE or PlayerColor.BLACK).
     * @param type      The type of the piece (PieceType.PAWN, PieceType.KNIGHT, etc.).
     * @param distance  The maximum movement distance for the piece.
     * @param mouvements An array of Movement objects representing the valid movements for the piece.
     */
    public Piece(PlayerColor color, PieceType type, int distance, Movement[] mouvements) {
        this.color = color;
        this.type = type;
        this.distance = distance;
        this.movements = new Movement[mouvements.length];

        System.arraycopy(mouvements, 0, this.movements, 0, mouvements.length);
    }

    /**
     * Validates the movement of the piece on the chessboard.
     *
     * @param board The chessboard represented as a 2D array of cells.
     * @param x     The x-coordinate of the destination cell.
     * @param y     The y-coordinate of the destination cell.
     * @return The type of movement (CLASSIC, DENIED) as specified in MovmentType enum.
     */
    public MovementType validateMov(Cell[][] board, int x, int y, int turn) {
        if (getCell() == null || (board[x][y].getPiece() != null && board[x][y].getPiece().getColor() == getColor())) {
            return MovementType.DENIED;
        }

        for (Movement mouvement : movements) {
            if (mouvement.canMove(board, getX(), getY(), x, y, getDistance(), getColor())) {
                return MovementType.CLASSIC;
            }
        }

        return MovementType.DENIED;
    }

    /**
     * Gets the color of the piece.
     *
     * @return The color of the piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Gets the type of the piece.
     *
     * @return The type of the piece (PieceType.PAWN, PieceType.KNIGHT, etc.).
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Gets the maximum movement distance of the piece.
     *
     * @return The maximum movement distance of the piece.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the current cell of the piece.
     *
     * @return The current cell of the piece.
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Gets the array of valid movements for the piece.
     *
     * @return An array of Movement objects representing valid movements for the piece.
     */
    public Movement[] getMovements() {
        return movements;
    }

    /**
     * Gets the x-coordinate of the piece on the chessboard.
     *
     * @return The x-coordinate of the piece.
     */
    public int getX() {
        return cell.getX();
    }

    /**
     * Gets the y-coordinate of the piece on the chessboard.
     *
     * @return The y-coordinate of the piece.
     */
    public int getY() {
        return cell.getY();
    }

    /**
     * Sets the current cell of the piece.
     *
     * @param c The cell to set as the current cell of the piece.
     */
    public void setCell(Cell c) {
        this.cell = c;
    }

    public String textValue() {
        return toString();
    }

   

}

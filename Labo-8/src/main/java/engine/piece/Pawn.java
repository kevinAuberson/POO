package engine.piece;

import chess.*;
import engine.Cell;
import engine.movement.*;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Pawn.java
 * @brief Contains the Pawn class that represents the Pawn piece in chess.
 * @date 10-01-2024
 *       <p>
 *       The Pawn class extends the Piece class.
 *       It defines the movement capabilities of the Pawn piece.
 *       </p>
 */
public class Pawn extends Piece {

    private boolean firstMove = true;
    private MovementType lastMove;
    private int lastTurn;

    /**
     * Constructs a new Pawn with the specified color.
     *
     * @param color The color of the Pawn piece (PlayerColor.WHITE or
     *              PlayerColor.BLACK).
     */
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN, 2, new Movement[] {
                new Vertical(Direction.UP),
                new Diagonal(Direction.DIAG_UP_RIGHT),
                new Diagonal(Direction.DIAG_UP_LEFT)
        });

        lastTurn = 0;
    }

    /**
     * Validates the Pawn's movement on the board.
     *
     * @param board The chessboard.
     * @param x     X-coordinate of the piece.
     * @param y     Y-coordinate of the piece.
     * @param turn  The current turn number.
     * @return The type of movement: PROMOTION, EN_PASSANT, or DENIED.
     */
    public MovementType validateMov(Cell[][] board, int x, int y, int turn) {
        MovementType movementType = super.validateMov(board, x, y, turn);

        // Check for pawn promotion
        if (canPromote(y) && movementType != MovementType.DENIED) {
            movementType = MovementType.PROMOTION;
        }

        // Check for en passant
        if (movementType == MovementType.DENIED) {
            enPassant(board, x, y, turn);
        }

        return movementType;
    }

    /**
     * Checks if the pawn is eligible for promotion.
     *
     * @param y Y-coordinate of the piece.
     * @return True if the pawn is eligible for promotion, otherwise false.
     */
    public boolean canPromote(int y) {
        if (this.getColor() == PlayerColor.WHITE) {
            return y == 7;
        } else {
            return y == 0;
        }
    }

    /**
     * Handles en passant movement for the pawn.
     *
     * @param board The chessboard.
     * @param toX   Destination X-coordinate.
     * @param toY   Destination Y-coordinate.
     * @param turn  The current turn number.
     * @return The type of movement: EN_PASSANT or DENIED.
     */
    private MovementType enPassant(Cell[][] board, int toX, int toY, int turn) {
        // Check if the destination cell is empty
        if (board[toX][toY].isEmpty()) {
            return MovementType.DENIED;
        }

        // Check if there is a piece on the current cell
        if (board[toX][getY()].getPiece() == null) {
            return MovementType.DENIED;
        }

        // Check if the piece on the current cell is a pawn of the opposite color
        if (board[toX][getY()].getPiece().getType() != PieceType.PAWN
                || board[toX][getY()].getPiece().getColor() == getColor()) {
            return MovementType.DENIED;
        }

        // Get the pawn on the current cell
        Pawn pawn = (Pawn) board[toX][getY()].getPiece();

        // Check if the last move of the pawn was a double move in the previous turn
        if (pawn.lastMove != MovementType.DOUBLE || pawn.getlastTurn() != turn - 1) {
            return MovementType.DENIED;
        }

        return MovementType.EN_PASSANT;
    }

    /**
     * Returns a string representation of the Pawn piece.
     *
     * @return A string representing the Pawn piece.
     */
    public String toString() {
        return "Pawn";
    }

    /**
     * Sets the first move flag for the pawn.
     */
    public void setFirstMove() {
        firstMove = false;
        distance = 1;
    }

    /**
     * Sets the false first move flag for the pawn.
     */
    public void setFalseFirstMove() {
        firstMove = false;
        distance = 1;
    }

    /**
     * Gets the turn number of the last play.
     *
     * @return The turn number of the last play.
     */
    public int getlastTurn() {
        return lastTurn;
    }

    /**
     * Sets the last play information for the pawn.
     *
     * @param lastMove The type of the last move.
     * @param turn     The turn number of the last play.
     */
    public void setLastPlay(MovementType lastMove, int turn) {
        this.lastMove = lastMove;
        lastTurn = turn;
    }

    /**
     * Checks if it's the first play of the pawn.
     *
     * @return True if it's the first play of the pawn, otherwise false.
     */
    public boolean getFirstPlay() {
        return firstMove;
    }
}

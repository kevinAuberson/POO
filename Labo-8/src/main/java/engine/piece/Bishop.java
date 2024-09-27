package engine.piece;

import chess.*;
import engine.movement.*;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Bishop.java
 * @brief The Bishop class represents the Bishop chess piece
 * @date 10-01-2024
 * <p>
 * The Bishop class extends the Piece class.
 * It defines the movement capabilities of the Bishop piece.
 * </p>
 */
public class Bishop extends Piece {

    /**
     * Constructs a new Bishop with the specified color.
     *
     * @param color The color of the Bishop piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public Bishop(PlayerColor color) {
        super(color, PieceType.BISHOP, 7, new Movement[]{
            new Diagonal(Direction.DIAG_DOWN_LEFT),
            new Diagonal(Direction.DIAG_DOWN_RIGHT),
            new Diagonal(Direction.DIAG_UP_LEFT),
            new Diagonal(Direction.DIAG_UP_RIGHT)
        });
    }

    /**
     * Returns a string representation of the Bishop piece.
     *
     * @return A string representing the Bishop piece.
     */
    public String toString() {
        return "Bishop";
    }
}

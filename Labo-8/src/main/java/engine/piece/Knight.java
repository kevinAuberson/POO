package engine.piece;

import chess.*;
import engine.movement.*;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Knight.java
 * @brief Contains the Knight class that represents the Knight piece in chess.
 * @date 10-01-2024
 * <p>
 * The Knight class extends the Piece class.
 * It defines the movement capabilities of the Knight piece.
 * </p>
 */
public class Knight extends Piece {

    /**
     * Constructs a new Knight with the specified color.
     *
     * @param color The color of the Knight piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT, 3, new Movement[]{new Jump()});
    }

    /**
     * Returns a string representation of the Knight piece.
     *
     * @return A string representing the Knight piece.
     */
    public String toString() {
        return "Knight";
    }
}

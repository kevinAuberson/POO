package engine.piece;

import chess.*;
import engine.movement.*;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Queen.java
 * @brief The Queen class represents the Queen chess piece
 * @date 10-01-2024
 * <p>
 * The Queen class extends the Piece class.
 * It defines the movement capabilities of the Queen piece.
 * </p>
 */
public class Queen extends Piece{

    /**
     * Constructs a new Queen with the specified color.
     *
     * @param color The color of the Queen piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN, 7, new Movement[]{
            new Horizontal(Direction.RIGHT),
            new Horizontal(Direction.LEFT),
            new Vertical(Direction.UP),
            new Vertical(Direction.DOWN),
            new Diagonal(Direction.DIAG_UP_LEFT),
            new Diagonal(Direction.DIAG_UP_RIGHT),
            new Diagonal(Direction.DIAG_DOWN_LEFT),
            new Diagonal(Direction.DIAG_DOWN_RIGHT)
        });
        
    }

    /**
     * Returns a string representation of the Queen piece.
     *
     * @return A string representing the Queen piece.
     */
    public String toString() {
        return "Queen";
    }
}

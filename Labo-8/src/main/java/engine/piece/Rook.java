package engine.piece;

import chess.*;
import engine.movement.*;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Rook.java
 * @brief The Rook class represents the Rook chess piece
 * @date 10-01-2024
 * <p>
 * The Rook class extends the Piece class.
 * It defines the movement capabilities of the Rook piece.
 * </p>
 */
public class Rook extends Piece{

    private boolean firstMove = true;
    /**
     * Constructs a new Rook with the specified color.
     *
     * @param color The color of the Rook piece (PlayerColor.WHITE or PlayerColor.BLACK).
     */
    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK, 7, new Movement[]{
            new Horizontal(Direction.RIGHT),
            new Horizontal(Direction.LEFT),
            new Vertical(Direction.UP),
            new Vertical(Direction.DOWN)
        });
    }

    /**
     * Sets the first move flag for the Rook.
     */
    public void setFirstMove() {
        this.firstMove = false;
    }

    /**
     * Gets the first move flag of the Rook.
     *
     * @return True if the Rook has not moved yet, otherwise false.
     */
    public boolean getFirstMove() {
        return firstMove;
    }

    /**
     * Returns a string representation of the Rook piece.
     *
     * @return A string representing the Rook piece.
     */
    public String toString() {
        return "Rook";
    }
}

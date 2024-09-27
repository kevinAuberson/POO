package engine.movement;

import engine.Cell;
import chess.PlayerColor;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Movement.java
 * @brief Contains the Movement abstract class that represents the base for specific movement types.
 * @date 10-01-2024
 */
public abstract class Movement {

    

    /**
     * Checks if a piece can move from one position to another.
     *
     * @param board      The chessboard.
     * @param fromX      Initial X-coordinate of the piece.
     * @param fromY      Initial Y-coordinate of the piece.
     * @param toX        Destination X-coordinate of the piece.
     * @param toY        Destination Y-coordinate of the piece.
     * @param distance   The maximum allowed distance for movement.
     * @param colorPiece The color of the piece.
     * @return True if the piece can move to the destination, otherwise false (default behavior).
     */
    public boolean canMove(Cell[][] board, int fromX, int fromY, int toX, int toY, int distance, PlayerColor colorPiece) {
        return false;
    }
}

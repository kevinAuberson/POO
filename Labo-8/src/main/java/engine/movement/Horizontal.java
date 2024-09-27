package engine.movement;

import chess.PlayerColor;
import engine.Cell;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Horizontal.java
 * @brief Contains the Horizontal class that handles horizontal movement logic for chess pieces.
 * @date 10-01-2024
 * <p>
 * This class determines the validity of horizontal movement for chess pieces on the board.
 * Supports checking if a piece can move horizontally from one position to another
 * within a specified distance and ensures the horizontal path is clear.
 * </p>
 */
public class Horizontal extends Movement {


    /**
     * Constructor for Horizontal class.
     *
     * @param direction The direction of horizontal movement.
     * @throws RuntimeException if the direction is not horizontal.
     */
    public Horizontal(Direction direction) {
        if (direction != Direction.LEFT && direction != Direction.RIGHT) {
            throw new RuntimeException("you can only move left or right");
        }
    }

    /**
     * Checks if a piece can move horizontally from one position to another.
     *
     * @param board      The chessboard.
     * @param fromX      Initial X-coordinate of the piece.
     * @param fromY      Initial Y-coordinate of the piece.
     * @param toX        Destination X-coordinate of the piece.
     * @param toY        Destination Y-coordinate of the piece.
     * @param distance   The maximum allowed distance for movement.
     * @param colorPiece The color of the piece.
     * @return True if the piece can move horizontally to the destination, otherwise false.
     */
    @Override
    public boolean canMove(Cell[][] board, int fromX, int fromY, int toX, int toY, int distance, PlayerColor colorPiece) {
        int deltaX = Math.abs(toX - fromX);
        Direction direction = (toX - fromX > 0) ? Direction.RIGHT : Direction.LEFT;


        // Check if the move is valid horizontally
        if (fromY == toY && deltaX <= distance) {
            for (int i = 1; deltaX != i; i++) {
                int colonne = (direction == Direction.RIGHT) ? fromX + i : fromX - i;

                // Check if there is any piece on the current cell
                if (!board[colonne][fromY].isEmpty()) {
                    return false; // Invalid move if there is a piece blocking the path
                }
            }

            return true; // Valid move if all conditions are satisfied
        }

        return false; // Invalid move if the initial conditions are not met
    }

}

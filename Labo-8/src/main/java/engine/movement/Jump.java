package engine.movement;
import engine.Cell;
import chess.PlayerColor;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Jump.java
 * @brief Contains the Jump class that handles jump movement logic for knight piece.
 * @date 10-01-2024
 * <p>
 * This class determines the validity of jump movement for knight piece on the board.
 * Supports checking if knight can jump from one position to another
 * within a specified distance and ensures the path is clear.
 * </p>
 */
public class Jump extends Movement {

    /**
     * Constructor for Jump class.
     *
     * @throws RuntimeException if the direction is not jump.
     */
    public Jump() {}

    /**
     * Checks if the knight can move jump from one position to another.
     *
     * @param board      The chessboard.
     * @param fromX      Initial X-coordinate of the knight.
     * @param fromY      Initial Y-coordinate of the knight.
     * @param toX        Destination X-coordinate of the knight.
     * @param toY        Destination Y-coordinate of the knight.
     * @param distance   The maximum allowed distance for movement.
     * @param colorPiece The color of the piece.
     * @return True if the knight can move jump to the destination, otherwise false.
     */
    public boolean canMove(Cell[][] board, int fromX, int fromY, int toX, int toY, int distance, PlayerColor colorPiece) {
        // Determine the direction of movement based on the player's color
        int sens = (colorPiece == PlayerColor.WHITE) ? 1 : -1;
        // Calculate the absolute differences in X and Y coordinates
        int deltaX = (toX - fromX) * sens;
        int deltaY = (toY - fromY) * sens;

        // Check if the move is in an "L-shaped" pattern for a knight
        return (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) ||
                (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1);
    }
}

package engine.movement;

import chess.PieceType;
import chess.PlayerColor;
import engine.Cell;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Vertical.java
 * @brief Contains the Vertical class that handles vertical movement logic for
 *        chess pieces.
 * @date 10-01-2024
 *       <p>
 *       This class determines the validity of vertical movement for chess
 *       pieces on the board.
 *       Supports checking if a piece can move vertically from one position to
 *       another
 *       within a specified distance and ensures the vertical path is clear.
 *       </p>
 */
public class Vertical extends Movement {

    /**
     * Constructor for Vertical class.
     *
     * @param direction The direction of vertical movement.
     * @throws RuntimeException if the direction is not vertical.
     */
    public Vertical(Direction direction) {
        if (direction != Direction.DOWN && direction != Direction.UP) {
            throw new RuntimeException("you can only move up or down");
        }
    }

    /**
     * Checks if a piece can move vertically from one position to another.
     *
     * @param board      The chessboard.
     * @param fromX      Initial X-coordinate of the piece.
     * @param fromY      Initial Y-coordinate of the piece.
     * @param toX        Destination X-coordinate of the piece.
     * @param toY        Destination Y-coordinate of the piece.
     * @param distance   The maximum allowed distance for movement.
     * @param colorPiece The color of the piece.
     * @return True if the piece can move vertically to the destination, otherwise false.
     */
    @Override
    public boolean canMove(Cell[][] board, int fromX, int fromY, int toX, int toY, int distance,
                           PlayerColor colorPiece) {
        // Determine the direction of movement based on the player's color
        Direction direction = (colorPiece == PlayerColor.WHITE) ? Direction.UP : Direction.DOWN;
        // Calculate the distance between the source and destination positions
        int deltaY = toY - fromY;

        // Check if the move is valid for a pawn
        if (board[fromX][fromY].getPiece().getType() == PieceType.PAWN) {
            if (fromX == toX && ((direction == Direction.UP && deltaY > 0 && deltaY <= distance)
                    || (direction == Direction.DOWN && deltaY < 0 && deltaY >= -distance))) {

                for (int i = 1; Math.abs(deltaY) != i; i++) {
                    // Adjust the row index based on the direction of movement
                    int ligne = fromY + i * (direction == Direction.UP ? 1 : -1);
                    // Check if there is any piece on the current cell
                    if (!board[fromX][ligne].isEmpty()) {
                        return false; // Invalid move if there is an obstruction in the vertical path
                    }
                }

                // Check if there is someone in front of the pawn; pawns can only capture diagonally
                if (!board[toX][toY].isEmpty() && board[fromX][fromY].getPiece().getType() == PieceType.PAWN) {
                    return false; // Invalid move if the destination cell is occupied and the piece is a pawn
                }
                return true; // Valid move if all conditions are satisfied
            }
        } else if (Math.abs(deltaY) <= distance) {
            for (int i = 1; Math.abs(deltaY) != i; i++) {
                // Adjust the row index based on the direction of movement
                int ligne = fromY + i * (deltaY > 0 ? 1 : -1);
                // Check if there is any piece on the current cell
                if (!board[fromX][ligne].isEmpty()) {
                    return false; // Invalid move if there is an obstruction in the vertical path
                }
            }
            return true; // Valid move if the vertical path is clear and within the allowed distance
        }
        return false; // Invalid move if the initial conditions are not met
    }
}

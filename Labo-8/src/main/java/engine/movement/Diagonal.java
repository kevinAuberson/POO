package engine.movement;

import chess.PieceType;
import chess.PlayerColor;
import engine.Cell;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Diagonal.java
 * @brief Contains the Diagonal class that handles diagonal movement logic for
 *        chess pieces.
 * @date 10-01-2024
 *       <p>
 *       This class determines the validity of diagonal movement for chess
 *       pieces on the board.
 *       Supports checking if a piece can move diagonally from one position to
 *       another
 *       within a specified distance and ensures the diagonal path is clear.
 *       </p>
 */
public class Diagonal extends Movement {

    /**
     * Constructor for Diagonal class.
     *
     * @param direction The direction of diagonal movement.
     * @throws RuntimeException if the direction is not diagonal.
     */
    public Diagonal(Direction direction) {
        if (direction != Direction.DIAG_DOWN_RIGHT &&
                direction != Direction.DIAG_DOWN_LEFT &&
                direction != Direction.DIAG_UP_LEFT &&
                direction != Direction.DIAG_UP_RIGHT) {
            throw new RuntimeException("you can only move in diagonal");
        }
    }

    /**
     * Checks if a piece can move diagonally from one position to another.
     *
     * @param board      The chessboard.
     * @param fromX      Initial X-coordinate of the piece.
     * @param fromY      Initial Y-coordinate of the piece.
     * @param toX        Destination X-coordinate of the piece.
     * @param toY        Destination Y-coordinate of the piece.
     * @param distance   The maximum allowed distance for movement.
     * @param colorPiece The color of the piece.
     * @return True if the piece can move diagonally to the destination, otherwise false.
     */
    public boolean canMove(Cell[][] board, int fromX, int fromY, int toX, int toY, int distance,
                           PlayerColor colorPiece) {
        // Calculate differences in coordinates
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        int deltaAbs = Math.abs(deltaX);
        Direction direction;

        // Check if the movement is diagonal and within the allowed distance
        if (Math.abs(deltaY) != deltaAbs || deltaAbs > distance) {
            return false; // Not moving diagonally or exceeding allowed distance
        }

        // Determine the diagonal direction
        if (deltaX > 0 && deltaY > 0) {
            direction = Direction.DIAG_UP_RIGHT;
        } else if (deltaX > 0 && deltaY < 0) {
            direction = Direction.DIAG_DOWN_RIGHT;
        } else if (deltaX < 0 && deltaY > 0) {
            direction = Direction.DIAG_UP_LEFT;
        } else {
            direction = Direction.DIAG_DOWN_LEFT;
        }

        // Special case handling for pawn diagonal moves
        if (board[fromX][fromY].getPiece().getType() == PieceType.PAWN) {
            if ((direction == Direction.DIAG_UP_LEFT || direction == Direction.DIAG_UP_RIGHT)
                    && colorPiece == PlayerColor.WHITE) {
                // Allow diagonal move only if the destination cell is occupied by an opponent's piece
                if (board[toX][toY].isEmpty() || board[toX][toY].getPiece().getColor() == PlayerColor.WHITE) {
                    return false; // Prevent pawn from moving diagonally without capture
                }
            } else if ((direction == Direction.DIAG_DOWN_LEFT || direction == Direction.DIAG_DOWN_RIGHT)
                    && colorPiece == PlayerColor.BLACK) {
                // Allow diagonal move only if the destination cell is occupied by an opponent's piece
                if (board[toX][toY].isEmpty() || board[toX][toY].getPiece().getColor() == PlayerColor.BLACK) {
                    return false; // Prevent pawn from moving diagonally without capture
                }
            }
        }

        // Determine step direction based on deltas
        int stepX = (deltaX > 0) ? 1 : -1;
        int stepY = (deltaY > 0) ? 1 : -1;

        // Check for obstructions in the diagonal path
        for (int i = 1; i < deltaAbs; i++) {
            int nextX = fromX + i * stepX;
            int nextY = fromY + i * stepY;
            if (!board[nextX][nextY].isEmpty()) {
                return false; // Obstruction in the diagonal path
            }
        }
        return true; // Diagonal movement is valid
    }
}

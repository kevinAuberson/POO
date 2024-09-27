package engine.movement;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Direction.java
 * @brief Enumerates different directions for movement.
 * @date 10-01-2024
 * <p>
 * Represents various directions for chess piece movements, including standard directions
 * (UP, DOWN, LEFT, RIGHT) and diagonal movements (DIAG_UP_RIGHT, DIAG_UP_LEFT, DIAG_DOWN_RIGHT, DIAG_DOWN_LEFT),
 * as well as L-shaped movements for knight's moves in different directions.
 * </p>
 */
public enum Direction {
    // Standard directions
    UP,
    DOWN,
    LEFT,
    RIGHT,

    // Diagonal directions
    DIAG_UP_RIGHT,
    DIAG_UP_LEFT,
    DIAG_DOWN_RIGHT,
    DIAG_DOWN_LEFT;
}

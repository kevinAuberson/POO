package engine.piece;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file MovementType.java
 * @brief Enumerates different special movement.
 * @date 10-01-2024
 * <p>
 * The MovementType enum represents different types of movements that can occur in a chess game.
 * It includes classic movements, denied movements, small castling, big castling, en passant, and promotion.
 * </p>
 */
public enum MovementType {
    CLASSIC,
    DENIED,
    SMALL_CASTLING,
    BIG_CASTLING,
    EN_PASSANT,
    PROMOTION,
    DOUBLE
}

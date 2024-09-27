package engine;

import engine.piece.Piece;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Cell.java
 * @brief Represents a cell on the chessboard.
 * @date 10-01-2024
 * <p>
 * Represents a cell on the chessboard. Contains a piece and its coordinates.
 * The cell can be empty or contain a piece.
 * </p>
 */
public class Cell {
    private final int x;
    private final int y;
    private Piece piece;

    /**
     * Constructeur
     * @param x
     * @param y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Vérifie si la case est vide
     * @return true si la case est vide, false sinon
     */
    public boolean isEmpty() {
        return piece == null;
    }
    /**
     * Place une pièce sur la case
     * @param piece
     */
    public void setPiece(Piece piece) {
        if(this.piece != null){
            removePiece();
        }
        this.piece = piece;
        this.piece.setCell(this);
    }
    /**
     * Enlève la pièce de la case
     */
    public void removePiece() {
        this.piece.setCell(null);
        this.piece = null;
    }
    /**
     * Récupère la pièce de la case
     * @return la pièce de la case
     */
    public Piece getPiece() {
        return piece;
    }
    /**
     * Récupère la coordonnée x de la case
     * @return la coordonnée x de la case
     */
    public int getX() {
        return x;
    }
    /**
     * Récupère la coordonnée y de la case
     * @return la coordonnée y de la case
     */
    public int getY() {
        return y;
    }
}

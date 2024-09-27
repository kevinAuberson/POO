package engine;

import chess.*;
import engine.piece.*;
import engine.movement.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * @author Kevin Auberson, Léonard Klassen
 * @version 1.0
 * @file Board.java
 * @brief Contains the Board class that handles the chessboard and game logic.
 * @date 10-01-2024
 *       <p>
 *       This class manages the chessboard, piece movements, game rules, and
 *       player turns.
 *       It includes methods for starting the game, making moves, setting up the
 *       board, checking for check conditions,
 *       and managing the game state.
 *       </p>
 */
public class Board implements ChessController {

    private Cell[][] board;
    private ChessView view;
    private static final int SIZE = 8;
    private boolean check;
    private int turn;
    private Piece whiteKing;
    private Piece blackKing;

    private LinkedList<Character> CellAtoF = new LinkedList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));

    /**
     * Constructor for the Board class.
     * Initializes the chessboard by creating Cell objects for each square.
     */
    public Board() {
        board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Starts the game by setting up the view and initiating the user interface.
     *
     * @param view The view (ConsoleView or GUIView) to be used for the game.
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    /**
     * Makes a move on the chessboard from one position to another.
     * Handles the movement of pieces, validates the move, and updates the game state.
     *
     * @param fromX Initial X-coordinate of the piece.
     * @param fromY Initial Y-coordinate of the piece.
     * @param toX   Destination X-coordinate of the piece.
     * @param toY   Destination Y-coordinate of the piece.
     * @return True if the move is valid and executed, otherwise false.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        // Get the cell of the piece before and after the move
        Cell caseBefore = board[fromX][fromY];
        Cell caseAfter = board[toX][toY];

        // Display "Check" if the current player is in check
        if (check) {
            view.displayMessage("Check");
        }

        // Return false if the initial cell is empty
        if (caseBefore.isEmpty()) {
            return false;
        }

        // Get the piece to be moved
        Piece piece = caseBefore.getPiece();

        // Check if it's the correct player's turn
        if (turn % 2 == 1 && piece.getColor() != PlayerColor.WHITE
                || turn % 2 == 0 && piece.getColor() != PlayerColor.BLACK) {
            return false;
        }

        // Validate the movement type for the piece
        MovementType movementType = piece.validateMov(board, toX, toY, turn);
        if (movementType == MovementType.DENIED) {
            return false;
        }

        // Handle castling
        if (movementType == MovementType.SMALL_CASTLING || movementType == MovementType.BIG_CASTLING) {
            return castling((King) piece, movementType, toX, toY);
        }

        // Remove the piece from the initial cell and place it in the destination cell
        caseBefore.removePiece();
        caseAfter.setPiece(piece);

        // Check if the move puts the player's own king in check
        if (isCheck((piece.getColor() == PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE)) {
            // Revert the move if it puts the king in check
            caseAfter.removePiece();
            caseBefore.setPiece(piece);
            return false;
        }

        // Update the graphical interface
        view.displayMessage("");
        view.removePiece(fromX, fromY);
        view.putPiece(piece.getType(), piece.getColor(), toX, toY);

        // Handle special conditions for pawn, king, and rook
        if (piece.getType() == PieceType.PAWN) {
            if (((Pawn) piece).getFirstPlay()) {
                ((Pawn) piece).setFalseFirstMove();
                if (Math.abs(fromY - toY) > 1) {
                    ((Pawn) piece).setLastPlay(MovementType.DOUBLE, turn);
                } else {
                    ((Pawn) piece).setLastPlay(MovementType.CLASSIC, turn);
                }
            }
        }
        if (piece.getType() == PieceType.KING) {
            King king = (King) piece;
            king.setFirstMove();
        }
        if (piece.getType() == PieceType.ROOK) {
            Rook rook = (Rook) piece;
            rook.setFirstMove();
        }

        // Handle pawn promotion
        if (movementType == MovementType.PROMOTION) {
            promotion(piece);
        }

        // Handle en passant
        if (movementType == MovementType.EN_PASSANT) {
            enPassant(fromY, toX);
        }

        // Display "Check" if the move results in check
        if (isCheck(piece.getColor())) {
            view.displayMessage("Check");
        }

        // Increment the turn counter
        turn++;

        // Display the move information
        System.out.println(piece + " " + piece.getColor() + " in " + CellAtoF.get(toX).toString() + (toY + 1));

        return true;
    }


    /**
     * Handles the promotion of a pawn.
     *
     * @param pawn The pawn to be promoted.
     */
    private void promotion(Piece pawn) {
        // Create pieces available for promotion
        Piece queen = new Queen(pawn.getColor());
        Piece knight = new Knight(pawn.getColor());
        Piece bishop = new Bishop(pawn.getColor());
        Piece rook = new Rook(pawn.getColor());

        // Ask the user to choose a piece for promotion
        Piece pieceChoosed = view.askUser("Promotion", "Choose a piece to promote your pawn", queen, knight, bishop, rook);

        // Replace the pawn with the chosen piece
        int x = pawn.getX();
        int y = pawn.getY();
        board[x][y].removePiece();
        board[x][y].setPiece(pieceChoosed);
        view.removePiece(x, y);
        view.putPiece(pieceChoosed.getType(), pieceChoosed.getColor(), pieceChoosed.getX(), pieceChoosed.getY());
    }


    /**
     * Handles the en passant capture.
     *
     * @param fromY The Y-coordinate of the pawn before the move.
     * @param toX   The X-coordinate of the square where the capturing pawn moves.
     */
    private void enPassant(int fromY, int toX) {
        // Remove the captured pawn from the board and the graphical interface
        board[toX][fromY].removePiece();
        view.removePiece(toX, fromY);
    }


    /**
     * Handles the castling move.
     *
     * @param king      The king piece involved in the castling.
     * @param castling  The type of castling (SMALL_CASTLING or BIG_CASTLING).
     * @param x         The X-coordinate of the destination square.
     * @param y         The Y-coordinate of the destination square.
     * @return True if the castling move is valid and executed, otherwise false.
     */
    private boolean castling(King king, MovementType castling, int x, int y) {
        // Get the cells involved in castling
        Cell kingFrom = board[king.getX()][king.getY()];
        Cell kingNext;
        Cell kingTo = board[x][y];
        Cell rookFrom;
        Cell rookTo;

        // Retrieve the cell next to the king
        if (castling == MovementType.SMALL_CASTLING) {
            kingNext = board[king.getX() + 1][king.getY()];
        } else {
            kingNext = board[king.getX() - 1][king.getY()];
        }

        // Check if the king is in check before castling
        if (check) {
            return false;
        }

        // Move the king one square and check for check
        kingFrom.removePiece();
        kingNext.setPiece(king);
        if (isCheck((king.getColor() == PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE)) {
            kingNext.removePiece();
            kingFrom.setPiece(king);
            return false;
        }

        // Move the king to the destination square and check for check
        kingNext.removePiece();
        kingTo.setPiece(king);
        if (isCheck((king.getColor() == PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE)) {
            kingTo.removePiece();
            kingFrom.setPiece(king);
            return false;
        }

        // Update the graphical interface for the king's move
        view.putPiece(king.getType(), king.getColor(), kingTo.getX(), kingTo.getY());
        view.removePiece(kingFrom.getX(), kingFrom.getY());

        // Get the cells for rook movement
        if (castling == MovementType.SMALL_CASTLING) {
            rookFrom = board[king.getX() + 1][king.getY()];
            rookTo = board[king.getX() - 1][king.getY()];
        } else {
            rookFrom = board[king.getX() - 2][king.getY()];
            rookTo = board[king.getX() + 1][king.getY()];
        }

        // Move the rook to its new position
        Piece rook = rookFrom.getPiece();
        rookFrom.removePiece();
        view.removePiece(rookFrom.getX(), rookFrom.getY());
        rookTo.setPiece(rook);
        view.putPiece(rook.getType(), rook.getColor(), rook.getX(), rook.getY());

        // Display "Check" if the move results in check
        if (isCheck((king.getColor()))) {
            view.displayMessage("Check");
        }

        // Set the first move for king and rook, increment turn, and return true
        king.setFirstMove();
        ((Rook) rook).setFirstMove();
        turn++;
        return true;
    }


    /**
     * Starts a new chess game by resetting the board and setting up the pieces.
     */
    @Override
    public void newGame() {
        clearBoard();
        setupPieces();
        check = false;
        turn = 1;
    }

    /**
     * Clears the chessboard by removing all pieces from the cells.
     */
    private void clearBoard() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (!cell.isEmpty()) {
                    cell.removePiece();
                }
            }
        }
    }

    /**
     * Sets up the initial configuration of pieces on the chessboard.
     * Places pieces at their starting positions for a new game.
     */
    private void setupPieces() {
        whiteKing = new King(PlayerColor.WHITE);
        blackKing = new King(PlayerColor.BLACK);
        LinkedList<Piece> whitePieces = new LinkedList<>(Arrays.asList(
                new Rook(PlayerColor.WHITE), new Knight(PlayerColor.WHITE),
                new Bishop(PlayerColor.WHITE), new Queen(PlayerColor.WHITE),
                whiteKing, new Bishop(PlayerColor.WHITE),
                new Knight(PlayerColor.WHITE), new Rook(PlayerColor.WHITE)));

        LinkedList<Piece> blackPieces = new LinkedList<>(Arrays.asList(
                new Rook(PlayerColor.BLACK), new Knight(PlayerColor.BLACK),
                new Bishop(PlayerColor.BLACK), new Queen(PlayerColor.BLACK),
                blackKing, new Bishop(PlayerColor.BLACK),
                new Knight(PlayerColor.BLACK), new Rook(PlayerColor.BLACK)));

        for (int col = 0; col < SIZE; ++col) {
            Piece pawnWhite = new Pawn(PlayerColor.WHITE);
            Piece pawnBlack = new Pawn(PlayerColor.BLACK);
            Piece whitePiece = whitePieces.get(col);
            Piece blackPiece = blackPieces.get(col);

            board[col][1].setPiece(pawnWhite);
            board[col][6].setPiece(pawnBlack);
            board[col][0].setPiece(whitePiece);
            board[col][7].setPiece(blackPiece);

            view.putPiece(pawnWhite.getType(), pawnWhite.getColor(), col, 1);
            view.putPiece(pawnBlack.getType(), pawnBlack.getColor(), col, 6);
            view.putPiece(whitePiece.getType(), whitePiece.getColor(), col, 0);
            view.putPiece(blackPiece.getType(), blackPiece.getColor(), col, 7);
        }
    }

    /**
     * Checks if a player is in a check position.
     *
     * @param opponentColor The color of the opponent player to check for check
     *                      condition.
     * @return True if the specified player is in check, otherwise false.
     */
    public boolean isCheck(PlayerColor opponentColor) {
        Piece king = (opponentColor == PlayerColor.WHITE) ? blackKing : whiteKing;

        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.isEmpty() || cell.getPiece().getColor() == king.getColor()) {
                    continue;
                }
                if (cell.getPiece().validateMov(board, king.getX(), king.getY(), turn) != MovementType.DENIED
                        && cell.getPiece().getColor() != king.getColor()) {
                    check = true;
                    return true;
                }
            }
        }
        check = false;
        return false;
    }
}

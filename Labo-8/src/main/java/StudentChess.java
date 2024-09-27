import chess.*;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;
import engine.Board;

/**
 * Jeu d'échecs
 */
public class StudentChess
{
    /**
     * Main method to start the chess game.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main( String[] args )
    {
        // 1. Création du contrôleur pour gérer le jeu d’échecs
        // Ici, vous devez instancier un ChessController
        ChessController controller = new Board();
        // 2. Création de la vue désirée
        ChessView view = new GUIView(controller); // mode GUI
        //ChessView view = new ConsoleView(controller) ; // ou mode Console

        // 3 . Lancement du programme
        controller.start (view) ;
    }
}
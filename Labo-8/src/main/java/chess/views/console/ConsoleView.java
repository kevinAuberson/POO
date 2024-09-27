
// Package for console-based views related to chess
package chess.views.console;

// Import statements for required classes and interfaces
import chess.assets.ConsoleAssets;
import chess.ChessController;
import chess.PieceType;
import chess.PlayerColor;
import chess.views.BaseView;
import chess.views.DrawableResource;

import java.util.Scanner;
import java.util.regex.Pattern;

// ConsoleView class represents a console-based view for a chess game
public class ConsoleView extends BaseView<String> {

  // Inner class representing a drawable resource for strings
  private static class StringResource implements DrawableResource<String> {
    private String value;

    // Constructor for StringResource, sets color based on PlayerColor
    private StringResource(String value, PlayerColor color) {
      this.value = (color == PlayerColor.BLACK ? "\u001B[31m" : "") + value + "\u001B[30m";
    }

    @Override
    public String getResource() {
      return value;
    }
  };

  // Factory method to create a StringResource with specified value and color
  public static DrawableResource<String> createResource(String value, PlayerColor color) {
    return new StringResource(value, color);
  }

  // Scanner for user input
  private final static Scanner scanner = new Scanner(System.in);
  // Regex pattern for valid movement input
  private final static Pattern movementPattern = Pattern.compile("[a-h][1-8][a-h][1-8]");
  // Constants for empty cell and unknown cell representations
  private final static String EMPTY_CELL = " ";
  private final static String UNKNOWN_CELL = "?";

  // Message for check status
  private String checkMessage = "";
  // 2D array to represent the chessboard
  private String[][] buffer;

  // Constructor for ConsoleView
  public ConsoleView(ChessController controller) {
    super(controller);
    ConsoleAssets.loadAssets(this); // Load console assets
    initialize(); // Initialize the buffer
    clearView(); // Clear the view
  }

  // Start the console-based chess game view
  @Override
  public void startView() {
    System.out.println("Chess game is starting");
    controller.newGame(); // Start a new game
    while (true) {
      printBoard(); // Print the current state of the chessboard
      askMovement(); // Ask the user for a movement input
    }
  }

  // Remove a piece from the specified position on the chessboard
  @Override
  public void removePiece(int x, int y) {
    buffer[x][y] = EMPTY_CELL;
  }

  // Place a piece on the chessboard at the specified position
  @Override
  public void putPiece(PieceType type, PlayerColor color, int x, int y) {
    buffer[x][y] = loadResourceFor(type, color, UNKNOWN_CELL);
  }

  // Display a message to the user
  @Override
  public void displayMessage(String msg) {
    System.out.println(msg);
  }

  // Ask the user to make a choice from a set of possibilities
  @Override
  public <T extends UserChoice> T askUser(String title, String question, T... possibilities) {
    T result = possibilities.length > 0 ? possibilities[0] : null;
    if (possibilities.length > 1) {
      int i = 0;
      for (T choice : possibilities) {
        System.out.println(i + ". " + choice.textValue());
        ++i;
      }

      int userChoice;
      do {
        userChoice = -1;
        System.out.println("Enter the desired number > ");

        try {
          userChoice = Integer.parseInt(scanner.nextLine());
          if (userChoice >= 0 && userChoice < possibilities.length)
            result = possibilities[userChoice];
          else
            userChoice = -1;
        } catch (NumberFormatException e) { // nothing
        }

        if (userChoice < 0)
          System.out.println("Error. Choose a value between 0 and " + (possibilities.length - 1));

      } while (userChoice < 0);
    }
    return result;
  }

  // Initialize the buffer with an empty chessboard
  private void initialize() {
    buffer = new String[8][8];
  }

  // Clear the view by removing all pieces from the chessboard
  private void clearView() {
    for (int i = 0; i < buffer.length; ++i) {
      for (int j = 0; j < buffer[i].length; ++j) {
        removePiece(i, j);
      }
    }
  }

  // Print the current state of the chessboard to the console
  private void printBoard() {
    for (int y = 7; y >= 0; --y) {
      System.out.print(y + 1 + " |");
      for (int x = 0; x < 8; ++x) {
        System.out.print(buffer[x][y]);
        System.out.print(" ");
      }
      System.out.print("\n");
    }
    System.out.println("-------------------");
    System.out.println("   A B C D E F G H ");
  }

  // Convert a character coordinate to its corresponding index
  private static int charCoordinateToIndex(char c) {
    assert (c >= 'a' && c < 'i');
    return c - 'a';
  }

  // Ask the user for input matching a specific pattern
  private static String askPattern(Pattern pattern, String text) {
    String in = null;
    while (in == null) {
      System.out.println(text);
      in = scanner.findInLine(pattern);
      scanner.nextLine(); // Clean buffer
    }
    return in;
  }

  // Convert an integer coordinate to its corresponding index
  private static int intCoordinateToIndex(char c) {
    assert (c >= '1' && c <= '9');
    return c - '1';
  }

  // Ask the user for a movement input and validate it
  private void askMovement() {
    boolean ok = false;
    while (!ok) {
      String in = askPattern(movementPattern, "Next move?");
      System.out.println(in);
      ok = controller.move(charCoordinateToIndex(in.charAt(0)), intCoordinateToIndex(in.charAt(1)),
          charCoordinateToIndex(in.charAt(2)), intCoordinateToIndex(in.charAt(3)));

      if (!ok) {
        System.out.println("Invalid move");
        printBoard();
      }
    }
  }
}
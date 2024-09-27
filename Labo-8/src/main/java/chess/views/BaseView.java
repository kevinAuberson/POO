package chess.views;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

import java.util.HashMap;

// Base class for implementing chess views
public abstract class BaseView<Resource> implements ChessView {
  // HashMap to store resources for each piece type and player color
  private final HashMap<PieceType, HashMap<PlayerColor, DrawableResource<Resource>>> resources = new HashMap<>();

  // Reference to the chess controller
  protected final ChessController controller;

  // Constructor that initializes the view with a chess controller
  public BaseView(ChessController controller) {
    this.controller = controller;
  }

  // Register a drawable resource for a specific piece type and player color
  public final void registerResource(PieceType type, PlayerColor color, DrawableResource<Resource> resource) {
    // Get the resources for the specified piece type
    HashMap<PlayerColor, DrawableResource<Resource>> pieceResources = resources.get(type);

    // If resources for this piece type don't exist, create a new HashMap
    if (pieceResources == null) {
      pieceResources = new HashMap<>();
    }

    // Put the drawable resource for the specified color into the HashMap
    pieceResources.put(color, resource);

    // Update the resources map with the piece type and its corresponding resources
    resources.put(type, pieceResources);
  }

  // Load a drawable resource for a specific piece type and player color
  protected final Resource loadResourceFor(PieceType type, PlayerColor color, Resource def) {
    Resource icon = def;

    // Check if piece type and color are not null
    if (type != null && color != null) {
      // Get the resources for the specified piece type
      HashMap<PlayerColor, DrawableResource<Resource>> pieceResources = resources.get(type);

      // If resources for this piece type exist, get the drawable resource for the
      // specified color
      if (pieceResources != null) {
        DrawableResource<Resource> resource = pieceResources.get(color);

        // If a resource for the specified color exists, set icon to that resource
        if (resource != null) {
          icon = resource.getResource();
        }
      }
    }

    // Return the loaded or default resource
    return icon;
  }
}

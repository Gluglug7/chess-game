package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Queen extends Piece {
  public Queen(String colour, ImageView image) {
    this.colour = colour;
    this.type = "queen";
    this.image = image;
  }

  /**
   * Returns a set of all possible moves for a queen at a given position.
   *
   * @param xOrdinate The x ordinate of the queen.
   * @param yOrdinate The y ordinate of the queen.
   * @return A set of all possible moves for a queen at a given position.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();

    boolean north = true;
    boolean east = true;
    boolean south = true;
    boolean west = true;

    boolean northEast = true;
    boolean southEast = true;
    boolean southWest = true;
    boolean northWest = true;

    for (int i = 1; i < 8; i++) {
      // Handling queen moving upwards
      if (north && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
          north = false;
        } else {
          north = false;
        }
      }

      // Handling queen moving right
      if (east && xOrdinate + i < 8) {
        if (board.get(yOrdinate).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
        } else if (!board.get(yOrdinate).get(xOrdinate + i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
          east = false;
        } else {
          east = false;
        }
      }

      // Handling queen moving downwards
      if (south && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
          south = false;
        } else {
          south = false;
        }
      }

      // Handling queen moving left
      if (west && xOrdinate - i >= 0) {
        if (board.get(yOrdinate).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
        } else if (!board.get(yOrdinate).get(xOrdinate - i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
          west = false;
        } else {
          west = false;
        }
      }

      // Handling queen moving diagonally upwards and right
      if (northEast && xOrdinate + i < 8 && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate + i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
          northEast = false;
        } else {
          northEast = false;
        }
      }

      // Handling queen moving diagonally downwards and right
      if (southEast && xOrdinate + i < 8 && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate + i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
          southEast = false;
        } else {
          southEast = false;
        }
      }

      // Handling queen moving diagonally downwards and left
      if (southWest && xOrdinate - i >= 0 && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate - i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
          southWest = false;
        } else {
          southWest = false;
        }
      }

      // Handling queen moving diagonally upwards and left
      if (northWest && xOrdinate - i >= 0 && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate - i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
          northWest = false;
        } else {
          northWest = false;
        }
      }
    }
    return moves;
  }
}

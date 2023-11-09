package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Rook extends Piece {
  public Rook(String colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = "rook";
    this.hasMoved = false;
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
  }

  /**
   * Returns a set of all possible moves for a rook at the given coordinates.
   *
   * @param board The board the rook is on.
   * @return A set of all possible moves for a rook at the given coordinates.
   */
  public Set<int[]> moveSet(List<List<Piece>> board, boolean checkCheck) {
    Set<int[]> moves = new HashSet<int[]>();
    boolean north = true;
    boolean east = true;
    boolean south = true;
    boolean west = true;

    for (int i = 1; i < 8; i++) {
      // Handling rook moving upwards
      if (north && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate).getColour().equals(this.colour)
            || checkCheck) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
          north = false;
        } else {
          north = false;
        }
      }

      // Handling rook moving right
      if (east && xOrdinate + i < 8) {
        if (board.get(yOrdinate).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
        } else if (!board.get(yOrdinate).get(xOrdinate + i).getColour().equals(this.colour)
            || checkCheck) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
          east = false;
        } else {
          east = false;
        }
      }

      // Handling rook moving downwards
      if (south && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate).getColour().equals(this.colour)
            || checkCheck) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
          south = false;
        } else {
          south = false;
        }
      }

      // Handling rook moving left
      if (west && xOrdinate - i >= 0) {
        if (board.get(yOrdinate).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
        } else if (!board.get(yOrdinate).get(xOrdinate - i).getColour().equals(this.colour)
            || checkCheck) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
          west = false;
        } else {
          west = false;
        }
      }
    }
    return moves;
  }

  /**
   * Returns a new rook with the same colour and image as the current rook.
   *
   * @param image The image of the new rook.
   * @return A new rook with the same colour and image as the current rook.
   */
  public Piece copy(ImageView newImage) {
    return new Rook(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

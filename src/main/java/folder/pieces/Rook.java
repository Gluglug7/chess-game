package folder.pieces;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Rook extends Piece {
  public Rook(String colour, ImageView image) {
    this.colour = colour;
    this.type = "rook";
    this.hasMoved = false;
    this.image = image;
  }

  /**
   * Returns a set of all possible moves for a rook at the given coordinates.
   *
   * @param xOrdinate The x ordinate of the rook.
   * @param yOrdinate The y ordinate of the rook.
   * @return A set of all possible moves for a rook at the given coordinates.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    Set<int[]> moves = new HashSet<int[]>();
    for (int i = 1; i < 8; i++) {
      // Handling adding horizontal and vertical moves
      if (xOrdinate + i < 8) {
        moves.add(new int[] {xOrdinate + i, yOrdinate});
      }
      if (xOrdinate - i >= 0) {
        moves.add(new int[] {xOrdinate - i, yOrdinate});
      }
      if (yOrdinate + i < 8) {
        moves.add(new int[] {xOrdinate, yOrdinate + i});
      }
      if (yOrdinate - i >= 0) {
        moves.add(new int[] {xOrdinate, yOrdinate - i});
      }
    }
    return moves;
  }
}

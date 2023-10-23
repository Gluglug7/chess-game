package folder.pieces;

import java.util.HashSet;
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
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    Set<int[]> moves = new HashSet<int[]>();
    for (int i = 1; i < 8; i++) {
      // Handling adding diagonal moves
      if (xOrdinate + i < 8 && yOrdinate + i < 8) {
        moves.add(new int[] {xOrdinate + i, yOrdinate + i});
      }
      if (xOrdinate + i < 8 && yOrdinate - i >= 0) {
        moves.add(new int[] {xOrdinate + i, yOrdinate - i});
      }
      if (xOrdinate - i >= 0 && yOrdinate + i < 8) {
        moves.add(new int[] {xOrdinate - i, yOrdinate + i});
      }
      if (xOrdinate - i >= 0 && yOrdinate - i >= 0) {
        moves.add(new int[] {xOrdinate - i, yOrdinate - i});
      }

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

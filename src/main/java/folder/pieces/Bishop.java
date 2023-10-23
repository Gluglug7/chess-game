package folder.pieces;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {
  public Bishop(String colour, ImageView image) {
    this.colour = colour;
    this.type = "bishop";
    this.image = image;
  }

  /**
   * Returns a set of all possible moves for a bishop at a given position.
   *
   * @param xOrdinate The x ordinate of the bishop.
   * @param yOrdinate The y ordinate of the bishop.
   * @return A set of all possible moves for a bishop at a given position.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handling adding all the possible diagonals
    for (int i = 1; i < 8; i++) {
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
    }
    return moves;
  }
}

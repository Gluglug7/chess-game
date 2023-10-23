package folder.pieces;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
  public King(String colour) {
    this.colour = colour;
    this.type = "king";
  }

  // TODO: Add castling mechanic
  /**
   * Returns a set of all possible moves for a king at a given position.
   *
   * @param xOrdinate The x ordinate of the king.
   * @param yOrdinate The y ordinate of the king.
   * @return A set of all possible moves for a king at a given position.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handling adding all the single moves the king can make
    if (xOrdinate + 1 < 8) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate});
    }
    if (xOrdinate - 1 >= 0) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate});
    }
    if (yOrdinate + 1 < 8) {
      moves.add(new int[] {xOrdinate, yOrdinate + 1});
    }
    if (yOrdinate - 1 >= 0) {
      moves.add(new int[] {xOrdinate, yOrdinate - 1});
    }
    if (xOrdinate + 1 < 8 && yOrdinate + 1 < 8) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate + 1});
    }
    if (xOrdinate + 1 < 8 && yOrdinate - 1 >= 0) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate - 1});
    }
    if (xOrdinate - 1 >= 0 && yOrdinate + 1 < 8) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate + 1});
    }
    if (xOrdinate - 1 >= 0 && yOrdinate - 1 >= 0) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate - 1});
    }
    return moves;
  }
}

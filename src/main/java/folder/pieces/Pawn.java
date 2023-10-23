package folder.pieces;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
  public Pawn(String colour) {
    this.colour = colour;
    this.type = "pawn";
    this.hasMoved = false;
  }

  /**
   * Returns a set of all possible moves for a pawn at the given coordinates.
   *
   * @param xOrdinate The x ordinate of the pawn.
   * @param yOrdinate The y ordinate of the pawn.
   * @return A set of all possible moves for a pawn at the given coordinates.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    Set<int[]> moves = new HashSet<int[]>();
    if (this.colour.equals("white")) {
      if (yOrdinate + 1 < 8) {
        moves.add(new int[] {xOrdinate, yOrdinate + 1});
      }
      if (!hasMoved) {
        moves.add(new int[] {xOrdinate, yOrdinate + 2});
      }
    } else {
      if (yOrdinate - 1 >= 0) {
        moves.add(new int[] {xOrdinate, yOrdinate - 1});
      }
      if (!hasMoved) {
        moves.add(new int[] {xOrdinate, yOrdinate - 2});
      }
    }
    return moves;
  }
}

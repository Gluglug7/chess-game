package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Knight extends Piece {
  public Knight(String colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = "knight";
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
  }

  /**
   * Returns a set of all possible moves for a knight at the given coordinates.
   *
   * @param xOrdinate The x ordinate of the knight.
   * @param yOrdinate The y ordinate of the knight.
   * @return A set of all possible moves for a knight at the given coordinates.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handles all the possible L shape movements the knight can make
    if (xOrdinate + 2 < 8
        && yOrdinate + 1 < 8
        && !board.get(yOrdinate + 1).get(xOrdinate + 2).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 2, yOrdinate + 1});
    }
    if (xOrdinate + 2 < 8
        && yOrdinate - 1 >= 0
        && !board.get(yOrdinate - 1).get(xOrdinate + 2).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 2, yOrdinate - 1});
    }
    if (xOrdinate - 2 >= 0
        && yOrdinate + 1 < 8
        && !board.get(yOrdinate + 1).get(xOrdinate - 2).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 2, yOrdinate + 1});
    }
    if (xOrdinate - 2 >= 0
        && yOrdinate - 1 >= 0
        && !board.get(yOrdinate - 1).get(xOrdinate - 2).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 2, yOrdinate - 1});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate + 2 < 8
        && !board.get(yOrdinate + 2).get(xOrdinate + 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate + 2});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate - 2 >= 0
        && !board.get(yOrdinate - 2).get(xOrdinate + 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate - 2});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate + 2 < 8
        && !board.get(yOrdinate + 2).get(xOrdinate - 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate + 2});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate - 2 >= 0
        && !board.get(yOrdinate - 2).get(xOrdinate - 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate - 2});
    }
    return moves;
  }
}

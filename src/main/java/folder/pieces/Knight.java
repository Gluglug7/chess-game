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
   * @param board The board the knight is on.
   * @return A set of all possible moves for a knight at the given coordinates.
   */
  public Set<int[]> moveSet(List<List<Piece>> board, boolean checkCheck) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handles all the possible L shape movements the knight can make
    if (xOrdinate + 2 < 8
        && yOrdinate + 1 < 8
        && (!board.get(yOrdinate + 1).get(xOrdinate + 2).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate + 2, yOrdinate + 1});
    }
    if (xOrdinate + 2 < 8
        && yOrdinate - 1 >= 0
        && (!board.get(yOrdinate - 1).get(xOrdinate + 2).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate + 2, yOrdinate - 1});
    }
    if (xOrdinate - 2 >= 0
        && yOrdinate + 1 < 8
        && (!board.get(yOrdinate + 1).get(xOrdinate - 2).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate - 2, yOrdinate + 1});
    }
    if (xOrdinate - 2 >= 0
        && yOrdinate - 1 >= 0
        && (!board.get(yOrdinate - 1).get(xOrdinate - 2).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate - 2, yOrdinate - 1});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate + 2 < 8
        && (!board.get(yOrdinate + 2).get(xOrdinate + 1).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate + 2});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate - 2 >= 0
        && (!board.get(yOrdinate - 2).get(xOrdinate + 1).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate - 2});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate + 2 < 8
        && (!board.get(yOrdinate + 2).get(xOrdinate - 1).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate + 2});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate - 2 >= 0
        && (!board.get(yOrdinate - 2).get(xOrdinate - 1).getColour().equals(this.colour)
            || checkCheck)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate - 2});
    }
    return moves;
  }

  /**
   * Returns a new knight with the same colour and image as the original.
   *
   * @param image The image of the new knight.
   * @return A new knight with the same colour and image as the original.
   */
  public Piece copy(ImageView newImage) {
    return new Knight(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

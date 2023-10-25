package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class King extends Piece {
  public King(String colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = "king";
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
  }

  /**
   * Returns a set of all possible moves for a king at a given position.
   *
   * @param xOrdinate The x ordinate of the king.
   * @param yOrdinate The y ordinate of the king.
   * @return A set of all possible moves for a king at a given position.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handling adding all the single moves the king can make
    if (xOrdinate + 1 < 8
        && !board.get(yOrdinate).get(xOrdinate + 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate});
    }
    if (xOrdinate - 1 >= 0
        && !board.get(yOrdinate).get(xOrdinate - 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate});
    }
    if (yOrdinate + 1 < 8
        && !board.get(yOrdinate + 1).get(xOrdinate).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate, yOrdinate + 1});
    }
    if (yOrdinate - 1 >= 0
        && !board.get(yOrdinate - 1).get(xOrdinate).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate, yOrdinate - 1});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate + 1 < 8
        && !board.get(yOrdinate + 1).get(xOrdinate + 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate + 1});
    }
    if (xOrdinate + 1 < 8
        && yOrdinate - 1 >= 0
        && !board.get(yOrdinate - 1).get(xOrdinate + 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate + 1, yOrdinate - 1});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate + 1 < 8
        && !board.get(yOrdinate + 1).get(xOrdinate - 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate + 1});
    }
    if (xOrdinate - 1 >= 0
        && yOrdinate - 1 >= 0
        && !board.get(yOrdinate - 1).get(xOrdinate - 1).getColour().equals(this.colour)) {
      moves.add(new int[] {xOrdinate - 1, yOrdinate - 1});
    }
    return moves;
  }

  /**
   * Returns a copy of the king.
   *
   * @return A copy of the king.
   */
  public Piece copy(ImageView newImage) {
    return new King(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

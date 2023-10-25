package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {
  public Pawn(String colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = "pawn";
    this.hasMoved = false;
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
  }

  /**
   * Returns a set of all possible moves for a pawn at the given coordinates. Pawns cannot move
   * vertically if there is another piece in the way, and can only take other pieces if they are
   * diagonal to them.
   *
   * @param xOrdinate The x ordinate of the pawn.
   * @param yOrdinate The y ordinate of the pawn.
   * @return A set of all possible moves for a pawn at the given coordinates.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();
    if (this.colour.equals("white")) {
      if (yOrdinate - 1 >= 0 && board.get(yOrdinate - 1).get(xOrdinate).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate, yOrdinate - 1});
      }
      // Case for initially moving two spaces
      if (!hasMoved
          && yOrdinate - 2 >= 0
          && board.get(yOrdinate - 2).get(xOrdinate).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate, yOrdinate - 2});
      }

      // Cases for taking a piece to the diagonal
      if (xOrdinate + 1 < 8
          && yOrdinate - 1 >= 0
          && !board.get(yOrdinate - 1).get(xOrdinate + 1).getColour().equals(this.colour)
          && !board.get(yOrdinate - 1).get(xOrdinate + 1).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate + 1, yOrdinate - 1});
      }
      // Cases for taking a piece to the diagonal
      if (xOrdinate - 1 >= 0
          && yOrdinate - 1 >= 0
          && !board.get(yOrdinate - 1).get(xOrdinate - 1).getColour().equals(this.colour)
          && !board.get(yOrdinate - 1).get(xOrdinate - 1).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate - 1, yOrdinate - 1});
      }
    } else {
      if (yOrdinate + 1 < 8 && board.get(yOrdinate + 1).get(xOrdinate).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate, yOrdinate + 1});
      }
      // Case for initially moving two spaces
      if (!hasMoved
          && yOrdinate + 2 < 8
          && board.get(yOrdinate + 2).get(xOrdinate).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate, yOrdinate + 2});
      }

      // Cases for taking a piece to the diagonal
      if (xOrdinate + 1 < 8
          && yOrdinate + 1 < 8
          && !board.get(yOrdinate + 1).get(xOrdinate + 1).getColour().equals(this.colour)
          && !board.get(yOrdinate + 1).get(xOrdinate + 1).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate + 1, yOrdinate + 1});
      }
      // Cases for taking a piece to the diagonal
      if (xOrdinate - 1 >= 0
          && yOrdinate + 1 < 8
          && !board.get(yOrdinate + 1).get(xOrdinate - 1).getColour().equals(this.colour)
          && !board.get(yOrdinate + 1).get(xOrdinate - 1).getType().equals("empty")) {
        moves.add(new int[] {xOrdinate - 1, yOrdinate + 1});
      }
    }
    return moves;
  }

  /**
   * Returns a new pawn with the same colour and image as the original.
   *
   * @param image The image of the pawn.
   * @return A new pawn with the same colour and image as the original.
   */
  public Piece copy(ImageView newImage) {
    return new Pawn(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

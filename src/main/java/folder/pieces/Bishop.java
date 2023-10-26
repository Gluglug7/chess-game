package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {
  public Bishop(String colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = "bishop";
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
  }

  /**
   * Returns a set of all possible moves for a bishop at a given position.
   *
   * @param board The board the bishop is on.
   * @return A set of all possible moves for a bishop at a given position.
   */
  public Set<int[]> moveSet(List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();

    boolean northEast = true;
    boolean southEast = true;
    boolean southWest = true;
    boolean northWest = true;

    for (int i = 1; i < 8; i++) {
      // Handling bishop moving diagonally upwards and right
      if (northEast && xOrdinate + i < 8 && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate + i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
          northEast = false;
        } else {
          northEast = false;
        }
      }

      // Handling bishop moving diagonally downwards and right
      if (southEast && xOrdinate + i < 8 && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate + i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
          southEast = false;
        } else {
          southEast = false;
        }
      }

      // Handling bishop moving diagonally downwards and left
      if (southWest && xOrdinate - i >= 0 && yOrdinate + i < 8) {
        if (board.get(yOrdinate + i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
        } else if (!board.get(yOrdinate + i).get(xOrdinate - i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
          southWest = false;
        } else {
          southWest = false;
        }
      }

      // Handling bishop moving diagonally upwards and left
      if (northWest && xOrdinate - i >= 0 && yOrdinate - i >= 0) {
        if (board.get(yOrdinate - i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
        } else if (!board.get(yOrdinate - i).get(xOrdinate - i).getColour().equals(this.colour)) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
          northWest = false;
        } else {
          northWest = false;
        }
      }
    }
    return moves;
  }

  /**
   * Returns a copy of the bishop.
   *
   * @param newImage The image of the bishop.
   * @return A copy of the bishop.
   */
  public Piece copy(ImageView newImage) {
    return new Bishop(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

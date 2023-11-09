package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Queen extends Piece {
  private Bishop queenBishop;
  private Rook queenRook;

  public Queen(Colour colour, ImageView image, int xOrdinate, int yOrdinate) {
    this.colour = colour;
    this.type = Type.QUEEN;
    this.image = image;
    this.xOrdinate = xOrdinate;
    this.yOrdinate = yOrdinate;
    // The queen is basically a bishop and a rook combined, so we can just use the properties of
    // those two pieces for it
    this.queenBishop = new Bishop(this.colour, this.image, xOrdinate, yOrdinate);
    this.queenRook = new Rook(this.colour, this.image, xOrdinate, yOrdinate);
  }

  /**
   * Returns a set of all possible moves for a queen at a given position.
   *
   * @param board The board the queen is on.
   * @return A set of all possible moves for a queen at a given position.
   */
  public Set<int[]> moveSet(List<List<Piece>> board, boolean checkCheck) {
    Set<int[]> moves = new HashSet<int[]>();
    queenBishop.setX(this.xOrdinate);
    queenBishop.setY(this.yOrdinate);
    queenRook.setX(this.xOrdinate);
    queenRook.setY(this.yOrdinate);
    moves.addAll(queenBishop.moveSet(board, checkCheck));
    moves.addAll(queenRook.moveSet(board, checkCheck));
    return moves;
  }

  /**
   * Returns a copy of the queen.
   *
   * @param image The image of the queen.
   * @return A copy of the queen.
   */
  public Piece copy(ImageView newImage) {
    return new Queen(this.colour, newImage, this.xOrdinate, this.yOrdinate);
  }
}

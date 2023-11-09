package folder.pieces;

import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

/** Unique class to represent an empty tile on the board. */
public class Empty extends Piece {
  public Empty() {
    this.colour = "empty";
    this.type = "empty";
  }

  public Set<int[]> moveSet(List<List<Piece>> board, boolean checkCheck) {
    return null;
  }

  public Piece copy(ImageView newImage) {
    return new Empty();
  }

  @Override
  public void printPiece() {
    System.out.println("empty tile");
  }
}

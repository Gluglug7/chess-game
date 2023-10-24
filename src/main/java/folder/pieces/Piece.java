package folder.pieces;

import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

/** Class to represent everything that can occupy a tile on the board. */
public abstract class Piece {
  protected String colour;
  protected String type;
  protected boolean hasMoved;
  protected ImageView image;

  public abstract Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board);

  public void printPiece() {
    System.out.println(this.colour + " " + this.type);
  }

  public String getType() {
    return this.type;
  }

  public String getColour() {
    return this.colour;
  }

  public ImageView getImage() {
    return this.image;
  }

  public void hasMoved() {
    this.hasMoved = true;
  }
}

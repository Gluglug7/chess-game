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

  protected int xOrdinate;
  protected int yOrdinate;

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

  public int getX() {
    return this.xOrdinate;
  }

  public int getY() {
    return this.yOrdinate;
  }

  public void setX(int x) {
    this.xOrdinate = x;
  }

  public void setY(int y) {
    this.yOrdinate = y;
  }
}

package folder.pieces;

import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

/** Class to represent everything that can occupy a tile on the board. */
public abstract class Piece {
  /** Enum to represent the colour of a piece. */
  public enum Colour {
    WHITE,
    BLACK,
    EMPTY
  }

  /** Enum to represent the type of a piece. */
  public enum Type {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    EMPTY
  }

  protected Colour colour;
  protected Type type;
  protected boolean hasMoved;
  protected ImageView image;

  protected int xOrdinate;
  protected int yOrdinate;

  public abstract Set<int[]> moveSet(List<List<Piece>> board, boolean checkCheck);

  public abstract Piece copy(ImageView newImage);

  public void printPiece() {
    System.out.println(this.colour + " " + this.type);
  }

  public Type getType() {
    return this.type;
  }

  public Colour getColour() {
    return this.colour;
  }

  public ImageView getImage() {
    return this.image;
  }

  public boolean hasMoved() {
    return this.hasMoved;
  }

  public void moved() {
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

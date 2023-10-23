package folder.pieces;

import java.util.Set;

/** Class to represent everything that can occupy a tile on the board. */
public abstract class Piece {
  protected String colour;
  protected String type;
  protected boolean hasMoved;

  public abstract Set<int[]> moveSet(int xOrdinate, int yOrdinate);

  public void printPiece() {
    System.out.println(this.colour + " " + this.type);
  }

  public boolean equals(Piece other) {
    return other.type.equals(this.type);
  }

  public boolean sameColour(Piece other) {
    return other.colour.equals(this.colour);
  }
}

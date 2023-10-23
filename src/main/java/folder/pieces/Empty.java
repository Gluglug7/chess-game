package folder.pieces;

import java.util.Set;

/** Unique class to represent an empty tile on the board. */
public class Empty extends Piece {
  public Empty() {
    this.colour = "empty";
    this.type = "empty";
  }

  public Set<int[]> moveSet(int xOrdinate, int yOrdinate) {
    return null;
  }

  @Override
  public void printPiece() {
    System.out.println("empty tile");
  }
}

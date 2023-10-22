package folder.pieces;

public abstract class Piece {
  protected String colour;
  protected String type;

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

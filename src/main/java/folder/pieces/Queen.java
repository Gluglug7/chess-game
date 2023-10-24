package folder.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Queen extends Piece {
  public Queen(String colour, ImageView image) {
    this.colour = colour;
    this.type = "queen";
    this.image = image;
  }

  /**
   * Returns a set of all possible moves for a queen at a given position.
   *
   * @param xOrdinate The x ordinate of the queen.
   * @param yOrdinate The y ordinate of the queen.
   * @return A set of all possible moves for a queen at a given position.
   */
  public Set<int[]> moveSet(int xOrdinate, int yOrdinate, List<List<Piece>> board) {
    Set<int[]> moves = new HashSet<int[]>();
    // Handling adding all the possible diagonals
    for (int i = 1; i < 8; i++) {
      if (xOrdinate + i < 8
          && yOrdinate + i < 8
          && !board.get(yOrdinate + i).get(xOrdinate + i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate + i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
        } else if (board.get(yOrdinate + i).get(xOrdinate + i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate + i, yOrdinate + i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 1; i < 8; i++) {
      if (xOrdinate + i < 8
          && yOrdinate - i >= 0
          && !board.get(yOrdinate - i).get(xOrdinate + i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate - i).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
        } else if (board.get(yOrdinate - i).get(xOrdinate + i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate + i, yOrdinate - i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 1; i < 8; i++) {
      if (xOrdinate - i >= 0
          && yOrdinate + i < 8
          && !board.get(yOrdinate + i).get(xOrdinate - i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate + i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
        } else if (board.get(yOrdinate + i).get(xOrdinate - i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate - i, yOrdinate + i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 1; i < 8; i++) {
      if (xOrdinate - i >= 0
          && yOrdinate - i >= 0
          && !board.get(yOrdinate - i).get(xOrdinate - i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate - i).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
        } else if (board.get(yOrdinate - i).get(xOrdinate - i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate - i, yOrdinate - i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    // Handling adding all the possible horizontal and vertical moves
    for (int i = 1; i < 8; i++) {
      if (xOrdinate + i < 8
          && !board.get(yOrdinate).get(xOrdinate + i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate).get(xOrdinate + i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
        } else if (board.get(yOrdinate).get(xOrdinate + i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate + i, yOrdinate});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 0; i < 8; i++) {
      if (xOrdinate - i >= 0
          && !board.get(yOrdinate).get(xOrdinate - i).getColour().equals(this.colour)) {
        if (board.get(yOrdinate).get(xOrdinate - i).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
        } else if (board.get(yOrdinate).get(xOrdinate - i).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate - i, yOrdinate});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 1; i < 8; i++) {
      if (yOrdinate + i < 8
          && !board.get(yOrdinate + i).get(xOrdinate).getColour().equals(this.colour)) {
        if (board.get(yOrdinate + i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
        } else if (board.get(yOrdinate + i).get(xOrdinate).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate, yOrdinate + i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    for (int i = 1; i < 8; i++) {
      if (yOrdinate - i >= 0
          && !board.get(yOrdinate - i).get(xOrdinate).getColour().equals(this.colour)) {
        if (board.get(yOrdinate - i).get(xOrdinate).getType().equals("empty")) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
        } else if (board.get(yOrdinate - i).get(xOrdinate).getColour() != this.colour) {
          moves.add(new int[] {xOrdinate, yOrdinate - i});
          break;
        } else {
          break;
        }
      } else {
        break;
      }
    }
    return moves;
  }
}

package folder;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ChessController {
  public static char[][] board;

  public void initialize() {
    board = new char[8][8];
    char[] row = {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
    for (int i = 0; i < 8; i++) {
      board[0][i] = Character.toUpperCase(row[i]);
      board[7][i] = row[i];
      board[1][i] = 'p';
      board[6][i] = 'P';
    }
  }

  /**
   * Method called when a tile is clicked. Finds the position of the tile clicked and performs the
   * approriate action.
   *
   * @param event The mouse event that triggered the method call.
   */
  @FXML
  public void onClicked(MouseEvent event) {
    Rectangle tile = (Rectangle) event.getSource();
    int xOrdinate = (int) (tile.getLayoutX() - 100) / 50;
    int yOrdinate = (int) tile.getLayoutY() / 50;

    switch (board[yOrdinate][xOrdinate]) {
      case 'P':
        System.out.println("Black Pawn");
        break;
      case 'p':
        System.out.println("White Pawn");
        break;
      case 'R':
        System.out.println("Black Rook");
        break;
      case 'r':
        System.out.println("White Rook");
        break;
      case 'N':
        System.out.println("Black Knight");
        break;
      case 'n':
        System.out.println("White Knight");
        break;
      case 'B':
        System.out.println("Black Bishop");
        break;
      case 'b':
        System.out.println("White Bishop");
        break;
      case 'Q':
        System.out.println("Black Queen");
        break;
      case 'q':
        System.out.println("White Queen");
        break;
      case 'K':
        System.out.println("Black King");
        break;
      case 'k':
        System.out.println("White King");
        break;
      default:
        System.out.println("Empty");
        break;
    }

    System.out.println("  xOrdinate: " + xOrdinate);
    System.out.println("  yOrdinate: " + yOrdinate);
  }
}

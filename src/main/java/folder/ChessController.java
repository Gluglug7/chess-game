package folder;

import folder.pieces.Bishop;
import folder.pieces.Empty;
import folder.pieces.King;
import folder.pieces.Knight;
import folder.pieces.Piece;
import folder.pieces.Queen;
import folder.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ChessController {
  public static List<List<Piece>> board;

  public void initialize() {
    board = new ArrayList<List<Piece>>();
    for (int i = 0; i < 8; i++) {
      board.add(createRow(i));
    }
  }

  /**
   * Creates a row of pieces for the board, depending on the row number. Only used when initialising
   * the board.
   *
   * @param i the row number
   * @return the row of pieces
   */
  private List<Piece> createRow(int i) {
    List<Piece> row = new ArrayList<Piece>();

    // Adding major black pieces
    if (i == 0) {
      row.add(new Rook("black"));
      row.add(new Knight("black"));
      row.add(new Bishop("black"));
      row.add(new Queen("black"));
      row.add(new King("black"));
      row.add(new Bishop("black"));
      row.add(new Knight("black"));
      row.add(new Rook("black"));
      // Adding major white pieces
    } else if (i == 7) {
      row.add(new Rook("white"));
      row.add(new Knight("white"));
      row.add(new Bishop("white"));
      row.add(new Queen("white"));
      row.add(new King("white"));
      row.add(new Bishop("white"));
      row.add(new Knight("white"));
      row.add(new Rook("white"));
      // Adding black pawns
    } else if (i == 1) {
      for (int j = 0; j < 8; j++) {
        row.add(new folder.pieces.Pawn("black"));
      }
      // Adding white pawns
    } else if (i == 6) {
      for (int j = 0; j < 8; j++) {
        row.add(new folder.pieces.Pawn("white"));
      }
      // Adding empty tiles
    } else {
      for (int j = 0; j < 8; j++) {
        row.add(new Empty());
      }
    }
    return row;
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

    board.get(yOrdinate).get(xOrdinate).printPiece();

    System.out.println("  xOrdinate: " + xOrdinate);
    System.out.println("  yOrdinate: " + yOrdinate);
  }
}

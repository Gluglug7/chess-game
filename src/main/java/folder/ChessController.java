package folder;

import folder.pieces.Bishop;
import folder.pieces.Empty;
import folder.pieces.King;
import folder.pieces.Knight;
import folder.pieces.Piece;
import folder.pieces.Queen;
import folder.pieces.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ChessController {
  public static List<List<Piece>> board;

  @FXML private ImageView bRookOne;
  @FXML private ImageView bKnightOne;
  @FXML private ImageView bBishopOne;
  @FXML private ImageView bQueen;
  @FXML private ImageView bKing;
  @FXML private ImageView bBishopTwo;
  @FXML private ImageView bKnightTwo;
  @FXML private ImageView bRookTwo;
  @FXML private ImageView bPawnOne;
  @FXML private ImageView bPawnTwo;
  @FXML private ImageView bPawnThree;
  @FXML private ImageView bPawnFour;
  @FXML private ImageView bPawnFive;
  @FXML private ImageView bPawnSix;
  @FXML private ImageView bPawnSeven;
  @FXML private ImageView bPawnEight;

  @FXML private ImageView wRookOne;
  @FXML private ImageView wKnightOne;
  @FXML private ImageView wBishopOne;
  @FXML private ImageView wQueen;
  @FXML private ImageView wKing;
  @FXML private ImageView wBishopTwo;
  @FXML private ImageView wKnightTwo;
  @FXML private ImageView wRookTwo;
  @FXML private ImageView wPawnOne;
  @FXML private ImageView wPawnTwo;
  @FXML private ImageView wPawnThree;
  @FXML private ImageView wPawnFour;
  @FXML private ImageView wPawnFive;
  @FXML private ImageView wPawnSix;
  @FXML private ImageView wPawnSeven;
  @FXML private ImageView wPawnEight;

  public Set<int[]> moves;
  public boolean pieceSelected;
  public Piece selectedPiece;
  public ImageView selectedImage;
  public int[] selectedPos;
  public String turn;

  public void initialize() {
    board = new ArrayList<List<Piece>>();
    for (int i = 0; i < 8; i++) {
      board.add(createRow(i));
    }
    pieceSelected = false;
    turn = "white";
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
      row.add(new Rook("black", bRookOne));
      row.add(new Knight("black", bKnightOne));
      row.add(new Bishop("black", bBishopOne));
      row.add(new Queen("black", bQueen));
      row.add(new King("black", bKing));
      row.add(new Bishop("black", bBishopTwo));
      row.add(new Knight("black", bKnightTwo));
      row.add(new Rook("black", bRookTwo));
      // Adding major white pieces
    } else if (i == 7) {
      row.add(new Rook("white", wRookOne));
      row.add(new Knight("white", wKnightOne));
      row.add(new Bishop("white", wBishopOne));
      row.add(new Queen("white", wQueen));
      row.add(new King("white", wKing));
      row.add(new Bishop("white", wBishopTwo));
      row.add(new Knight("white", wKnightTwo));
      row.add(new Rook("white", wRookTwo));
      // Adding black pawns
    } else if (i == 1) {
      row.add(new folder.pieces.Pawn("black", bPawnOne));
      row.add(new folder.pieces.Pawn("black", bPawnTwo));
      row.add(new folder.pieces.Pawn("black", bPawnThree));
      row.add(new folder.pieces.Pawn("black", bPawnFour));
      row.add(new folder.pieces.Pawn("black", bPawnFive));
      row.add(new folder.pieces.Pawn("black", bPawnSix));
      row.add(new folder.pieces.Pawn("black", bPawnSeven));
      row.add(new folder.pieces.Pawn("black", bPawnEight));
      // Adding white pawns
    } else if (i == 6) {
      row.add(new folder.pieces.Pawn("white", wPawnOne));
      row.add(new folder.pieces.Pawn("white", wPawnTwo));
      row.add(new folder.pieces.Pawn("white", wPawnThree));
      row.add(new folder.pieces.Pawn("white", wPawnFour));
      row.add(new folder.pieces.Pawn("white", wPawnFive));
      row.add(new folder.pieces.Pawn("white", wPawnSix));
      row.add(new folder.pieces.Pawn("white", wPawnSeven));
      row.add(new folder.pieces.Pawn("white", wPawnEight));
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

    Piece piece = board.get(yOrdinate).get(xOrdinate);
    if (pieceSelected) {
      if (moves.stream().anyMatch(c -> Arrays.equals(c, new int[] {xOrdinate, yOrdinate}))) {
        move(xOrdinate, yOrdinate);
        pieceSelected = false;
      }
    }
    if (piece.getColour().equals(this.turn) && !piece.getType().equals("empty")) {
      select(xOrdinate, yOrdinate);
      pieceSelected = true;
    }

    printTurnDetails(piece, xOrdinate, yOrdinate);
  }

  /**
   * Method called when moving a piece. Sets the selected piece to an empty tile and the tile at the
   * given coordinates to the selected piece.
   *
   * @param xOrdinate The x ordinate of the tile to move to.
   * @param yOrdinate The y ordinate of the tile to move to.
   */
  private void move(int xOrdinate, int yOrdinate) {
    board.get(selectedPos[1]).set(selectedPos[0], new Empty());
    board.get(yOrdinate).set(xOrdinate, selectedPiece);
    selectedImage.setLayoutX(xOrdinate * 50 + 100);
    selectedImage.setLayoutY(yOrdinate * 50);
    turn = turn.equals("white") ? "black" : "white";
  }

  /**
   * Method called when selecting a piece. Sets the selected piece, the selected position, and the
   * possible moves for the selected piece.
   *
   * @param xOrdinate The x ordinate of the selected piece.
   * @param yOrdinate The y ordinate of the selected piece.
   */
  private void select(int xOrdinate, int yOrdinate) {
    selectedPiece = board.get(yOrdinate).get(xOrdinate);
    selectedPos = new int[] {xOrdinate, yOrdinate};
    selectedImage = selectedPiece.getImage();
    moves = selectedPiece.moveSet(xOrdinate, yOrdinate);
  }

  /**
   * Printing out what piece has been clicked, which piece has been selected, the possible moves for
   * the selected piece, and the current coordinates of the clicked piece.
   *
   * @param piece The piece that has been clicked.
   * @param xOrdinate The x ordinate of the clicked piece.
   * @param yOrdinate The y ordinate of the clicked piece.
   */
  private void printTurnDetails(Piece piece, int xOrdinate, int yOrdinate) {
    piece.printPiece();
    System.out.println(turn + "'s turn");
    System.out.println("  pieceSelected: " + pieceSelected);
    System.out.println("  selectedPiece: " + selectedPiece.getType());
    // Printing all the possible moves
    for (int[] move : moves) {
      System.out.println("  move: " + move[0] + ", " + move[1]);
    }
    System.out.println("  xOrdinate: " + xOrdinate);
    System.out.println("  yOrdinate: " + yOrdinate + "\n");
  }
}

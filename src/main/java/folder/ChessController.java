package folder;

import folder.pieces.Bishop;
import folder.pieces.Empty;
import folder.pieces.King;
import folder.pieces.Knight;
import folder.pieces.Pawn;
import folder.pieces.Piece;
import folder.pieces.Queen;
import folder.pieces.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
  public Set<Piece> whitePieces;
  public Set<Piece> blackPieces;
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
    whitePieces = new HashSet<Piece>();
    blackPieces = new HashSet<Piece>();
    for (int i = 0; i < 8; i++) {
      whitePieces.add(board.get(7).get(i));
      whitePieces.add(board.get(6).get(i));
      blackPieces.add(board.get(0).get(i));
      blackPieces.add(board.get(1).get(i));
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
      row.add(new Rook("black", bRookOne, 0, 0));
      row.add(new Knight("black", bKnightOne, 1, 0));
      row.add(new Bishop("black", bBishopOne, 2, 0));
      row.add(new Queen("black", bQueen, 3, 0));
      row.add(new King("black", bKing, 4, 0));
      row.add(new Bishop("black", bBishopTwo, 5, 0));
      row.add(new Knight("black", bKnightTwo, 6, 0));
      row.add(new Rook("black", bRookTwo, 7, 0));
      // Adding major white pieces
    } else if (i == 7) {
      row.add(new Rook("white", wRookOne, 0, 7));
      row.add(new Knight("white", wKnightOne, 1, 7));
      row.add(new Bishop("white", wBishopOne, 2, 7));
      row.add(new Queen("white", wQueen, 3, 7));
      row.add(new King("white", wKing, 4, 7));
      row.add(new Bishop("white", wBishopTwo, 5, 7));
      row.add(new Knight("white", wKnightTwo, 6, 7));
      row.add(new Rook("white", wRookTwo, 7, 7));
      // Adding black pawns
    } else if (i == 1) {
      row.add(new Pawn("black", bPawnOne, 0, 1));
      row.add(new Pawn("black", bPawnTwo, 1, 1));
      row.add(new Pawn("black", bPawnThree, 2, 1));
      row.add(new Pawn("black", bPawnFour, 3, 1));
      row.add(new Pawn("black", bPawnFive, 4, 1));
      row.add(new Pawn("black", bPawnSix, 5, 1));
      row.add(new Pawn("black", bPawnSeven, 6, 1));
      row.add(new Pawn("black", bPawnEight, 7, 1));
      // Adding white pawns
    } else if (i == 6) {
      row.add(new Pawn("white", wPawnOne, 0, 6));
      row.add(new Pawn("white", wPawnTwo, 1, 6));
      row.add(new Pawn("white", wPawnThree, 2, 6));
      row.add(new Pawn("white", wPawnFour, 3, 6));
      row.add(new Pawn("white", wPawnFive, 4, 6));
      row.add(new Pawn("white", wPawnSix, 5, 6));
      row.add(new Pawn("white", wPawnSeven, 6, 6));
      row.add(new Pawn("white", wPawnEight, 7, 6));
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
      if (moves.stream().anyMatch(c -> Arrays.equals(c, new int[] {xOrdinate, yOrdinate}))
          && selectedPiece.getColour().equals(this.turn)) {
        // Removing the piece from the board if taken
        // Could include a remove method for each piece that moves it to a position to the side of
        // the boards
        if (!piece.getType().equals("empty")) {
          piece.getImage().setLayoutX(-50);
          if (piece.getColour().equals("white")) {
            whitePieces.remove(piece);
          } else {
            blackPieces.remove(piece);
          }
        }
        move(xOrdinate, yOrdinate);
        pieceSelected = false;
        turn = turn.equals("white") ? "black" : "white";
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
    selectedPiece.hasMoved();
    selectedPiece.setX(xOrdinate);
    selectedPiece.setY(yOrdinate);
    selectedImage.setLayoutX(xOrdinate * 50 + 100);
    selectedImage.setLayoutY(yOrdinate * 50);
    checkAllChecks();
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
    moves = selectedPiece.moveSet(xOrdinate, yOrdinate, board);
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

  /**
   * Method to check whether either side is in check. Calls the checkCheck method for both sides.
   */
  private void checkAllChecks() {
    checkCheck("white", board);
    checkCheck("black", board);
  }

  /**
   * Method to check whether one side is in check or not. Method goes through all opposing pieces
   * and checks if any of them can move to the king's position. If at least one can, then it is a
   * check.
   *
   * @param colour The colour of the king to check.
   * @param boardToCheck The board to check for a check.
   */
  private boolean checkCheck(String colour, List<List<Piece>> boardToCheck) {
    ImageView king = colour.equals("white") ? wKing : bKing;
    int kingX = (int) (king.getLayoutX() - 100) / 50;
    int kingY = (int) king.getLayoutY() / 50;

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Piece piece = boardToCheck.get(i).get(j);
        if (!piece.getColour().equals(colour) && !piece.getType().equals("empty")) {
          // Using the piece location to get the possible moves for that piece
          Set<int[]> potentialMoves = piece.moveSet(j, i, boardToCheck);
          if (potentialMoves == null) {
            continue;
          }
          // If a check is found, then the check is printed and the check boolean is set to true
          // and the function is returned
          if (potentialMoves.stream().anyMatch(c -> Arrays.equals(c, new int[] {kingX, kingY}))) {
            GameState.wCheck = colour.equals("white") ? true : false;
            GameState.bCheck = colour.equals("black") ? true : false;
            System.out.println(colour + " king is in check");
            return true;
          }
        }
      }
    }
    return false;
  }
}

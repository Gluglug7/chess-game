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
  public static final int BOARD_SIZE = 8;
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

  @FXML private ImageView dummyImage;

  public Set<int[]> moves;
  public Set<Piece> whitePieces;
  public Set<Piece> blackPieces;
  public List<Piece> checkingPieces;
  public boolean pieceSelected;
  public Piece selectedPiece;
  public ImageView selectedImage;
  public int[] selectedPos;
  public String turn;

  public void initialize() {
    board = new ArrayList<List<Piece>>();
    for (int i = 0; i < BOARD_SIZE; i++) {
      board.add(createRow(i));
    }
    whitePieces = new HashSet<Piece>();
    blackPieces = new HashSet<Piece>();
    checkingPieces = new ArrayList<Piece>();
    for (int i = 0; i < BOARD_SIZE; i++) {
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
      for (int j = 0; j < BOARD_SIZE; j++) {
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
      if (selectedPiece.getType().equals("king")) {
        if (castle(xOrdinate, yOrdinate)) {
          pieceSelected = false;
          turn = turn.equals("white") ? "black" : "white";
          System.out.println("Castle");
          printTurnDetails(piece, xOrdinate, yOrdinate);
          return;
        }
      }
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
    selectedPiece.moved();
    selectedPiece.setX(xOrdinate);
    selectedPiece.setY(yOrdinate);
    selectedImage.setLayoutX(xOrdinate * 50 + 100);
    selectedImage.setLayoutY(yOrdinate * 50);
    checkAllChecks();
  }

  private boolean castle(int xOrdinate, int yOrdinate) {
    // Cannot castle if the king has moved
    if (!selectedPiece.getType().equals("king") || selectedPiece.hasMoved()) {
      return false;
    }

    String colour = selectedPiece.getColour();
    int yValue = colour.equals("white") ? 7 : 0;
    int xValue = xOrdinate == 6 ? 7 : 0;

    // Piece on that tile must be a rook and must not have moved
    Piece rook = board.get(yValue).get(xValue);
    if (!rook.getType().equals("rook") || rook.hasMoved()) {
      return false;
    }

    // If there are tiles that are not empty between the two casting pieces, then cannot castle
    if (selectedPiece.getX() < xValue) {
      for (int i = 5; i < 7; i++) {
        if (!board.get(yValue).get(i).getType().equals("empty")) {
          return false;
        }
      }
    } else if (selectedPiece.getX() > xValue) {
      for (int i = 1; i < 4; i++) {
        if (!board.get(yValue).get(i).getType().equals("empty")) {
          return false;
        }
      }
    }

    int xRookPlacement = xOrdinate == 6 ? 5 : 3;
    int xKingPlacement = xOrdinate == 6 ? 6 : 2;
    board.get(yValue).set(xRookPlacement, rook);
    rook.moved();
    board.get(yValue).set(xKingPlacement, selectedPiece);
    selectedPiece.moved();
    rook.getImage().setLayoutX(xRookPlacement * 50 + 100);
    selectedImage.setLayoutX(xKingPlacement * 50 + 100);
    board.get(yValue).set(xValue, new Empty());
    board.get(yValue).set(selectedPos[0], new Empty());
    checkAllChecks();
    return true;
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
    if (checkCheck("white", board) > 0) {
      System.out.println("White is in check");
      if (checkCheckMate("white")) {
        System.out.println("White is in checkmate");
      }
    } else if (checkCheck("black", board) > 0) {
      System.out.println("Black is in check");
      if (checkCheckMate("black")) {
        System.out.println("Black is in checkmate");
      }
    }
  }

  /**
   * Method to check whether one side is in check or not. Method goes through all opposing pieces
   * and checks if any of them can move to the king's position. If at least one can, then it is a
   * check.
   *
   * @param colour The colour of the king to check.
   * @param boardToCheck The board to check for a check.
   */
  private int checkCheck(String colour, List<List<Piece>> boardToCheck) {
    ImageView king = colour.equals("white") ? wKing : bKing;
    int kingX = (int) (king.getLayoutX() - 100) / 50;
    int kingY = (int) king.getLayoutY() / 50;
    checkingPieces.clear();
    int checkingNumber = 0;

    for (Piece piece : colour.equals("white") ? blackPieces : whitePieces) {
      Set<int[]> potentialMoves = piece.moveSet(piece.getX(), piece.getY(), boardToCheck);
      if (potentialMoves == null) {
        continue;
      }
      if (potentialMoves.stream().anyMatch(c -> Arrays.equals(c, new int[] {kingX, kingY}))) {
        checkingPieces.add(piece);
        checkingNumber++;
      }
    }
    return checkingNumber;
  }

  /**
   * Checks whether a given colour king is in checkmate or not. Does this by checking two scenarios;
   * the first, when two pieces are checking the king, in which case only the king can potentially
   * move out of the mate. The other is when only one piece is checking the king, in which case you
   * need to check if the king can move out of it and if other pieces can intersect the checkmate or
   * take the checking piece.
   *
   * @param colour
   * @return
   */
  private boolean checkCheckMate(String colour) {
    boolean checkmate = false;
    if (checkingPieces.size() > 1) {
      checkmate = checkKingMoves(colour);
    } else {
      // If the king can move out of check or a piece can take the checking piece, then it is
      // definitely not a checkmate, so can return false at this point
      if (!checkKingMoves(colour) || !checkTakePiece(colour)) {
        return false;
      }
      // If the king cannot move and no pieces can take the checking piece, then if the piece's path
      // can be blocked it will not be a checkmate
      Piece checkingPiece = checkingPieces.get(0);
      if (checkingPiece.getType().equals("rook")
          || checkingPiece.getType().equals("bishop")
          || checkingPiece.getType().equals("queen")) {
        checkmate = checkPathBlock(colour);
      } else {
        // If the checking piece is not a rook, bishop, or queen, then it's path cannot be blocked,
        // as if it were a king or pawn, then blocking the path would mean taking the piece, and if
        // it were a knight, then the path cannot be blocked at all
        checkmate = true;
      }
    }
    return checkmate;
  }

  /**
   * Method to check whether the king can move out of check. If the king cannot move out of check,
   * depending on the type of checkmate, other methods may need to be called to see if it is a
   * checkmate or not.
   *
   * @param colour The colour of the king to check.
   * @return Whether the king can move out of check or not.
   */
  private boolean checkKingMoves(String colour) {
    Piece king = null;
    Set<int[]> potentialMoves = null;

    // Finding the king of the given colour colour
    for (Piece piece : colour.equals("white") ? whitePieces : blackPieces) {
      if (piece.getType().equals("king")) {
        king = piece;
        potentialMoves = king.moveSet(piece.getX(), piece.getY(), board);
        break;
      }
    }

    Set<int[]> coveredSet = new HashSet<int[]>();
    for (Piece piece : checkingPieces) {
      for (int[] move : piece.moveSet(piece.getX(), piece.getY(), board)) {
        if (potentialMoves.stream().anyMatch(c -> Arrays.equals(c, move))) {
          coveredSet.add(move);
        }
      }
    }

    if (coveredSet.size() == potentialMoves.size()) {
      return true;
    } else {
      for (int[] move : potentialMoves) {
        if (!coveredSet.stream().anyMatch(c -> Arrays.equals(c, move))) {
          System.out.println(
              "No checkmate because the king can move out of check by moving to "
                  + move[0]
                  + ", "
                  + move[1]);
        }
      }
      return false;
    }
  }

  private boolean checkPathBlock(String colour) {
    for (Piece piece : colour.equals("white") ? whitePieces : blackPieces) {
      Set<int[]> potentialMoves = piece.moveSet(piece.getX(), piece.getY(), board);
      if (piece.getType().equals("king") || potentialMoves == null || potentialMoves.isEmpty()) {
        continue;
      }
      for (int[] move : potentialMoves) {
        List<List<Piece>> boardClone = cloneBoard();
        boardClone.get(move[1]).set(move[0], piece);
        boardClone.get(piece.getY()).set(piece.getX(), new Empty());
        if (checkCheck(colour, boardClone) == 0) {
          System.out.println("No checkmate because " + piece.getType() + " can block the check");
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Method to check whether the checking piece can be taken or not. If it can, then it is not a
   * checkmate by this method. This method is only called when there is one checking piece.
   *
   * @param colour The colour of the king to check.
   * @return Whether the checking piece can be taken or not.
   */
  private boolean checkTakePiece(String colour) {
    int checkingX = checkingPieces.get(0).getX();
    int checkingY = checkingPieces.get(0).getY();

    // Checks if any potential moves can take the checking piece away, thus removing the check
    for (Piece piece : colour.equals("white") ? whitePieces : blackPieces) {
      Set<int[]> potentialMoves = piece.moveSet(piece.getX(), piece.getY(), board);
      if (potentialMoves == null || potentialMoves.isEmpty()) {
        continue;
      }
      if (potentialMoves.stream()
          .anyMatch(c -> Arrays.equals(c, new int[] {checkingX, checkingY}))) {
        System.out.println(
            "No checkmate because " + piece.getType() + " can take the checking " + "piece");
        return false;
      }
    }

    return true;
  }

  /**
   * Method to create a clone of the board as not to affect the real board when simulating moves
   * when checking for checkmates.
   *
   * @return the cloned board.
   */
  private List<List<Piece>> cloneBoard() {
    List<List<Piece>> clone = new ArrayList<List<Piece>>();
    // Initialising the board clone
    for (int i = 0; i < BOARD_SIZE; i++) {
      clone.add(new ArrayList<Piece>());
      for (int j = 0; j < BOARD_SIZE; j++) {
        clone.get(i).add(new Empty());
      }
    }

    // Adds the appropriate pieces to the board clone
    for (Piece whitePiece : whitePieces) {
      clone.get(whitePiece.getY()).set(whitePiece.getX(), whitePiece.copy(dummyImage));
    }
    for (Piece blackPiece : blackPieces) {
      clone.get(blackPiece.getY()).set(blackPiece.getX(), blackPiece.copy(dummyImage));
    }
    return clone;
  }
}

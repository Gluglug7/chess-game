package folder;

import folder.pieces.Empty;
import folder.pieces.Piece;
import folder.pieces.Piece.Colour;
import folder.pieces.Piece.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ChessController {
  // Board size can be changed here
  public static final int BOARD_SIZE = 8;
  public static Board board;

  public static Set<Piece> whitePieces;
  public static Set<Piece> blackPieces;
  public static Set<Piece> whiteTakenPieces;
  public static Set<Piece> blackTakenPieces;

  @FXML public Label textLabel;

  @FXML public ImageView bRookOne;
  @FXML public ImageView bKnightOne;
  @FXML public ImageView bBishopOne;
  @FXML public ImageView bQueen;
  @FXML public ImageView bKing;
  @FXML public ImageView bBishopTwo;
  @FXML public ImageView bKnightTwo;
  @FXML public ImageView bRookTwo;
  @FXML public ImageView bPawnOne;
  @FXML public ImageView bPawnTwo;
  @FXML public ImageView bPawnThree;
  @FXML public ImageView bPawnFour;
  @FXML public ImageView bPawnFive;
  @FXML public ImageView bPawnSix;
  @FXML public ImageView bPawnSeven;
  @FXML public ImageView bPawnEight;

  @FXML public ImageView wRookOne;
  @FXML public ImageView wKnightOne;
  @FXML public ImageView wBishopOne;
  @FXML public ImageView wQueen;
  @FXML public ImageView wKing;
  @FXML public ImageView wBishopTwo;
  @FXML public ImageView wKnightTwo;
  @FXML public ImageView wRookTwo;
  @FXML public ImageView wPawnOne;
  @FXML public ImageView wPawnTwo;
  @FXML public ImageView wPawnThree;
  @FXML public ImageView wPawnFour;
  @FXML public ImageView wPawnFive;
  @FXML public ImageView wPawnSix;
  @FXML public ImageView wPawnSeven;
  @FXML public ImageView wPawnEight;

  @FXML public ImageView dummyImage;

  public Checker checker;
  public Set<int[]> moves;
  public boolean pieceSelected;
  public Piece selectedPiece;
  public ImageView selectedImage;
  public int[] selectedPos;
  public Colour turn;

  /** Method called when the program is started. Initialises the board and the pieces. */
  public void initialize() {
    board = new Board(this);
    this.checker = new Checker(this);

    whitePieces = new HashSet<Piece>();
    blackPieces = new HashSet<Piece>();

    whiteTakenPieces = new HashSet<Piece>();
    blackTakenPieces = new HashSet<Piece>();

    for (int i = 0; i < BOARD_SIZE; i++) {
      whitePieces.add(board.getBoard().get(7).get(i));
      whitePieces.add(board.getBoard().get(6).get(i));
      blackPieces.add(board.getBoard().get(0).get(i));
      blackPieces.add(board.getBoard().get(1).get(i));
    }
    pieceSelected = false;
    turn = Colour.WHITE;
    textLabel.setText("White's turn");
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

    Piece piece = board.getBoard().get(yOrdinate).get(xOrdinate);
    if (pieceSelected) {
      if (selectedPiece.getType().equals(Type.KING)) {
        if (castle(xOrdinate, yOrdinate)) {
          pieceSelected = false;
          turn = turn.equals(Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
          if (GameState.check) {
            setText("Check!");
          } else {
            setText((turn.equals(Colour.WHITE) ? "White" : "Black") + "'s turn");
          }
          System.out.println("Castle");
          printTurnDetails(piece, xOrdinate, yOrdinate);
          return;
        }
      }
      if (moves.stream().anyMatch(c -> Arrays.equals(c, new int[] {xOrdinate, yOrdinate}))
          && selectedPiece.getColour().equals(this.turn)) {
        // If the move would put the player in check, then the move is invalid
        if (simulateMove(xOrdinate, yOrdinate)) {
          // Removing the piece from the board if taken
          // Could include a remove method for each piece that moves it to a position to the side of
          // the boards
          if (!piece.getType().equals(Type.EMPTY)) {
            piece.getImage().setLayoutX(-50);
            if (piece.getColour().equals(Colour.WHITE)) {
              whitePieces.remove(piece);
              whiteTakenPieces.add(piece);
            } else {
              blackPieces.remove(piece);
              blackTakenPieces.add(piece);
            }
            piece = new Empty();
          }
          // Only moving if the move would not put the player in check
          move(xOrdinate, yOrdinate);
          pieceSelected = false;
          turn = turn.equals(Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
          if (GameState.check) {
            setText("Check!");
          } else {
            setText((turn.equals(Colour.WHITE) ? "White" : "Black") + "'s turn");
          }
        } else {
          // Telling the player why the move is invalid
          setText(GameState.checkmate ? "Checkmate!" : "Invalid move!");
          System.out.println(
              GameState.checkmate
                  ? "Checkmate! The game is over, you cannot move!"
                  : "Invalid move! Cannot move into check!");
        }
      }
    }
    // Not else if statement because user may want to select another piece to move
    if (piece.getColour().equals(this.turn) && !piece.getType().equals(Type.EMPTY)) {
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
    board.getBoard().get(selectedPos[1]).set(selectedPos[0], new Empty());
    board.getBoard().get(yOrdinate).set(xOrdinate, selectedPiece);
    selectedPiece.moved();
    selectedPiece.setX(xOrdinate);
    selectedPiece.setY(yOrdinate);
    selectedImage.setLayoutX(xOrdinate * 50 + 100);
    selectedImage.setLayoutY(yOrdinate * 50);
    checkAllChecks();
  }

  /**
   * Method called when simulating a move. Creates a clone of the board and moves the selected piece
   * to the given coordinates. Checks whether the move would put the player in check.
   *
   * @param xOrdinate The x ordinate of the tile to move to.
   * @param yOrdinate The y ordinate of the tile to move to.
   * @return Whether the move would put the player in check.
   */
  private boolean simulateMove(int xOrdinate, int yOrdinate) {
    // Using board clone as not to affect the original board
    List<List<Piece>> boardClone = board.cloneBoard();
    Piece piece = boardClone.get(selectedPos[1]).get(selectedPos[0]);
    boardClone.get(selectedPos[1]).set(selectedPos[0], new Empty());
    boardClone.get(yOrdinate).set(xOrdinate, piece);
    // If the piece is the king, then using its new coordinates to check for check
    if (piece.getType().equals(Type.KING)) {
      return checker.checkCheck(piece.getColour(), boardClone, xOrdinate, yOrdinate) == 0;
    } else {
      return checker.checkCheck(piece.getColour(), boardClone) == 0;
    }
  }

  /**
   * Method called when castling. Moves the rook and the king to the appropriate positions.
   *
   * @param xOrdinate The x ordinate of the tile to castle to.
   * @param yOrdinate The y ordinate of the tile to castle to.
   * @return Whether the castle was successful or not.
   */
  private boolean castle(int xOrdinate, int yOrdinate) {
    // Cannot castle if the king has moved
    if (!selectedPiece.getType().equals(Type.KING) || selectedPiece.hasMoved()) {
      return false;
    }

    Colour colour = selectedPiece.getColour();
    int yValue = colour.equals(Colour.WHITE) ? 7 : 0;
    int xValue = xOrdinate == 6 ? 7 : 0;

    // Piece on that tile must be a rook and must not have moved
    Piece rook = board.getBoard().get(yValue).get(xValue);
    if (!rook.getType().equals(Type.ROOK) || rook.hasMoved()) {
      return false;
    }

    // If there are tiles that are not empty between the two casting pieces, then cannot castle
    if (selectedPiece.getX() < xValue) {
      for (int i = 5; i < 7; i++) {
        if (!board.getBoard().get(yValue).get(i).getType().equals(Type.EMPTY)) {
          return false;
        }
      }
    } else if (selectedPiece.getX() > xValue) {
      for (int i = 1; i < 4; i++) {
        if (!board.getBoard().get(yValue).get(i).getType().equals(Type.EMPTY)) {
          return false;
        }
      }
    }

    int xRookPlacement = xOrdinate == 6 ? 5 : 3;
    int xKingPlacement = xOrdinate == 6 ? 6 : 2;
    board.getBoard().get(yValue).set(xRookPlacement, rook);
    rook.moved();
    rook.setX(xRookPlacement);
    board.getBoard().get(yValue).set(xKingPlacement, selectedPiece);
    selectedPiece.moved();
    selectedPiece.setX(xKingPlacement);
    rook.getImage().setLayoutX(xRookPlacement * 50 + 100);
    selectedImage.setLayoutX(xKingPlacement * 50 + 100);
    board.getBoard().get(yValue).set(xValue, new Empty());
    board.getBoard().get(yValue).set(selectedPos[0], new Empty());
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
    selectedPiece = board.getBoard().get(yOrdinate).get(xOrdinate);
    selectedPos = new int[] {xOrdinate, yOrdinate};
    selectedImage = selectedPiece.getImage();
    moves = selectedPiece.moveSet(board.getBoard(), false);
    setText(selectedPiece.getType() + " selected");
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
    if (checker.checkCheck(Colour.WHITE, board.getBoard()) > 0) {
      GameState.check = true;
      setText("Check!");
      System.out.println("White is in check");
      if (checker.checkCheckMate(Colour.WHITE)) {
        GameState.checkmate = true;
        setText("Checkmate!");
        System.out.println("White is in checkmate");
      }
    } else if (checker.checkCheck(Colour.BLACK, board.getBoard()) > 0) {
      GameState.check = true;
      setText("Check!");
      System.out.println("Black is in check");
      if (checker.checkCheckMate(Colour.BLACK)) {
        GameState.checkmate = true;
        setText("Checkmate!");
        System.out.println("Black is in checkmate");
      }
    } else {
      GameState.check = false;
      GameState.checkmate = false;
    }
  }

  /**
   * Method to set the text of the text label. If the game is in checkmate, then the text is set to
   * "Checkmate!". If the game is in check, then the text is set to "Check!".
   *
   * @param text The text to set the text label to.
   */
  private void setText(String text) {
    if (GameState.checkmate) {
      text = "Checkmate!";
    }
    textLabel.setText(text);
  }
}

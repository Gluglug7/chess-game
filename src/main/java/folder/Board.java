package folder;

import folder.pieces.Bishop;
import folder.pieces.Empty;
import folder.pieces.King;
import folder.pieces.Knight;
import folder.pieces.Pawn;
import folder.pieces.Piece;
import folder.pieces.Piece.Colour;
import folder.pieces.Queen;
import folder.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

public class Board {
  List<List<Piece>> board;
  ChessController controller;

  /**
   * Constructor for the board. Creates a 2D array of pieces, with the correct pieces in the correct
   * positions.
   */
  public Board(ChessController controller) {
    this.board = new ArrayList<List<Piece>>();
    this.controller = controller;
    for (int i = 0; i < ChessController.BOARD_SIZE; i++) {
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
  private List<Piece> createRow(int rowNumber) {
    List<Piece> row = new ArrayList<Piece>();

    // Adding major black pieces
    if (rowNumber == 0) {
      row.add(new Rook(Colour.BLACK, controller.bRookOne, 0, 0));
      row.add(new Knight(Colour.BLACK, controller.bKnightOne, 1, 0));
      row.add(new Bishop(Colour.BLACK, controller.bBishopOne, 2, 0));
      row.add(new Queen(Colour.BLACK, controller.bQueen, 3, 0));
      row.add(new King(Colour.BLACK, controller.bKing, 4, 0));
      row.add(new Bishop(Colour.BLACK, controller.bBishopTwo, 5, 0));
      row.add(new Knight(Colour.BLACK, controller.bKnightTwo, 6, 0));
      row.add(new Rook(Colour.BLACK, controller.bRookTwo, 7, 0));
      // Adding major white pieces
    } else if (rowNumber == 7) {
      row.add(new Rook(Colour.WHITE, controller.wRookOne, 0, 7));
      row.add(new Knight(Colour.WHITE, controller.wKnightOne, 1, 7));
      row.add(new Bishop(Colour.WHITE, controller.wBishopOne, 2, 7));
      row.add(new Queen(Colour.WHITE, controller.wQueen, 3, 7));
      row.add(new King(Colour.WHITE, controller.wKing, 4, 7));
      row.add(new Bishop(Colour.WHITE, controller.wBishopTwo, 5, 7));
      row.add(new Knight(Colour.WHITE, controller.wKnightTwo, 6, 7));
      row.add(new Rook(Colour.WHITE, controller.wRookTwo, 7, 7));
      // Adding black pawns
    } else if (rowNumber == 1) {
      row.add(new Pawn(Colour.BLACK, controller.bPawnOne, 0, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnTwo, 1, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnThree, 2, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnFour, 3, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnFive, 4, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnSix, 5, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnSeven, 6, 1));
      row.add(new Pawn(Colour.BLACK, controller.bPawnEight, 7, 1));
      // Adding white pawns
    } else if (rowNumber == 6) {
      row.add(new Pawn(Colour.WHITE, controller.wPawnOne, 0, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnTwo, 1, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnThree, 2, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnFour, 3, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnFive, 4, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnSix, 5, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnSeven, 6, 6));
      row.add(new Pawn(Colour.WHITE, controller.wPawnEight, 7, 6));
      // Adding empty tiles
    } else {
      for (int j = 0; j < ChessController.BOARD_SIZE; j++) {
        row.add(new Empty());
      }
    }
    return row;
  }

  public List<List<Piece>> getBoard() {
    return board;
  }

  /**
   * Method to create a clone of the board as not to affect the real board when simulating moves
   * when checking for checkmates.
   *
   * @return the cloned board.
   */
  public List<List<Piece>> cloneBoard() {
    List<List<Piece>> clone = new ArrayList<List<Piece>>();
    // Initialising the board clone
    for (int i = 0; i < ChessController.BOARD_SIZE; i++) {
      clone.add(new ArrayList<Piece>());
      for (int j = 0; j < ChessController.BOARD_SIZE; j++) {
        clone.get(i).add(new Empty());
      }
    }

    // Adds the appropriate pieces to the board clone
    for (Piece whitePiece : ChessController.whitePieces) {
      clone.get(whitePiece.getY()).set(whitePiece.getX(), whitePiece.copy(controller.dummyImage));
    }
    for (Piece blackPiece : ChessController.blackPieces) {
      clone.get(blackPiece.getY()).set(blackPiece.getX(), blackPiece.copy(controller.dummyImage));
    }
    return clone;
  }
}

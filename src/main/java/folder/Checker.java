package folder;

import folder.pieces.Empty;
import folder.pieces.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Checker {
  public List<Piece> checkingPieces;
  public ChessController controller;

  public Checker(ChessController controller) {
    this.checkingPieces = new ArrayList<Piece>();
    this.controller = controller;
  }

  /**
   * Method to check whether one side is in check or not. Method goes through all opposing pieces
   * and checks if any of them can move to the king's position. If at least one can, then it is a
   * check.
   *
   * @param colour The colour of the king to check.
   * @param boardToCheck The board to check for a check.
   */
  public int checkCheck(String colour, List<List<Piece>> boardToCheck) {
    ImageView king = colour.equals("white") ? controller.wKing : controller.bKing;
    int kingX = (int) (king.getLayoutX() - 100) / 50;
    int kingY = (int) king.getLayoutY() / 50;
    checkingPieces.clear();
    int checkingNumber = 0;

    for (Piece piece :
        colour.equals("white") ? ChessController.blackPieces : ChessController.whitePieces) {
      Set<int[]> potentialMoves = piece.moveSet(boardToCheck, false);
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
  public boolean checkCheckMate(String colour) {
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
    boolean canMove = false;

    // Finding the king of the given colour colour
    for (Piece piece :
        colour.equals("white") ? ChessController.whitePieces : ChessController.blackPieces) {
      if (piece.getType().equals("king")) {
        king = piece;
        potentialMoves = king.moveSet(ChessController.board.getBoard(), false);
        break;
      }
    }

    // Using a clone of the board without the king to find all tiles that would potentially be
    // attacked
    List<List<Piece>> boardClone = ChessController.board.cloneBoard();
    boardClone.get(king.getY()).set(king.getX(), new Empty());
    // Findind all the tiles that are being attacked by the opposite colour
    Set<int[]> attackedTiles = findAttackedTiles(colour, boardClone);

    // If the king can move to a tile that is not attacked by the opposite colour, then no checkmate
    for (int[] move : potentialMoves) {
      if (attackedTiles.stream().noneMatch(nullMove -> Arrays.equals(nullMove, move))) {
        canMove = true;
        System.out.println(
            "No checkmate because the king can move out of check to " + move[0] + ", " + move[1]);
      }
    }

    return !canMove;
  }

  /**
   * Checks if the path of a checking piece can be blocked to prevent the check. Only applicable for
   * checking pieces whose path can be blocked, those being the rook, bishop and queen.
   *
   * @param colour The colour of the king in check.
   * @return Whether the checking piece's path can be blocked.
   */
  private boolean checkPathBlock(String colour) {
    for (Piece piece :
        colour.equals("white") ? ChessController.whitePieces : ChessController.blackPieces) {
      Set<int[]> potentialMoves = piece.moveSet(ChessController.board.getBoard(), false);
      if (piece.getType().equals("king") || potentialMoves == null || potentialMoves.isEmpty()) {
        continue;
      }
      // Uses a simulation to check for check if a piece's path is blocked.
      for (int[] move : potentialMoves) {
        List<List<Piece>> boardClone = ChessController.board.cloneBoard();
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
    for (Piece piece :
        colour.equals("white") ? ChessController.whitePieces : ChessController.blackPieces) {
      Set<int[]> potentialMoves = piece.moveSet(ChessController.board.getBoard(), false);
      if (potentialMoves == null || potentialMoves.isEmpty()) {
        continue;
      }

      // Can't take the checking piece if the piece that can take it is the king and it will move
      // into check
      if (piece.getType().equals("king")
          && findAttackedTiles(colour, ChessController.board.getBoard()).stream()
              .anyMatch(c -> Arrays.equals(c, new int[] {checkingX, checkingY}))) {
        continue;
      }

      // If there is a single piece attacking the king and it can be taken, then no checkmate
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
   * Method to check whether a given colour is in stalemate or not. Does this by checking whether
   * the king can move or not. Can be used to check for all the different types of checks and mates.
   *
   * @param colour The colour of the king to check.
   * @param boardToCheck The board to check.
   * @return The set of attacked tiles.
   */
  public Set<int[]> findAttackedTiles(String colour, List<List<Piece>> boardToCheck) {
    Set<int[]> attackedTiles = new HashSet<int[]>();
    for (Piece piece :
        colour.equals("white") ? ChessController.blackPieces : ChessController.whitePieces) {
      Set<int[]> pieceMoves = piece.moveSet(boardToCheck, true);
      if (pieceMoves == null) {
        continue;
      }
      attackedTiles.addAll(pieceMoves);
    }
    return attackedTiles;
  }
}

package chess;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ArrayList<ArrayList<ChessPiece>> board;

    public ChessBoard() {
        // make a board filled with nulls (theoretically)
        this.board = new ArrayList<>();

        // add the rows. Rows are the inner collection for the outer board. Rows hold squares that can have pieces.
        for (int i = 0; i < 8; i++) {
            var row = new ArrayList<ChessPiece>();
            // add the columns for each row
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            board.add(row);
        }
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        // board indexes 0-7 but squares are indexed 1-8
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;
        board.get(row).set(col, piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;
        return board.get(row).get(col);
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // start by clearing the board of all pieces (reset to nulls)
        for (int i = 0; i < 8; i++) {
            var row = board.get(i);
            row.clear();
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
        }

        // then add the starting pieces for the two teams
        addPieces(ChessGame.TeamColor.WHITE);
        addPieces(ChessGame.TeamColor.BLACK);
    }

    // add the pieces for one team
    private void addPieces(ChessGame.TeamColor color) {
        // teams have different starting rows
        int back_rank, pawn_rank;
        if (color == ChessGame.TeamColor.WHITE) {
            back_rank = 1;
            pawn_rank = 2;
        } else { // if (color == ChessGame.TeamColor.BLACK)
            back_rank = 8;
            pawn_rank = 7;
        }

        // add the back rank
        addPiece(new ChessPosition(back_rank, 1), new ChessPiece(color, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(back_rank, 2), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(back_rank, 3), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(back_rank, 4), new ChessPiece(color, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(back_rank, 5), new ChessPiece(color, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(back_rank, 6), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(back_rank, 7), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(back_rank, 8), new ChessPiece(color, ChessPiece.PieceType.ROOK));

        // add the pawn rank
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(pawn_rank, i), new ChessPiece(color, ChessPiece.PieceType.PAWN));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}

package chess;

import java.util.ArrayList;

public interface PieceMovesCalculator {
    ArrayList<ChessMove> calculateMoves(ChessBoard board, ChessPosition position);

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        UP_RIGHT,
        UP_LEFT,
        DOWN_RIGHT,
        DOWN_LEFT
    }

    // find if a desired move is outside the chess board boundary
    default boolean outOfBounds(ChessMove move) {
        var end = move.getEndPosition();
        return end.getRow() < 1 || end.getRow() > 8 || end.getColumn() < 1 || end.getColumn() > 8;
    }

    // find if a desired move has a piece on it
    default boolean isOccupiedSquare(ChessBoard board, ChessMove move) {
        var end = move.getEndPosition();
        return board.getPiece(end) != null;
    }
}

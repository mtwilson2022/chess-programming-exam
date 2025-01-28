package chess;

import java.util.ArrayList;

public class KingMovesCalculator implements PieceMovesCalculator {
    @Override
    public ArrayList<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        var moves = new ArrayList<ChessMove>();

        int curr_row = position.getRow();
        int curr_col = position.getColumn();

        // kings have one move in each direction
        addMove(board, position, new ChessPosition(curr_row + 1, curr_col), moves); // UP
        addMove(board, position, new ChessPosition(curr_row - 1, curr_col), moves); // DOWN
        addMove(board, position, new ChessPosition(curr_row, curr_col + 1), moves); // RIGHT
        addMove(board, position, new ChessPosition(curr_row, curr_col - 1), moves); // LEFT
        addMove(board, position, new ChessPosition(curr_row + 1, curr_col + 1), moves); // UP_RIGHT
        addMove(board, position, new ChessPosition(curr_row + 1, curr_col - 1), moves); // UP_LEFT
        addMove(board, position, new ChessPosition(curr_row - 1, curr_col + 1), moves); // DOWN_RIGHT
        addMove(board, position, new ChessPosition(curr_row - 1, curr_col - 1), moves); // DOWN_LEFT

        return moves;
    }

    private void addMove(ChessBoard board, ChessPosition start,
                         ChessPosition end, ArrayList<ChessMove> moves) {

        ChessPiece king = board.getPiece(start);
        var move = new ChessMove(start, end, null);

        // only add the move if the following conditions apply
        if (!outOfBounds(move)) {
            if (board.getPiece(end) == null) {
                moves.add(move);
            } else if (board.getPiece(end).getTeamColor() != king.getTeamColor()) {
                moves.add(move);
            }
        }
    }
}

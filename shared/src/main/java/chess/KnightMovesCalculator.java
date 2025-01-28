package chess;

import java.util.ArrayList;

public class KnightMovesCalculator implements PieceMovesCalculator {
    @Override
    public ArrayList<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        var moves = new ArrayList<ChessMove>();

        int curr_row = position.getRow();
        int curr_col = position.getColumn();

        // knight moves similar to king moves (1 move in each direction, except directions are weird)
        addMove(board, position, new ChessPosition(curr_row + 2, curr_col + 1), moves); // UUR
        addMove(board, position, new ChessPosition(curr_row + 1, curr_col + 2), moves); // URR
        addMove(board, position, new ChessPosition(curr_row - 1, curr_col + 2), moves); // DRR
        addMove(board, position, new ChessPosition(curr_row - 2, curr_col + 1), moves); // DDR
        addMove(board, position, new ChessPosition(curr_row - 2, curr_col - 1), moves); // DDL
        addMove(board, position, new ChessPosition(curr_row - 1, curr_col - 2), moves); // DLL
        addMove(board, position, new ChessPosition(curr_row + 1, curr_col - 2), moves); // ULL
        addMove(board, position, new ChessPosition(curr_row + 2, curr_col - 1), moves); // UUL

        return moves;
    }

    private void addMove(ChessBoard board, ChessPosition start,
                         ChessPosition end, ArrayList<ChessMove> moves) {

        ChessPiece knight = board.getPiece(start);
        var move = new ChessMove(start, end, null);

        // only add the move if the following conditions apply
        if (!outOfBounds(move)) {
            if (board.getPiece(end) == null) {
                moves.add(move);
            } else if (board.getPiece(end).getTeamColor() != knight.getTeamColor()) {
                moves.add(move);
            }
        }
    }
}

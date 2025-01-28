package chess;

import java.util.ArrayList;

public class RookMovesCalculator implements PieceMovesCalculator {
    @Override
    public ArrayList<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        var moves = new ArrayList<ChessMove>();

        // add moves going in the 4 cardinal directions until rook reaches invalid square
        addMoves(board, position, Direction.UP, moves);
        addMoves(board, position, Direction.DOWN, moves);
        addMoves(board, position, Direction.RIGHT, moves);
        addMoves(board, position, Direction.LEFT, moves);

        return moves;
    }

    // add moves going in one direction
    private void addMoves(ChessBoard board, ChessPosition position,
                          PieceMovesCalculator.Direction dir, ArrayList<ChessMove> moves) {

        int curr_row = position.getRow();
        int curr_col = position.getColumn();
        ChessPiece rook = board.getPiece(position);

        // loop until you reach an invalid move
        int num_squares = 0; // number of squares to move
        while (true) {
            // make a potential move
            num_squares++;
            ChessPosition end_pos;
            if (dir == Direction.UP) {
                end_pos = new ChessPosition(curr_row + num_squares, curr_col);
            } else if (dir == Direction.DOWN) {
                end_pos = new ChessPosition(curr_row - num_squares, curr_col);
            } else if (dir == Direction.RIGHT) {
                end_pos = new ChessPosition(curr_row, curr_col + num_squares);
            } else { // if (dir == Direction.LEFT)
                end_pos = new ChessPosition(curr_row, curr_col - num_squares);
            }
            var move = new ChessMove(position, end_pos, null);

            // make sure it's valid and add the move
            if (outOfBounds(move)) {
                break;
            }
            if (isOccupiedSquare(board, move)) {
                if (board.getPiece(end_pos).getTeamColor() != rook.getTeamColor()) { // move square has enemy piece
                    moves.add(move); // only add the move if piece is opposite color
                }
                break; // can't move beyond that square

            } else {
                moves.add(move);
            }
        }
    }
}

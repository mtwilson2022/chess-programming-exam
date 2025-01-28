package chess;

import java.util.ArrayList;

public class PawnMovesCalculator implements PieceMovesCalculator {
    @Override
    public ArrayList<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        var moves = new ArrayList<ChessMove>();

        var pawn = board.getPiece(position);
        // pawns move in one direction only!!
        if (pawn.getTeamColor() == ChessGame.TeamColor.WHITE) {
            addPawnMovesWhite(board, position, moves);
        } else { // if (pawn.getTeamColor() == ChessGame.TeamColor.BLACK)
            addPawnMovesBlack(board, position, moves);
        }

        return moves;
    }

    private void addPawnMovesWhite(ChessBoard board, ChessPosition position, ArrayList<ChessMove> moves) {
        int curr_row = position.getRow();
        int curr_col = position.getColumn();

        // add forward square(s)
        var fwd_pos = new ChessPosition(curr_row + 1, curr_col);
        var fwd_move = new ChessMove(position, fwd_pos, null);
        if (!outOfBounds(fwd_move) && !isOccupiedSquare(board, fwd_move)) {
            // check for promotion
            if (curr_row == 7) {
                addPromotionMoves(position, fwd_pos, moves);
            } else {
                moves.add(fwd_move);
            }

            // after moving once forward, check conditions for moving a second square
            if (curr_row == 2) {
                var second_pos = new ChessPosition(curr_row + 2, curr_col);
                var second_move = new ChessMove(position, second_pos, null);
                if (!isOccupiedSquare(board, second_move)) {
                    moves.add(second_move);
                }
            }
        }

        // add right attack square
        var right_pos = new ChessPosition(curr_row + 1, curr_col + 1);
        var right_attack = new ChessMove(position, right_pos, null);
        if (!outOfBounds(right_attack)) {
            var piece = board.getPiece(right_pos);
            if (piece != null && piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                // check promotion
                if (curr_row == 7) {
                    addPromotionMoves(position, right_pos, moves);
                } else {
                    moves.add(right_attack);
                }
            }
        }

        // add left attack square
        var left_pos = new ChessPosition(curr_row + 1, curr_col - 1);
        var left_attack = new ChessMove(position, left_pos, null);
        if (!outOfBounds(left_attack)) {
            var piece = board.getPiece(left_pos);
            if (piece != null && piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                // check promotion
                if (curr_row == 7) {
                    addPromotionMoves(position, left_pos, moves);
                } else {
                    moves.add(left_attack);
                }
            }
        }
    }

    private void addPawnMovesBlack(ChessBoard board, ChessPosition position, ArrayList<ChessMove> moves) {
        int curr_row = position.getRow();
        int curr_col = position.getColumn();

        // add forward square(s)
        var fwd_pos = new ChessPosition(curr_row - 1, curr_col);
        var fwd_move = new ChessMove(position, fwd_pos, null);
        if (!outOfBounds(fwd_move) && !isOccupiedSquare(board, fwd_move)) {
            // check for promotion
            if (curr_row == 2) {
                addPromotionMoves(position, fwd_pos, moves);
            } else {
                moves.add(fwd_move);
            }

            // after moving once forward, check conditions for moving a second square
            if (curr_row == 7) {
                var second_pos = new ChessPosition(curr_row - 2, curr_col);
                var second_move = new ChessMove(position, second_pos, null);
                if (!isOccupiedSquare(board, second_move)) {
                    moves.add(second_move);
                }
            }
        }

        // add right attack square
        var right_pos = new ChessPosition(curr_row - 1, curr_col + 1);
        var right_attack = new ChessMove(position, right_pos, null);
        if (!outOfBounds(right_attack)) {
            var piece = board.getPiece(right_pos);
            if (piece != null && piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                // check promotion
                if (curr_row == 2) {
                    addPromotionMoves(position, right_pos, moves);
                } else {
                    moves.add(right_attack);
                }
            }
        }

        // add left attack square
        var left_pos = new ChessPosition(curr_row - 1, curr_col - 1);
        var left_attack = new ChessMove(position, left_pos, null);
        if (!outOfBounds(left_attack)) {
            var piece = board.getPiece(left_pos);
            if (piece != null && piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                // check promotion
                if (curr_row == 2) {
                    addPromotionMoves(position, left_pos, moves);
                } else {
                    moves.add(left_attack);
                }
            }
        }
    }

    // used only when a pawn is promoting
    private void addPromotionMoves(ChessPosition start, ChessPosition end, ArrayList<ChessMove> moves) {
        moves.add(new ChessMove(start, end, ChessPiece.PieceType.QUEEN));
        moves.add(new ChessMove(start, end, ChessPiece.PieceType.ROOK));
        moves.add(new ChessMove(start, end, ChessPiece.PieceType.KNIGHT));
        moves.add(new ChessMove(start, end, ChessPiece.PieceType.BISHOP));
    }
}

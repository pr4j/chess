package util;

import board.ChessBoard;
import board.Move;
import javafx.util.Pair;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class util {

    Set<Pair> a = new HashSet<>();

    public Set<Pair> pawnMoves(Pair<Integer, Integer> pos, ChessBoard CB, Move prevMove) {
        int row = pos.getKey();
        int col = pos.getValue();
        Set<Pair> moves = new HashSet<>();
        Piece pawn = CB.board[row][col].p;
        Piece prev = prevMove.p;

        boolean scenario1 = (CB.board[row + 1][col].p == null);
        boolean scenario2 = (pawn.first
                && CB.board[row + 1][col].p == null
                && CB.board[row + 2][col].p == null);
        boolean scenario3 = prev.getName() == "Pawn"
                && (((int)prevMove.start.getKey() - (int)prevMove.end.getKey()) == 2);
        boolean scenario4 = (CB.board[row - 1][col].p == null);
        boolean scenario5 = (pawn.first
                && CB.board[row - 1][col].p == null
                && CB.board[row - 2][col].p == null);
        boolean scenario6 = (prev.getName() == "Pawn"
                && (((int)prevMove.end.getKey() - (int)prevMove.start.getKey()) == 2));


        if(pawn.start==0) {
            if (scenario1) {
                moves.add(new Pair<>(row + 1, col));
            }
            if (scenario2) {
                moves.add(new Pair<>(row + 2, col));
            }
            if (scenario3 && (prevMove.start.equals(new Pair<>(6, col - 1)))) {
                moves.add(new Pair<>(row + 1, col - 1));
            }
            else if (scenario3 &&  prevMove.start.equals(new Pair<>(6, col + 1))){
                moves.add(new Pair<>(row + 1, col + 1));
            }
        }
        else{
            if (scenario4) {
                moves.add(new Pair<>(row - 1, col));
            }
            if (scenario5) {
                moves.add(new Pair<>(row - 2, col));
            }
            if (scenario6 && (prevMove.start.equals(new Pair<>(1, col - 1)))) {
                moves.add(new Pair<>(row - 1, col - 1));
            }
            else if (scenario6 &&  prevMove.start.equals(new Pair<>(1, col + 1))){
                moves.add(new Pair<>(row - 1, col + 1));
            }
        }
        return moves;
    }

    public Set<Pair> scan(Pair<Integer, Integer> pos, ChessBoard CB, Move prevMove) {
        Set<Pair> moves = pawnMoves( pos, CB, prevMove);
        return moves;
    }
}

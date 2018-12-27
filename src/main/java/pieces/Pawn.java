package pieces;

import board.ChessBoard;

public class Pawn extends Piece {
    public boolean first = true;

    public Pawn(int row, int column, int start) {
        super(row, column, start);
    }


    public boolean validateMove(int row1, int col1, int row2, int col2, ChessBoard CB) {
        int steps = row2 - row1;
        boolean scenario1 = (steps == 1 && CB.board[row1 + 1][col1].p == null);
        boolean scenario2 = (steps == 2 && this.first
                && CB.board[row1 + 1][col1].p == null
                && CB.board[row1 + 2][col1].p == null);
        boolean scenario3 = (col1 != col2 && steps == 1 && CB.board[row2][col2].p != null);
        //boolean scenario4 = (col1 != col2 && this.first)
        if (scenario3) {
            return false;
        } else if (scenario1 || scenario2) {
            this.first = false;
            return true;
        } else {
            return false;
        }
    }

    public String getName(){
        return "Pawn";
    }

    @Override
    public String toString() {
        return "Pawn" + "   [" + startString() + "]";
    }
}

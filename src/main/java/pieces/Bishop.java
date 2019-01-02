package Pieces;

public class Bishop extends Piece {

    public Bishop(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "Bishop";
    }

    @Override
    public String toString() {
        return "Bishop" + " [" + startString() + "]";
    }
}
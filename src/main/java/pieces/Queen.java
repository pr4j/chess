package Pieces;

public class Queen extends Piece {

    public Queen(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "Queen";
    }

    @Override
    public String toString() {
        return "Queen" + "  [" + startString() + "]";
    }
}
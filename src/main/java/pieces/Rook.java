package Pieces;

public class Rook extends Piece {

    public Rook(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "Rook";
    }

    @Override
    public String toString() {
        return "Rook" + "   [" + startString() + "]";
    }
}
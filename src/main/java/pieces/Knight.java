package Pieces;

public class Knight extends Piece {

    public Knight(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "Knight";
    }

    @Override
    public String toString() {
        return "Knight" + " [" + startString() + "]";
    }
}
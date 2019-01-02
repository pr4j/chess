package Pieces;

public class King extends Piece {

    public King(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "King";
    }

    @Override
    public String toString() {
        return "King" + "   [" + startString() + "]";
    }
}

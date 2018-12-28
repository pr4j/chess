package pieces;

public class Knight extends Piece {

    public Knight(int row, int column, int start) {
        super(row, column, start);
    }

    public int validateMove() {
        if (super.start == 0) {
        }
        return -1;
    }

    public String getName() {
        return "Knight";
    }

    @Override
    public String toString() {
        return "Knight" + " [" + startString() + "]";
    }
}
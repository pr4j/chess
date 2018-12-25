package pieces;

public class Bishop extends Piece {

    public Bishop(int row, int column, int start) {
        super(row, column, start);
    }

    public int validateMove() {
        if (super.start == 0) {
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Bishop" + " [" + startString() + "]";
    }
}
package pieces;

public class Queen extends Piece {

    public Queen(int row, int column, int start) {
        super(row, column, start);
    }

    public int validateMove() {
        if (super.start == 0) {
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Queen" + "  [" + startString() + "]";
    }
}
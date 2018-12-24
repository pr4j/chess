package pieces;

public class Rook extends Piece {

    public Rook(int row, int column, int start) {
        super(row, column, start);
    }

    public int validateMove() {
        if (super.start == 0) {
        }
        return -1;
    }
}
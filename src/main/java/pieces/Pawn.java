package pieces;

public class Pawn extends Piece {

    public Pawn(int row, int column, int start) {
        super(row, column, start);
    }


    public int validateMove() {
        if (super.start == 0) {

        }
        return -1;
    }

    @Override
    public String toString() {
        return "Pawn" + "   [" + startString() + "]";
    }
}

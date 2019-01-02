package Pieces;

public class Pawn extends Piece {
    public boolean first = true;

    public Pawn(int row, int column, int start) {
        super(row, column, start);
    }

    public String getName() {
        return "Pawn";
    }

    @Override
    public String toString() {
        return "Pawn" + "   [" + startString() + "]";
    }
}

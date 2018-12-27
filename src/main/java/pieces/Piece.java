package pieces;

public abstract class Piece {
    public int row;
    public int column;
    public int start;
    public boolean first = true;

    public Piece() {}

    public Piece(int row, int column, int start) {
        this.row = row;
        this.column = column;
        this.start = start;
    }

    public int validateMove() {
        return -1;
    }

    public String getName(){
        return "P";
    }

    String startString() {
        return (start == 0) ? "W" : "B";
    }
}

package board;

import pieces.Piece;

public class Cell {
    public Piece p;

    public Cell(Piece p) {
        this.p = p;
    }

    public void setP(Piece p) {
        this.p = p;
    }
}
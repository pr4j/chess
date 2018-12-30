package board;

import pieces.Piece;

public class Move {
    public Position start;
    public Position end;
    public Piece p;

    public Move(Position  start, Position  end, Piece p) {
        this.start = start;
        this.end = end;
        this.p = p;
    }
}

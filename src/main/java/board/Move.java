package board;

import javafx.util.Pair;
import pieces.Piece;

public class Move {
    public Pair start;
    public Pair end;
    public Piece p;

    public Move(Pair start, Pair end, Piece p) {
        this.start = start;
        this.end = end;
        this.p = p;
    }
}

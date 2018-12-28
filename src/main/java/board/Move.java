package board;

import javafx.util.Pair;
import pieces.Piece;

public class Move {
    public Pair<Integer, Integer> start;
    public Pair<Integer, Integer> end;
    public Piece p;

    public Move(Pair<Integer, Integer>  start, Pair<Integer, Integer>  end, Piece p) {
        this.start = start;
        this.end = end;
        this.p = p;
    }
}

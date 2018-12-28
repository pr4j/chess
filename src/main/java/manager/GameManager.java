package manager;

import board.Cell;
import board.ChessBoard;
import board.Move;
import javafx.util.Pair;
import pieces.Piece;
import util.Util;

import java.util.Iterator;
import java.util.Set;


public class GameManager {
    private Long turnCounter;
    Move move;
    //Set<Move> moves = new HashSet<>();

    private int turn = 0;
    ChessBoard CB;

    public GameManager() {
        this.CB = new ChessBoard();
        CB.setup();
        this.move = new Move(new Pair<>(0, 0), new Pair<>(0, 0), this.CB.board[1][0].p);
        CB.printBoard();
    }

    public void play(Pair<Integer, Integer> initPos, Pair<Integer, Integer> finPos) {
        int finRow = (int) finPos.getKey();
        int finCol = (int) finPos.getValue();

        if (areValidPositions(initPos, finPos)) {
            System.out.println(String.format("STUB: Valid init=%d,%d fin=%d,%d.", (int) initPos.getKey(),
                    (int) initPos.getValue(), (int) finPos.getKey(), (int) finPos.getValue()));
            updateBoard(initPos, finPos);
            this.move = new Move(initPos, finPos, this.CB.board[finRow][finCol].p);
        } else {
            System.err.println(String.format(" Log error: Invalid positions init=%d,%d fin=%d,%d.", (int) initPos.getKey(),
                    (int) initPos.getValue(), (int) finPos.getKey(), (int) finPos.getValue()));
        }
    }

    private boolean areValidPositions(Pair<Integer, Integer> initPos, Pair<Integer, Integer> finPos) {

        Set<Pair> validate = new Util().scan(initPos, this.CB, this.move);
        if(validate==null){
            return false;
        }
        for (Iterator<Pair> it = validate.iterator(); it.hasNext(); ) {
            Pair f = it.next();
            //System.out.println( f.getKey().toString() + f.getValue().toString());
        }
        return (validate.contains(finPos));
    }

    private void capture(Pair initPos, Pair finPos) {
        int initRow = (int) initPos.getKey();
        int initCol = (int) initPos.getValue();
        int finRow = (int) finPos.getKey();
        int finCol = (int) finPos.getValue();
        Piece current = CB.board[initRow][initCol].p;
        Piece prev = this.CB.board[(int) this.move.end.getKey()][(int) this.move.end.getValue()].p;
        if (initCol != finCol
                && prev.start!=current.start
                && prev.getName() == "Pawn"
                && current.getName() == "Pawn") {
            System.out.println();
            System.out.println(prev + ": captured");
            System.out.println();
            this.CB.board[(int) this.move.end.getKey()][(int) this.move.end.getValue()] = new Cell();
        }
        if (this.CB.board[finRow][finCol].p != null) {
            System.out.println();
            System.out.println(this.CB.board[finRow][finCol].p + "captured");
            System.out.println();
        }

    }

    private void updateBoard(Pair initPos, Pair finPos) {
        int initRow = (int) initPos.getKey();
        int initCol = (int) initPos.getValue();
        int finRow = (int) finPos.getKey();
        int finCol = (int) finPos.getValue();
        Piece init = this.CB.board[initRow][initCol].p;
        capture(initPos, finPos);

        this.CB.board[initRow][initCol] = new Cell();
        this.CB.board[finRow][finCol].p = init;
        this.CB.printBoard();
        //System.out.println(String.format("STUB: Board Updated init=%s fin=%s.", initPos, finPos));
    }
}

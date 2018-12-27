package manager;

import board.Cell;
import board.ChessBoard;
import board.Move;
import javafx.util.Pair;
import pieces.Piece;
import util.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class GameManager {
    private Long turnCounter;
    Move move ;
    //Set<Move> moves = new HashSet<>();

    private int turn = 0;
    ChessBoard CB;

    public GameManager() {
        this.CB = new ChessBoard();
        CB.setup();
        this.move = new Move(new Pair(1,0),new Pair(1,0),this.CB.board[1][0].p);
        CB.printBoard();
    }

    public void play(Pair initPos, Pair finPos) {
        int initRow = (int)initPos.getKey();
        int initCol = (int)initPos.getValue();
        int finRow = (int)finPos.getKey();
        int finCol = (int)finPos.getValue();

        if (areValidPositions(initPos, finPos)) {
            updateBoard(initPos, finPos);
            this.move = new Move(initPos,finPos,this.CB.board[finRow][finCol].p);
        } else {
            //System.err.println(String.format("STUB: Log error: Invalid positions init=%s fin=%s", initPos, finPos));
        }
    }

    private boolean areValidPositions(Pair initPos, Pair finPos) {
        System.out.println(String.format("STUB: Valid init=%d,%d fin=%d,%d.", (int)initPos.getKey(),
                (int)initPos.getValue(),(int)finPos.getKey(), (int)finPos.getValue()));
        Set<Pair> validate = new util().scan(initPos,this.CB,this.move);
        for (Iterator<Pair> it = validate.iterator(); it.hasNext(); ) {
            Pair f = it.next();
            //System.out.println( f.getKey().toString() + f.getValue().toString());
        }
        if(validate.contains(finPos)){
            return true;
        }
        else {
            return false;
        }
    }
    private void capture(Pair initPos, Pair finPos){
        int initRow = (int)initPos.getKey();
        int initCol = (int)initPos.getValue();
        int finRow = (int)finPos.getKey();
        int finCol = (int)finPos.getValue();
        Piece prev = this.CB.board[(int)this.move.end.getKey()][(int)this.move.end.getValue()].p;
        if(initCol!=finCol
                && prev.getName()=="Pawn"){
            System.out.println();
            System.out.println(prev + ": captured");
            System.out.println();
            this.CB.board[(int)this.move.end.getKey()][(int)this.move.end.getValue()] = new Cell();
        }
        if(this.CB.board[finRow][finCol].p!=null){
            System.out.println();
            System.out.println(this.CB.board[finRow][finCol].p + "captured");
            System.out.println();
        }

    }

    private void updateBoard(Pair initPos, Pair finPos) {
        int initRow = (int)initPos.getKey();
        int initCol = (int)initPos.getValue();
        int finRow = (int)finPos.getKey();
        int finCol = (int)finPos.getValue();
        Piece init = this.CB.board[initRow][initCol].p;
        capture(initPos,finPos);

        this.CB.board[initRow][initCol] = new Cell();
        this.CB.board[finRow][finCol].p = init;
        this.CB.printBoard();
        //System.out.println(String.format("STUB: Board Updated init=%s fin=%s.", initPos, finPos));
    }
}

package Manager;

import Board.Cell;
import Board.ChessBoard;
import Board.Move;
import Board.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Pieces.Piece;
import Util.Util;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class GameManager {
    private static final Logger log = LoggerFactory.getLogger(GameManager.class);

    public ChessBoard CB;

    Move move;
    //Set<Move> moves = new HashSet<>();

    private int turn = 0;
    private Long turnCounter;
    private Position whiteKing = new Position(0, 3);
    private Position blackKing = new Position(7, 3);
    //Set<Move> moves = new HashSet<>();

    public GameManager() {
        CB = new ChessBoard();
        CB.setup();
        move = new Move(new Position(0, 0), new Position(0, 0), CB.board[1][0].p);
        CB.printBoard();
    }

    public void play(Position initPos, Position finPos) {
        int finRow = finPos.x;
        int finCol = finPos.y;

        if (areValidPositions(initPos, finPos)) {
            log.debug("Valid positions init={} fin={}", initPos, finPos);
            updateBoard(initPos, finPos);
            this.move = new Move(initPos, finPos, CB.board[finRow][finCol].p);

            displayCheck(initPos, finPos);
            this.turn = (this.turn + 1) % 2;
        } else {
            log.error("Invalid positions init={} fin={}", initPos, finPos);
        }
    }

    private boolean areValidPositions(Position initPos, Position finPos) {

        Util u = new Util();
        Piece piece = this.CB.board[initPos.x][initPos.y].p;

        if (piece == null || this.turn != piece.start) return false;
        Set<Position> validate = new Util().scan(initPos, CB, move, 0);
        if (validate == null) {
            return false;
        }
        log.debug("All valid positions: [{}]", validate.stream().map(Position::toString).collect(joining(", ")));


        Set<Position> span = u.checkSameSide(initPos, finPos, this.CB, this.move);
        log.debug("All valid positions: [{}]", span.stream().map(Position::toString).collect(joining(", ")));

        boolean check;
        System.out.println(this.CB.board[this.move.end.x][this.move.end.y].p);
        if (piece.start == 0) check = span.contains(this.whiteKing);
        else check = span.contains(this.blackKing);
        return (validate.contains(finPos) && !check);
    }

    private void capture(Position initPos, Position finPos) {
        int initRow = initPos.x;
        int initCol = initPos.y;
        int finRow = finPos.x;
        int finCol = finPos.y;
        Piece current = CB.board[initRow][initCol].p;

        Piece prev = CB.board[move.end.x][move.end.y].p;
        //System.out.print(this.move.end.getKey());System.out.println(prev);
        if (initCol != finCol
                && prev.row != current.row
                && prev.start != current.start
                && prev.getName().equals("Pawn")
                && current.getName().equals("Pawn")) {
            System.out.println();
            System.out.println(prev + ": captured");
            System.out.println();
            CB.board[move.end.x][move.end.y] = new Cell();
        }
        if (CB.board[finRow][finCol].p != null) {
            System.out.println();
            System.out.println(CB.board[finRow][finCol].p + "captured");
            System.out.println();
        }
    }

    private void updateBoard(Position initPos, Position finPos) {
        int initRow = initPos.x;
        int initCol = initPos.y;
        int finRow = finPos.x;
        int finCol = finPos.y;

        if (this.CB.board[initRow][initCol].p.getName().equals("King")) {
            updateKing(this.CB.board[initRow][initCol].p.start, finRow, finCol);
        }

        Piece init = CB.board[initRow][initCol].p;
        capture(initPos, finPos);

        CB.board[initRow][initCol] = new Cell();
        CB.board[finRow][finCol].p = init;

        log.debug("Board Updated init={} fin={}", initPos, finPos);

        this.move = new Move(initPos, finPos, this.CB.board[finRow][finCol].p);
        this.CB.printBoard();
        System.out.println(String.format("STUB: Board Updated init=%s fin=%s.", initPos, finPos));

    }

    private void updateKing(int side, int finRow, int finCol) {
        if (side == 0) this.whiteKing = new Position(finRow, finCol);
        else this.blackKing = new Position(finRow, finCol);
    }

    private void displayCheck(Position initPos, Position finPos) {
        Util u = new Util();
        Set<Position> moves = u.checkOtherSide(initPos, finPos, this.CB, this.move);

        if (this.turn == 0 && moves.contains(this.blackKing)) System.out.println("Check to Black");
        else if (this.turn == 1 && moves.contains(this.whiteKing)) System.out.println("Check to White");

    }
}

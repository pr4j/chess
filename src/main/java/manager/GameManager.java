package manager;

import board.Cell;
import board.ChessBoard;
import board.Move;
import board.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pieces.Piece;
import util.Util;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class GameManager {
    private static final Logger log = LoggerFactory.getLogger(GameManager.class);

    ChessBoard CB;

    Move move;
    //Set<Move> moves = new HashSet<>();

    private int turn = 0;

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
            move = new Move(initPos, finPos, CB.board[finRow][finCol].p);
        } else {
            log.error("Invalid positions init={} fin={}", initPos, finPos);
        }
    }

    private boolean areValidPositions(Position initPos, Position finPos) {

        Set<Position> validate = new Util().scan(initPos, CB, move);
        if (validate == null) {
            return false;
        }

        log.debug("All valid positions: [{}]", validate.stream().map(Position::toString).collect(joining(", ")));

        return validate.contains(finPos);
    }

    private void capture(Position initPos, Position finPos) {
        int initRow = initPos.x;
        int initCol = initPos.y;
        int finRow = finPos.x;
        int finCol = finPos.y;
        Piece current = CB.board[initRow][initCol].p;
        Piece prev = CB.board[move.end.x][move.end.y].p;
        if (initCol != finCol
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

        Piece init = CB.board[initRow][initCol].p;
        capture(initPos, finPos);

        CB.board[initRow][initCol] = new Cell();
        CB.board[finRow][finCol].p = init;
        CB.printBoard();

        log.debug("Board Updated init={} fin={}", initPos, finPos);
    }
}

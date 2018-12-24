package manager;

import board.*;

public class GameManager {
    private Long turnCounter;

    private int turn = 0;

    public GameManager() {
        ChessBoard CB = new ChessBoard();
        CB.setup();
    }

    public void play(String initPos, String finPos) {
        if (areValidPositions(initPos, finPos)) {
            updateBoard(initPos, finPos);
        } else {
            System.err.println(String.format("STUB: Log error: Invalid positions init=%s fin=%s", initPos, finPos));
        }
    }

    private static boolean areValidPositions(String initPos, String finPos) {
        System.out.println(String.format("STUB: Valid init=%s fin=%s.", initPos, finPos));
        return true;
    }

    private void updateBoard(String initPos, String finPos) {
        System.out.println(String.format("STUB: Board Updated init=%s fin=%s.", initPos, finPos));
    }
}

package Board;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class ChessBoard {
    public Cell[][] board;

    public ChessBoard() {
        this.board = new Cell[8][8];
    }

    public void setupPawns() {
        for (int i = 1; i <= 1; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell c = new Cell(new Pawn(i, j, 0));
                this.board[i][j] = c;
            }
        }
        for (int i = 6; i <= 6; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell c = new Cell(new Pawn(i, j, 1));
                this.board[i][j] = c;
            }
        }
    }

    public void setupKing() {
        Cell k1 = new Cell(new King(0, 3, 0));
        Cell k2 = new Cell(new King(7, 3, 1));
        this.board[0][3] = k1;
        this.board[7][3] = k2;

    }

    public void setupQueen() {
        Cell q1 = new Cell(new Queen(0, 4, 0));
        Cell q2 = new Cell(new Queen(7, 4, 1));
        this.board[0][4] = q1;
        this.board[7][4] = q2;

    }

    public void setupRook() {
        Cell r1 = new Cell(new Rook(0, 0, 0));
        Cell r2 = new Cell(new Rook(0, 7, 0));
        Cell r3 = new Cell(new Rook(7, 0, 1));
        Cell r4 = new Cell(new Rook(7, 7, 1));
        this.board[0][0] = r1;
        this.board[0][7] = r2;
        this.board[7][0] = r3;
        this.board[7][7] = r4;
    }

    public void setupKnight() {
        Cell k1 = new Cell(new Knight(0, 1, 0));
        Cell k2 = new Cell(new Knight(0, 6, 0));
        Cell k3 = new Cell(new Knight(7, 1, 1));
        Cell k4 = new Cell(new Knight(7, 6, 1));
        this.board[0][1] = k1;
        this.board[0][6] = k2;
        this.board[7][1] = k3;
        this.board[7][6] = k4;

    }

    public void setupBishop() {
        Cell b1 = new Cell(new Bishop(0, 2, 0));
        Cell b2 = new Cell(new Bishop(0, 5, 0));
        Cell b3 = new Cell(new Bishop(7, 2, 1));
        Cell b4 = new Cell(new Bishop(7, 5, 1));
        this.board[0][2] = b1;
        this.board[0][5] = b2;
        this.board[7][2] = b3;
        this.board[7][5] = b4;

    }

    public void setup() {
        for(int i =0;i<8;i++){
            for(int j =0;j<8;j++){
                this.board[i][j] = new Cell();
            }
        }
        setupPawns();
        setupKing();
        setupQueen();
        setupRook();
        setupBishop();
        setupKnight();
    }

    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append(printRow(i));
        }
        return sb.toString();
    }

    private String printRow(int r) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            if (board[r][i].p != null) {
                sb.append(board[r][i].p + " ");
                System.out.print(board[r][i].p + " ");
            } else {
                sb.append("           ");
                System.out.print("           ");
            }
        }
        System.out.println();
        sb.append("\n");
        return sb.toString();
    }
}


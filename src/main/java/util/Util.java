package Util;

import Board.ChessBoard;
import Board.Move;
import Board.Position;
import javafx.util.Pair;
import Pieces.Piece;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Set;


import static java.lang.Math.abs;

public class Util {

    Set<Position> a = new HashSet<>();

    public Set<Pair> pawnCaptures(Pair<Integer, Integer> pos, ChessBoard CB, Move prevMove) {
        int row = pos.getKey();
        int col = pos.getValue();

        Set<Pair> moves = new HashSet<>();
        Piece pawn = CB.board[row][col].p;

        if (pawn.start == 0) {
            if (row + 1 < 8 && col + 1 < 8 && CB.board[row + 1][col + 1].p != null && CB.board[row + 1][col + 1].p.start == 1)
                moves.add(new Pair<>(row + 1, col + 1));
            if (row + 1 < 8 && col - 1 > -1 && CB.board[row + 1][col - 1].p != null && CB.board[row + 1][col - 1].p.start == 1)
                moves.add(new Pair<>(row + 1, col - 1));
        } else {
            //System.out.print("st" + row);System.out.print(col);System.out.print("   ");
            if (row - 1 > -1 && col + 1 < 8 && CB.board[row - 1][col + 1].p != null && CB.board[row - 1][col + 1].p.start == 0)
                moves.add(new Pair<>(row - 1, col + 1));
            if (row - 1 > -1 && col - 1 > -1 && CB.board[row - 1][col - 1].p != null && CB.board[row - 1][col - 1].p.start == 0)
                moves.add(new Pair<>(row - 1, col - 1));
        }
        for (Iterator<Pair> it = moves.iterator(); it.hasNext(); ) {
            Pair f = it.next();
            //System.out.print( f.getKey().toString() + f.getValue().toString() + "  ");
        }
        //System.out.println();
        return moves;
    }


    public Set<Position> pawnMoves(Position pos, ChessBoard CB, Move prevMove) {
        int row = pos.x;
        int col = pos.y;
        Set<Position> moves = new HashSet<>();
        Piece pawn = CB.board[row][col].p;
        Piece prev = prevMove.p;

        boolean scenario1 = (CB.board[row + 1][col].p == null);
        boolean scenario2 = (pawn.first
                && CB.board[row + 1][col].p == null
                && CB.board[row + 2][col].p == null);
        boolean scenario3 = prev.getName() == "Pawn"
                && (((int) prevMove.start.x - (int) prevMove.end.x) == 2);
        boolean scenario4 = (CB.board[row - 1][col].p == null);
        boolean scenario5 = (pawn.first && pawn.start == 1
                && CB.board[row - 1][col].p == null
                && CB.board[row - 2][col].p == null);
        boolean scenario6 = (prev.getName() == "Pawn"
                && (((int) prevMove.end.x - (int) prevMove.start.x) == 2));



        if (pawn.start == 0) {
            if (scenario1) {
                moves.add(new Position(row + 1, col));
            }
            if (scenario2) {
                moves.add(new Position(row + 2, col));
            }
            if (scenario3 && (prevMove.start.equals(new Position(6, col - 1)))) {
                moves.add(new Position(row + 1, col - 1));
            } else if (scenario3 && prevMove.start.equals(new Position(6, col + 1))) {
                moves.add(new Position(row + 1, col + 1));
            }
        } else {
            if (scenario4) {
                moves.add(new Position(row - 1, col));
            }
            if (scenario5) {
                moves.add(new Position(row - 2, col));
            }
            if (scenario6 && (prevMove.start.equals(new Position(1, col - 1)))) {
                moves.add(new Position(row - 1, col - 1));
            } else if (scenario6 && prevMove.start.equals(new Position(1, col + 1))) {
                moves.add(new Position(row - 1, col + 1));
            }
        }
        return moves;
    }

    public Set<Position> rookMoves(Position pos, ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<Position>();
        int row = pos.x;
        int col = pos.y;

        for (int i = col + 1; i < 8; i++) {
            if (CB.board[row][i].p == null) {

                moves.add(new Position(row, i));
            } else if (CB.board[row][i].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(row, i));
                break;
            } else {
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (CB.board[row][i].p == null) {
                moves.add(new Position(row, i));
            } else if (CB.board[row][i].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(row, i));
                break;
            } else {
                break;
            }
        }

        for (int i = row + 1; i < 8; i++) {
            if (CB.board[i][col].p == null) {
                moves.add(new Position(i, col));
            } else if (CB.board[i][col].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, col));
                break;
            } else {
                break;
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            if (CB.board[i][col].p == null) {
                moves.add(new Position(i, col));
            } else if (CB.board[i][col].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, col));
                break;
            } else {
                break;
            }
        }
        return moves;
    }

    public Set<Position> bishopMoves(Position pos, ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<>();
        int row = pos.x;
        int col = pos.y;


        for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
            if (CB.board[i][j].p == null) {
                moves.add(new Position(i, j));
            } else if (CB.board[i][j].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i > -1 && j > -1; i--, j--) {
            if (CB.board[i][j].p == null) {
                moves.add(new Position(i, j));
            } else if (CB.board[i][j].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col + 1; i > -1 && j < 8; i--, j++) {
            if (CB.board[i][j].p == null) {
                moves.add(new Position(i, j));
            } else if (CB.board[i][j].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }
        for (int i = row + 1, j = col - 1; i < 8 && j > -1; i++, j--) {
            if (CB.board[i][j].p == null) {
                moves.add(new Position(i, j));
            } else if (CB.board[i][j].p.start != CB.board[row][col].p.start) {
                moves.add(new Position(i, j));
                break;
            } else {
                break;
            }
        }

        return moves;
    }

    public Set<Position> knightMoves(Position pos, ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<>();
        int row = pos.x;
        int col = pos.y;

        for (int i = 2; i > -3; i--) {
            if (i == 0) continue;
            for (int j = 2; j > -3; j--) {

                if ((abs(i) == abs(j)) || j == 0 || (row + i > 7 || row + i < 0 || col + j > 7 || col + j < 0))
                    continue;
                //System.out.println(i);System.out.println(j);
                if (CB.board[row + i][col + j].p == null) {
                    moves.add(new Position(row + i, col + j));
                } else if (CB.board[row + i][col + j].p.start != CB.board[row][col].p.start) {
                    moves.add(new Position(row + i, col + j));
                } else {
                    continue;

                }
            }
        }
        return moves;

    }
    public Set<Position> kingMoves(Position pos, ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<>();

        int row = pos.x;
        int col = pos.y;
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if ((j == 0 && i == 0) || (row + i > 7 || row + i < 0 || col + j > 7 || col + j < 0)) continue;
                //System.out.println(i);System.out.println(j);
                if (CB.board[row + i][col + j].p == null) {
                    moves.add(new Position(row + i, col + j));
                } else if (CB.board[row + i][col + j].p.start != CB.board[row][col].p.start) {
                    moves.add(new Position(row + i, col + j));
                }
            }
        }
        return moves;
    }


    public Set<Position> scan(Position pos, ChessBoard CB, Move prevMove) {
        Set<Position> moves;
        if (CB.board[pos.x][pos.y].p == null) {
            moves = null;
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Pawn")) {
            moves = pawnMoves(pos, CB, prevMove);
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Rook")) {
            moves = rookMoves(pos, CB, prevMove);
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Bishop")) {
            moves = bishopMoves(pos, CB, prevMove);
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Knight")) {
            moves = knightMoves(pos, CB, prevMove);
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Queen")) {
            moves = rookMoves(pos, CB, prevMove);
            moves.addAll(bishopMoves(pos, CB, prevMove));
        } else if (CB.board[pos.x][pos.y].p.getName().equals("Queen")) {
            moves = kingMoves(pos, CB, prevMove);

        } else {
            moves = null;
        }
        return moves;
    }


    public Set<Position> checkSameSide(Position initPos, Position finPos,
                                   ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<>();
        int initRow = initPos.x;
        int initCol = initPos.y;
        int finRow = finPos.x;
        int finCol = finPos.y;
        Piece init = CB.board[initRow][initCol].p;

        CB.board[initRow][initCol].p = null;
        Piece end = CB.board[finRow][finCol].p;
        CB.board[finRow][finCol].p = init;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (CB.board[i][j].p == null) continue;
                if (init.start == CB.board[i][j].p.start) continue;
                int row = CB.board[i][j].p.row;
                int col = CB.board[i][j].p.column;
                Position Pos = new Position(row, col);
                Set<Position> temp = scan(Pos, CB, prevMove);
                moves.addAll(temp);
            }
        }
        CB.board[initRow][initCol].p = init;
        CB.board[finRow][finCol].p = end;
        return moves;
    }

    public Set<Position> checkOtherSide(Position initPos, Position finPos,
                                    ChessBoard CB, Move prevMove) {
        Set<Position> moves = new HashSet<>();
        int initRow = initPos.x;
        int initCol = initPos.y;
        int finRow = finPos.x;
        int finCol = finPos.y;

        Piece p = CB.board[finRow][finCol].p;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (CB.board[i][j].p == null || p == null) continue;
                if (p.start != CB.board[i][j].p.start) continue;
                //System.out.println(CB.board[i][j].p);
                int row = CB.board[i][j].p.row;
                int col = CB.board[i][j].p.column;
                //System.out.print(row);System.out.println(col);
                Position Pos = new Position(row, col);
                Set<Position> temp = scan(Pos, CB, prevMove);
                moves.addAll(temp);
            }
        }
        return moves;
    }

}

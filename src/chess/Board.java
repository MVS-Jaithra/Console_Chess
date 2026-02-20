package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Board {
    public Piece[][] board;

    private int whiteTime = 600; // 10 minutes
    private int blackTime = 600;

    private PieceColor currentTurn = PieceColor.WHITE;

    private Timer gameTimer;
    private boolean gameOver = false;

    public Board() {
        board = new Piece[8][8];
        setupPieces();
        startTimer(); // start background timer
    }

    private void setupPieces() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.WHITE, new Position(1, i));
            board[6][i] = new Pawn(PieceColor.BLACK, new Position(6, i));
        }

        board[0][0] = new Rook(PieceColor.WHITE, new Position(0, 0));
        board[0][7] = new Rook(PieceColor.WHITE, new Position(0, 7));
        board[7][0] = new Rook(PieceColor.BLACK, new Position(7, 0));
        board[7][7] = new Rook(PieceColor.BLACK, new Position(7, 7));

        board[0][1] = new Knight(PieceColor.WHITE, new Position(0, 1));
        board[0][6] = new Knight(PieceColor.WHITE, new Position(0, 6));
        board[7][1] = new Knight(PieceColor.BLACK, new Position(7, 1));
        board[7][6] = new Knight(PieceColor.BLACK, new Position(7, 6));

        board[0][2] = new Bishop(PieceColor.WHITE, new Position(0, 2));
        board[0][5] = new Bishop(PieceColor.WHITE, new Position(0, 5));
        board[7][2] = new Bishop(PieceColor.BLACK, new Position(7, 2));
        board[7][5] = new Bishop(PieceColor.BLACK, new Position(7, 5));

        board[0][3] = new Queen(PieceColor.WHITE, new Position(0, 3));
        board[7][3] = new Queen(PieceColor.BLACK, new Position(7, 3));

        board[0][4] = new King(PieceColor.WHITE, new Position(0, 4));
        board[7][4] = new King(PieceColor.BLACK, new Position(7, 4));
    }

    public void printBoard() {
        System.out.println("\nTime: White " + formatTime(whiteTime) + " | Black " + formatTime(blackTime));
        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) System.out.print(". ");
                else System.out.print(board[row][col].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public boolean move(Position from, Position to, PieceColor turn) {
        if (gameOver) return false;

        Piece piece = getPieceAt(from);
        if (piece == null || piece.getColor() != turn) return false;
        if (!piece.isValidMove(to, this)) return false;

        Piece target = getPieceAt(to);
        board[to.getRow()][to.getCol()] = piece;
        board[from.getRow()][from.getCol()] = null;
        piece.setPosition(to);

        if (isKingInCheck(turn)) {
            board[from.getRow()][from.getCol()] = piece;
            board[to.getRow()][to.getCol()] = target;
            piece.setPosition(from);
            return false;
        }

        switchTurn();
        return true;
    }

    public Piece getPieceAt(Position pos) {
        if (pos.getRow() < 0 || pos.getRow() >= 8 || pos.getCol() < 0 || pos.getCol() >= 8)
            return null;
        return board[pos.getRow()][pos.getCol()];
    }

    public boolean isKingInCheck(PieceColor color) {
        Position kingPos = findKing(color);
        if (kingPos == null) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p != null && p.getColor() != color && p.isValidMove(kingPos, this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Piece> getAttackingPieces(PieceColor color) {
        List<Piece> attackers = new ArrayList<>();
        Position kingPos = findKing(color);
        if (kingPos == null) return attackers;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p != null && p.getColor() != color && p.isValidMove(kingPos, this)) {
                    attackers.add(p);
                }
            }
        }
        return attackers;
    }

    public boolean isCheckmate(PieceColor color) {
        if (!isKingInCheck(color)) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p != null && p.getColor() == color) {
                    List<Position> moves = p.getAllPossibleMoves(this);
                    for (Position move : moves) {
                        Piece captured = getPieceAt(move);
                        Position from = p.getPosition();

                        board[move.getRow()][move.getCol()] = p;
                        board[from.getRow()][from.getCol()] = null;
                        p.setPosition(move);

                        boolean stillInCheck = isKingInCheck(color);

                        board[from.getRow()][from.getCol()] = p;
                        board[move.getRow()][move.getCol()] = captured;
                        p.setPosition(from);

                        if (!stillInCheck) return false;
                    }
                }
            }
        }

        gameOver = true;
        stopTimer();
        return true;
    }

    private Position findKing(PieceColor color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p instanceof King && p.getColor() == color)
                    return p.getPosition();
            }
        }
        return null;
    }

    // Timer decrements in background without printing each second
    private void startTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameOver) return;

                if (currentTurn == PieceColor.WHITE) {
                    whiteTime--;
                    if (whiteTime <= 0) {
                        gameOver = true;
                        System.out.println("\nWhite ran out of time! Black wins!");
                        stopTimer();
                    }
                } else {
                    blackTime--;
                    if (blackTime <= 0) {
                        gameOver = true;
                        System.out.println("\nBlack ran out of time! White wins!");
                        stopTimer();
                    }
                }
            }
        }, 1000, 1000);
    }

    private void switchTurn() {
        currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    public void stopTimer() {
        if (gameTimer != null) gameTimer.cancel();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWhiteTime() {
        return whiteTime;
    }

    public int getBlackTime() {
        return blackTime;
    }
}
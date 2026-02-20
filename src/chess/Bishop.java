package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) { super(color, position); }

    @Override
    public boolean isValidMove(Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - position.getRow());
        int colDiff = Math.abs(to.getCol() - position.getCol());
        if (rowDiff != colDiff || rowDiff == 0) return false;

        int rowStep = (to.getRow() - position.getRow()) / rowDiff;
        int colStep = (to.getCol() - position.getCol()) / colDiff;
        int r = position.getRow() + rowStep;
        int c = position.getCol() + colStep;

        while (r != to.getRow() && c != to.getCol()) {
            if (board.getPieceAt(new Position(r, c)) != null) return false;
            r += rowStep;
            c += colStep;
        }

        Piece target = board.getPieceAt(to);
        return target == null || target.getColor() != color;
    }

    @Override
    public List<Position> getAllPossibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position p = new Position(r, c);
                if (isValidMove(p, board)) moves.add(p);
            }
        }
        return moves;
    }

    @Override
    public char getSymbol() { return (color == PieceColor.WHITE) ? 'B' : 'b'; }
}
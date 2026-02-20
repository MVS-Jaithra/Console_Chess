
package chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor color, Position position) { super(color, position); }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (position.equals(to)) return false;
        if (position.getRow() != to.getRow() && position.getCol() != to.getCol()) return false;

        int rowStep = Integer.compare(to.getRow(), position.getRow());
        int colStep = Integer.compare(to.getCol(), position.getCol());

        int r = position.getRow() + rowStep;
        int c = position.getCol() + colStep;
        while (r != to.getRow() || c != to.getCol()) {
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
    public char getSymbol() { return (color == PieceColor.WHITE) ? 'R' : 'r'; }
}

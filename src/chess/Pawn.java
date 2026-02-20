
package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        int dir = (color == PieceColor.WHITE) ? 1 : -1;
        int startRow = (color == PieceColor.WHITE) ? 1 : 6;

        int rowDiff = to.getRow() - position.getRow();
        int colDiff = to.getCol() - position.getCol();
        Piece target = board.getPieceAt(to);

        // Move forward
        if (colDiff == 0) {
            if (rowDiff == dir && target == null) return true;
            if (position.getRow() == startRow && rowDiff == 2 * dir && target == null &&
                    board.getPieceAt(new Position(position.getRow() + dir, position.getCol())) == null) return true;
        }
        // Capture diagonally
        if (Math.abs(colDiff) == 1 && rowDiff == dir && target != null && target.getColor() != color) return true;

        return false;
    }

    @Override
    public List<Position> getAllPossibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position to = new Position(r, c);
                if (isValidMove(to, board)) moves.add(to);
            }
        }
        return moves;
    }

    @Override
    public char getSymbol() {
        return (color == PieceColor.WHITE) ? 'P' : 'p';
    }
}

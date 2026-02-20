
package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(PieceColor color, Position position) { super(color, position); }

    @Override
    public boolean isValidMove(Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - position.getRow());
        int colDiff = Math.abs(to.getCol() - position.getCol());
        if (rowDiff > 1 || colDiff > 1) return false;

        Piece target = board.getPieceAt(to);
        if (target != null && target.getColor() == color) return false;

        // Temporarily move the king to see if it is in check
        Position original = this.position;
        Piece captured = board.getPieceAt(to);
        board.board[to.getRow()][to.getCol()] = this;
        board.board[original.getRow()][original.getCol()] = null;
        this.position = to;
        boolean inCheck = board.isKingInCheck(color);
        // Undo
        board.board[original.getRow()][original.getCol()] = this;
        board.board[to.getRow()][to.getCol()] = captured;
        this.position = original;

        return !inCheck;
    }

    @Override
    public List<Position> getAllPossibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        for (int r = position.getRow() - 1; r <= position.getRow() + 1; r++)
            for (int c = position.getCol() - 1; c <= position.getCol() + 1; c++) {
                Position p = new Position(r, c);
                if (r >= 0 && r < 8 && c >= 0 && c < 8 && isValidMove(p, board))
                    moves.add(p);
            }
        return moves;
    }

    @Override
    public char getSymbol() { return (color == PieceColor.WHITE) ? 'K' : 'k'; }
}

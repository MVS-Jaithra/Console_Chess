
package chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position) { super(color, position); }

    @Override
    public boolean isValidMove(Position to, Board board) {
        // Combine Rook + Bishop logic
        Rook rookPart = new Rook(color, position);
        Bishop bishopPart = new Bishop(color, position);
        return rookPart.isValidMove(to, board) || bishopPart.isValidMove(to, board);
    }

    @Override
    public List<Position> getAllPossibleMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                if (isValidMove(new Position(r, c), board))
                    moves.add(new Position(r, c));
        return moves;
    }

    @Override
    public char getSymbol() { return (color == PieceColor.WHITE) ? 'Q' : 'q'; }
}

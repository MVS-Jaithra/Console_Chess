package chess;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(color);
    }

    public char getSymbol() {
        return color == PieceColor.WHITE ? 'P' : 'p';
    }
    public boolean isValidMove(Board board, Position from, Position to) {
        int dir = (getColor() == PieceColor.WHITE) ? -1 : 1;
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        Piece target = board.getPiece(to);

        // Forward move
        if (colDiff == 0) {
            if (rowDiff == dir && target == null) return true;
            // Double move from initial position
            if ((getColor() == PieceColor.WHITE && from.getRow() == 6 ||
                    getColor() == PieceColor.BLACK && from.getRow() == 1)
                    && rowDiff == 2 * dir && target == null) {
                // Check path clear
                int midRow = from.getRow() + dir;
                if (board.getPiece(new Position(midRow, from.getCol())) == null)
                    return true;
            }
        }

        // Capture diagonally
        if (Math.abs(colDiff) == 1 && rowDiff == dir && target != null && target.getColor() != getColor()) {
            return true;
        }

        return false;
    }
}

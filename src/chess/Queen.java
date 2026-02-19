package chess;

public class Queen extends Piece {
    public Queen(PieceColor color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (to == null) return false;
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());
        Piece target = board.getPiece(to);

        boolean diagonal = rowDiff == colDiff;
        boolean straight = from.getRow() == to.getRow() || from.getCol() == to.getCol();
        if (!diagonal && !straight) return false;
        return target == null || target.getColor() != getColor();
    }

    @Override
    public char getSymbol() { return (getColor() == PieceColor.WHITE) ? 'Q' : 'q'; }
}

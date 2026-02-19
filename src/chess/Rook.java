package chess;

public class Rook extends Piece {
    public Rook(PieceColor color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (to == null) return false;
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());
        Piece target = board.getPiece(to);
        if (rowDiff != 0 && colDiff != 0) return false;
        return target == null || target.getColor() != getColor();
    }

    @Override
    public char getSymbol() { return (getColor() == PieceColor.WHITE) ? 'R' : 'r'; }
}

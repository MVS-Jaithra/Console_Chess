package chess;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    public char getSymbol() {
        return color == PieceColor.WHITE ? 'N' : 'n';
    }
    public boolean isValidMove(Board board, Position from, Position to) {
        if (to == null) return false;   // <-- prevent NPE
        boolean diagonal =
                Math.abs(from.getRow() - to.getRow()) ==
                        Math.abs(from.getCol() - to.getCol());
        boolean straight =
                from.getRow() == to.getRow() ||
                        from.getCol() == to.getCol();

        if (diagonal || straight)
            return board.isPathClear(from, to);

        return false;
    }
}


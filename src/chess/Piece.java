package chess;

public abstract class Piece {
    protected PieceColor color;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract char getSymbol();

    public abstract boolean isValidMove(Board board,
                                        Position from,
                                        Position to);
}

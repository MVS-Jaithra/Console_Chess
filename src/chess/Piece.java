
package chess;

import java.util.List;

public abstract class Piece {
    protected PieceColor color;
    protected Position position;

    public Piece(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public PieceColor getColor() { return color; }
    public Position getPosition() { return position; }
    public void setPosition(Position pos) { this.position = pos; }

    public abstract boolean isValidMove(Position to, Board board);
    public abstract List<Position> getAllPossibleMoves(Board board);
    public abstract char getSymbol();
}


package chess;

public class Board {

    private Piece[][] grid = new Piece[8][8];

    public Board() { initialize(); }

    private void initialize() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Pawn(PieceColor.WHITE);
            grid[1][i] = new Pawn(PieceColor.BLACK);
        }

        // Rooks
        grid[7][0] = new Rook(PieceColor.WHITE);
        grid[7][7] = new Rook(PieceColor.WHITE);
        grid[0][0] = new Rook(PieceColor.BLACK);
        grid[0][7] = new Rook(PieceColor.BLACK);

        // Knights
        grid[7][1] = new Knight(PieceColor.WHITE);
        grid[7][6] = new Knight(PieceColor.WHITE);
        grid[0][1] = new Knight(PieceColor.BLACK);
        grid[0][6] = new Knight(PieceColor.BLACK);

        // Bishops
        grid[7][2] = new Bishop(PieceColor.WHITE);
        grid[7][5] = new Bishop(PieceColor.WHITE);
        grid[0][2] = new Bishop(PieceColor.BLACK);
        grid[0][5] = new Bishop(PieceColor.BLACK);

        // Queens
        grid[7][3] = new Queen(PieceColor.WHITE);
        grid[0][3] = new Queen(PieceColor.BLACK);

        // Kings
        grid[7][4] = new King(PieceColor.WHITE);
        grid[0][4] = new King(PieceColor.BLACK);
    }

    public Piece getPiece(Position pos) {
        return grid[pos.getRow()][pos.getCol()];
    }

    public void move(Position from, Position to, PieceColor turn) {
        Piece piece = getPiece(from);
        if (piece == null) { System.out.println("No piece there!"); return; }
        if (piece.getColor() != turn) { System.out.println("Wrong turn!"); return; }
        if (!piece.isValidMove(this, from, to)) { System.out.println("Invalid move!"); return; }

        Piece target = getPiece(to);
        if (target != null && target.getColor() == turn) { System.out.println("Cannot capture own piece!"); return; }

        grid[to.getRow()][to.getCol()] = piece;
        grid[from.getRow()][from.getCol()] = null;
    }

    public boolean isPathClear(Position from, Position to) {
        int rowStep = Integer.compare(to.getRow(), from.getRow());
        int colStep = Integer.compare(to.getCol(), from.getCol());
        int r = from.getRow() + rowStep;
        int c = from.getCol() + colStep;
        while (r != to.getRow() || c != to.getCol()) {
            if (grid[r][c] != null) return false;
            r += rowStep; c += colStep;
        }
        return true;
    }

    public Position findKing(PieceColor color) {
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p instanceof King && p.getColor() == color) return new Position(r, c);
            }
        return null;
    }

    public boolean isKingInCheck(PieceColor color) {
        Position kingPos = findKing(color);
        PieceColor opponent = (color == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.getColor() == opponent) {
                    if (p.isValidMove(this, new Position(r, c), kingPos)) {
                        if (!(p instanceof Knight) && !isPathClear(new Position(r, c), kingPos)) continue;
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean isCheckmate(PieceColor color) {
        if (!isKingInCheck(color)) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.getColor() == color) {
                    Position from = new Position(r, c);
                    for (int tr = 0; tr < 8; tr++) {
                        for (int tc = 0; tc < 8; tc++) {
                            Position to = new Position(tr, tc);
                            if (!p.isValidMove(this, from, to)) continue;
                            if (!(p instanceof Knight) && !isPathClear(from, to)) continue;

                            Piece captured = grid[tr][tc];
                            grid[tr][tc] = p;
                            grid[r][c] = null;

                            boolean stillInCheck = isKingInCheck(color);

                            grid[r][c] = p;
                            grid[tr][tc] = captured;

                            if (!stillInCheck) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("\n  a b c d e f g h");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                if (grid[r][c] == null) System.out.print(". ");
                else System.out.print(grid[r][c].getSymbol() + " ");
            }
            System.out.println();
        }
    }
}

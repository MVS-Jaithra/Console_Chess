
package chess;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public void setRow(int row) { this.row = row; }
    public void setCol(int col) { this.col = col; }

    public static Position fromInput(String input) {
        if (input.length() != 2) return null;
        char file = input.charAt(0);
        char rank = input.charAt(1);
        int col = file - 'a';
        int row = rank - '1';
        if (col < 0 || col > 7 || row < 0 || row > 7) return null;
        return new Position(row, col);
    }

    @Override
    public String toString() {
        return "" + (char)('a' + col) + (row + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return false;
        Position p = (Position) obj;
        return this.row == p.row && this.col == p.col;
    }
}

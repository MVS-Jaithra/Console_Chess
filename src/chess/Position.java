package chess;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public static Position fromInput(String input) {
        if (input == null || input.length() != 2) return null;
        int col = input.charAt(0) - 'a';
        int row = 8 - Character.getNumericValue(input.charAt(1));
        if (row < 0 || row > 7 || col < 0 || col > 7) return null;
        return new Position(row, col);
    }


    public int getRow() { return row; }
    public int getCol() { return col; }
}

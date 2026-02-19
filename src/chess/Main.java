package chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        PieceColor turn = PieceColor.WHITE;

        while (true) {
            board.printBoard();
            System.out.println(turn + " move (e2 e4):");
            String fromInput = scanner.next();
            String toInput = scanner.next();

            Position from = Position.fromInput(fromInput);
            Position to = Position.fromInput(toInput);

            if (from == null || to == null) {
                System.out.println("Invalid input! Use e2 e4 format.");
                continue;
            }

            board.move(from, to, turn);

            PieceColor opponent = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;

            if (board.isKingInCheck(opponent)) {
                if (board.isCheckmate(opponent)) {
                    System.out.println("CHECKMATE! " + turn + " WINS!");
                    board.printBoard();
                    break;
                } else {
                    System.out.println(opponent + " is in CHECK!");
                }
            }

            turn = opponent;
        }
    }
}

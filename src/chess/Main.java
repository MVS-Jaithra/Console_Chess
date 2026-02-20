package chess;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        PieceColor turn = PieceColor.WHITE;

        while (true) {
            if (board.isGameOver()) {
                System.out.println("\nGame Over!");
                break;
            }

            board.printBoard(); // Timer printed with board
            System.out.println(turn + " move (e2 e4):");

            String fromInput = scanner.next();
            String toInput = scanner.next();

            Position from = Position.fromInput(fromInput);
            Position to = Position.fromInput(toInput);

            if (from == null || to == null) {
                System.out.println("Invalid input! Use e2 e4 format.");
                continue;
            }

            if (!board.move(from, to, turn)) {
                System.out.println("Illegal move. Try again.");
                continue;
            }

            PieceColor opponent = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;

            if (board.isKingInCheck(opponent)) {
                List<Piece> attackers = board.getAttackingPieces(opponent);
                System.out.print(opponent + " is in CHECK by: ");
                for (Piece p : attackers) {
                    System.out.print(p.getColor() + " " + p.getClass().getSimpleName() + " at " + p.getPosition() + "  ");
                }
                System.out.println();
            }

            if (board.isCheckmate(opponent)) {
                System.out.println("\nCHECKMATE! " + turn + " WINS!");
                board.printBoard();
                board.stopTimer();
                break;
            }

            turn = opponent;
        }

        scanner.close();
    }
}
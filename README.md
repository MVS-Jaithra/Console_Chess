# Two Player Chess (Console-Based) – Java
A two-player chess game built in Java that runs entirely in the console (no GUI).
This project focuses on implementing core chess logic, rules, and turn-based gameplay using object-oriented programming principles.

# Features
Two-player local gameplay (Player vs Player)
Full chess board initialization (standard 8×8 setup)
 
# Legal move validation for all pieces:
Pawn
Rook
Knight
Bishop
Queen
King

# Alternating turns (White / Black)
 Prevents illegal moves
 Check and basic checkmate detection (if implemented)
 Console-based board display

# Technologies Used
Java
Object-Oriented Programming (OOP)
Command-line interface (CLI)

# Project Structure (Example)
TwoPlayerChess/
│── src/
│   ├── Main.java
│   ├── Board.java
│   ├── Piece.java
│   ├── Pawn.java
│   ├── Rook.java
│   ├── Knight.java
│   ├── Bishop.java
│   ├── Queen.java
│   ├── King.java
│── README.md


# How to Run
1. Clone the repository
git clone https://github.com/your-username/two-player-chess.git

2️. Navigate to project folder
cd two-player-chess

3️. Compile the program
javac src/*.java

4️. Run the game
java src.Main

# How to Play
The board is displayed in the console.
Players enter moves using coordinate-based input.
Example move format:
e2 e4
This moves the piece from e2 → e4.
White moves first.
Players alternate turns.
The game continues until checkmate or manual exit.

# Concepts Practiced

Inheritance & Polymorphism
Encapsulation
Game logic implementation
Input validation
2D arrays
Turn-based system design

# Future Improvements
Add castling
Add en passant
Add pawn promotion
Improve checkmate detection

 # license
This project is open-source and available under the MIT License.

Enjoy this project.

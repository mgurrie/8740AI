TicTacToe/
⊢ TicTacToe.java          // Main application class
⊢ GameBoard.java         // Handles game logic and board representation
⊢ GameGUI.java           // Manages the graphical user interface

Explanation:

TicTacToe.java: This is the main class that starts the game by creating an instance of GameGUI.
GameBoard.java: This class handles the game logic:
Stores the game board as a 2D array of char.
Keeps track of the current player (X or O).
Provides methods to make moves, check for wins/draws, and get the board state.
GameGUI.java: This class creates the graphical user interface:
Uses a 3x3 grid of JButton components to represent the board.
Uses a JLabel to display game status (player turns, win/draw messages).
Handles button clicks to make moves and update the game state.
To run the game:

Save the code files as .java files in the same directory.
Compile the files (e.g., javac *.java).
Run the TicTacToe class (e.g., java TicTacToe).
To add computer AI (1-player mode):

You'll need to implement a simple AI algorithm (like Minimax) in the GameBoard class.
Modify the GameGUI to make computer moves after the player's turn.
This detailed structure will help you build your Java Tic-Tac-Toe game!

Rate this answer: 
Sources
https://github.com/hederdorneles/misc
https://github.com/483759/AlgorithmSolutionStorage
https://github.com/GrayFluex/LIFE_GAME_SE_PUBLIC
https://github.com/kimbackdoo/Mini-Project_Java
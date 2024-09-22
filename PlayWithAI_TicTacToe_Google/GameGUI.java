import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame implements ActionListener {
    private GameBoard gameBoard;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JButton restartButton;
    private JRadioButton humanOpponent, computerOpponent;
    private ButtonGroup opponentGroup;
    private boolean playAgainstComputer; // True for vs. computer

    public GameGUI() {
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450); // Slightly larger for new elements
        setLayout(new BorderLayout());

        gameBoard = new GameBoard();
        buttons = new JButton[3][3];
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        // Create buttons and add them to the panel
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Choose an opponent:");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        // Restart button
        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(this);

        // Opponent selection radio buttons
        humanOpponent = new JRadioButton("2 Players");
        computerOpponent = new JRadioButton("vs. Computer");
        opponentGroup = new ButtonGroup();
        opponentGroup.add(humanOpponent);
        opponentGroup.add(computerOpponent);

        // Add action listeners for radio buttons
        humanOpponent.addActionListener(this);
        computerOpponent.addActionListener(this);

        // Panel for opponent selection
        JPanel opponentPanel = new JPanel(new FlowLayout());
        opponentPanel.add(humanOpponent);
        opponentPanel.add(computerOpponent);

        // Add components to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH); 
        add(restartButton, BorderLayout.SOUTH);
        add(opponentPanel, BorderLayout.EAST); // Or BorderLayout.WEST

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == buttons[i][j]) {
                    handleButtonClick(i, j);
                }
            }
        }

        if (e.getSource() == restartButton) {
            restartGame();
        } else if (e.getSource() == humanOpponent) {
            playAgainstComputer = false;
            startGame(); // Start after selection
        } else if (e.getSource() == computerOpponent) {
            playAgainstComputer = true;
            startGame(); // Start after selection
        }
    }

    private void handleButtonClick(int row, int col) {
        if (gameBoard.makeMove(row, col)) {
            updateBoard();
            startGame(); // Start or continue the game after a valid move

            if (gameBoard.checkWin()) {
                gameOver("Player " + gameBoard.getCurrentPlayer() + " wins!");
            } else if (gameBoard.isDraw()) {
                gameOver("It's a draw!");
            } else {
                // Computer's turn (only if playing against computer and game not over)
                if (playAgainstComputer) {
                    computerMove();
                    updateBoard();
                    if (gameBoard.checkWin()) {
                        gameOver("Computer wins!");
                    } else if (gameBoard.isDraw()) {
                        gameOver("It's a draw!");
                    } else {
                        statusLabel.setText("Player X's turn"); 
                    }
                } else {
                    // If not computer's turn, update status for next player
                    statusLabel.setText("Player " + gameBoard.getCurrentPlayer() + "'s turn");
                }
            }
        }
    }

    private void startGame() {
        statusLabel.setText("Player X's turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void computerMove() {
        // **1. Check if the computer can win in the next move.**
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard.getBoard()[i][j] == ' ') {
                    // Simulate the move
                    gameBoard.getBoard()[i][j] = 'O'; 
                    if (gameBoard.checkWin()) {
                        // Make the move if it leads to a win
                        gameBoard.getBoard()[i][j] = 'O';
                        return; 
                    } else {
                        // Undo the simulation
                        gameBoard.getBoard()[i][j] = ' '; 
                    }
                }
            }
        }
    
        // **2. Check if the player can win in the next move and block them.**
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard.getBoard()[i][j] == ' ') {
                    // Simulate the player's move
                    gameBoard.getBoard()[i][j] = 'X'; 
                    if (gameBoard.checkWin()) {
                        // Block the player's win
                        gameBoard.getBoard()[i][j] = 'O'; 
                        return; 
                    } else {
                        // Undo the simulation
                        gameBoard.getBoard()[i][j] = ' '; 
                    }
                }
            }
        }
    
        // **3. Otherwise, make a random valid move.**
        while (true) {
            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if (gameBoard.makeMove(row, col)) {
                return;
            }
        }
    }
    

    private void restartGame() {
        gameBoard = new GameBoard();
        updateBoard();

        // Reset buttons and status
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText(" ");
            }
        }

        // Reset opponent selection (optional)
        opponentGroup.clearSelection(); 
        playAgainstComputer = false; 

        statusLabel.setText("Choose an opponent:"); 
    }

    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(gameBoard.getBoard()[i][j]));
            }
        }
    }

    private void gameOver(String message) {
        statusLabel.setText(message);
        // Disable further moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}

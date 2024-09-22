import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeAI extends JFrame {
    private JButton[] buttons = new JButton[9];
    private boolean player1Turn = true;
    private Random random = new Random();

    public TicTacToeAI() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    if (player1Turn) {
                        clickedButton.setText("X");
                    } else {
                        clickedButton.setText("O");
                    }
                    clickedButton.setEnabled(false);
                    player1Turn = !player1Turn;
                    checkWin();
                    if (!player1Turn) {
                        computerMove();
                    }
                }
            });
            add(buttons[i]);
        }

        setSize(300, 300);
        setVisible(true);
    }

    private void checkWin() {
        String[] buttonTexts = new String[9];
        for (int i = 0; i < 9; i++) {
            buttonTexts[i] = buttons[i].getText();
        }

        if (checkRow(buttonTexts) || checkColumn(buttonTexts) || checkDiagonal(buttonTexts)) {
            JOptionPane.showMessageDialog(null, "Player " + (player1Turn ? "2" : "1") + " wins!");
            resetGame();
        } else if (isBoardFull(buttonTexts)) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        }
    }

    private boolean checkRow(String[] buttonTexts) {
        for (int i = 0; i <= 6; i += 3) {
            if (isSame(buttonTexts[i], buttonTexts[i + 1], buttonTexts[i + 2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(String[] buttonTexts) {
        for (int i = 0; i <= 2; i++) {
            if (isSame(buttonTexts[i], buttonTexts[i + 3], buttonTexts[i + 6])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(String[] buttonTexts) {
        return isSame(buttonTexts[0], buttonTexts[4], buttonTexts[8]) ||
                isSame(buttonTexts[2], buttonTexts[4], buttonTexts[6]);
    }

    private boolean isSame(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private boolean isBoardFull(String[] buttonTexts) {
        for (String text : buttonTexts) {
            if (text.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        player1Turn = true;
    }

    private void computerMove() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().isEmpty()) {
                emptyCells.add(i);
            }
        }

        if (!emptyCells.isEmpty()) {
            int randomIndex = random.nextInt(emptyCells.size());
            int randomCell = emptyCells.get(randomIndex);
            buttons[randomCell].doClick();
        }
    }

    public static void main(String[] args) {
        int numPlayers = 0;
        while (numPlayers != 1 && numPlayers != 2) {
            try {
                numPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose the number of players:\n1 - Play against the computer\n2 - Play against another player"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1 or 2.");
            }
        }

        SwingUtilities.invokeLater(() -> {
            if (numPlayers == 1) {
                new TicTacToeAI();
            } else {
                new TicTacToe();
            }
        });
    }
}
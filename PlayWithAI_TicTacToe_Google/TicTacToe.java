import javax.swing.*;

public class TicTacToe {
    public static void main(String[] args) {
        // Create the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new GameGUI());
    }
}
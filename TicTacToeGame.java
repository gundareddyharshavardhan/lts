import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private String currentPlayer;
    private JLabel statusLabel;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        buttons = new JButton[3][3];
        currentPlayer = "X";

        statusLabel = new JLabel("Current Player: " + currentPlayer);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        add(statusLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            clickedButton.setText(currentPlayer);
            if (currentPlayer.equals("X")) {
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }
            statusLabel.setText("Current Player: " + currentPlayer);
        }

        if (checkForWin()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer) &&
                buttons[i][1].getText().equals(currentPlayer) &&
                buttons[i][2].getText().equals(currentPlayer)) {
                return true; // Row win
            }
            if (buttons[0][i].getText().equals(currentPlayer) &&
                buttons[1][i].getText().equals(currentPlayer) &&
                buttons[2][i].getText().equals(currentPlayer)) {
                return true; // Column win
            }
        }

        if (buttons[0][0].getText().equals(currentPlayer) &&
            buttons[1][1].getText().equals(currentPlayer) &&
            buttons[2][2].getText().equals(currentPlayer)) {
            return true; // Diagonal win
        }

        if (buttons[0][2].getText().equals(currentPlayer) &&
            buttons[1][1].getText().equals(currentPlayer) &&
            buttons[2][0].getText().equals(currentPlayer)) {
            return true; // Diagonal win
        }

        return false;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Xsi0Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameForm());
    }
}

class GameForm extends JFrame {
    private JTextField player1Field, player2Field;
    private JCheckBox playAgainstComputer;

    public GameForm() {
        setTitle("Introduceti numele jucatorilor");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nume Jucător 1:"));
        player1Field = new JTextField();
        add(player1Field);

        add(new JLabel("Nume Jucător 2 (sau lăsați gol pentru Calculator):"));
        player2Field = new JTextField();
        add(player2Field);

        playAgainstComputer = new JCheckBox("Joacă contra Calculator");
        playAgainstComputer.addActionListener(e -> {
            if (playAgainstComputer.isSelected()) {
                player2Field.setText("Calculator");
                player2Field.setEnabled(false);
            } else {
                player2Field.setText("");
                player2Field.setEnabled(true);
            }
        });
        add(playAgainstComputer);

        JButton startButton = new JButton("Start Joc");
        startButton.addActionListener(e -> startGame());
        add(startButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame() {
        String player1 = player1Field.getText().trim();
        String player2 = player2Field.getText().trim();

        if (player1.isEmpty() || (!playAgainstComputer.isSelected() && player2.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Introduceti numele ambilor jucatori!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new TicTacToeGame(player1, player2, playAgainstComputer.isSelected());
        dispose(); // Închide fereastra curentă
    }
}

class TicTacToeGame extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private String currentPlayer;
    private String player1, player2;
    private boolean againstComputer;

    public TicTacToeGame(String player1, String player2, boolean againstComputer) {
        this.player1 = player1;
        this.player2 = player2;
        this.againstComputer = againstComputer;
        currentPlayer = "X";

        setTitle("X și 0 - Jocul începe!");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeGrid();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGrid() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].addActionListener(e -> handleMove((JButton) e.getSource()));
                add(buttons[row][col]);
            }
        }
    }

    private void handleMove(JButton button) {
        if (!button.getText().isEmpty() || (againstComputer && currentPlayer.equals("O"))) return;

        button.setText(currentPlayer);
        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Jucătorul " + (currentPlayer.equals("X") ? player1 : player2) + " a câștigat!", "Felicitări!", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            return;
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";

        if (againstComputer && currentPlayer.equals("O")) {
            computerMove();
        }
    }

    private void computerMove() {
        ArrayList<JButton> availableButtons = new ArrayList<>();
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                if (button.getText().isEmpty()) {
                    availableButtons.add(button);
                }
            }
        }

        if (!availableButtons.isEmpty()) {
            Random random = new Random();
            JButton chosenButton = availableButtons.get(random.nextInt(availableButtons.size()));
            chosenButton.setText("O");

            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Calculatorul a câștigat!", "Felicitări!", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
                return;
            }

            currentPlayer = "X";
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            // Verificare rânduri
            if (buttons[i][0].getText().equals(currentPlayer) &&
                    buttons[i][1].getText().equals(currentPlayer) &&
                    buttons[i][2].getText().equals(currentPlayer)) return true;

            // Verificare coloane
            if (buttons[0][i].getText().equals(currentPlayer) &&
                    buttons[1][i].getText().equals(currentPlayer) &&
                    buttons[2][i].getText().equals(currentPlayer)) return true;
        }

        // Verificare diagonale
        if (buttons[0][0].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][2].getText().equals(currentPlayer)) return true;

        if (buttons[0][2].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][0].getText().equals(currentPlayer)) return true;

        return false;
    }

    private void resetGame() {
        for (JButton[] row : buttons)
            for (JButton button : row)
                button.setText("");

        currentPlayer = "X";
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Minesweeper {
    private static final int SIZE = 8; // Grid size
    private static final int MINES = 10; // Number of mines
    private static final String MINE = "💣"; // Emoji for mine

    private JFrame frame;
    private JPanel gridPanel;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private boolean[][] revealed = new boolean[SIZE][SIZE];
    private boolean[][] mines = new boolean[SIZE][SIZE];
    private boolean[][] flagged = new boolean[SIZE][SIZE];
    private boolean flagMode = false; // Flag mode toggle

    private JButton revealButton;
    private JButton flagButton;
    private JLabel timerLabel;
    private Timer timer;
    private int elapsedTime;

    public Minesweeper() {
        initializeMines();
        createGUI();
    }

    private void initializeMines() {
        Random rand = new Random();
        int placedMines = 0;

        while (placedMines < MINES) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);

            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && mines[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || revealed[row][col] || flagged[row][col]) {
            return;
        }

        revealed[row][col] = true;
        int adjacentMines = countAdjacentMines(row, col);

        if (mines[row][col]) {
            buttons[row][col].setText(MINE);
            buttons[row][col].setBackground(Color.RED);
            timer.stop();
            JOptionPane.showMessageDialog(frame, "Game Over! You hit a mine.");
            revealAllMines();
        } else if (adjacentMines > 0) {
            buttons[row][col].setText(String.valueOf(adjacentMines));
            buttons[row][col].setEnabled(false);
        } else {
            buttons[row][col].setText(" ");
            buttons[row][col].setEnabled(false);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(row + i, col + j);
                }
            }
        }

        if (isWin()) {
            timer.stop();
            JOptionPane.showMessageDialog(frame, "Congratulations! You cleared the minefield.");
        }
    }

    private void toggleFlag(int row, int col) {
        if (revealed[row][col]) {
            return;
        }

        if (flagged[row][col]) {
            flagged[row][col] = false;
            buttons[row][col].setText("");
            buttons[row][col].setBackground(null);
        } else {
            flagged[row][col] = true;
            buttons[row][col].setText("🚩");
            buttons[row][col].setBackground(Color.YELLOW);
        }
    }

    private boolean isWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!mines[i][j] && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void revealAllMines() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText(MINE);
                    buttons[i][j].setBackground(Color.RED);
                }
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);
                revealed[i][j] = false;
                mines[i][j] = false;
                flagged[i][j] = false;
            }
        }
        initializeMines();
        flagMode = false;
        elapsedTime = 0;
        timerLabel.setText("Time: 0 seconds");
        timer.restart();
    }

    private void createGUI() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int row = i;
                int col = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 18));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (flagMode) {
                            toggleFlag(row, col);
                        } else {
                            revealCell(row, col);
                        }
                    }
                });
                gridPanel.add(buttons[i][j]);
            }
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        revealButton = new JButton("Reveal Mode");
        revealButton.setFont(new Font("Arial", Font.BOLD, 16));
        revealButton.setBackground(Color.LIGHT_GRAY);
        revealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagMode = false;
                revealButton.setBackground(Color.LIGHT_GRAY);
                revealButton.setOpaque(true);
                flagButton.setBackground(null);
            }
        });

        flagButton = new JButton("Flag Mode");
        flagButton.setFont(new Font("Arial", Font.BOLD, 16));
        flagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagMode = true;
                flagButton.setBackground(Color.LIGHT_GRAY);
                flagButton.setOpaque(true);
                revealButton.setBackground(null);
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        timerLabel = new JLabel("Time: 0 seconds");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        elapsedTime = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                timerLabel.setText("Time: " + elapsedTime + " seconds");
            }
        });
        timer.start();

        controlPanel.add(revealButton);
        controlPanel.add(flagButton);
        controlPanel.add(restartButton);
        controlPanel.add(timerLabel);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(600, 700);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minesweeper::new);
    }
}

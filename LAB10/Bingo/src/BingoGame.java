import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BingoGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerRegistration());
    }
}

class PlayerRegistration extends JFrame {
    private JTextField playerNameField;
    private DefaultListModel<String> playersListModel;

    public PlayerRegistration() {
        setTitle("Înregistrare Jucători");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        playerNameField = new JTextField(20);
        JButton addPlayerButton = new JButton("Adaugă Jucător");
        addPlayerButton.addActionListener(e -> addPlayer());

        inputPanel.add(new JLabel("Nume Jucător:"));
        inputPanel.add(playerNameField);
        inputPanel.add(addPlayerButton);

        playersListModel = new DefaultListModel<>();
        JList<String> playersList = new JList<>(playersListModel);
        JScrollPane scrollPane = new JScrollPane(playersList);

        JButton startGameButton = new JButton("Start Joc");
        startGameButton.addActionListener(e -> startGame());

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(startGameButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPlayer() {
        String playerName = playerNameField.getText().trim();
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Numele jucătorului nu poate fi gol!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (playersListModel.contains(playerName)) {
            JOptionPane.showMessageDialog(this, "Jucătorul este deja înregistrat!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        playersListModel.addElement(playerName);
        playerNameField.setText("");
    }

    private void startGame() {
        if (playersListModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adăugați cel puțin un jucător pentru a începe jocul!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> players = Collections.list(playersListModel.elements());
        new PlayerBoard(players);
        dispose();
    }
}

class PlayerBoard extends JFrame {
    private Map<String, int[][]> playerBoards = new HashMap<>();
    private JComboBox<String> playerSelector;
    private JTextField[][] boardFields = new JTextField[5][5];
    private JButton saveBoardButton;

    public PlayerBoard(List<String> players) {
        setTitle("Completați Grilele");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        playerSelector = new JComboBox<>(players.toArray(new String[0]));
        playerSelector.addActionListener(e -> loadPlayerBoard());

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardFields[i][j] = new JTextField();
                boardFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(boardFields[i][j]);
            }
        }

        saveBoardButton = new JButton("Salvează Grila");
        saveBoardButton.addActionListener(e -> savePlayerBoard());

        add(playerSelector, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(saveBoardButton, BorderLayout.SOUTH);

        loadPlayerBoard();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadPlayerBoard() {
        String selectedPlayer = (String) playerSelector.getSelectedItem();
        int[][] board = playerBoards.getOrDefault(selectedPlayer, new int[5][5]);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardFields[i][j].setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));
            }
        }
    }

    private void savePlayerBoard() {
        String selectedPlayer = (String) playerSelector.getSelectedItem();
        int[][] board = new int[5][5];
        Set<Integer> uniqueNumbers = new HashSet<>();

        try {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    String text = boardFields[i][j].getText().trim();
                    if (text.isEmpty()) throw new IllegalArgumentException("Toate casetele trebuie completate!");
                    int number = Integer.parseInt(text);
                    if (number < 1 || number > 75) throw new IllegalArgumentException("Numerele trebuie să fie între 1 și 75!");
                    if (!uniqueNumbers.add(number)) throw new IllegalArgumentException("Numerele nu trebuie să se repete!");
                    board[i][j] = number;
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduceți numai numere valide!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        playerBoards.put(selectedPlayer, board);
        JOptionPane.showMessageDialog(this, "Grila a fost salvată cu succes pentru " + selectedPlayer + "!", "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
}

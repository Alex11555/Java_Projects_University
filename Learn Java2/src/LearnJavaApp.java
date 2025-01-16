import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LearnJavaApp {

    private static JTextArea lessonArea;
    private static JTextArea codeArea;
    private static JButton compileButton;
    private static JButton checkButton;

    public static void main(String[] args) {
        // Crearea ferestrei principale
        JFrame frame = new JFrame("Learn Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Crearea unui panel principal cu layout de tip GridLayout
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // Panelul pentru lecție
        JPanel lessonPanel = new JPanel();
        lessonPanel.setLayout(new BorderLayout());
        lessonArea = new JTextArea("Lesson 1: Hello, World!\nWrite your code in the right section and press 'Compile' to check syntax, and 'Check' to verify the code.");
        lessonArea.setEditable(false);
        lessonArea.setFont(new Font("Arial", Font.PLAIN, 16));
        lessonPanel.add(lessonArea, BorderLayout.CENTER);

        // Panelul pentru cod
        JPanel codePanel = new JPanel();
        codePanel.setLayout(new BorderLayout());
        codeArea = new JTextArea();
        codeArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(codeArea);
        codePanel.add(scrollPane, BorderLayout.CENTER);

        // Butoane pentru compilare și verificare
        JPanel buttonPanel = new JPanel(new FlowLayout());
        compileButton = new JButton("Compile");
        checkButton = new JButton("Check");
        buttonPanel.add(compileButton);
        buttonPanel.add(checkButton);
        codePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adăugarea panourilor în fereastră
        mainPanel.add(lessonPanel);
        mainPanel.add(codePanel);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Logica pentru compilare (verificare sintaxă)
        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userCode = codeArea.getText().trim();
                if (userCode.isEmpty()) {
                    lessonArea.setText("Please write your code in the right section.");
                } else {
                    lessonArea.setText("Syntax looks fine. Now press 'Check' to verify the code.");
                }
            }
        });

        // Logica pentru verificare (verificarea corectitudinii codului)
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userCode = codeArea.getText().trim();

                if (lessonArea.getText().contains("Hello, World!") && userCode.equals("System.out.println(\"Hello, World!\");")) {
                    lessonArea.setText("Lesson 1 completed! Now proceed to Lesson 2.\nWrite code: System.out.println(\"Hello, Mike!\");");
                    codeArea.setText(""); // Golim codul pentru lecția 2
                } else if (lessonArea.getText().contains("Hello, Mike!") && userCode.equals("System.out.println(\"Hello, Mike!\");")) {
                    lessonArea.setText("Congratulations! You completed both lessons.");
                } else {
                    lessonArea.setText("Incorrect code. Please try again.");
                }
            }
        });
    }
}

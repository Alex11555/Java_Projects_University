import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Robot;
import java.awt.AWTException;
import java.util.Timer;
import java.util.TimerTask;

public class ImageAnimationApp {

    private static final String[] IMAGE_PATHS = {
            "pics/Picture_1.JPG",
            "pics/Picture_2.JPG",
            "pics/Picture_3.JPG",
            "pics/Picture_4.JPG",
            "pics/Picture_5.JPG",
            "pics/Picture_6.JPG",
            "pics/Picture_7.JPG",
            "pics/Picture_8.JPG",
            "pics/Picture_9.JPG",
    };

    private int currentImageIndex = 0;
    private JLabel imageLabel;
    private Timer timer;
    private boolean isRunning = true; //pause
    private int animationSpeed = 1000; //speed (default 1 second)
    private boolean isForward = true; //sens (default direction is forward)

    public ImageAnimationApp() {
        JFrame frame = new JFrame("Image Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Full-screen toggle using Robot
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateImage();

        JButton pausePlayButton = new JButton("Pause"); //pause
        pausePlayButton.addActionListener(new ActionListener() { //pause
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    stopAnimation();
                    pausePlayButton.setText("Play");
                } else {
                    startAnimation();
                    pausePlayButton.setText("Pause");
                }
                isRunning = !isRunning;
            }
        });

        JButton speedUpButton = new JButton("Speed Up"); //speed
        speedUpButton.addActionListener(new ActionListener() { //speed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationSpeed > 200) { // Minimum speed limit
                    animationSpeed -= 200;
                    restartAnimation();
                }
            }
        });

        JButton slowDownButton = new JButton("Slow Down"); //speed
        slowDownButton.addActionListener(new ActionListener() { //speed
            @Override
            public void actionPerformed(ActionEvent e) {
                animationSpeed += 200; // No maximum limit for slowing down
                restartAnimation();
            }
        });

        JButton changeDirectionButton = new JButton("Change Direction"); //sens
        changeDirectionButton.addActionListener(new ActionListener() { //sens
            @Override
            public void actionPerformed(ActionEvent e) {
                isForward = !isForward; // Toggle direction
            }
        });

        JPanel panel = new JPanel(); // Container for buttons
        panel.add(pausePlayButton);
        panel.add(speedUpButton); //speed
        panel.add(slowDownButton); //speed
        panel.add(changeDirectionButton); //sens

        frame.setLayout(new BorderLayout());
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH); // Add button panel at the bottom

        frame.setVisible(true);

        startAnimation();
    }

    private void startAnimation() { //pause
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isForward) { //sens
                    currentImageIndex = (currentImageIndex + 1) % IMAGE_PATHS.length;
                } else {
                    currentImageIndex = (currentImageIndex - 1 + IMAGE_PATHS.length) % IMAGE_PATHS.length;
                }
                updateImage();
            }
        }, 0, animationSpeed); //speed
    }

    private void stopAnimation() { //pause
        if (timer != null) {
            timer.cancel();
        }
    }

    private void restartAnimation() { //speed
        stopAnimation();
        startAnimation();
    }

    private void updateImage() {
        ImageIcon icon = new ImageIcon(IMAGE_PATHS[currentImageIndex]);
        Image scaledImage = icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageAnimationApp::new);
    }
}
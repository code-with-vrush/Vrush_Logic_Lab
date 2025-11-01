import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int targetNumber;
    private int attempts;
    
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    
    public GuessTheNumberGame() {
        super("Guess The Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1; // number between 1-100
        attempts = 0;

        add(new JLabel("Guess a number between 1 and 100:"));

        guessInput = new JTextField(10);
        add(guessInput);

        guessButton = new JButton("Guess");
        add(guessButton);

        messageLabel = new JLabel("Enter your guess and click Guess.");
        messageLabel.setPreferredSize(new Dimension(350, 30));
        add(messageLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        add(attemptsLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guessText = guessInput.getText();
                try {
                    int guess = Integer.parseInt(guessText);
                    attempts++;
                    attemptsLabel.setText("Attempts: " + attempts);

                    if (guess < 1 || guess > 100) {
                        messageLabel.setText("Please guess a number between 1 and 100.");
                    } else if (guess < targetNumber) {
                        messageLabel.setText("Too low! Try again.");
                    } else if (guess > targetNumber) {
                        messageLabel.setText("Too high! Try again.");
                    } else {
                        messageLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                        guessButton.setEnabled(false);
                        guessInput.setEditable(false);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number.");
                }
                guessInput.setText("");
                guessInput.requestFocus();
            }
        });

        setLocationRelativeTo(null);  // Center window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessTheNumberGame());
    }
}

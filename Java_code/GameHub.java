import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class GameHub extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JTextArea instructionArea = new JTextArea();

    private String[] gameNames = {
            "Menu",
            "Guess The Number",
            "Tic-Tac-Toe",
            "Rock-Paper-Scissors",
            "Memory Match",
            "Dice Rolling",
            "Snake Game",
            "Pong",
            "Minesweeper",
            "Calculator",
            "Simon Says",
            "Hangman",
            "Color Reaction"
    };

    public GameHub() {
        setTitle("Game Hub");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left panel for selection + instructions
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(260, 0));
        leftPanel.setBackground(new Color(40, 40, 40));

        // Game selection list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String g : gameNames) listModel.addElement(g);

        JList<String> gameList = new JList<>(listModel);
        gameList.setFont(new Font("Consolas", Font.BOLD, 18));
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setSelectedIndex(0);
        gameList.setBackground(new Color(30, 30, 30));
        gameList.setForeground(Color.WHITE);
        gameList.setFixedCellHeight(35);

        leftPanel.add(new JLabel("  Select a Game:", SwingConstants.LEFT), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(gameList), BorderLayout.CENTER);

        // Instruction area
        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);
        instructionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionArea.setBackground(new Color(30, 30, 30));
        instructionArea.setForeground(Color.LIGHT_GRAY);
        instructionArea.setMargin(new Insets(10,10,10,10));
        instructionArea.setText(getInstructions("Menu"));

        JPanel instructionPanel = new JPanel(new BorderLayout());
        instructionPanel.setPreferredSize(new Dimension(260, 200));
        instructionPanel.add(new JScrollPane(instructionArea));
        leftPanel.add(instructionPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        // Main area with card layout
        mainPanel.setBackground(new Color(20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        // Add all game panels
        mainPanel.add(new MenuPanel(), "Menu");
        mainPanel.add(new GuessTheNumberPanel(), "Guess The Number");
        mainPanel.add(new TicTacToePanel(), "Tic-Tac-Toe");
        mainPanel.add(new RockPaperScissorsPanel(), "Rock-Paper-Scissors");
        mainPanel.add(new MemoryMatchPanel(), "Memory Match");
        mainPanel.add(new DiceRollingPanel(), "Dice Rolling");
        mainPanel.add(new SnakeGamePanel(), "Snake Game");

        mainPanel.add(new PongPanel(), "Pong");
        mainPanel.add(new MinesweeperPanel(), "Minesweeper");
        mainPanel.add(new CalculatorPanel(), "Calculator");
        mainPanel.add(new SimonSaysPanel(), "Simon Says");
        mainPanel.add(new HangmanPanel(), "Hangman");
        mainPanel.add(new ColorReactionPanel(), "Color Reaction");

        cardLayout.show(mainPanel, "Menu");

        // Switch game on selection change
        gameList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = gameList.getSelectedValue();
                if (selected != null) {
                    instructionArea.setText(getInstructions(selected));
                    cardLayout.show(mainPanel, selected);
                }
            }
        });

        setVisible(true);
    }

    private String getInstructions(String game) {
        switch (game) {
            case "Menu":
                return "Welcome to Game Hub!\n\nSelect a game from the left menu to start playing.\nEnjoy!";
            case "Guess The Number":
                return "Guess the secret number between 1 and 100.\nEnter your guess and try to find the number in as few attempts as possible.";
            case "Tic-Tac-Toe":
                return "Play Tic-Tac-Toe against the computer.\nYou are X, computer is O.\nGet 3 in a row to win.";
            case "Rock-Paper-Scissors":
                return "Play Rock-Paper-Scissors against the computer.\nChoose one and try to win!";
            case "Memory Match":
                return "Flip cards to find matching pairs.\nMatch all pairs to win.";
            case "Dice Rolling":
                return "Roll a six-sided dice.\nTry your luck!";
            case "Snake Game":
                return "Control the snake with arrow keys.\nEat food to grow.\nAvoid hitting walls or yourself.";
            case "Pong":
                return "Classic Pong game.\nControl the paddle with W/S keys.\nBeat the AI paddle.";
            case "Minesweeper":
                return "Click tiles to find all safe spots.\nAvoid mines!\nNumbers show how many mines are nearby.";
            case "Calculator":
                return "Basic calculator.\nSupports +, -, *, / operations.";
            case "Simon Says":
                return "Repeat the color sequence.\nSequence grows each round.";
            case "Hangman":
                return "Guess the hidden word one letter at a time.\nYou have 6 wrong guesses max.";
            case "Color Reaction":
                return "Test your reaction time.\nClick the panel when it changes color.";
            default:
                return "Instructions are not available for this game yet.";
        }
    }

    // ----------------------- MENU PANEL -------------------------
    class MenuPanel extends JPanel {
        public MenuPanel() {
            setBackground(new Color(20, 20, 20));
            JLabel label = new JLabel("<html><center><br><br><br>Welcome to Game Hub!<br><br>Select a game from the left menu to play.</center></html>", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 24));
            label.setForeground(Color.CYAN);
            setLayout(new BorderLayout());
            add(label, BorderLayout.CENTER);
        }
    }

    // -------------------- GUESS THE NUMBER -----------------------
    class GuessTheNumberPanel extends JPanel {
        private int target;
        private int attempts;
        private JTextField guessField;
        private JButton guessButton, resetButton;
        private JLabel messageLabel, attemptsLabel;

        public GuessTheNumberPanel() {
            setBackground(new Color(20, 20, 20));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8,8,8,8);

            JLabel prompt = new JLabel("Guess a number between 1 and 100:");
            prompt.setForeground(Color.CYAN);
            prompt.setFont(new Font("Arial", Font.BOLD, 18));
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(prompt, gbc);

            guessField = new JTextField(10);
            guessField.setFont(new Font("Arial", Font.PLAIN, 18));
            gbc.gridy = 1; gbc.gridwidth = 1;
            add(guessField, gbc);

            guessButton = new JButton("Guess");
            guessButton.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 1;
            add(guessButton, gbc);

            messageLabel = new JLabel("Enter your guess and press Guess.");
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
            add(messageLabel, gbc);

            attemptsLabel = new JLabel("Attempts: 0");
            attemptsLabel.setForeground(Color.LIGHT_GRAY);
            attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            gbc.gridy = 3;
            add(attemptsLabel, gbc);

            resetButton = new JButton("Reset Game");
            resetButton.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridy = 4;
            add(resetButton, gbc);

            resetGame();

            guessButton.addActionListener(e -> {
                try {
                    int guess = Integer.parseInt(guessField.getText().trim());
                    attempts++;
                    attemptsLabel.setText("Attempts: " + attempts);

                    if (guess < 1 || guess > 100) {
                        messageLabel.setText("Please enter a number between 1 and 100.");
                    } else if (guess < target) {
                        messageLabel.setText("Too low! Try again.");
                    } else if (guess > target) {
                        messageLabel.setText("Too high! Try again.");
                    } else {
                        messageLabel.setText("Correct! You guessed it in " + attempts + " tries.");
                        guessButton.setEnabled(false);
                        guessField.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number.");
                }
                guessField.setText("");
                guessField.requestFocus();
            });

            resetButton.addActionListener(e -> resetGame());
        }

        private void resetGame() {
            target = new Random().nextInt(100) + 1;
            attempts = 0;
            messageLabel.setText("Enter your guess and press Guess.");
            attemptsLabel.setText("Attempts: 0");
            guessButton.setEnabled(true);
            guessField.setEnabled(true);
            guessField.setText("");
            guessField.requestFocus();
        }
    }

    // -------------------- TIC-TAC-TOE -----------------------
    class TicTacToePanel extends JPanel {
        private JButton[] buttons = new JButton[9];
        private boolean playerTurn = true; // player 'X'
        private boolean gameOver = false;

        public TicTacToePanel() {
            setBackground(new Color(20, 20, 20));
            setLayout(new BorderLayout());

            JPanel board = new JPanel(new GridLayout(3, 3));
            board.setBackground(new Color(20, 20, 20));
            board.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            Font font = new Font("Arial", Font.BOLD, 48);
            for (int i = 0; i < 9; i++) {
                JButton b = new JButton("");
                b.setFont(font);
                b.setBackground(Color.DARK_GRAY);
                b.setForeground(Color.WHITE);
                final int idx = i;
                b.addActionListener(e -> {
                    if (gameOver || !b.getText().equals("")) return;
                    b.setText("X");
                    if (checkWin("X")) {
                        JOptionPane.showMessageDialog(this, "You Win!");
                        gameOver = true;
                        return;
                    }
                    if (boardFull()) {
                        JOptionPane.showMessageDialog(this, "Draw!");
                        gameOver = true;
                        return;
                    }
                    computerMove();
                });
                buttons[i] = b;
                board.add(b);
            }

            JButton resetBtn = new JButton("Reset");
            resetBtn.addActionListener(e -> resetGame());

            JButton backBtn = new JButton("Back to Menu");
            backBtn.addActionListener(e -> {
                resetGame();
                cardLayout.show(mainPanel, "Menu");
            });

            JPanel control = new JPanel();
            control.add(resetBtn);
            control.add(backBtn);

            add(board, BorderLayout.CENTER);
            add(control, BorderLayout.SOUTH);
        }

        private void computerMove() {
            if(gameOver) return;
            Random rand = new Random();
            int move;
            do {
                move = rand.nextInt(9);
            } while(!buttons[move].getText().equals(""));
            buttons[move].setText("O");

            if (checkWin("O")) {
                JOptionPane.showMessageDialog(this, "Computer Wins!");
                gameOver = true;
                return;
            }
            if (boardFull()) {
                JOptionPane.showMessageDialog(this, "Draw!");
                gameOver = true;
            }
        }

        private boolean checkWin(String player) {
            String[] b = new String[9];
            for(int i=0; i<9; i++) b[i] = buttons[i].getText();
            int[][] wins = {
                    {0,1,2}, {3,4,5}, {6,7,8},
                    {0,3,6}, {1,4,7}, {2,5,8},
                    {0,4,8}, {2,4,6}
            };
            for(int[] w : wins) {
                if (b[w[0]].equals(player) && b[w[1]].equals(player) && b[w[2]].equals(player)) return true;
            }
            return false;
        }

        private boolean boardFull() {
            for(JButton b : buttons) {
                if(b.getText().equals("")) return false;
            }
            return true;
        }

        private void resetGame() {
            for (JButton b : buttons) b.setText("");
            gameOver = false;
            playerTurn = true;
        }
    }

    // -------------------- ROCK PAPER SCISSORS -----------------------
    class RockPaperScissorsPanel extends JPanel {
        private JLabel resultLabel;
        private JLabel scoreLabel;
        private int userScore = 0;
        private int compScore = 0;

        public RockPaperScissorsPanel() {
            setBackground(new Color(20, 20, 20));
            setLayout(new BorderLayout());

            JPanel btnPanel = new JPanel();
            btnPanel.setBackground(new Color(20, 20, 20));

            String[] choices = {"Rock", "Paper", "Scissors"};
            for (String choice : choices) {
                JButton btn = new JButton(choice);
                btn.setFont(new Font("Arial", Font.BOLD, 24));
                btnPanel.add(btn);
                btn.addActionListener(e -> playRound(choice));
            }

            resultLabel = new JLabel("Make your choice", SwingConstants.CENTER);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 28));
            resultLabel.setForeground(Color.CYAN);

            scoreLabel = new JLabel("User: 0 | Computer: 0", SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
            scoreLabel.setForeground(Color.LIGHT_GRAY);

            JButton backBtn = new JButton("Back to Menu");
            backBtn.addActionListener(e -> {
                resetGame();
                cardLayout.show(mainPanel, "Menu");
            });

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(scoreLabel, BorderLayout.CENTER);
            bottomPanel.add(backBtn, BorderLayout.EAST);

            add(btnPanel, BorderLayout.NORTH);
            add(resultLabel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        private void playRound(String userChoice) {
            String[] choices = {"Rock", "Paper", "Scissors"};
            String compChoice = choices[new Random().nextInt(3)];
            String result;

            if(userChoice.equals(compChoice)) {
                result = "Draw! Both chose " + userChoice;
            } else if (
                (userChoice.equals("Rock") && compChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && compChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && compChoice.equals("Paper"))
            ) {
                result = "You Win! " + userChoice + " beats " + compChoice;
                userScore++;
            } else {
                result = "You Lose! " + compChoice + " beats " + userChoice;
                compScore++;
            }
            resultLabel.setText(result);
            scoreLabel.setText("User: " + userScore + " | Computer: " + compScore);
        }

        private void resetGame() {
            userScore = 0;
            compScore = 0;
            resultLabel.setText("Make your choice");
            scoreLabel.setText("User: 0 | Computer: 0");
        }
    }

    // -------------------- MEMORY MATCH -----------------------
    class MemoryMatchPanel extends JPanel {
        private final int PAIRS = 8;
        private JButton[] cards = new JButton[PAIRS * 2];
        private String[] values = new String[PAIRS * 2];
        private JButton firstSelected = null;
        private JButton secondSelected = null;
        private Timer timer;
        private int matchesFound = 0;

        public MemoryMatchPanel() {
            setLayout(new GridLayout(4, 4, 5, 5));
            setBackground(new Color(20, 20, 20));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            for (int i = 0; i < PAIRS; i++) {
                values[2 * i] = String.valueOf((char) ('A' + i));
                values[2 * i + 1] = String.valueOf((char) ('A' + i));
            }
            shuffleArray(values);

            Font font = new Font("Arial", Font.BOLD, 32);

            for (int i = 0; i < cards.length; i++) {
                JButton btn = new JButton();
                btn.setFont(font);
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setFocusPainted(false);
                btn.addActionListener(e -> selectCard(btn));
                cards[i] = btn;
                add(btn);
            }
        }

        private void selectCard(JButton btn) {
            if (timer != null && timer.isRunning()) return;
            if (btn == firstSelected || btn == secondSelected || !btn.getText().equals("")) return;

            int index = -1;
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == btn) {
                    index = i;
                    break;
                }
            }
            if (index == -1) return;

            btn.setText(values[index]);

            if (firstSelected == null) {
                firstSelected = btn;
            } else {
                secondSelected = btn;
                if (firstSelected.getText().equals(secondSelected.getText())) {
                    matchesFound++;
                    firstSelected.setEnabled(false);
                    secondSelected.setEnabled(false);
                    firstSelected = null;
                    secondSelected = null;
                    if (matchesFound == PAIRS) {
                        JOptionPane.showMessageDialog(this, "You won the Memory Match!");
                        resetGame();
                    }
                } else {
                    timer = new Timer(1000, e -> {
                        firstSelected.setText("");
                        secondSelected.setText("");
                        firstSelected = null;
                        secondSelected = null;
                        timer.stop();
                    });
                    timer.start();
                }
            }
        }

        private void shuffleArray(String[] array) {
            Random rand = new Random();
            for (int i = array.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        private void resetGame() {
            matchesFound = 0;
            for (JButton b : cards) {
                b.setEnabled(true);
                b.setText("");
                b.setBackground(Color.LIGHT_GRAY);
            }
            shuffleArray(values);
        }
    }

    // -------------------- DICE ROLLING -----------------------
    class DiceRollingPanel extends JPanel {
        private JLabel diceLabel;
        private JButton rollButton;
        private Random rand = new Random();

        public DiceRollingPanel() {
            setBackground(new Color(20, 20, 20));
            setLayout(new FlowLayout(FlowLayout.CENTER, 40, 80));

            diceLabel = new JLabel("Roll the Dice!");
            diceLabel.setFont(new Font("Arial", Font.BOLD, 48));
            diceLabel.setForeground(Color.CYAN);
            add(diceLabel);

            rollButton = new JButton("Roll");
            rollButton.setFont(new Font("Arial", Font.BOLD, 32));
            rollButton.addActionListener(e -> rollDice());
            add(rollButton);
        }

        private void rollDice() {
            int roll = rand.nextInt(6) + 1;
            // Unicode dice faces from ⚀ (U+2680) to ⚅ (U+2685)
            char diceFace = (char) (0x2680 + roll - 1);
            diceLabel.setText(diceFace + "  You rolled a " + roll);
        }
    }

    // -------------------- SNAKE GAME -----------------------
    class SnakeGamePanel extends JPanel implements ActionListener, KeyListener {
        private final int CELL_SIZE = 20;
        private final int WIDTH = 30;
        private final int HEIGHT = 25;
        private final int DELAY = 120;

        private final int[] x = new int[WIDTH * HEIGHT];
        private final int[] y = new int[WIDTH * HEIGHT];

        private int snakeLength;
        private int foodX, foodY;

        private char direction = 'R'; // U,D,L,R
        private boolean running = false;
        private Timer timer;

        public SnakeGamePanel() {
            setPreferredSize(new Dimension(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
            startGame();
        }

        public void startGame() {
            snakeLength = 5;
            for (int i = 0; i < snakeLength; i++) {
                x[i] = 100 - i * CELL_SIZE;
                y[i] = 100;
            }
            spawnFood();
            direction = 'R';
            running = true;
            timer = new Timer(DELAY, this);
            timer.start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!running) {
                gameOver(g);
                return;
            }

            // draw food
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, CELL_SIZE, CELL_SIZE);

            // draw snake
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
                }
            }
        }

        private void gameOver(Graphics g) {
            String msg = "Game Over";
            String scoreMsg = "Score: " + (snakeLength - 5);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics fm = g.getFontMetrics();
            int msgX = (getWidth() - fm.stringWidth(msg)) / 2;
            int msgY = getHeight() / 2 - 50;
            g.drawString(msg, msgX, msgY);

            g.setFont(new Font("Arial", Font.BOLD, 30));
            int scoreX = (getWidth() - fm.stringWidth(scoreMsg)) / 2;
            int scoreY = getHeight() / 2;
            g.drawString(scoreMsg, scoreX, scoreY);
        }

        public void actionPerformed(ActionEvent e) {
            if (running) {
                move();
                checkFood();
                checkCollision();
            }
            repaint();
        }

        private void move() {
            for (int i = snakeLength; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            switch (direction) {
                case 'U': y[0] -= CELL_SIZE; break;
                case 'D': y[0] += CELL_SIZE; break;
                case 'L': x[0] -= CELL_SIZE; break;
                case 'R': x[0] += CELL_SIZE; break;
            }
        }

        private void checkFood() {
            if (x[0] == foodX && y[0] == foodY) {
                snakeLength++;
                spawnFood();
            }
        }

        private void spawnFood() {
            Random rand = new Random();
            foodX = rand.nextInt(WIDTH) * CELL_SIZE;
            foodY = rand.nextInt(HEIGHT) * CELL_SIZE;
        }

        private void checkCollision() {
            // Check self collision
            for (int i = snakeLength; i > 0; i--) {
                if ((x[0] == x[i]) && (y[0] == y[i])) {
                    running = false;
                    timer.stop();
                }
            }
            // Check wall collision
            if (x[0] < 0 || x[0] >= WIDTH * CELL_SIZE || y[0] < 0 || y[0] >= HEIGHT * CELL_SIZE) {
                running = false;
                timer.stop();
            }
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
            }
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    // -------------------- PONG -----------------------
    class PongPanel extends JPanel implements ActionListener, KeyListener {
        private final int WIDTH = 700;
        private final int HEIGHT = 400;
        private final int PADDLE_WIDTH = 10;
        private final int PADDLE_HEIGHT = 80;
        private final int BALL_SIZE = 15;

        private int playerY = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        private int aiY = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        private int ballX = WIDTH / 2;
        private int ballY = HEIGHT / 2;
        private int ballXSpeed = 4;
        private int ballYSpeed = 3;

        private int playerScore = 0;
        private int aiScore = 0;

        private Timer timer;

        private boolean upPressed = false;
        private boolean downPressed = false;

        public PongPanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
            timer = new Timer(15, this);
            timer.start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.WHITE);
            g.fillRect(10, playerY, PADDLE_WIDTH, PADDLE_HEIGHT); // player paddle
            g.fillRect(WIDTH - 20, aiY, PADDLE_WIDTH, PADDLE_HEIGHT); // AI paddle

            g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Player: " + playerScore, 20, 30);
            g.drawString("AI: " + aiScore, WIDTH - 100, 30);
        }

        public void actionPerformed(ActionEvent e) {
            // Move player paddle
            if (upPressed) playerY = Math.max(playerY - 5, 0);
            if (downPressed) playerY = Math.min(playerY + 5, HEIGHT - PADDLE_HEIGHT);

            // Move AI paddle simple logic
            if (aiY + PADDLE_HEIGHT / 2 < ballY) aiY = Math.min(aiY + 4, HEIGHT - PADDLE_HEIGHT);
            else aiY = Math.max(aiY - 4, 0);

            // Move ball
            ballX += ballXSpeed;
            ballY += ballYSpeed;

            // Ball collision with top/bottom
            if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
                ballYSpeed = -ballYSpeed;
            }

            // Ball collision with player paddle
            if (ballX <= 20 && ballY + BALL_SIZE >= playerY && ballY <= playerY + PADDLE_HEIGHT) {
                ballXSpeed = -ballXSpeed;
            }

            // Ball collision with AI paddle
            if (ballX + BALL_SIZE >= WIDTH - 20 && ballY + BALL_SIZE >= aiY && ballY <= aiY + PADDLE_HEIGHT) {
                ballXSpeed = -ballXSpeed;
            }

            // Score check
            if (ballX < 0) {
                aiScore++;
                resetBall();
            } else if (ballX > WIDTH) {
                playerScore++;
                resetBall();
            }

            repaint();
        }

        private void resetBall() {
            ballX = WIDTH / 2;
            ballY = HEIGHT / 2;
            ballXSpeed = -ballXSpeed;
            ballYSpeed = 3 * (new Random().nextBoolean() ? 1 : -1);
        }

        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W) upPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_S) downPressed = true;
        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
            if(e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
        }

        public void keyTyped(KeyEvent e) {}
    }

    // -------------------- MINESWEEPER -----------------------
    // -------------------- MINESWEEPER -----------------------
class MinesweeperPanel extends JPanel {
    private final int ROWS = 9;
    private final int COLS = 9;
    private final int MINES = 10;

    private Cell[][] cells = new Cell[ROWS][COLS];
    private boolean gameOver = false;
    private int cellsRevealed = 0;

    public MinesweeperPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        setBackground(new Color(50, 50, 50));

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                cells[r][c] = new Cell(r, c);
                add(cells[r][c]);
            }
        }

        setupMines();
        calculateNumbers();
    }

    private void setupMines() {
        int minesPlaced = 0;
        Random rand = new Random();

        while (minesPlaced < MINES) {
            int r = rand.nextInt(ROWS);
            int c = rand.nextInt(COLS);
            if (!cells[r][c].isMine) {
                cells[r][c].isMine = true;
                minesPlaced++;
            }
        }
    }

    private void calculateNumbers() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (!cells[r][c].isMine) {
                    int count = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int nr = r + i, nc = c + j;
                            if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS) {
                                if (cells[nr][nc].isMine) count++;
                            }
                        }
                    }
                    cells[r][c].adjacentMines = count;
                }
            }
        }
    }

    private void resetGame() {
        gameOver = false;
        cellsRevealed = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                cells[r][c].reset();
            }
        }
        setupMines();
        calculateNumbers();
        repaint();
    }

    // This method reveals all cells (called when player hits a mine)
    private void revealAll() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                cells[r][c].revealed = true;
                if (cells[r][c].isMine) {
                    cells[r][c].setText("💣");
                    cells[r][c].setBackground(Color.RED);
                } else {
                    cells[r][c].setBackground(Color.LIGHT_GRAY);
                    if (cells[r][c].adjacentMines > 0) {
                        cells[r][c].setText("" + cells[r][c].adjacentMines);
                    } else {
                        cells[r][c].setText("");
                    }
                }
            }
        }
    }

    class Cell extends JButton {
        int row, col;
        boolean isMine = false;
        boolean revealed = false;
        int adjacentMines = 0;

        boolean isFlagged = false;

        public Cell(int r, int c) {
            row = r; col = c;
            setPreferredSize(new Dimension(40,40));
            setBackground(Color.GRAY);
            setFont(new Font("Arial", Font.BOLD, 20));
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (gameOver) return;
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        reveal();
                        if (checkWin() && !gameOver) {
                            JOptionPane.showMessageDialog(MinesweeperPanel.this, "You Win!");
                            gameOver = true;
                        }
                    }
                    else if (SwingUtilities.isRightMouseButton(e)) {
                        toggleFlag();
                    }
                }
            });
        }

        public void reveal() {
            if (revealed || isFlagged) return;
            revealed = true;
            cellsRevealed++;
            if (isMine) {
                setText("💣");
                setBackground(Color.RED);
                gameOver = true;
                JOptionPane.showMessageDialog(MinesweeperPanel.this, "Game Over! You hit a mine.");
                revealAll();  // <-- This was missing in your code, now fixed
            } else {
                setBackground(Color.LIGHT_GRAY);
                if (adjacentMines > 0) setText("" + adjacentMines);
                else revealAdjacentZeros(row, col);
            }
        }

        private void revealAdjacentZeros(int r, int c) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nr = r + i, nc = c + j;
                    if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS) {
                        Cell neighbor = cells[nr][nc];
                        if (!neighbor.revealed && !neighbor.isMine) {
                            neighbor.revealed = true;
                            cellsRevealed++;
                            neighbor.setBackground(Color.LIGHT_GRAY);
                            if (neighbor.adjacentMines > 0)
                                neighbor.setText("" + neighbor.adjacentMines);
                            else
                                revealAdjacentZeros(nr, nc);
                        }
                    }
                }
            }
        }

        public void toggleFlag() {
            if (revealed) return;
            if (isFlagged) {
                setText("");
                setBackground(Color.GRAY);
                isFlagged = false;
            } else {
                setText("⚑");
                setBackground(Color.YELLOW);
                isFlagged = true;
            }
        }

        public void reset() {
            isMine = false;
            revealed = false;
            isFlagged = false;
            adjacentMines = 0;
            setText("");
            setBackground(Color.GRAY);
        }
    }

    private boolean checkWin() {
        return cellsRevealed == (ROWS * COLS - MINES);
    }
}


    // -------------------- CALCULATOR -----------------------
    class CalculatorPanel extends JPanel {
        private JTextField display;
        private StringBuilder currentInput = new StringBuilder();

        public CalculatorPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(30, 30, 30));
            display = new JTextField();
            display.setFont(new Font("Arial", Font.BOLD, 32));
            display.setEditable(false);
            display.setBackground(Color.BLACK);
            display.setForeground(Color.GREEN);
            display.setHorizontalAlignment(JTextField.RIGHT);
            add(display, BorderLayout.NORTH);

            JPanel buttonsPanel = new JPanel(new GridLayout(4, 4, 5, 5));
            buttonsPanel.setBackground(new Color(30, 30, 30));
            String[] buttons = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", "C", "=", "+"
            };

            for (String text : buttons) {
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.BOLD, 24));
                btn.addActionListener(e -> buttonClicked(text));
                buttonsPanel.add(btn);
            }
            add(buttonsPanel, BorderLayout.CENTER);
        }

        private void buttonClicked(String text) {
            switch(text) {
                case "C":
                    currentInput.setLength(0);
                    display.setText("");
                    break;
                case "=":
                    try {
                        String expr = currentInput.toString();
                        double result = eval(expr);
                        display.setText("" + result);
                        currentInput.setLength(0);
                    } catch (Exception e) {
                        display.setText("Error");
                        currentInput.setLength(0);
                    }
                    break;
                default:
                    currentInput.append(text);
                    display.setText(currentInput.toString());
            }
        }

        // Very simple eval method (supports + - * / only, no parenthesis)
        private double eval(String expr) {
            String[] tokens = expr.split("(?=[-+*/])|(?<=[-+*/])");
            double result = Double.parseDouble(tokens[0]);
            for (int i = 1; i < tokens.length; i += 2) {
                String op = tokens[i];
                double val = Double.parseDouble(tokens[i + 1]);
                switch(op) {
                    case "+": result += val; break;
                    case "-": result -= val; break;
                    case "*": result *= val; break;
                    case "/": result /= val; break;
                }
            }
            return result;
        }
    }

    // -------------------- SIMON SAYS -----------------------
    class SimonSaysPanel extends JPanel {
        private final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        private JButton[] colorButtons = new JButton[4];
        private List<Integer> sequence = new ArrayList<>();
        private int playerStep = 0;
        private boolean playerTurn = false;

        public SimonSaysPanel() {
            setLayout(new GridLayout(2, 2, 10, 10));
            setBackground(Color.DARK_GRAY);
            for (int i = 0; i < 4; i++) {
                JButton btn = new JButton();
                btn.setBackground(COLORS[i]);
                btn.setOpaque(true);
                btn.setBorderPainted(false);
                final int idx = i;
                btn.addActionListener(e -> {
                    if (playerTurn) {
                        checkPlayerInput(idx);
                    }
                });
                colorButtons[i] = btn;
                add(btn);
            }

            startNewGame();
        }

        private void startNewGame() {
            sequence.clear();
            playerStep = 0;
            playerTurn = false;
            newRound();
        }

        private void newRound() {
            playerStep = 0;
            sequence.add(new Random().nextInt(4));
            playSequence();
        }

        private void playSequence() {
            playerTurn = false;
            Timer t = new Timer(600, null);
            final int[] i = {0};
            t.addActionListener(e -> {
                if (i[0] > 0) {
                    colorButtons[sequence.get(i[0] - 1)].setBackground(COLORS[sequence.get(i[0] - 1)]);
                }
                if (i[0] == sequence.size()) {
                    t.stop();
                    playerTurn = true;
                } else {
                    int idx = sequence.get(i[0]);
                    colorButtons[idx].setBackground(COLORS[idx].brighter());
                    i[0]++;
                }
            });
            t.start();
        }

        private void checkPlayerInput(int idx) {
            if (sequence.get(playerStep) == idx) {
                playerStep++;
                if (playerStep == sequence.size()) {
                    // player succeeded this round
                    JOptionPane.showMessageDialog(this, "Good job! Next round.");
                    newRound();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong color! Game Over.\nYour score: " + (sequence.size()-1));
                startNewGame();
            }
        }
    }

    // -------------------- HANGMAN -----------------------
    class HangmanPanel extends JPanel {
        private String[] words = {"JAVA", "SWING", "COMPUTER", "PROGRAM", "GAME", "HANGMAN", "CODE", "KEYBOARD"};
        private String word;
        private char[] guessed;
        private int attemptsLeft = 6;
        private JLabel wordLabel;
        private JLabel attemptsLabel;
        private JTextField guessField;
        private JButton guessButton;

        public HangmanPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(30, 30, 30));
            wordLabel = new JLabel("", SwingConstants.CENTER);
            wordLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
            wordLabel.setForeground(Color.CYAN);
            attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
            attemptsLabel.setFont(new Font("Arial", Font.BOLD, 18));
            attemptsLabel.setForeground(Color.LIGHT_GRAY);

            JPanel inputPanel = new JPanel();
            inputPanel.setBackground(new Color(30, 30, 30));
            guessField = new JTextField(5);
            guessField.setFont(new Font("Arial", Font.BOLD, 24));
            guessButton = new JButton("Guess");
            guessButton.setFont(new Font("Arial", Font.BOLD, 20));

            inputPanel.add(new JLabel("Enter letter: "));
            inputPanel.add(guessField);
            inputPanel.add(guessButton);

            add(wordLabel, BorderLayout.NORTH);
            add(attemptsLabel, BorderLayout.CENTER);
            add(inputPanel, BorderLayout.SOUTH);

            guessButton.addActionListener(e -> guessLetter());
            resetGame();
        }

        private void resetGame() {
            word = words[new Random().nextInt(words.length)];
            guessed = new char[word.length()];
            Arrays.fill(guessed, '_');
            attemptsLeft = 6;
            updateLabels();
        }

        private void updateLabels() {
            wordLabel.setText(String.valueOf(guessed).replaceAll("", " ").trim());
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
        }

        private void guessLetter() {
            String text = guessField.getText().toUpperCase();
            if (text.length() != 1) {
                JOptionPane.showMessageDialog(this, "Enter exactly one letter.");
                return;
            }
            char ch = text.charAt(0);
            boolean correct = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == ch) {
                    guessed[i] = ch;
                    correct = true;
                }
            }
            if (!correct) attemptsLeft--;

            guessField.setText("");
            updateLabels();

            if (String.valueOf(guessed).equals(word)) {
                JOptionPane.showMessageDialog(this, "You Win! The word was: " + word);
                resetGame();
            } else if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(this, "You Lose! The word was: " + word);
                resetGame();
            }
        }
    }

    // -------------------- COLOR REACTION CHALLENGE -----------------------
    class ColorReactionPanel extends JPanel {
        private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN};
        private JPanel colorPanel;
        private JButton startButton;
        private JLabel timeLabel;
        private Timer timer;
        private long startTime;
        private boolean waitingForClick = false;

        public ColorReactionPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(20, 20, 20));

            colorPanel = new JPanel();
            colorPanel.setBackground(Color.DARK_GRAY);
            colorPanel.setPreferredSize(new Dimension(300, 300));
            add(colorPanel, BorderLayout.CENTER);

            startButton = new JButton("Start Test");
            startButton.setFont(new Font("Arial", Font.BOLD, 20));
            add(startButton, BorderLayout.NORTH);

            timeLabel = new JLabel("Click the panel when it changes color", SwingConstants.CENTER);
            timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
            timeLabel.setForeground(Color.CYAN);
            add(timeLabel, BorderLayout.SOUTH);

            startButton.addActionListener(e -> startTest());
            colorPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (waitingForClick) {
                        long reactionTime = System.currentTimeMillis() - startTime;
                        timeLabel.setText("Reaction Time: " + reactionTime + " ms");
                        waitingForClick = false;
                        timer.stop();
                        startButton.setEnabled(true);
                    }
                }
            });
        }

        private void startTest() {
            startButton.setEnabled(false);
            timeLabel.setText("Wait for the color to change...");
            Random rand = new Random();
            int delay = 2000 + rand.nextInt(3000);

            timer = new Timer(delay, e -> {
                colorPanel.setBackground(colors[new Random().nextInt(colors.length)]);
                startTime = System.currentTimeMillis();
                waitingForClick = true;
                timeLabel.setText("Click NOW!");
                timer.stop();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameHub::new);
    }
}

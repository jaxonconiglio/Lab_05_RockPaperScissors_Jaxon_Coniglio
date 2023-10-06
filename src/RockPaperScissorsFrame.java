import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private JTextArea resultsTextArea;
    private JTextField playerWinsTextField;
    private JTextField computerWinsTextField;
    private JTextField tiesTextField;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder((BorderFactory.createTitledBorder("Actions")));

        JButton rockButton = new JButton("Rock");
        JButton scissorButton = new JButton("Scissor");
        JButton paperButton = new JButton("Paper");
        JButton quitButton = new JButton("Quit");

        rockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Rock");
            }
        });
        paperButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Paper");
            }
        });
        scissorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Scissors");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorButton);
        buttonPanel.add(quitButton);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));

        JLabel playerLabel = new JLabel("Player Wins");
        JLabel computerLabel = new JLabel("Computer Wins");
        JLabel tiesLabel = new JLabel("Tie");

        playerWinsTextField = new JTextField(5);
        computerWinsTextField = new JTextField(5);
        tiesTextField = new JTextField(5);

        playerWinsTextField.setEditable(false);
        computerWinsTextField.setEditable(false);
        tiesTextField.setEditable(false);

        statsPanel.add(playerLabel);
        statsPanel.add(playerWinsTextField);
        statsPanel.add(computerLabel);
        statsPanel.add(computerWinsTextField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesTextField);

        resultsTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane((resultsTextArea));

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];
        String result = determineWinner(playerChoice, computerChoice);

        resultsTextArea.append(playerChoice + "vs." + computerChoice + "(" + result + ")\n");

        if (result.equals("Player Wins")) {
            playerWins++;
        } else if (result.equals("Computer Wins")) {
            computerWins++;
        } else {
            ties++;
        }
        playerWinsTextField.setText(String.valueOf(playerWins));
        computerWinsTextField.setText(String.valueOf(computerWins));
        tiesTextField.setText(String.valueOf(ties));
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "Tie";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }
}
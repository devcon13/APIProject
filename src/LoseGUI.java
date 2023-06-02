import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseGUI {
    public static final int width = 1500;
    public static final int height = 950;
    public static JFrame main;
    public static int whatGame;

    public LoseGUI(int game) {
        whatGame = game;
        FiveGUI.main.dispose();
        TenGUI.main.dispose();
        TwentyGUI.main.dispose();
        new HintsGUI(5);
        HintsGUI.main.dispose();
        main = new JFrame("Better luck next time...");
        main.setSize(width, height);
        main.setLayout(new BorderLayout());
        main.setBackground(Color.YELLOW);
        JLabel lose = new JLabel("GAME OVER", JLabel.CENTER);
        lose.setFont(new Font("Trebuchet MS", Font.BOLD, 100));
        JPanel panel = new JPanel(new GridLayout(3,1));
        panel.setBackground(Color.YELLOW);
        lose.setForeground(Color.BLACK);
        panel.add(new JLabel());
        panel.add(lose);
        main.add(panel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.YELLOW);
        JButton back = new JButton("Back to Main Screen");
        back.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(new ButtonClickListener());
        back.setActionCommand("back");
        back.setPreferredSize(new Dimension(175,40));
        southPanel.add(back);
        main.add(southPanel, BorderLayout.SOUTH);
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.YELLOW);
        JButton answers = new JButton("See Answers");
        answers.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        answers.setBackground(Color.BLACK);
        answers.setForeground(Color.WHITE);
        answers.addActionListener(new ButtonClickListener());
        answers.setActionCommand("answers");
        answers.setPreferredSize(new Dimension(125,40));
        northPanel.add(answers);
        main.add(northPanel, BorderLayout.NORTH);
        main.setVisible(true);


    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("back")) {
                WelcomeGUI.whichGame.setText("");
                WelcomeGUI.gameButtonPanel.setVisible(false);
                WelcomeGUI.userInput.setText(" ");
                WelcomeGUI.main.setVisible(true);
                LoseGUI.main.dispose();
                AnswersGUI.main.dispose();
            }
            if (command.equals("answers")) {
                new AnswersGUI(LoseGUI.whatGame);
            }

        }
    }
}

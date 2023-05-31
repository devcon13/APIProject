import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseGUI {
    public static final int width = 700;
    public static final int height = 700;
    public static JFrame main;
    public static int whatGame;

    public LoseGUI(int game) {
        whatGame = game;
        FiveGUI.main.dispose();
        HintsGUI.main.dispose();
        main = new JFrame("Better luck next time...");
        main.setSize(width,height);
        main.setLayout(new BorderLayout());
        JLabel lose = new JLabel("GAME OVER", JLabel.CENTER);
        lose.setFont(new Font("Arial", Font.BOLD, 100));
        main.add(lose, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        JButton back = new JButton("Back to Main Screen");
        back.addActionListener(new ButtonClickListener());
        back.setActionCommand("back");
        back.setPreferredSize(new Dimension(175,20));
        southPanel.add(back);
        main.add(southPanel, BorderLayout.SOUTH);
        JPanel northPanel = new JPanel();
        JButton answers = new JButton("See Answers");
        answers.addActionListener(new ButtonClickListener());
        answers.setActionCommand("answers");
        answers.setPreferredSize(new Dimension(125,20));
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

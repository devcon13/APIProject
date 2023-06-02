import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinGUI {
    public static final int width = 1500;
    public static final int height = 950;
    public static JFrame main;
    public static int whatGame;

    public WinGUI(int game) {
        whatGame = game;
        FiveGUI.main.dispose();
        TenGUI.main.dispose();
        TwentyGUI.main.dispose();
        new HintsGUI(5);
        HintsGUI.main.dispose();
        main = new JFrame("You Win!");
        main.setSize(width, height);
        main.setLayout(new BorderLayout());
        main.setBackground(Color.YELLOW);
        JLabel lose = new JLabel("YOU WIN!", JLabel.CENTER);
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
                WinGUI.main.dispose();
                AnswersGUI.main.dispose();
            }
        }
    }
}

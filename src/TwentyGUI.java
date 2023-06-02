import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwentyGUI {
    public static final int width = 1500;
    public static final int height = 950;
    public static JFrame main = new JFrame("Guess " + Main.artist.name + "'s Top 20 Songs!");
    public static JTextArea userGuess = new JTextArea();
    public static JPanel panel = new JPanel();
    public JLabel instructions;
    public JLabel[] songs = new JLabel[20];
    public int guesses;
    public int correct;

    public TwentyGUI() {
        panel.removeAll();
        WelcomeGUI.main.dispose();
        new HintsGUI(20);
        HintsGUI.main.dispose();
        correct = 0;

        panel.setLayout(new GridLayout(2, 2));
        main.setSize(width, height);
        guesses = 3;

        // Top Left
        JPanel topLeft = new JPanel(new GridLayout(3, 1));
        instructions = new JLabel("<html><font color='white'>Guess " + Main.artist.name + "'s Top 20 Songs!<br/>You have <font color='yellow'>" + guesses + "<font color='white'> wrong guesses remaining.<html>", JLabel.CENTER);
        instructions.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        instructions.setForeground(Color.WHITE);
        JPanel subLeft = new JPanel();
        subLeft.setBackground(Color.BLACK);
        JButton help = new JButton("I need a hint!");
        help.addActionListener(new ButtonClickListener());
        help.setActionCommand("help");
        help.setBackground(Color.YELLOW);
        help.setForeground(Color.BLACK);
        help.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        help.setPreferredSize(new Dimension(125, 20));
        subLeft.add(help);
        topLeft.add(new JLabel());
        topLeft.add(instructions);
        topLeft.add(subLeft);
        topLeft.setBackground(Color.BLACK);
        panel.add(topLeft);

        // Top Right
        JLabel pic = new JLabel(new ImageIcon(Main.artist.pic));
        panel.setBackground(Color.BLACK);
        panel.add(pic);

        // Bottom Left
        JPanel bottomLeft = new JPanel(new GridLayout(10, 2));
        for(int i = 0; i < songs.length; i++) {
            JPanel temporary = new JPanel();
            temporary.setBackground(Color.BLACK);
            JLabel num = new JLabel((Integer.toString(i+1)) + " ");
            num.setForeground(Color.WHITE);
            num.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
            temporary.add(num);
            songs[i] = new JLabel();
            songs[i].setForeground(Color.WHITE);
            songs[i].setFont(new Font("Trebuchet MS", Font.BOLD, 25));
            temporary.add(songs[i]);
            bottomLeft.add(temporary);
        }
        bottomLeft.setBackground(Color.BLACK);
        panel.add(bottomLeft);

        // Bottom Right
        JPanel bottomRight = new JPanel(new GridLayout(5, 1));
        JLabel guess = new JLabel("Enter your guesses here! (Case Sensitive)", JLabel.CENTER);
        guess.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        guess.setForeground(Color.WHITE);
        userGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        userGuess.setBackground(Color.BLACK);
        userGuess.setForeground(Color.YELLOW);
        userGuess.setLineWrap(true);
        userGuess.setWrapStyleWord(true);
        JPanel enterPanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.setBackground(Color.YELLOW);
        enter.setForeground(Color.BLACK);
        enter.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        enterPanel.setBackground(Color.BLACK);
        enter.addActionListener(new ButtonClickListener());
        enter.setActionCommand("enter");
        enter.setPreferredSize(new Dimension(75, 20));
        enterPanel.add(enter);
        bottomRight.add(guess);
        bottomRight.add(userGuess);
        bottomRight.add(enterPanel);
        bottomRight.setBackground(Color.BLACK);
        panel.add(bottomRight);
        main.add(panel);
        main.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("enter")) {
                if (Main.artist.songMatch(userGuess.getText(), 20)) {
                    songs[Main.artist.songNumber].setText(userGuess.getText());
                    correct++;
                    int wow = 0;
                    for (int i = 0; i < 20; i++) {
                        if (songs[i].getText().equals("")) {} else {
                            wow++;
                        }
                    }
                    if (wow == 20) {
                        new WinGUI(20);
                    }
                }
                else {
                    guesses--;
                    if (guesses != 0) {
                        instructions.setText("<html><font color='white'>Guess " + Main.artist.name + "'s Top 20 Songs!<br/>You have <font color='yellow'>" + guesses + "<font color='white'> wrong guesses remaining.<html>");
                    } else {
                        new LoseGUI(20);
                    }
                }
            }
            if (command.equals("help")) {
                new HintsGUI(20);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiveGUI {
    public static final int width = 700;
    public static final int height = 700;
    public static JFrame main = new JFrame("Guess " + Main.artist.name + "'s Top 5 Songs!");
    public static JTextArea userGuess = new JTextArea();
    public static JPanel panel = new JPanel();
    public JLabel instructions;
    public JLabel[] songs = new JLabel[5];
    public int guesses;
    public int correct;

    public FiveGUI() {
        panel.removeAll();
        WelcomeGUI.main.dispose();
        correct = 0;

        panel.setLayout(new GridLayout(2, 2));
        main.setSize(width, height);
        guesses = 3;

        // Top Left
        JPanel topLeft = new JPanel(new GridLayout(3, 1));
        instructions = new JLabel("<html>Guess " + Main.artist.name + "'s Top 5 Songs!<br/>You have " + guesses + " wrong guesses remaining.<html>", JLabel.CENTER);
        JPanel subLeft = new JPanel();
        JButton help = new JButton("I need a hint!");
        help.addActionListener(new ButtonClickListener());
        help.setActionCommand("help");
        help.setPreferredSize(new Dimension(125, 20));
        subLeft.add(help);
        topLeft.add(new JLabel());
        topLeft.add(instructions);
        topLeft.add(subLeft);
        panel.add(topLeft);

        // Top Right
        JLabel pic = new JLabel(new ImageIcon(Main.artist.pic));
        panel.add(pic);

        // Bottom Left
        JPanel bottomLeft = new JPanel(new GridLayout(7, 1));
        bottomLeft.add(new JLabel());
        for(int i = 0; i < songs.length; i++) {
            JPanel temporary = new JPanel();
            temporary.add(new JLabel(Integer.toString(i+1)));
            songs[i] = new JLabel();
            temporary.add(songs[i]);
            bottomLeft.add(temporary);
        }
        bottomLeft.add(new JLabel());
        panel.add(bottomLeft);

        // Bottom Right
        JPanel bottomRight = new JPanel(new GridLayout(5, 1));
        JLabel guess = new JLabel("Enter your guesses here! (Case Sensitive)", JLabel.CENTER);
        userGuess.setFont(new Font("Arial", Font.BOLD, 25));
        userGuess.setLineWrap(true);
        userGuess.setWrapStyleWord(true);
        JPanel enterPanel = new JPanel();
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ButtonClickListener());
        enter.setActionCommand("enter");
        enter.setPreferredSize(new Dimension(75, 20));
        enterPanel.add(enter);
        bottomRight.add(guess);
        bottomRight.add(userGuess);
        bottomRight.add(enterPanel);
        panel.add(bottomRight);
        main.add(panel);
        main.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("enter")) {
              if (Main.artist.songMatch(userGuess.getText(), 5)) {
                    songs[Main.artist.songNumber].setText(userGuess.getText());
                    correct++;
                  int wow = 0;
                    for (int i = 0; i < 5; i++) {
                        if (songs[i].getText().equals("")) {} else {
                          wow++;
                        }
                    }
                    if (wow == 5) {
                        new WinGUI(5);
                    }
                }
              else {
                  guesses--;
                  if (guesses != 0) {
                      instructions.setText("<html>Guess " + Main.artist.name + "'s Top 5 Songs!<br/>You have " + guesses + " wrong guesses remaining.<html>");
                  } else {
                      new LoseGUI(5);
                  }
              }
            }
            if (command.equals("help")) {
                new HintsGUI(5);
            }
        }
    }
}

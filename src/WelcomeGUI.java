import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI {
    public static final int width = 1500;
    public static final int height = 950;

    public static JFrame main = new JFrame("Genius API Game!");
    public JPanel bottom;
    public static JPanel popUpPanel = new JPanel(new GridLayout(1,2));
    public static JPanel gameButtonPanel = new JPanel(new GridLayout(3, 3));

    public static JTextArea userInput = new JTextArea();
    static JLabel whichGame = new JLabel();
    public static JPanel panel = new JPanel();

    public WelcomeGUI(){
        WelcomeGUI.panel.removeAll();
        main.setSize(width, height);
        panel.setLayout(new GridLayout(2,1));

        // TOP HALF
        JPanel top = new JPanel(new GridLayout(4,1));
        top.add(new JLabel());
        top.add(new JLabel());
        JLabel welcome = new JLabel("GENIUS", JLabel.CENTER);
        JLabel welcome2 = new JLabel("SONG GUESSER", JLabel.CENTER);

        welcome.setFont(new Font("Trebuchet MS", Font.BOLD, 125));
        welcome.setForeground(Color.BLACK);
        welcome2.setFont(new Font("Trebuchet MS", Font.BOLD, 75));
        welcome2.setForeground(Color.BLACK);
        top.add(welcome);
        top.add(welcome2);
        top.setBackground(Color.YELLOW);
        panel.add(top);


        // BOTTOM HALF
        bottom = new JPanel(new GridLayout (8,1));

        JPanel typePanel = new JPanel();
        JLabel typeHere = new JLabel("Type in the name of your favorite artist:");
        typeHere.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        typeHere.setForeground(Color.BLACK);
        typePanel.add(typeHere);
        userInput.setBackground(Color.YELLOW);
        userInput.setForeground(Color.BLACK);
        userInput.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        typePanel.add(userInput);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 7));
        JButton go = new JButton("Continue");
        go.setActionCommand("continue");
        go.addActionListener(new ButtonClickListener());
        go.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        go.setBackground(Color.BLACK);
        go.setForeground(Color.WHITE);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(go); // reformat to change go props
        for(int i=1; i<=7; i++){
            buttonPanel.add(new JLabel());
        }
        bottom.add(typePanel);
        typePanel.setBackground(Color.YELLOW);
        bottom.add(buttonPanel);
        buttonPanel.setBackground(Color.YELLOW);
        panel.add(bottom);
        bottom.setBackground(Color.YELLOW);
        main.add(panel);

        main.setVisible(true);

        JButton five = new JButton("5");
        five.setActionCommand("5");
        five.addActionListener(new ButtonClickListener());
        five.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        five.setBackground(Color.BLACK);
        five.setForeground(Color.WHITE);
        JButton ten = new JButton("10");
        ten.setActionCommand("10");
        ten.addActionListener(new ButtonClickListener());
        ten.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        ten.setBackground(Color.BLACK);
        ten.setForeground(Color.WHITE);
        JButton twenty = new JButton("20");
        twenty.setActionCommand("20");
        twenty.addActionListener(new ButtonClickListener());
        twenty.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        twenty.setBackground(Color.BLACK);
        twenty.setForeground(Color.WHITE);

        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(five);
        gameButtonPanel.add(ten);
        gameButtonPanel.add(twenty);
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());

        popUpPanel.setBackground(Color.YELLOW);
        gameButtonPanel.setBackground(Color.YELLOW);
        popUpPanel.add(whichGame);
        popUpPanel.add(gameButtonPanel);
        bottom.add(popUpPanel);

        popUpPanel.setVisible(false);
    }

    public void popUp() {

        whichGame.setText("How many songs do you think you can guess?");
        whichGame.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        popUpPanel.setVisible(true);
        gameButtonPanel.setVisible(true);
        main.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("continue")) {
                Main.newArtist(userInput.getText());
                try {
                    popUp();
                    Main.artist.firstSearch();
                    System.out.println("woah");
                } catch (Exception ex) {
                }
            }
            if (command.equals("5")) {
                new FiveGUI();
            }
            if (command.equals("10")) {
                new TenGUI();
            }
            if (command.equals("20")) {
                new TwentyGUI();

            }
        }
    }
}

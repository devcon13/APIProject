import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI {
    public static final int width = 700;
    public static final int height = 700;

    public static JFrame main = new JFrame("hi");
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
        JPanel top = new JPanel(new GridLayout(2,1));
        top.add(new JLabel());
        JLabel welcome = new JLabel("WELCOME", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 100));
        top.add(welcome);
        panel.add(top);

        // BOTTOM HALF
        bottom = new JPanel(new GridLayout (8,1));

        JPanel typePanel = new JPanel();
        JLabel typeHere = new JLabel("Type in the name of your favorite artist:");
        typePanel.add(typeHere);
        typePanel.add(userInput);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 5));
        JButton go = new JButton("Continue");
        go.setActionCommand("continue");
        go.addActionListener(new ButtonClickListener());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(go); // reformat to change go props
        for(int i=1; i<=7; i++){
            buttonPanel.add(new JLabel());
        }
        bottom.add(typePanel);
        bottom.add(buttonPanel);
        panel.add(bottom);
        main.add(panel);


        main.setVisible(true);

        JButton five = new JButton("5");
        five.setActionCommand("5");
        five.addActionListener(new ButtonClickListener());
        JButton ten = new JButton("10");
        ten.setActionCommand("10");
        ten.addActionListener(new ButtonClickListener());
        JButton twenty = new JButton("20");
        twenty.setActionCommand("20");
        twenty.addActionListener(new ButtonClickListener());

        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(five);
        gameButtonPanel.add(ten);
        gameButtonPanel.add(twenty);
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());
        gameButtonPanel.add(new JLabel());

        popUpPanel.add(whichGame);
        popUpPanel.add(gameButtonPanel);
        bottom.add(popUpPanel);

        popUpPanel.setVisible(false);
    }

    public void popUp() {

        whichGame.setText("How many songs do you think you can guess?");
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

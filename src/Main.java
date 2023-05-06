import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Main {

    public JFrame mainFrame;
    public JPanel topPanel;
    public JPanel lowerPanel;
    public JLabel pokeName, statsLabel;
    public JButton lArrow, rArrow;
    public JTextArea stats;
    public JTextArea pokemon;

    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        prepareGUI();
    }



    public void prepareGUI(){
        mainFrame = new JFrame("Pokedeck");
        mainFrame.setLayout(new GridLayout(2,1));

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        pokeName = new JLabel("Name of Pokemon", JLabel.CENTER);
        lArrow = new JButton("<");
        rArrow = new JButton(">");
        pokemon = new JTextArea("image of pokemon goes here");
        topPanel.add(pokeName, BorderLayout.NORTH);
        topPanel.add(lArrow, BorderLayout.WEST);
        topPanel.add(rArrow, BorderLayout.EAST);
        topPanel.add(pokemon, BorderLayout.CENTER);


        lowerPanel = new JPanel();
        lowerPanel.setLayout(new BorderLayout());
        statsLabel = new JLabel("Stats", JLabel.LEFT);
        stats = new JTextArea("stats go here");
        lowerPanel.add(statsLabel, BorderLayout.NORTH);
        lowerPanel.add(stats, BorderLayout.CENTER);


        mainFrame.add(topPanel);
        mainFrame.add(lowerPanel);
        mainFrame.setSize(800,800);
        mainFrame.setVisible(true);


    }
}
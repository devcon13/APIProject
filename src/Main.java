import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Main {

    public JFrame mainFrame;
    public JPanel topPanel;
    public JPanel lowerPanel;
    public JLabel pokeName, statsLabel;
    public JButton lArrow, rArrow;
    public JTextArea stats;
    public JTextArea pokemon;

    public static void main(String[] args) {new Main();}
    public Main(){
        prepareGUI();
        String pokemonName = "pikachu"; // Change this to the desired Pokémon name

        try {
            // Make a GET request to the PokeAPI
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokemonName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            // Here, you can extract the name and stats of the Pokémon
            // For simplicity, let's assume you only want the base stats
            String name = response.toString().split("\"name\":\"pikachu\"")[1].split("\"")[0];
            String stats = response.toString().split("\"stats\":")[1].split("],")[0];

            // Print the name and stats
            System.out.println("Name: " + name);
            System.out.println("Stats: " + stats);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
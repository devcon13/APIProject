import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0


/** API ideas:
 * genius
 *
 * ID: xOImlcaeWwXBirWtcksbs2Jue72Zmx4QqLM6Wct7sMwEnAEsGSwdMXTZ9mHkk9R7
 * key: QAtro-IQjvUmz8JWDkj7nU_MIwANJwf6VAxNvyGG8JAS-MgfDblPBgvFLrqcDxDRLDpXd3XdexigASC59nsghw
 * access token: EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv
 *
 *
 * pyongs = upvotes
 * guess most popular songs?
 *
 * pageviews
 *
 *
 */


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

// Program for print data in JSON format.
public class Main {
    public String searchTerm = "";
    public JFrame welcomeFrame;
    public JFrame mainFrame;

    public JPanel panel = new JPanel();
    public JTextArea type = new JTextArea();
    public JPanel thisPanel = new JPanel(new GridLayout(1,2));
    public JLabel howMany = new JLabel();
    public JButton go = new JButton("Continue");
    public String[] topfive;
    public String[] topten;
    public String[] toptwenty;
    public boolean huh = false;

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        new Main();
        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));


    }
    public Main() throws ParseException {
        topfive = new String[5];
        topten = new String[10];
        toptwenty = new String[20];
        pull("Lorde3");
        welcomeFrame = new JFrame("Genius API Game!");
        welcomeFrame.setSize(700,700);
        welcomeGUI();
    }


    public void pull(String search) throws ParseException {

        String output = "abc";
        String totlaJson="";

        String replace = search.replace(" ","%20");


        try {

            URL url = new URL("https://api.genius.com/search?q="+search+"&access_token=EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv");
           // URL url = new URL("https://api.genius.com/search?q=Taylor%20Swift&access_token=EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv");



            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                if(output.contains("[{\"name\":")) {
                    System.out.println(output);
                }
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }



        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject artist = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(artist+"hi");

        org.json.simple.JSONObject response1 = (org.json.simple.JSONObject) artist.get("response");
        System.out.println("whatt");
        org.json.simple.JSONArray test = (org.json.simple.JSONArray) response1.get("hits");
        System.out.println("whatt");
        if(test.isEmpty()) {
            howMany.setText("Artist not found.");
            welcomeFrame.setVisible(true);
            huh = true;
        } else {
            huh = false;
            org.json.simple.JSONObject hii = (org.json.simple.JSONObject) test.get(1);
            org.json.simple.JSONObject hii2 = (org.json.simple.JSONObject) hii.get("result");
            org.json.simple.JSONObject primArtist = (org.json.simple.JSONObject) hii2.get("primary_artist");

            try {
                URL url = new URL("https://api.genius.com" + primArtist.get("api_path") + "/songs?sort=popularity&access_token=EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv");
                pull2(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }

            //System.out.println(person.getInt("key"));



    }

    public void pull2(URL newURL) throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            HttpURLConnection conn = (HttpURLConnection) newURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                if(output.contains("[{\"name\":")) {
                    System.out.println(output);
                }
                totlaJson+=output;
            }

            conn.disconnect();


            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject artistSongs = (org.json.simple.JSONObject) parser.parse(totlaJson);
            org.json.simple.JSONObject response1 = (org.json.simple.JSONObject) artistSongs.get("response");
            org.json.simple.JSONArray test = (org.json.simple.JSONArray) response1.get("songs");
            System.out.println("hi");
            for (int i=0; i < test.size(); ++i) {
                org.json.simple.JSONObject aah = (org.json.simple.JSONObject) test.get(i);
                System.out.println(aah.get("title"));
                toptwenty[i] = (String) aah.get("title");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void welcomeGUI(){
        welcomeFrame.setLayout(new GridLayout(2,1));
        JPanel aaah = new JPanel(new GridLayout(2,1));
        aaah.add(new JLabel());
        JLabel welcome = new JLabel("WELCOME", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 100));
        aaah.add(welcome);
        welcomeFrame.add(aaah);
        JPanel subPanel = new JPanel();
        JPanel subSubPanel = new JPanel(new GridLayout(2, 5));

        JLabel label = new JLabel("Type in the name of your favorite artist:");
        go.setActionCommand("continue");
        go.addActionListener(new ButtonClickListener());

        panel.setLayout(new GridLayout (8,1));
        subPanel.add(label);
        subPanel.add(type);

        subSubPanel.add(new JLabel());
        subSubPanel.add(new JLabel());
        subSubPanel.add(go);
        for(int i=1; i<=7; i++){
            subSubPanel.add(new JLabel());
        }

        panel.add(subPanel);
        panel.add(subSubPanel);

        thisPanel.add(howMany);
        panel.add(thisPanel);

        welcomeFrame.add(panel);
        welcomeFrame.setVisible(true);
    }

    public void popUpGUI() {
        howMany.setText("How many songs do you think you can guess?");
        JButton five = new JButton("5");
        five.setActionCommand("5");
        five.addActionListener(new ButtonClickListener());

        JButton ten = new JButton("10");
        JButton twenty = new JButton("20");

        JPanel extraPanel = new JPanel(new GridLayout(3, 3));
        extraPanel.add(new JLabel());
        extraPanel.add(new JLabel());
        extraPanel.add(new JLabel());
        extraPanel.add(five);
        extraPanel.add(ten);
        extraPanel.add(twenty);
        extraPanel.add(new JLabel());
        extraPanel.add(new JLabel());
        extraPanel.add(new JLabel());

        thisPanel.add(extraPanel);

        panel.add(thisPanel);

        welcomeFrame.setVisible(true);

    }

    public void fiveGUI() {
        welcomeFrame.dispose();
        mainFrame = new JFrame("Guess!");
        mainFrame.setSize(700,700);
        mainFrame.setVisible(true);
    }


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String command = e.getActionCommand();
                if (command.equals("continue")) {
                    pull(type.getText());
                    if(huh == false) {
                        popUpGUI();
                        //go.setActionCommand("disabled");
                    }
                }
                if (command.equals("5")){
                    System.out.println("hi");
                    fiveGUI();
                }
                if (command.equals("10")){

                }
                if (command.equals("20")){

                }
            } catch (Exception ex) {

            }
        }
    }
}


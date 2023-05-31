import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Artist {
    public Image pic;
    public String name;
    public String[] songs;
    public int songNumber;

    public Artist(String name) {
        this.name = name;
        songs = new String[20];
    }

    public void firstSearch() throws ParseException {
        // GET JSON
        String output = "";
        String totalJson = "";
        String replace = name.replace(" ", "%20"); // replace spaces with stuff readable to HTML
        try {
            URL url = new URL("https://api.genius.com/search?q=" + replace + "&access_token=EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                if (output.contains("[{\"name\":")) {
                    System.out.println(output);
                }
                totalJson += output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // PARSING
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject artist = (org.json.simple.JSONObject) parser.parse(totalJson);
        org.json.simple.JSONObject response1 = (org.json.simple.JSONObject) artist.get("response");
        org.json.simple.JSONArray test = (org.json.simple.JSONArray) response1.get("hits");

        if (test.isEmpty()) { // check if valid artist
            WelcomeGUI.whichGame.setText("Artist not found.");
            WelcomeGUI.gameButtonPanel.setVisible(false);
            WelcomeGUI.main.setVisible(true);

        } else {
            org.json.simple.JSONObject hii = (org.json.simple.JSONObject) test.get(1);
            org.json.simple.JSONObject hii2 = (org.json.simple.JSONObject) hii.get("result");
            org.json.simple.JSONObject primArtist = (org.json.simple.JSONObject) hii2.get("primary_artist");

            try { // get picture and resize
                pic = ImageIO.read(new URL((String) primArtist.get("image_url"))).getScaledInstance(WelcomeGUI.width / 2, WelcomeGUI.height / 2, Image.SCALE_DEFAULT);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fetchSongs(new URL("https://api.genius.com" + primArtist.get("api_path") + "/songs?sort=popularity&access_token=EA74kgu6YMC3BQ53x-cGY-aNiy-pdijBQoQD0vd_qG-fdWUnD9XWVQ93nWP1EdMv"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void fetchSongs(URL newURL) throws ParseException {
        // GET JSON
        String output = "";
        String totalJson = "";
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
                if (output.contains("[{\"name\":")) {
                    System.out.println(output);
                }
                totalJson += output;
            }
            conn.disconnect();


            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject artistSongs = (org.json.simple.JSONObject) parser.parse(totalJson);
            org.json.simple.JSONObject response1 = (org.json.simple.JSONObject) artistSongs.get("response");
            org.json.simple.JSONArray test = (org.json.simple.JSONArray) response1.get("songs");
            System.out.println("hi");
            for (int i = 0; i < test.size(); ++i) {
                org.json.simple.JSONObject aah = (org.json.simple.JSONObject) test.get(i);
                System.out.println(aah.get("title"));
                songs[i] = (String) (aah.get("title"));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean songMatch(String guess, int songLimit) {
        for (int i = 0; i < songLimit; i++) {
            if (guess.equals(songs[i])) {
                songNumber = i;
                return true;
            }
        }
        return false;
    }
}

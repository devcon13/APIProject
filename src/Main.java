
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

// Program for print data in JSON format.
public class Main {
    public static Artist artist;

    public static void main(String args[]) {

        new Main();
        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));
    }

    public Main() {
        new WelcomeGUI();
        new HintsGUI(5);
        //new AnswersGUI(5);
    }
    public static void newArtist(String name){
        artist = new Artist(name);
    }
}


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HintsGUI {
    public static final int width = 1500;
    public static final int height = 950;
    public static JFrame main;
    public static JPanel panel = new JPanel(new BorderLayout());
    public HintsGUI(int game) {
        panel.removeAll();
        panel.setBackground(Color.YELLOW);
        main = new JFrame("Hints");
        main.setSize(width, height);
        JLabel header = new JLabel("THE FIRST LETTER OF EACH SONG:", JLabel.CENTER);
        header.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        header.setForeground(Color.BLACK);
        panel.add(header, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(game, 1));
        centerPanel.setBackground(Color.YELLOW);
        for (int i = 0; i < game; i++) {
            JLabel song = new JLabel((i + 1) + ". " + Main.artist.songs[i].charAt(0), JLabel.CENTER);
            song.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
            song.setForeground(Color.BLACK);
            centerPanel.add(song);
        }

        panel.add(centerPanel);


        main.add(panel);
        main.setVisible(true);
    }
}

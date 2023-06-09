import javax.swing.*;
import java.awt.*;

public class AnswersGUI {
    public static final int width = 1500;
    public static final int height = 950;
    public static JFrame main;
    public static JPanel panel = new JPanel(new BorderLayout());

    public AnswersGUI(int game) {
        panel.removeAll();
        panel.setBackground(Color.YELLOW);
        main = new JFrame("Answers");
        main.setSize(width, height);
        JLabel header = new JLabel("ANSWERS", JLabel.CENTER);
        header.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        panel.add(header, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(game, 1));
        centerPanel.setBackground(Color.YELLOW);
        for (int i = 0; i < game; i++) {
            JLabel song = new JLabel((i + 1) + ". " + Main.artist.songs[i], JLabel.CENTER);
            song.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
            centerPanel.add(song);
        }

        panel.add(centerPanel);


        main.add(panel);
        main.setVisible(true);
    }
}

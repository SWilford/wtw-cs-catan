import javax.swing.*;
public class catanDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setSize(750, 750);
        frame.setLocation(0, 0);
        catanGraphics game = new catanGraphics();
        frame.setContentPane(game);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

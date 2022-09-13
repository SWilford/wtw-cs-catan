import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class catanGraphics extend JPanel{
    private JButton startButton, rules, settings, stats;
    private JLabel title;

    public startingScreen()
    {
        //making buttons
        startButton = new JButton("Start");
        rules = new JButton("Rules");
        settings = new JButton("Settings");
        stats = new JButton("Stats");
        title = new JLabel("The Settlers of Catan");
        setLayout(new FlowLayout);
        add(title);
        add(rules);
        add(settings);
        add(startButton);
        add(stats);
    }
}

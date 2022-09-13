import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class catanGraphics extends JPanel{
    private JButton startButton, rules, settings, stats;
    private JLabel title;

    public catanGraphics()
    {
        //making buttons & action listeners
        startButton = new JButton("Start");
        startButton.addActionListener(new startClicked());
        rules = new JButton("Rules");
        rules.addActionListener(new rulesClicked());
        settings = new JButton("Settings");
        settings.addActionListener(new settingsClicked());
        stats = new JButton("Stats");
        stats.addActionListener(new statsClicked());
        title = new JLabel("The Settlers of Catan");
        setLayout(new FlowLayout());
        add(title);
        add(rules);
        add(settings);
        add(startButton);
        add(stats);
    }


    //Action listeners
    private class startClicked implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
    private class rulesClicked implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
    private class statsClicked implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
    private class settingsClicked implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
}
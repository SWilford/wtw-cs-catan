import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    private ImageIcon titlePic = new ImageIcon("images/title.png");
    private ImageIcon startBack = new ImageIcon("images/background.gif");
    private ImageIcon rulesButton = new ImageIcon("images/rulesbut.png");
    private ImageIcon rulesHighlighted = new ImageIcon("images/rulesbutHL.png");
    private static final int SIZE = 500;
    private int currentScreen; //home = 1, rules = 2
    private static final int textSize = 25;
    private static final int DELAY = 1;
    private static Timer t;
    private static int frame;
    private rulebook rules;
    private catanButton[] buttons = new catanButton[3];
    protected static int mouseX;
    protected static int mouseY;

    private String header, ruleText;
    public catanGraphics()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;
        Shape rect = new Rectangle(650, SIZE - 100, 75, 50);
        Shape rect1 = new Rectangle(550, 400, 75, 50);
        Shape rect2 = new Rectangle(650, 500, 75, 50);
        buttons[0] = new catanButton(rect, "rules", rulesHighlighted, rulesButton);
        buttons[1] = new catanButton(rect1, "back", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[2] = new catanButton(rect2, "settings", Color.YELLOW, Color.RED, Color.BLACK);
        currentScreen = 1;
    }
    public void showGame(Graphics g)
    {
        //start screen
        if(currentScreen == 1) {
            g.drawImage(startBack.getImage(), 0,0, 1366, 768, null);
            buttons[0].drawButton(g);
            buttons[2].drawButton(g);
            g.drawImage(titlePic.getImage(), 450, 100, titlePic.getIconWidth(), titlePic.getIconHeight(), null);

        }
        else if(currentScreen == 2) //rules screen
        {
            header = "Rules";
            ruleText = "Catan is a game";
            buttons[1].drawButton(g);
            g.drawString(header, 650, 50);
            g.drawString(ruleText, 550, 200);
            repaint();
        }
        else if(currentScreen == 3) //settings
        {
            header = "Settings";
            g.drawString(header, 400, 50);
            buttons[1].drawButton(g);
            repaint();
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showGame(g);					//draw the contents of the array board on the screen
    }

    public void mousePressed(MouseEvent e)
    {}
    public void mouseReleased(MouseEvent e)
    {}
    public void mouseEntered(MouseEvent e)
    {}
    public void mouseExited(MouseEvent e)
    {}
    public void mouseDragged(MouseEvent e)
    {}

    //mouse stuff for buttons
    public void mouseClicked(MouseEvent e)
    {
        int button = e.getButton();
        if(button == MouseEvent.BUTTON1)
        {
            for(catanButton b:buttons)
            {
                if(b.getShape().contains(mouseX, mouseY))
                {
                    if(b.getTitle().equals("rules"))
                    {
                        currentScreen = 2;
                        repaint();
                    }
                    else if(b.getTitle().equals("back"))
                    {
                        currentScreen = 1;
                        repaint();
                    }
                    else if(b.getTitle().equals("settings"))
                    {
                        currentScreen = 3;
                        repaint();
                    }
                }
            }
        }
        repaint();
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        for(catanButton b:buttons)
        {
            if(b.getShape().contains(mouseX, mouseY))
            {
                b.highlight();
            }
            else
                b.unHighlight();
        }
        repaint();
    }

}

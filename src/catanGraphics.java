import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    private ImageIcon titlePic = new ImageIcon("images/title.png");
    private static final int SIZE = 500;
    private static final int textSize = 25;
    private static final int DELAY = 1;
    private static Timer t;
    private static int frame;
    private catanButton[] buttons = new catanButton[1];
    protected static int mouseX;
    protected static int mouseY;

    private JLabel header;
    public catanGraphics()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;
        Shape rect = new Rectangle(250, SIZE - 100, 75, 50);
        buttons[0] = new catanButton(rect, "rules", Color.YELLOW, Color.RED, Color.BLACK);
    }
    public void showGame(Graphics g)
    {
        for(catanButton b:buttons)
        {
            b.drawButton(g);
        }
        g.drawImage(titlePic.getImage(), 100, 100, titlePic.getIconWidth(), titlePic.getIconHeight(), null);
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
                        showRules();
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
    public void showRules()
    {
        removeAll();
        header = new JLabel("rules");
        add(header);
        repaint();
    }
}

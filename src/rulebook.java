import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rulebook extends JPanel implements MouseListener, MouseMotionListener{
    private ImageIcon page1 = new ImageIcon("images/page1-01.png");
    private ImageIcon page2 = new ImageIcon("images/page1-02.png");
    private ImageIcon page3 = new ImageIcon("images/page1-03.png");
    private ImageIcon page4 = new ImageIcon("images/page1-04.png");
    private ImageIcon page5 = new ImageIcon("images/page1-05.png");
    private ImageIcon page6 = new ImageIcon("images/page1-06.png");
    private ImageIcon page7 = new ImageIcon("images/page1-07.png");
    private ImageIcon page8 = new ImageIcon("images/page1-08.png");
    private ImageIcon page9 = new ImageIcon("images/page1-09.png");
    private ImageIcon page10 = new ImageIcon("images/page1-10.png");
    private ImageIcon page11 = new ImageIcon("images/page1-11.png");
    private ImageIcon page12 = new ImageIcon("images/page1-12.png");
    private ImageIcon page13 = new ImageIcon("images/page1-13.png");
    private ImageIcon page14 = new ImageIcon("images/page1-14.png");
    private ImageIcon page15 = new ImageIcon("images/page1-15.png");
    private ImageIcon page16 = new ImageIcon("images/page1-16.png");

    private static final int SIZE = 500;

    protected static int mouseX;
    protected static int mouseY;
    private int currentPage;
    private catanButton[] buttons = new catanButton[2];


    public rulebook()
    {
            addMouseListener(this);
            addMouseMotionListener(this);
            mouseX = SIZE/2;
            mouseY = SIZE/2;
            Shape rect1 = new Rectangle(100, 400, 75, 50);
            Shape rect2 = new Rectangle(550, 400, 75, 50);

            buttons[1] = new catanButton(rect1, "back", Color.WHITE, Color.GRAY, Color.BLACK);
            buttons[0] = new catanButton(rect2, "next", Color.WHITE, Color.GRAY, Color.BLACK);
            currentPage = 1;
    }

    public void showRules(Graphics g)
    {
        if(currentPage == 1)
        {
            g.drawImage(page1.getImage(), 400, 100, 400, 700, null);
            buttons[0].drawButton(g);
        }
        else if(currentPage == 2)
        {
            g.drawImage(page2.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page3.getImage(), 200+page2.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 3)
        {
            g.drawImage(page4.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page5.getImage(), 200+page4.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 4)
        {
            g.drawImage(page6.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page7.getImage(), 200+page6.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 5)
        {
            g.drawImage(page8.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page9.getImage(), 200+page8.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 6) {
            g.drawImage(page10.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page11.getImage(), 200 + page10.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 7)
        {
            g.drawImage(page12.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page13.getImage(), 200+page12.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else if(currentPage == 8) {
            g.drawImage(page14.getImage(), 200, 100, 400, 700, null);
            g.drawImage(page15.getImage(), 200 + page14.getIconWidth(), 100, 400, 700, null);
            buttons[0].drawButton(g);
            buttons[1].drawButton(g);
        }
        else {
            g.drawImage(page16.getImage(), 400, 100, 400, 700, null);
            buttons[1].drawButton(g);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showRules(g);					//draw the contents of the array board on the screen
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
                    if(b.getTitle().equals("back"))
                    {
                        currentPage -= 1;
                        repaint();
                    }
                    else if(b.getTitle().equals("next"))
                    {
                        currentPage += 1;
                        repaint();
                    }
                }
            }
        }
        repaint();
    }
    public boolean stillRules()
    {
        if(currentPage != 0)
        {
            return true;
        }
        else {
            return false;
        }
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

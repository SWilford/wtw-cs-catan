import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rulebook extends JPanel{
    private final ImageIcon page1 = new ImageIcon("images/page1-01.png");
    private final ImageIcon page2 = new ImageIcon("images/page1-02.png");
    private final ImageIcon page3 = new ImageIcon("images/page1-03.png");
    private final ImageIcon page4 = new ImageIcon("images/page1-04.png");
    private final ImageIcon page5 = new ImageIcon("images/page1-05.png");
    private final ImageIcon page6 = new ImageIcon("images/page1-06.png");
    private final ImageIcon page7 = new ImageIcon("images/page1-07.png");
    private final ImageIcon page8 = new ImageIcon("images/page1-08.png");
    private final ImageIcon page9 = new ImageIcon("images/page1-09.png");
    private final ImageIcon page10 = new ImageIcon("images/page1-10.png");
    private final ImageIcon page11 = new ImageIcon("images/page1-11.png");
    private final ImageIcon page12 = new ImageIcon("images/page1-12.png");
    private final ImageIcon page13 = new ImageIcon("images/page1-13.png");
    private final ImageIcon page14 = new ImageIcon("images/page1-14.png");
    private final ImageIcon page15 = new ImageIcon("images/page1-15.png");
    private final ImageIcon page16 = new ImageIcon("images/page1-16.png");

    private static final int SIZE = 500;

    protected static int mouseX;
    protected static int mouseY;
    private int currentPage;
    private final catanButton[] buttons = new catanButton[2];


    public rulebook()
    {
            currentPage = 1;
    }

    public void showRules(Graphics g)
    {
        if(currentPage == 1)
        {
            g.drawImage(page1.getImage(), 475, 25, 550, 700, null);

        }
        else if(currentPage == 2)
        {
            g.drawImage(page2.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page3.getImage(), 750, 25, 550, 700, null);
        }
        else if(currentPage == 3)
        {
            g.drawImage(page4.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page5.getImage(), 750, 25, 550, 700, null);
        }
        else if(currentPage == 4)
        {
            g.drawImage(page6.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page7.getImage(), 750, 25, 550, 700, null);

        }
        else if(currentPage == 5)
        {
            g.drawImage(page8.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page9.getImage(), 750, 25, 550, 700, null);

        }
        else if(currentPage == 6) {
            g.drawImage(page10.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page11.getImage(), 750, 25, 550, 700, null);

        }
        else if(currentPage == 7)
        {
            g.drawImage(page12.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page13.getImage(), 750, 25, 550, 700, null);

        }
        else if(currentPage == 8) {
            g.drawImage(page14.getImage(), 200, 25, 550, 700, null);
            g.drawImage(page15.getImage(), 750, 25, 550, 700, null);

        }
        else if(currentPage == 9){
            g.drawImage(page16.getImage(), 475, 25, 550, 700, null);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showRules(g);					//draw the contents of the array board on the screen
    }


    public void nextPage()
    {
        if(currentPage != 9)
            currentPage++;

        repaint();
    }
    public void prevPage()
    {
        if(currentPage != 1)
            currentPage--;

        repaint();
    }



}

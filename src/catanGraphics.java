import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    private final ImageIcon titlePic = new ImageIcon("images/title.png");
    private final ImageIcon startBack = new ImageIcon("images/background.gif");
    private final ImageIcon emptyBoard = new ImageIcon("images/blankcatanboard.png");
    private final ImageIcon desertTile = new ImageIcon("images/desertcatantile.png");
    private final ImageIcon mountainTile = new ImageIcon("images/mountainscatantile.png");
    private final ImageIcon fieldTile = new ImageIcon("images/fieldscatantile.png");
    private final ImageIcon forestTile = new ImageIcon("images/forestcatantile.png");
    private final ImageIcon hillTile = new ImageIcon("images/hillscatantile.png");
    private final ImageIcon pastureTile = new ImageIcon("images/pasturecatantile.png");

    private final board board = new board();

    private static final int SIZE = 500;
    private int currentScreen; //home = 1, rules = 2

    private final catanButton[] buttons = new catanButton[5];
    protected static int mouseX;
    protected static int mouseY;

    public catanGraphics()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseX = SIZE/2;
        mouseY = SIZE/2;
        Shape rect = new Rectangle(650, SIZE - 100, 75, 50);
        Shape rect1 = new Rectangle(550, 400, 75, 50);
        Shape rect2 = new Rectangle(650, 500, 75, 50);
        Shape rect3 = new Rectangle(500, 300, 150, 100);
        Shape rect4 = new Rectangle(100, 600, 75, 50);
        ImageIcon rulesButton = new ImageIcon("images/rulesbut.png");
        ImageIcon rulesHighlighted = new ImageIcon("images/rulesbutHL.png");
        buttons[0] = new catanButton(rect, "rules", rulesHighlighted, rulesButton);
        buttons[1] = new catanButton(rect1, "back", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[2] = new catanButton(rect2, "settings", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[3] = new catanButton(rect3, "Start", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[4] = new catanButton(rect4, "quit", Color.YELLOW, Color.RED, Color.BLACK);
        currentScreen = 1;
    }
    public void showGame(Graphics g)
    {
        //start screen
        String header;
        if(currentScreen == 1) {
            g.drawImage(startBack.getImage(), 0,0, 1366, 768, null);
            buttons[0].drawButton(g);
            buttons[3].drawButton(g);
            buttons[2].drawButton(g);
            buttons[4].drawButton(g);
            g.drawImage(titlePic.getImage(), 450, 100, titlePic.getIconWidth(), titlePic.getIconHeight(), null);

        }
        else if(currentScreen == 2) //rules screen
        {
            header = "Rules";
            String ruleText = "Catan is a game";
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
        else if(currentScreen == 4)
        {
                drawBoard(g);
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
                    switch (b.getTitle()) {
                        case "rules" -> {
                            currentScreen = 2;
                            repaint();
                        }
                        case "back" -> {
                            currentScreen = 1;
                            repaint();
                        }
                        case "settings" -> {
                            currentScreen = 3;
                            repaint();
                        }
                        case "Start" -> {
                            currentScreen = 4;
                            repaint();
                        }
                        case "quit" -> System.exit(0);
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

    public void drawBoard(Graphics g)
    {
        int x;
        int y = 100;
        g.drawImage(emptyBoard.getImage(), 250, 25, 832, 702, null);
        x = 478;
        for(int c1 = 2; c1 < 5; c1++) {
            if(board.getTileType(1, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(1, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(1, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(1, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(1, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(1, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        }
        y+= 103;
        x = 416;
        for(int c1 = 2; c1 < 6; c1++) {
            if(board.getTileType(2, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(2, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(2, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(2, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(2, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(2, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        }
        y+= 103;
        x = 356;
        for(int c1 = 1; c1 < 6; c1++) {
            if(board.getTileType(3, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(3, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(3, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(3, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(3, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(3, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        }
        y+= 103;
        x = 416;
        for(int c1 = 2; c1 < 6; c1++) {
            if(board.getTileType(4, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(4, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(4, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(4, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(4, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(4, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        }
        y+= 103;
        x = 478;
        for(int c1 = 2; c1 < 5; c1++) {
            if(board.getTileType(5, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(5, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(5, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(5, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(5, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(board.getTileType(5, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        }


        repaint();


    }
}

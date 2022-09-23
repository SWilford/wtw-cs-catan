import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    //declaring all images
    private final ImageIcon titlePic = new ImageIcon("images/title.png");
    private final ImageIcon startBack = new ImageIcon("images/background.gif");
    private final ImageIcon emptyBoard = new ImageIcon("images/blankcatanboard.png");
    private final ImageIcon desertTile = new ImageIcon("images/desertcatantile.png");
    private final ImageIcon mountainTile = new ImageIcon("images/mountainscatantile.png");
    private final ImageIcon fieldTile = new ImageIcon("images/fieldscatantile.png");
    private final ImageIcon forestTile = new ImageIcon("images/forestcatantile.png");
    private final ImageIcon hillTile = new ImageIcon("images/hillscatantile.png");
    private final ImageIcon pastureTile = new ImageIcon("images/pasturecatantile.png");

    private final board board = new board(); //creating board

    private static final int SIZE = 500;
    private int currentScreen; //home = 1, rules = 2, etc. Allows us to only use 1 panel

    private final catanButton[] buttons = new catanButton[5]; //array of all buttons, just add to the array length if needed
    protected static int mouseX; //position of mouse on X
    protected static int mouseY; //position of mouse on Y

    //constructor
    public catanGraphics()
    {
        addMouseListener(this); //mouse
        addMouseMotionListener(this); //mouse
        mouseX = SIZE/2; //mouse location
        mouseY = SIZE/2; //mouse location
        //shapes of the buttons
        Shape rect = new Rectangle(650, SIZE - 100, 75, 50);
        Shape rect1 = new Rectangle(550, 400, 75, 50);
        Shape rect2 = new Rectangle(650, 500, 75, 50);
        Shape rect3 = new Rectangle(500, 300, 150, 100);
        Shape rect4 = new Rectangle(100, 600, 75, 50);
        ImageIcon rulesButton = new ImageIcon("images/rulesbut.png"); //rules
        ImageIcon rulesHighlighted = new ImageIcon("images/rulesbutHL.png"); //rules hightlighted
        //declaration of all buttons
        buttons[0] = new catanButton(rect, "rules", rulesHighlighted, rulesButton);
        buttons[1] = new catanButton(rect1, "back", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[2] = new catanButton(rect2, "settings", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[3] = new catanButton(rect3, "Start", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[4] = new catanButton(rect4, "quit", Color.YELLOW, Color.RED, Color.BLACK);
        currentScreen = 1; //sets to start screen
    }

    //actual drawing of game
    public void showGame(Graphics g)
    {
        //start screen
        String header;
        if(currentScreen == 1) {
            g.drawImage(startBack.getImage(), 0,0, 1366, 768, null); //draws background
            buttons[0].drawButton(g); //rules
            buttons[3].drawButton(g); //start
            buttons[2].drawButton(g); //settings
            buttons[4].drawButton(g); //quit
            g.drawImage(titlePic.getImage(), 450, 100, titlePic.getIconWidth(), titlePic.getIconHeight(), null); //draws title

        }
        else if(currentScreen == 2) //rules screen
        {
            header = "Rules"; //header
            String ruleText = "Catan is a game"; //rules text
            buttons[1].drawButton(g); //back
            g.drawString(header, 650, 50); //draws header
            g.drawString(ruleText, 550, 200); //draws rule text
            repaint();
        }
        else if(currentScreen == 3) //settings
        {
            header = "Settings"; //headers
            g.drawString(header, 400, 50); //draws header
            //TODO add setting options
            buttons[1].drawButton(g); //back
            repaint();
        }
        else if(currentScreen == 4) //start
        {
                drawBoard(g); //draws board
        }
    }
    //paints component
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        showGame(g);					//draw the contents of the array board on the screen
    }

    public void mousePressed(MouseEvent e) //abstract
    {}
    public void mouseReleased(MouseEvent e) //abstract
    {}
    public void mouseEntered(MouseEvent e) //abstract
    {}
    public void mouseExited(MouseEvent e) //abstract
    {}
    public void mouseDragged(MouseEvent e) //abstract
    {}

    //mouse clicked
    public void mouseClicked(MouseEvent e)
    {
        int button = e.getButton(); //left click, right click
        if(button == MouseEvent.BUTTON1) //if left click
        {
            for(catanButton b:buttons) //goes through array of buttons
            {
                if(b.getShape().contains(mouseX, mouseY)) //checks if mouse click in on a button
                {
                    switch (b.getTitle()) { //checks what the title is
                        case "rules" -> { //goes through all the cases until it finds the right one
                            currentScreen = 2; //rules screen
                            repaint();
                        }
                        case "back" -> {
                            currentScreen = 1; //goes back to main
                            repaint();
                        }
                        case "settings" -> {
                            currentScreen = 3; //settings
                            repaint();
                        }
                        case "Start" -> {
                            currentScreen = 4; //board draws
                            repaint();
                        }
                        case "quit" -> System.exit(0); //closes window
                    }
                }
            }
        }
        repaint();
    }

    //when mouse is moved
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX(); //gets new X position
        mouseY = e.getY(); //gets new Y position
        for(catanButton b:buttons) //goes through array of buttons
        {
            if(b.getShape().contains(mouseX, mouseY)) //if the mouse is over a button
            {
                b.highlight(); //highlighted form of the button is drawn
            }
            else
                b.unHighlight(); //goes back to un highlighted form
        }
        repaint();
    }

    //draws the actual board
    public void drawBoard(Graphics g)
    {
        int x = 478; //starting x & y coordinates
        int y = 100;
        g.drawImage(emptyBoard.getImage(), 250, 25, 832, 702, null); //draws empty board outline
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
        } //draws top row
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
        } //row 2
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
        } //row 3
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
        } //row 4
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
        } //bottom row


        repaint();


    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    private catanClient connection;
    private int myID;
    private int bNum = 0;

    //declaring all images
    private final ImageIcon endt1 = new ImageIcon("images/endturn1.png");
    private final ImageIcon endt2 = new ImageIcon("images/endturn2.png");
    private final ImageIcon cplayerb = new ImageIcon("images/cplayerblue.png");
    private final ImageIcon cplayero = new ImageIcon("cplayerorange.png");
    private final ImageIcon cplayerr = new ImageIcon("cplayerred.png");
    private final ImageIcon cplayerw = new ImageIcon("cplayerwhite.png");
    private final ImageIcon buildm1 = new ImageIcon("images/buildmode1.png");
    private final ImageIcon buildm2 = new ImageIcon("images/buildmode2.png");
    private final ImageIcon p1Blue = new ImageIcon("images/p1blue.png");
    private final ImageIcon p2Orange = new ImageIcon("images/p2orange.png");
    private final ImageIcon p3Red = new ImageIcon("images/p3red.png");
    private final ImageIcon p4White = new ImageIcon("images/p4white.png");
    private final ImageIcon titlePic = new ImageIcon("images/title.png");
    private final ImageIcon placeBut = new ImageIcon("images/thing.png");
    private final ImageIcon developmentBack = new ImageIcon("images/developmentBack.png");
    private final ImageIcon startBack = new ImageIcon("images/background.gif");
    private final ImageIcon emptyBoard = new ImageIcon("images/blankcatanboard.png");
    private final ImageIcon desertTile = new ImageIcon("images/desertcatantile.png");
    private final ImageIcon mountainTile = new ImageIcon("images/mountainscatantile.png");
    private final ImageIcon fieldTile = new ImageIcon("images/fieldscatantile.png");
    private final ImageIcon forestTile = new ImageIcon("images/forestcatantile.png");
    private final ImageIcon hillTile = new ImageIcon("images/hillscatantile.png");
    private final ImageIcon pastureTile = new ImageIcon("images/pasturecatantile.png");
    private final ImageIcon hlBut = new ImageIcon("images/otherThing.png");

    private static final int SIZE = 500;
    private static int currentScreen; //home = 1, rules = 2, etc. Allows us to only use 1 panel
    private static Timer t;
    private static final int DELAY = 10;
    private static int numFrames;
    private static Player[] players = new Player[4];
    private static int currentPlayer;
    private static boolean placing, mousePlaced;
    private boolean building;

    private final catanButton[] buttons = new catanButton[8]; //array of all buttons, just add to the array length if needed
    private final catanButton[] buttons1 = new catanButton[11];
    protected static int mouseX; //position of mouse on X
    protected static int mouseY; //position of mouse on Y
    private catanButton[] places;

    private catanState state;

    private class catanSClient extends catanClient {
        public catanSClient(String hubHostName,int hubPort) throws IOException {
            super(hubHostName, hubPort);
        }
        protected void messageReceived(final Object message) {
            if(message instanceof catanState) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        newState((catanState) message);
                    }
                });
            }
        }
    }

    //constructor
    public catanGraphics(String hostName, int serverPortNumber) throws IOException {
        building = false;
        connection = new catanSClient(hostName, serverPortNumber);
        myID = connection.getID();
        addMouseListener(this); //mouse
        addMouseMotionListener(this); //mouse
        mouseX = SIZE/2; //mouse location
        mouseY = SIZE/2; //mouse location
        catanSound.initialize();
        for(int i = 0; i<4;i++)
        {
            players[i] = new Player("red", "spencer");
        }
        currentPlayer = 0;
        //shapes of the buttons
        Shape rect = new Rectangle(650, SIZE - 100, 75, 50);
        Shape rect1 = new Rectangle(550, 400, 75, 50);
        Shape rect2 = new Rectangle(650, 500, 75, 50);
        Shape rect3 = new Rectangle(500, 300, 150, 100);
        Shape rect4 = new Rectangle(100, 600, 75, 50);
        Shape rect5 = new Rectangle(100, 500, 75, 50);
        Shape rect6 = new Rectangle(0,658, 127,47);
        Shape rect7 = new Rectangle(129, 658, 127, 47);
        ImageIcon rulesButton = new ImageIcon("images/rulesbut.png"); //rules
        ImageIcon rulesHighlighted = new ImageIcon("images/rulesbutHL.png"); //rules highlighted
        //declaration of all buttons
        buttons[0] = new catanButton(rect, "rules", rulesHighlighted, rulesButton);
        buttons[1] = new catanButton(rect1, "back", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[2] = new catanButton(rect2, "settings", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[3] = new catanButton(rect3, "Start", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[4] = new catanButton(rect4, "quit", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[5] = new catanButton(rect5, "place", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[6] = new catanButton(rect6, "builds", buildm1, buildm2);
        buttons[7] = new catanButton(rect7, "end", endt1, endt2);
        currentScreen = 1; //sets to start screen
        t = new Timer(DELAY, new Listener());
        t.start();
        numFrames = 0;
        placing = false;
        mousePlaced = false;
        places = new catanButton[20];

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
            //add setting options
            buttons[1].drawButton(g); //back
            repaint();
        }
        else if(currentScreen == 4) //start
        {
            if(state.currentPlayer == myID) {
                buttons[6].drawButton(g);
                buttons[7].drawButton(g);
            }
                drawBoard(g); //draws board
                g.drawImage(developmentBack.getImage(), 50, 150, 125, 200, null);
                drawCurrentPlayer(g);
                repaint();
                //place(g);
            placing = true;
            if (mousePlaced)
            {
                g.drawImage(developmentBack.getImage(), mouseX, mouseY, 50, 50, null);
                mousePlaced = false;
                removeAll();
            }
            if(building) {
                drawBuildButtons(g);
            }
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

        catanSound.click();
        int button = e.getButton(); //left click, right click
        if(button == MouseEvent.BUTTON1) //if left click
        {
            for(catanButton b:buttons) //goes through array of buttons
            {
                if(b.getShape().contains(mouseX, mouseY)) //checks if mouse click in on a button
                {
                    if(currentScreen == 1) {
                        switch (b.getTitle()) { //checks what the title is
                            case "rules" -> { //goes through all the cases until it finds the right one
                                currentScreen = 2; //rules screen
                            }
                            case "settings" -> {
                                currentScreen = 3; //settings
                            }
                            case "Start" -> {
                                currentScreen = 4; //board draws
                            }
                            case "quit" -> System.exit(0); //closes window
                        }
                    }
                    else if(currentScreen == 2 || currentScreen == 3)
                    {
                        if(b.getTitle().equals("back"))
                        {
                            currentScreen = 1;
                        }
                    }
                    else if(currentScreen == 4) {
                        if(b.getTitle().equals("builds")) {
                            if(!building) {
                                building = true;
                            }
                            else {
                                building = false;
                            }
                        }
                        else if(b.getTitle().equals("end")) {
                            String nm = "nextplayer";
                            connection.send(nm);
                        }
                    }
                }
            }
            if(currentScreen == 4 && building) {
                for(catanButton c: buttons1) {
                    if(c.getShape().contains(mouseX, mouseY)) {
                        //needs code to make sure the player has the correct amount of resources
                        Integer i = Integer.parseInt(c.getTitle());
                        i++;
                        connection.send(i);
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
        if(building) {
            for (catanButton c : buttons1) {
                if (c.getShape().contains(mouseX, mouseY)) {
                    c.highlight();
                }
                else {
                    c.unHighlight();
                }
            }
        }
        repaint();
    }

    private void newState(catanState state) {
        if(state.playerDisconnected) {
            System.out.println("Players have left, game ended.");
            System.exit(0);
        }
        this.state = state;
        if(state.bard == null) {
            return; //Has not started yet
        }
        repaint();
    }

    public void drawCurrentPlayer(Graphics g) {
        if(state.noPlayers == 4) {
            if(state.currentPlayer == 1) {
                g.drawImage(cplayerb.getImage(), 70, 0, null);
            }
            else if(state.currentPlayer == 2) {
                g.drawImage(cplayero.getImage(), 70, 0, null);
            }
            else if(state.currentPlayer == 3) {
                g.drawImage(cplayerr.getImage(), 70, 0, null);
            }
            else if(state.currentPlayer == 4) {
                g.drawImage(cplayerw.getImage(), 70, 0, null);
            }
        }
        else if(state.noPlayers == 3) {
            if(state.currentPlayer == 1) {
                g.drawImage(cplayerb.getImage(), 70, 0, null);
            } else if(state.currentPlayer == 2) {
                g.drawImage(cplayero.getImage(), 70, 0, null);
            }
            else if(state.currentPlayer == 3) {
                g.drawImage(cplayerr.getImage(), 70, 0, null);
            }
        }
        else if(state.noPlayers == 2) {
            if(state.currentPlayer == 1) {
                g.drawImage(cplayerb.getImage(), 70, 0, null);
            } else if(state.currentPlayer == 2) {
                g.drawImage(cplayero.getImage(), 70, 0, null);
            }
        }
        else if(state.noPlayers == 1) { //needs to be deleted, for testing purposes
            if(state.currentPlayer == 1) {
                g.drawImage(cplayerb.getImage(), 70, 0, null);
            }
        }
        repaint();
    }

    public void drawBuildButtons(Graphics g) {
        String Bnum;
        Shape rect;
        bNum = 0;
        int x = 532;
        int y = 96;
        for(int col = 2; col < 5; col++) { //first row of vertices
                Bnum = ""+bNum;
                rect = new Rectangle(x, y, 17, 17);
                buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
                if(!state.bard.isSettlement(1, col, 0)) {
                    buttons1[bNum].drawButton(g);
                }
                x+=125;
                bNum++;
        }
        x = 471;
        y = 130;
        for(int col = 2; col < 5; col++) { //second row of vertices
                Bnum = ""+bNum;
                rect = new Rectangle(x, y, 17, 17);
                buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
                if(!state.bard.isSettlement(1, col, 5)) {
                    buttons1[bNum].drawButton(g);
                }
                x+=123;
                bNum++;
            if(col == 4) {
                    Bnum = ""+bNum;
                    rect = new Rectangle(844, y, 17, 17);
                    buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
                    if(!state.bard.isSettlement(1,col, 1)) {
                        buttons1[bNum].drawButton(g);
                    }
                    bNum++;
            }
        }
        x = 470;
        y = 199;
        for(int col = 2; col < 5; col++) {//third row
                Bnum = ""+bNum;
                rect = new Rectangle(x, y, 17, 17);
                buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
                if(!state.bard.isSettlement(1, col, 4)) {
                    buttons1[bNum].drawButton(g);
                }
                x+=124;
                bNum++;
            if(col == 4) {
                    Bnum = ""+bNum;
                    rect = new Rectangle(845, y, 17, 17);
                    buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
                    if(!state.bard.isSettlement(1, col, 2)) {
                        buttons1[bNum].drawButton(g);
                    }
                    bNum++;
            }
        }
        repaint();
    }

    //draws the actual board
    public void drawBoard(Graphics g)
    {
        newState(state);
        if(connection.getID()==1) {
            g.drawImage(p1Blue.getImage(),0,0,null);
        }
        if(connection.getID()==2) {
            g.drawImage(p2Orange.getImage(),0,0,null);
        }
        if(connection.getID()==3) {
            g.drawImage(p3Red.getImage(), 0, 0, null);
        }
        if(connection.getID()==4) {
            g.drawImage(p4White.getImage(), 0, 0, null);
        }
        int x = 478; //starting x & y coordinates
        int y = 100;
        g.drawImage(emptyBoard.getImage(), 250, 25, 832, 702, null); //draws empty board outline
        for(int c1 = 2; c1 < 5; c1++) {
            if(state.bard.getTileType(1, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(1, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(1, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(1, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(1, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(1, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        } //draws top row
        y+= 103;
        x = 416;
        for(int c1 = 2; c1 < 6; c1++) {
            if(state.bard.getTileType(2, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(2, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(2, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(2, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(2, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(2, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        } //row 2
        y+= 103;
        x = 356;
        for(int c1 = 1; c1 < 6; c1++) {
            if(state.bard.getTileType(3, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(3, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(3, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(3, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(3, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(3, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        } //row 3
        y+= 103;
        x = 416;
        for(int c1 = 2; c1 < 6; c1++) {
            if(state.bard.getTileType(4, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(4, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(4, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(4, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(4, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(4, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        } //row 4
        y+= 103;
        x = 478;
        for(int c1 = 2; c1 < 5; c1++) {
            if(state.bard.getTileType(5, c1).equals("Forest")) {
                g.drawImage(forestTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(5, c1).equals("Fields")) {
                g.drawImage(fieldTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(5, c1).equals("Pasture")) {
                g.drawImage(pastureTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(5, c1).equals("Hills")) {
                g.drawImage(hillTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(5, c1).equals("Mountains")) {
                g.drawImage(mountainTile.getImage(), x, y, 125, 140, null);
            }
            if(state.bard.getTileType(5, c1).equals("Desert")) {
                g.drawImage(desertTile.getImage(), x, y, 125, 140, null);
            }
            x+= 125;
        } //bottom row


        repaint();


    }
    //listener for timer
    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)	//this is called for each timer iteration
        {
            catanSound.runBackgroundSounds();
            numFrames++;
            catanSound.checkToClearSound();
            if(numFrames == Integer.MAX_VALUE) {
                numFrames = 0;
            }
            repaint();
        }

    }
    public static int getCurrentScreen()
    {
        return currentScreen;
    }
    public static int getFrames()
    {
        return numFrames;
    }

    public void place(Graphics g) {
        Shape[] shapes = new Shape[20];
        int x = 478; //starting x & y coordinates
        int y = 100;
        for(int i = 0; i<3; i++) {
            shapes[i] = new Rectangle(x, y, 50, 50);
            x+= 125;
        } //draws top row
        y+= 103;
        x = 416;
        for(int i = 3; i<7; i++) {
            shapes[i] = new Rectangle(x, y, 50, 50);
            x+= 125;
        } //row 2
        y+= 103;
        x = 356;
        for(int i = 7; i < 13; i++) {
            shapes[i] = new Rectangle(x, y, 50, 50);
            x+= 125;
        } //row 3
        y+= 103;
        x = 416;
        for(int i = 13; i<17; i++) {
            shapes[i] = new Rectangle(x, y, 50, 50);
            x+= 125;
        } //row 4
        y+= 103;
        x = 478;
        for(int i = 17; i<20; i++) {
            shapes[i] = new Rectangle(x, y, 50, 50);
            x+= 125;
        } //bottom row
        for(int i = 0; i<20; i++)
        {
            places[i] = new catanButton(shapes[i], "" + i, placeBut, hlBut);
            places[i].drawButton(g);
        }
    }


    private static catanGraphics screen;
    public static void main(String[]args) throws IOException {
        screen = new catanGraphics("localhost", 41018);
        JFrame frame = new JFrame("catan");    //window title
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocation(100, 50);                 //location of game window on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(screen);
        frame.setVisible(true);
    }
}

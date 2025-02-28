import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class catanGraphics extends JPanel implements MouseListener, MouseMotionListener{
    private catanClient connection;
    private int myID;
    private int bNum = 0;

    //declaring all images
    private int currentPage, actPage;
    private final ImageIcon token2 = new ImageIcon("images/token2.png");
    private final ImageIcon tradeBut = new ImageIcon("images/trademode.png");
    private final ImageIcon npcTrade = new ImageIcon("images/npctrade.png");
    private final ImageIcon playerTrade = new ImageIcon("images/playerTrade.png");
    private final ImageIcon token3 = new ImageIcon("images/token3.png");
    private final ImageIcon token4 = new ImageIcon("images/token4.png");
    private final ImageIcon token5 = new ImageIcon("images/token5.png");
    private final ImageIcon token6 = new ImageIcon("images/token6.png");
    private final ImageIcon token8 = new ImageIcon("images/token8.png");
    private final ImageIcon token9 = new ImageIcon("images/token9.png");
    private final ImageIcon token10 = new ImageIcon("images/token10.png");
    private final ImageIcon token11 = new ImageIcon("images/token11.png");
    private final ImageIcon token12 = new ImageIcon("images/token12.png");

    private boolean rules;
    private final ImageIcon endt1 = new ImageIcon("images/endturn1.png");
    private final ImageIcon endt2 = new ImageIcon("images/endturn2.png");
    private final ImageIcon cplayerb = new ImageIcon("images/cplayerblue.png");
    private final ImageIcon cplayero = new ImageIcon("images/cplayerorange.png");
    private final ImageIcon cplayerr = new ImageIcon("images/cplayerred.png");
    private final ImageIcon cplayerw = new ImageIcon("images/cplayerwhite.png");
    private final ImageIcon buildm1 = new ImageIcon("images/buildmode1.png");
    private final ImageIcon buildm2 = new ImageIcon("images/buildmode2.png");
    private final ImageIcon p1Blue = new ImageIcon("images/p1blue.png");
    private final ImageIcon p2Orange = new ImageIcon("images/p2orange.png");
    private final ImageIcon p3Red = new ImageIcon("images/p3red.png");
    private final ImageIcon p4White = new ImageIcon("images/p4white.png");
    private final ImageIcon titlePic = new ImageIcon("images/logo.png");
    private final ImageIcon placeBut = new ImageIcon("images/thing.png");
    private final ImageIcon roll1 = new ImageIcon("images/rollDice1.png");
    private final ImageIcon roll2 = new ImageIcon("images/rollDice2.png");
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

    private final ImageIcon blueSettlement = new ImageIcon("images/blueSettlement.png");

    private final ImageIcon orangeSettlement = new ImageIcon("images/orangeSettlement.png");

    private final ImageIcon redSettlement = new ImageIcon("images/redSettlement.png");

    private final ImageIcon whiteSettlement = new ImageIcon("images/whiteSettlement.png");

    private final ImageIcon blueCity = new ImageIcon("images/blueCity.png");

    private final ImageIcon orangeCity = new ImageIcon("images/orangeCity.png");

    private final ImageIcon redCity = new ImageIcon("images/redCity.png");

    private final ImageIcon whiteCity = new ImageIcon("images/whiteCity.png");

    //road images
    private final ImageIcon blueRoad1 = new ImageIcon("images/roads/blueRoad1.png");
    private final ImageIcon blueRoad2 = new ImageIcon("images/roads/blueRoad2.png");
    private final ImageIcon blueRoad3 = new ImageIcon("images/roads/blueRoad3.png");

    private final ImageIcon orangeRoad1 = new ImageIcon("images/roads/orangeRoad1.png");
    private final ImageIcon orangeRoad2 = new ImageIcon("images/roads/orangeRoad2.png");
    private final ImageIcon orangeRoad3 = new ImageIcon("images/roads/orangeRoad3.png");

    private final ImageIcon redRoad1 = new ImageIcon("images/roads/redRoad1.png");
    private final ImageIcon redRoad2 = new ImageIcon("images/roads/redRoad2.png");
    private final ImageIcon redRoad3 = new ImageIcon("images/roads/redRoad3.png");

    private final ImageIcon whiteRoad1 = new ImageIcon("images/roads/whiteRoad1.png");
    private final ImageIcon whiteRoad2 = new ImageIcon("images/roads/whiteRoad2.png");
    private final ImageIcon whiteRoad3 = new ImageIcon("images/roads/whiteRoad3.png");
    private final ImageIcon devBut = new ImageIcon("images/developmentBut.png");


    private static final int SIZE = 500;

    private static int currentScreen; //home = 1, rules = 2, etc. Allows us to only use 1 panel
    private static Timer t;
    private static final int DELAY = 10;
    private static int numFrames;
    //blocked out private static Player[] players = new Player[4];
    private static int currentPlayer;
    private static boolean placing, mousePlaced;
    public static boolean hasGiven;
    public static boolean trading, npcTrading, playerTrading;
    private boolean building, rolling, hasRolled;
    private static developmentDeck devDeck;

    private rulebook book;
    private final catanButton[] buttons = new catanButton[15]; //array of all buttons, just add to the array length if needed
    private final catanButton[] buttons1 = new catanButton[54];

    private final catanButton[] upgradeButtons = new catanButton[54];

    private final catanButton[] roadButtons = new catanButton[72];
    protected static int mouseX; //position of mouse on X
    protected static int mouseY; //position of mouse on Y
    private catanButton[] places;
    private dice die1, die2;
    private int rollNum;
    private card wood, brick, sheep, ore, wheat;
    private catanState state;
    //blocked out private playerHand hand1, hand2, hand3, hand4;
    int time;
    ImageIcon rollFace1, rollFace2;

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
        connection = new catanSClient(hostName, serverPortNumber);
        myID = connection.getID();
        building = false;
        hasRolled = true;
        wood = new resourceCard(1);
        brick = new resourceCard(2);
        sheep = new resourceCard(3);
        ore = new resourceCard(4);
        wheat = new resourceCard(5);
        /*blocked outhand1 = new playerHand();
        hand2 = new playerHand();*/
        book = new rulebook();
        devDeck = new developmentDeck();
        time = 0;
        rollNum = 0;
        hasGiven = true;
        trading = false;
        playerTrading = false;
        npcTrading = false;
        currentPage = 1;
        actPage = 1;
        addMouseListener(this); //mouse
        addMouseMotionListener(this); //mouse
        mouseX = SIZE/2; //mouse location
        mouseY = SIZE/2; //mouse location
        rules = false;
        catanSound.initialize();
        for(int i = 0; i<4;i++)
        /*blocked out{
            players[i] = new Player("red", "spencer");
        }*/
        currentPlayer = 0;
        //shapes of the buttons
        Shape rect = new Rectangle(580, SIZE- 100, 150, 100);
        Shape rect1 = new Rectangle(175, 600, 75, 50);
        Shape rect2 = new Rectangle(650, 500, 75, 50);
        Shape rect3 = new Rectangle(500, 300, 150, 100);
        Shape rect4 = new Rectangle(50, 500, 75, 50);
        Shape rect5 = new Rectangle(100, 500, 75, 50);
        Shape rect6 = new Rectangle(0,658, 127,47);
        Shape rect7 = new Rectangle(129, 658, 127, 47);
        Shape rect8 = new Rectangle(0, 611, 127, 47);
        Shape dice1 = new Rectangle(50, 150, 50, 50);
        Shape dice2 = new Rectangle(100, 150, 50, 50);
        Shape rect10 = new Rectangle(100, 400, 75, 50);
        Shape rect12 = new Rectangle(900, 500, 75, 50);
        Shape rect11 = new Rectangle(200, 50, 127, 47);
        Shape rect13 = new Rectangle(200, 97, 127, 47);
        Shape rect14 = new Rectangle(327, 97, 127, 47);
        Shape rect15 = new Rectangle(200, 500, 127, 47);

        buttons[14] = new catanButton(rect15, "devBut", devBut, devBut);
        buttons[11] = new catanButton(rect11, "trade", tradeBut, tradeBut);
        buttons[9] = new catanButton(rect10, "back", Color.WHITE, Color.GRAY, Color.BLACK);

        buttons[10] = new catanButton(rect12, "next", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[12] = new catanButton(rect13, "playerTrade", playerTrade, playerTrade);
        buttons[13] = new catanButton(rect14, "npcTrade", npcTrade, npcTrade);

        ImageIcon rulesButton = new ImageIcon("images/rulesRed.png"); //rules
        ImageIcon rulesHighlighted = new ImageIcon("images/rulesYellow.png"); //rules highlighted
        //declaration of all buttons
        buttons[0] = new catanButton(rect, "rules", rulesHighlighted, rulesButton);
        buttons[1] = new catanButton(rect1, "mainmenu", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[2] = new catanButton(rect2, "settings", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[3] = new catanButton(rect3, "Start", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[4] = new catanButton(rect4, "quit", Color.YELLOW, Color.RED, Color.BLACK);
        buttons[5] = new catanButton(rect5, "place", Color.WHITE, Color.GRAY, Color.BLACK);
        buttons[6] = new catanButton(rect6, "builds", buildm1, buildm2);
        buttons[7] = new catanButton(rect7, "end", endt1, endt2);
        buttons[8] = new catanButton(rect8, "roll", roll1, roll2);
        currentScreen = 1; //sets to start screen
        t = new Timer(DELAY, new Listener());
        t.start();
        numFrames = 0;
        placing = false;
        mousePlaced = false;
        places = new catanButton[20];
        die1 = new dice(dice1);
        die2 = new dice(dice2);
    }

    //actual drawing of game
    public void showGame(Graphics g)
    {
        //start screen
        String header;
        if(currentScreen == 1) {
            g.drawImage(startBack.getImage(), 0,0, 1366, 768, null); //draws background
            buttons[0].drawButton(g); //rules
            if(state!= null && state.isCanStart()) {
                buttons[3].drawButton(g); //start
            }
            buttons[2].drawButton(g); //settings
            buttons[4].drawButton(g); //quit
            g.drawImage(titlePic.getImage(), 450, 78, 466, 200, null); //draws title

        }
        else if(currentScreen == 2) //rules screen
        {
                        header = "Rules"; //header
            String ruleText = "Catan is a game"; //rules text
            buttons[1].drawButton(g); //back
                book.showRules(g);
            buttons[10].drawButton(g);
            buttons[9].drawButton(g);

            if(currentPage != actPage)
            {
                book.showRules(g);
                currentPage = actPage;
            }
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
                buttons[14].drawButton(g);
                if(!hasRolled) {
                    buttons[8].drawButton(g);
                }
                buttons[11].drawButton(g);

            }
            String longestOwner;
            if(state.longestRoadOwner == 0) {
                longestOwner = "BLUE";
            }
            else if(state.longestRoadOwner == 1) {
                longestOwner = "ORANGE";
            }
            else if(state.longestRoadOwner == 2) {
                longestOwner = "RED";
            }
            else {
                longestOwner = "WHITE";
            }
            String longest;
            if(state.longestRoad > 5) {
                longest = "Longest Road Owner: " + longestOwner + "\n Road Length: " + state.longestRoad;
            }
            else {
                longest = "Longest Road Owner: Road Length: ";
            }
            g.drawString(longest, 1066, 562);

                drawBoard(g); //draws board
                drawTokens(g);
                g.drawImage(developmentBack.getImage(), 50, 150, 125, 200, null);
                drawCurrentPlayer(g);
                drawRoads(g);
                drawSettlements(g);//jump point
                drawCities(g);

                //place(g);
            die1.drawDice(g);
            die2.drawDice(g);
            placing = true;
            if (mousePlaced)
            {
                g.drawImage(developmentBack.getImage(), mouseX, mouseY, 50, 50, null);
                mousePlaced = false;
                removeAll();
            }
            if(building) {
                drawBuildButtons(g);
                drawUpgradeButtons(g);
                drawRoadButtons(g);
            }
            if(die1.isRolling())
            {
                die1.rollDice(g);
            }
            if(die2.isRolling())
            {
                die2.rollDice(g);
            }
            if(!die1.isRolling() && !die2.isRolling()) {
                rollNum = die1.getRollNum() + die2.getRollNum();
                catanMessage roll = new catanMessage("roll", ""+rollNum);
                connection.send(roll);
            }
            if(!hasGiven && !die1.isRolling())
            {
            for(int r = 0; r<7; r++)
            {
                for(int c = 0; c<7; c++)
                {
                    if(state.bard.getNumber(r, c) == (state.bard.getRollNum())) {
                        //checks if vertices of tile are owned
                        for (int i = 0; i < 53; i++) {
                            if (state.bard.web.isNext(i, r, c)) {
                                if(state.bard.web.getOwner(i) != null) {
                                    if (state.bard.web.getOwner(i).equals("BLUE")) {
                                            /*resourceCard rc = new resourceCard(state.bard.getTypeInt(r,c));
                                            gainMessage gain = new gainMessage(rc, 0);
                                            connection.send(gain);*/
                                            resourceMessage collect = new resourceMessage(state.bard.getTileType(r,c), 0);
                                            connection.send(collect);
                                            hasGiven = true;
                                    }
                                    if (state.bard.web.getOwner(i).equals("ORANGE")) {
                                        gainMessage gain = new gainMessage(new resourceCard(state.bard.getTypeInt(r, c)), 1);
                                        connection.send(gain);
                                        resourceMessage collect = new resourceMessage(state.bard.getTileType(r,c), 1);
                                        connection.send(collect);
                                        hasGiven = true;
                                    }
                                    if (state.bard.web.getOwner(i).equals("RED")) {
                                        gainMessage gain = new gainMessage(new resourceCard(state.bard.getTypeInt(r, c)), 2);
                                        connection.send(gain);
                                        resourceMessage collect = new resourceMessage(state.bard.getTileType(r,c), 2);
                                        connection.send(collect);
                                        hasGiven = true;
                                    }
                                    if (state.bard.web.getOwner(i).equals("WHITE")) {
                                        gainMessage gain = new gainMessage(new resourceCard(state.bard.getTypeInt(r, c)), 3);
                                        connection.send(gain);
                                        resourceMessage collect = new resourceMessage(state.bard.getTileType(r,c), 3);
                                        connection.send(collect);
                                        hasGiven = true;
                                    }
                                }
                            }
                        }
                    }
                        //give correct resource to players
                    }
                }
            }
            if(connection.getID()==1) {
                playerHand h = state.players.get(0).getHand();
                showHand(g, 1000, 0, h);
            }
            if(connection.getID()==2) {
                playerHand h = state.players.get(1).getHand();
                showHand(g, 1000, 0, h);
            }
            if(connection.getID()==3) {
                playerHand h = state.players.get(2).getHand();
                showHand(g, 1000, 0, h);
            }
            if(connection.getID()==4) {
                playerHand h = state.players.get(3).getHand();
                showHand(g, 1000, 0, h);
            }
        }
        else if(currentScreen == 5)
        {
            header = "Trading";
            g.drawString(header, 600, 50);
            buttons[9].drawButton(g);


            if(state.noPlayers == 2)
            {
                //this needs to be fixed
                state.hands.get(0).showHand(g, 250, 0);
                state.hands.get(1).showHand(g, 250, 300);
                if(connection.getID() == 1)
                {
                    g.drawString("You --->", 100, 125);
                }
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
                                currentScreen = 2;
                                rules = true;//rules screen
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
                        if(b.getTitle().equals("mainmenu"))
                        {
                            currentScreen = 1;
                        }
                    }
                    if(currentScreen == 2)
                    {
                        if(b.getTitle().equals("next"))
                        {
                            book.nextPage();
                            actPage++;
                        }
                        if(b.getTitle().equals("back"))
                        {
                            book.prevPage();
                            actPage--;
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
                        else if(b.getTitle().equals("end") && hasRolled) {
                            String nm = "nextplayer";
                            if(state.getTurn() != 0) {
                                hasRolled = false;
                            }
                            connection.send(nm);
                            if(building) {
                                building = false;
                            }
                        }
                        else if(b.getTitle().equals("roll") && !hasRolled) {
                            die1.startRoll();
                            die2.startRoll();
                            hasRolled = true;
                            hasGiven = false;
                        }
                        /*blocked outelse if(b.getTitle().equals("devBut"))
                        {
                            hand1.addCard(devDeck.dealTop());
                        }*/
                        else if(b.getTitle().equals("trade")) {
                            currentScreen = 5;
                            //click button again to end trade mode
                        }
                    }
                    else if(currentScreen == 5)
                    {
                        if(b.getTitle().equals("back"))
                        {
                            currentScreen = 4;
                        }
                    }
                }
            }
            if(currentScreen == 4 && building) {
                int currentNumber = state.currentPlayer -1;
                for(catanButton c: buttons1) {
                    if(c.getShape().contains(mouseX, mouseY)) {
                        //needs code to make sure the player has the correct amount of resources
                        if(state.noPlayers > 3) {
                            if(state.getTurn() > 7) {
                                if(state.players.get(currentNumber).getLumber() >= 1 && state.players.get(currentNumber).getSheep() >= 1 && state.players.get(currentNumber).getWheat() >= 1 && state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(0, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumSettlement() > 4 && state.getTurn() < 4) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                                else if(state.getTurn() < 8 && state.players.get(currentNumber).getNumSettlement() > 3) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        if(state.noPlayers > 2) {
                            if(state.getTurn() > 5) {
                                if(state.players.get(currentNumber).getLumber() >= 1 && state.players.get(currentNumber).getSheep() >= 1 && state.players.get(currentNumber).getWheat() >= 1 && state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(0, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumSettlement() > 4 && state.getTurn() < 3) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                                else if(state.players.get(currentNumber).getNumSettlement() > 3 && state.getTurn() > 2) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        if(state.noPlayers > 1) {
                            if(state.getTurn() > 3) {
                                if(state.players.get(currentNumber).getLumber() >= 1 && state.players.get(currentNumber).getSheep() >= 1 && state.players.get(currentNumber).getWheat() >= 1 && state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(0, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumSettlement() > 4 && state.getTurn() < 2) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                                else if(state.players.get(currentNumber).getNumSettlement() > 3 && state.getTurn() > 1) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        else {
                            if(state.getTurn() > 0) {
                                if(state.players.get(currentNumber).getLumber() >= 1 && state.players.get(currentNumber).getSheep() >= 1 && state.players.get(currentNumber).getWheat() >= 1 && state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(0, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumSettlement() > 3) {
                                    buildMessage b = new buildMessage(3, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("settlement", c.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                    }
                }
                for(catanButton b: upgradeButtons) {
                    //needs code to make sure the player has the correct amount of resources
                    if(b.getShape().contains(mouseX, mouseY)) {
                        if(state.players.get(currentNumber).getWheat() >= 2 && state.players.get(currentNumber).getOre() >= 3) {
                            buildMessage build = new buildMessage(1, currentNumber);
                            connection.send(build);
                            catanMessage m = new catanMessage("city", b.getTitle());
                            connection.send(m);
                        }
                    }
                }
                for(catanButton r: roadButtons) {
                    if (r.getShape().contains(mouseX, mouseY)) {
                        if(state.noPlayers > 3) {
                            if(state.getTurn() > 7) {
                                if(state.players.get(currentNumber).getLumber() >= 1 &&  state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(2, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumRoad() > 14 && state.getTurn() < 4) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                                else if(state.players.get(currentNumber).getNumRoad() > 13 && state.getTurn() > 3) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        if(state.noPlayers > 2) {
                            if(state.getTurn() > 5) {
                                if(state.players.get(currentNumber).getLumber() >= 1 &&  state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(2, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumRoad() > 14 && state.getTurn() < 3) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                                else if(state.players.get(currentNumber).getNumRoad() > 13 && state.getTurn() > 2) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        if(state.noPlayers > 1) {
                            if(state.getTurn() > 3) {
                                if(state.players.get(currentNumber).getLumber() >= 1 &&  state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(2, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumRoad() > 14 && state.getTurn() < 2) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                                else if(state.players.get(currentNumber).getNumRoad() > 13 && state.getTurn() > 1) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
                        else {
                            if(state.getTurn() > 0) {
                                if(state.players.get(currentNumber).getLumber() >= 1 &&  state.players.get(currentNumber).getBrick() >= 1) {
                                    buildMessage build = new buildMessage(2, currentNumber);
                                    connection.send(build);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                            else {
                                if(state.players.get(currentNumber).getNumRoad() > 13) {
                                    buildMessage b = new buildMessage(4, currentNumber);
                                    connection.send(b);
                                    catanMessage m = new catanMessage("road", r.getTitle());
                                    connection.send(m);
                                }
                            }
                        }
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

    public void drawRoads(Graphics g) {
        int edgeNumber = 0;
        int x = 476;
        int y = 97;
        for(int i = 0; i < 6; i++) {
            if(i%2 == 0) {
                if(state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x+=61;
            }
            else {
                if(state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x+=65;
            }

            edgeNumber++;
        } //First edge row

        x = 476;
        y = 133;
        for(int i = 0; i < 4; i++) {
            if(i > 2) {
                x+=3;
            }
            if(state.bard.isRoadBuilt(edgeNumber)) {
                if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                    g.drawImage(blueRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                    g.drawImage(orangeRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                    g.drawImage(redRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                    g.drawImage(whiteRoad3.getImage(), x, y, 7, 72, null);
                }
            }
            x+=123;
            edgeNumber++;
        }//First straight row

        x = 415;
        y = 199;
        for(int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 61;
            } else {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 65;
            }
            edgeNumber++;
        }//Second edge row

        x = 415;
        y = 235;
        for(int i = 0; i < 5; i++) {
            if(i > 2) {
                x+=3;
            }
            if(state.bard.isRoadBuilt(edgeNumber)) {
                if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                    g.drawImage(blueRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                    g.drawImage(orangeRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                    g.drawImage(redRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                    g.drawImage(whiteRoad3.getImage(), x, y, 7, 72, null);
                }
            }
            x+=123;
            edgeNumber++;
        }//second straight row

        x = 355;
        y = 303;
        for(int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 61;
            } else {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 65;
            }
            edgeNumber++;
        }//third edge row

        x = 354;
        y = 340;
        for(int i = 0; i < 6; i++) {
            if(i > 2) {
                x+=3;
            }
            if(state.bard.isRoadBuilt(edgeNumber)) {
                if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                    g.drawImage(blueRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                    g.drawImage(orangeRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                    g.drawImage(redRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                    g.drawImage(whiteRoad3.getImage(), x, y, 7, 72, null);
                }
            }
            x+=123;
            edgeNumber++;
        }//third straight row

        x = 354;
        y = 405;
        for(int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            } else {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            }
            edgeNumber++;
        }//fourth edge row

        x = 414;
        y = 443;
        for(int i = 0; i < 5; i++) {
            if(i > 2) {
                x+=3;
            }
            if(state.bard.isRoadBuilt(edgeNumber)) {
                if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                    g.drawImage(blueRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                    g.drawImage(orangeRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                    g.drawImage(redRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                    g.drawImage(whiteRoad3.getImage(), x, y, 7, 72, null);
                }
            }
            x+=123;
            edgeNumber++;
        }//fourth straight row

        x = 415;
        y = 509;
        for(int i = 0; i < 8; i++) {
            if (i % 2 != 0) {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            } else {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            }
            edgeNumber++;
        }//fifth edge row

        x = 475;
        y = 546;
        for(int i = 0; i < 4; i++) {
            if(i > 2) {
                x+=3;
            }
            if(state.bard.isRoadBuilt(edgeNumber)) {
                if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                    g.drawImage(blueRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                    g.drawImage(orangeRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                    g.drawImage(redRoad3.getImage(), x, y, 7, 72, null);
                } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                    g.drawImage(whiteRoad3.getImage(), x, y, 7, 72, null);
                }
            }
            x+=123;
            edgeNumber++;
        }//fifth straight row

        x = 476;
        y = 611;
        for(int i = 0; i < 6; i++) {
            if (i % 2 != 0) {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad2.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad2.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            } else {
                if (state.bard.isRoadBuilt(edgeNumber)) {
                    if (state.bard.getRoadOwner(edgeNumber).equals("BLUE")) {
                        g.drawImage(blueRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("ORANGE")) {
                        g.drawImage(orangeRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("RED")) {
                        g.drawImage(redRoad1.getImage(), x, y, 66, 43, null);
                    } else if (state.bard.getRoadOwner(edgeNumber).equals("WHITE")) {
                        g.drawImage(whiteRoad1.getImage(), x, y, 66, 43, null);
                    }
                }
                x += 62;
            }
            edgeNumber++;
        }//sixth edge row



    }

    public void drawRoadButtons(Graphics g) {
        String Bnum;
        Shape rect;
        bNum = 0;
        int x = 499;
        int y = 110;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        x = 471;
        y = 163;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=123;
            bNum++;
        }
        x = 437;
        y = 214;
        for(int i = 0; i < 8; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        x = 408;
        y = 264;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=123;
            bNum++;
        }
        x = 376;
        y = 316;
        for(int i = 0; i < 10; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        x = 349;
        y = 366;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=123;
            bNum++;
        }
        x = 376;
        y = 419;
        for(int i = 0; i < 10; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        x = 409;
        y = 467;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=123;
            bNum++;
        }
        x = 438;
        y = 522;
        for(int i = 0; i < 8; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        x = 471;
        y = 571;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=123;
            bNum++;
        }
        x = 501;
        y = 625;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            roadButtons[bNum] = new catanButton(rect, Bnum, Color.WHITE, Color.BLACK, Color.WHITE);
            x+=63;
            bNum++;
        }
        for(int i = 0; i < 72; i++) {
            ArrayList<Integer> temp = state.bard.getOriginatingVertices(i);
            int temp1 = temp.get(0);
            int temp2 = temp.get(1);
            ArrayList<Integer> temp3 = state.bard.getConnections(i);
            int temp5 = temp3.get(0);
            int temp6 = temp3.get(1);
            int temp7 = -1;
            int temp8 = -1;
            if(temp3.size() >= 3) {
                temp7 = temp3.get(2);
                if(temp3.size() == 4) {
                    temp8 = temp3.get(3);
                }
            }
            if(state.bard.isRoadBuildable(i) && isRoadBuildableHelper(temp1, temp2, temp5, temp6, temp7, temp8) && !state.bard.isRoadBuilt(i)) {
                roadButtons[i].drawButton(g);
            }
        }
    }

    private boolean isRoadBuildableHelper(int i, int j, int n, int o, int k, int m) {
        if(state.bard.getOwner(i).equals("none") && state.bard.getOwner(j).equals("none")) { //if road connected to another road (no settlements)
            if(m != -1) {
                if(state.bard.getRoadOwner(n).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(o).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(k).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(m).equals(state.currentPlayerColor())) {
                    return true;
                }
            }
            else if(k != -1 && m == -1) {
                if(state.bard.getRoadOwner(n).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(o).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(k).equals(state.currentPlayerColor())) {
                    return true;
                }
            }
            else if(k==-1) {
                if(state.bard.getRoadOwner(n).equals(state.currentPlayerColor()) || state.bard.getRoadOwner(o).equals(state.currentPlayerColor())) {
                    return true;
                }
            }
        }
        else if(state.bard.getOwner(i).equals(state.currentPlayerColor()) && state.bard.getOwner(j).equals("none")) {
            return true;
        }
        else if(state.bard.getOwner(i).equals("none") && state.bard.getOwner(j).equals(state.currentPlayerColor())) {
            return true;
        }
        else if(state.bard.getOwner(i).equals(state.currentPlayerColor()) && state.bard.getOwner(j).equals(state.currentPlayerColor())) {
            return true;
        }
        return false;
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

    public void drawUpgradeButtons(Graphics g) {
        String Bnum;
        Shape rect;
        bNum = 0;
        int x = 532;
        int y = 76;
        for(int i = 0; i < 3; i++) { //First row of upgrade buttons
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=125;
            bNum++;
        }
        x = 471;
        y = 110;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=123;
            bNum++;
        }
        x = 471;
        y = 181;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=123;
            bNum++;
        }
        x = 408;
        y = 208;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 408;
        y = 283;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 348;
        y = 309;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 349;
        y = 386;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 409;
        y = 423;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 409;
        y = 490;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 471;
        y = 517;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 471;
        y = 593;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        x = 532;
        y = 619;
        for(int i = 0; i < 3; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            upgradeButtons[bNum] = new catanButton(rect, Bnum, Color.YELLOW, Color.YELLOW, Color.YELLOW);
            x+=124;
            bNum++;
        }
        for(int i = 0; i < 54; i++) {
            if(!state.bard.isCity(i) && state.bard.isSettled(i) && state.currentPlayer == myID) {
                upgradeButtons[i].drawButton(g);
            }
        }
        repaint();
    }

    public void showHand(Graphics g, int x1, int y1, playerHand f)
    {
        int x = x1;
        int y = y1;
        for (int i = 0; i<f.size(); i++) {
            ImageIcon drawnCard;
            resourceCard cord = (resourceCard)f.getHand(i);
            if(cord.getType() == 1) {
                drawnCard = new ImageIcon("images/woodCard.png");
            }
            else if(cord.getType() == 2) {
                drawnCard = new ImageIcon("images/brickCard.png");
            }
            else if(cord.getType() == 3) {
                drawnCard = new ImageIcon("images/sheepCard.png");
            }
            else if(cord.getType() == 4) {
                drawnCard = new ImageIcon("images/oreCard.png");
            }
            else {
                drawnCard = new ImageIcon("images/wheatCard.png");
            }
            g.drawImage(drawnCard.getImage(), x, y, 130, 250, null);
            x += 20;
            if(x>=1346)
            {
                x = 1000;
                y+= 250;
            }
        }
    }

    public void drawBuildButtons(Graphics g) {
        String Bnum;
        Shape rect;
        bNum = 0;
        int x = 532;
        int y = 96;
        for(int i = 0; i < 3; i++) { //First row of build buttons
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=125;
            bNum++;
        }
        x = 471;
        y = 130;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=123;
            bNum++;
        }
        x = 471;
        y = 201;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=123;
            bNum++;
        }
        x = 408;
        y = 228;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 408;
        y = 303;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 348;
        y = 329;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 349;
        y = 406;
        for(int i = 0; i < 6; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 409;
        y = 443;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 409;
        y = 510;
        for(int i = 0; i < 5; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 471;
        y = 537;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 471;
        y = 613;
        for(int i = 0; i < 4; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        x = 532;
        y = 639;
        for(int i = 0; i < 3; i++) {
            Bnum = ""+bNum;
            rect = new Rectangle(x, y, 17, 17);
            buttons1[bNum] = new catanButton(rect, Bnum, Color.BLACK, Color.YELLOW, Color.BLACK);
            x+=124;
            bNum++;
        }
        for(int i = 0; i < 54; i++) {
            if(state.bard.isBuildable(i)) {
                buttons1[i].drawButton(g);
            }
        }
        repaint();
    }

    public void drawSettlements(Graphics g) {
        int vertexNumber = 0;
        int x = 530;
        int y = 95;
        for(int i = 0; i < 3; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 118;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 198;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 221;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 301;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 346;
        y = 324;
        for(int i = 0; i < 6; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 346;
        y = 395;
        for(int i = 0; i < 6; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 427;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x=406;
        y = 503;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 530;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 603;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 529;
        y = 634;
        for(int i = 0; i < 3; i++) {
            if(state.bard.isSettled(vertexNumber) && !state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redSettlement.getImage(), x, y, 21, 22, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteSettlement.getImage(), x, y, 21, 22, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        repaint();
    }

    public void drawCities(Graphics g) {
        int vertexNumber = 0;
        int x = 530;
        int y = 95;
        for(int i = 0; i < 3; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 118;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 198;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 221;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 301;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 346;
        y = 324;
        for(int i = 0; i < 6; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 346;
        y = 395;
        for(int i = 0; i < 6; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 406;
        y = 427;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x=406;
        y = 503;
        for(int i = 0; i < 5; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 530;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 468;
        y = 603;
        for(int i = 0; i < 4; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        x = 529;
        y = 634;
        for(int i = 0; i < 3; i++) {
            if(state.bard.isCity(vertexNumber)) {
                if(state.bard.getOwner(vertexNumber).equals("BLUE")) {
                    g.drawImage(blueCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("ORANGE")) {
                    g.drawImage(orangeCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("RED")) {
                    g.drawImage(redCity.getImage(), x, y, 29, 25, null);
                }
                else if(state.bard.getOwner(vertexNumber).equals("WHITE")) {
                    g.drawImage(whiteCity.getImage(), x, y, 29, 25, null);
                }
            }
            x+=125;
            vertexNumber++;
        }
        repaint();
    }

    public void drawTokens(Graphics g) {
        int x = 522;
        int y = 198;
        for(int col = 2; col < 5; col++) {
            if(!state.bard.getTileType(1, col).equals("Desert")) {
                if(state.bard.getNumber(1, col) == 2) {
                    g.drawImage(token2.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 3) {
                    g.drawImage(token3.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 4) {
                    g.drawImage(token4.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 5) {
                    g.drawImage(token5.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 6) {
                    g.drawImage(token6.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 8) {
                    g.drawImage(token8.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 9) {
                    g.drawImage(token9.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 10) {
                    g.drawImage(token10.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 11) {
                    g.drawImage(token11.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(1, col) == 12) {
                    g.drawImage(token12.getImage(), x, y, 36, 36, null);
                }
            }
            x+=125;
        }
        y = 301;
        x = 461;
        for(int col = 2; col < 6; col++) {
            if(!state.bard.getTileType(2, col).equals("Desert")) {
                if(state.bard.getNumber(2, col) == 2) {
                    g.drawImage(token2.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 3) {
                    g.drawImage(token3.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 4) {
                    g.drawImage(token4.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 5) {
                    g.drawImage(token5.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 6) {
                    g.drawImage(token6.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 8) {
                    g.drawImage(token8.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 9) {
                    g.drawImage(token9.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 10) {
                    g.drawImage(token10.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 11) {
                    g.drawImage(token11.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(2, col) == 12) {
                    g.drawImage(token12.getImage(), x, y, 36, 36, null);
                }
            }
            x+=125;
        }
        x = 400;
        y = 404;
        for(int col = 1; col < 6; col++) {
            if(!state.bard.getTileType(3, col).equals("Desert")) {
                if(state.bard.getNumber(3, col) == 2) {
                    g.drawImage(token2.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 3) {
                    g.drawImage(token3.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 4) {
                    g.drawImage(token4.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 5) {
                    g.drawImage(token5.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 6) {
                    g.drawImage(token6.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 8) {
                    g.drawImage(token8.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 9) {
                    g.drawImage(token9.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 10) {
                    g.drawImage(token10.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 11) {
                    g.drawImage(token11.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(3, col) == 12) {
                    g.drawImage(token12.getImage(), x, y, 36, 36, null);
                }
            }
            x+=125;
        }
        y = 507;
        x = 460;
        for(int col = 2; col < 6; col++) {
            if(!state.bard.getTileType(4, col).equals("Desert")) {
                if(state.bard.getNumber(4, col) == 2) {
                    g.drawImage(token2.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 3) {
                    g.drawImage(token3.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 4) {
                    g.drawImage(token4.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 5) {
                    g.drawImage(token5.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 6) {
                    g.drawImage(token6.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 8) {
                    g.drawImage(token8.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 9) {
                    g.drawImage(token9.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 10) {
                    g.drawImage(token10.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 11) {
                    g.drawImage(token11.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(4, col) == 12) {
                    g.drawImage(token12.getImage(), x, y, 36, 36, null);
                }
            }
            x+=125;
        }
        x = 522;
        y = 610;
        for(int col = 2; col < 5; col++) {
            if(!state.bard.getTileType(4, col).equals("Desert")) {
                if(state.bard.getNumber(5, col) == 2) {
                    g.drawImage(token2.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 3) {
                    g.drawImage(token3.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 4) {
                    g.drawImage(token4.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 5) {
                    g.drawImage(token5.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 6) {
                    g.drawImage(token6.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 8) {
                    g.drawImage(token8.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 9) {
                    g.drawImage(token9.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 10) {
                    g.drawImage(token10.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 11) {
                    g.drawImage(token11.getImage(), x, y, 36, 36, null);
                }
                if(state.bard.getNumber(5, col) == 12) {
                    g.drawImage(token12.getImage(), x, y, 36, 36, null);
                }
            }
            x+=125;
        }
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

import java.util.*;
import java.io.*;

public class catanState implements Serializable {
    public int noPlayers;
    public boolean canStart;
    public board bard;
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<playerHand> hands = new ArrayList<>();
    public boolean gameInProgress;
    public boolean playerDisconnected; //True if player has disconnected
    public int currentPlayer; //Stores the id of the player whose turn it is
    public int winner; //The id of the player who won
    public int playerPlayingBlue; //The id of the player who is playing blue
    public int playerPlayingOrange; //The id of the player who is playing orange
    public int playerPlayingRed; //The id of the player who is playing red
    public int playerPlayingWhite; //The id of the player who is playing white
    public int longestRoad;
    public int longestRoadOwner;
    private int turn;

    public int getTurn() {
        return turn;
    }

    public void applyMessage(int sender, Object message) {
        if(gameInProgress && message instanceof buildMessage && sender == currentPlayer) {
            int t = ((buildMessage)message).getType();
            int p = ((buildMessage)message).getPlayer();
            if(p == 0) {
                if(t == 0) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 1) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 2) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 3) {
                    players.get(p).startSettlement();
                }
                else if(t == 4) {
                    players.get(p).startRoad();
                }
            }
            else if(p == 1) {
                if(t == 0) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 1) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 2) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 3) {
                    players.get(p).startSettlement();
                }
                else if(t == 4) {
                    players.get(p).startRoad();
                }
            }
            else if(p == 2) {
                if(t == 0) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 1) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 2) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 3) {
                    players.get(p).startSettlement();
                }
                else if(t == 4) {
                    players.get(p).startRoad();
                }
            }
            else if(p == 3) {
                if(t == 0) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 1) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 2) {
                    players.get(p).placeBuilding(t);
                }
                else if(t == 3) {
                    players.get(p).startSettlement();
                }
                else if(t == 4) {
                    players.get(p).startRoad();
                }
            }
        }
        if(gameInProgress && message instanceof gainMessage /*&& sender == currentPlayer*/) {
            int p = ((gainMessage)message).getPlayer();
            resourceCard c = ((gainMessage)message).getCard();
            if(p == 0) {
                hands.get(p).addCard(c);
                System.out.println("I am reaching you.");
            }
            else if(p == 1) {
                hands.get(p).addCard(c);
            }
            else if(p == 2) {
                hands.get(p).addCard(c);
            }
            else if(p == 3) {
                hands.get(p).addCard(c);
            }

        }
        if(gameInProgress && message instanceof resourceMessage && sender == currentPlayer) {
            String r = ((resourceMessage)message).getResource();
            int p = ((resourceMessage)message).getPlayer();
            if(p == 0) {
                players.get(0).gainResource(r, 1);
            }
            else if(p == 1) {
                players.get(1).gainResource(r, 1);
            }
            else if(p == 2) {
                players.get(2).gainResource(r, 1);
            }
            else if(p == 3) {
                players.get(3).gainResource(r, 1);
            }
        }
        if(gameInProgress && message instanceof Tile[][] && sender == currentPlayer) { //Updates for changes to buildings and roads
            Tile[][] move = (Tile[][])message;
            bard.setTiles(move);
            if(winner()) { //Needs to be called in the other updates as well
                gameInProgress = false;
                winner = currentPlayer;
            }
        }
        if(gameInProgress && message instanceof catanMessage && sender == currentPlayer) {//changes settlements, cities, roads
            catanMessage change = (catanMessage)message;
            if(change.getType().equals("settlement")) {
                bard.buildSettlement(change.getNumber(), this.currentPlayerColor());
                players.get(currentPlayer-1).gainPoint(1);
            }
            else if(change.getType().equals("city")) {
                bard.upgradeSettlement(change.getNumber(), this.currentPlayerColor());
                players.get(currentPlayer-1).gainPoint(2);
            }
            else if(change.getType().equals("road")) {
                bard.buildRoad(change.getNumber(), this.currentPlayerColor());
                if(players.size() > 1) {
                    if (longestRoad < bard.longestRoad(this.currentPlayerColor())) {
                        int previousLongestRoadOwner = longestRoadOwner;
                        longestRoadOwner = currentPlayer-1;
                        longestRoad = bard.longestRoad(this.currentPlayerColor());
                        players.get(previousLongestRoadOwner).losePoint(1);
                        players.get(longestRoadOwner).gainPoint(1);
                    }
                }
            }
            else if(change.getType().equals("roll")) {
                int r = change.getNumber();
                bard.setRollNum(r);
            }
        }
        else if(gameInProgress && message.equals("nextplayer") && sender == currentPlayer) {//ends turn and makes turn go to next player
            turn++;
            if(noPlayers == 4) {
                if(currentPlayer <= 3) {
                    currentPlayer++;
                }
                else if(currentPlayer == 4) {
                    currentPlayer = 1;
                }
            }
            else if(noPlayers == 3) {
                if(currentPlayer <= 2) {
                    currentPlayer++;
                }
                else if(currentPlayer == 3) {
                    currentPlayer = 1;
                }
            }
            else if(noPlayers == 2) {
                if(currentPlayer == 1) {
                    currentPlayer++;
                }
                else if(currentPlayer == 2) {
                    currentPlayer = 1;
                }
            }
        }
        else if(gameInProgress && message instanceof Player && sender == currentPlayer) { //Updates player for the current player
            Player update = (Player)message;
        }
        else if(!gameInProgress && message.equals("newgame")) {
            startGame();
        }
        if(gameInProgress && message instanceof String && message.equals("addVictoryPointBLUE"))
        {
            players.get(0).gainPoint(1);
        }
        else if(gameInProgress && message instanceof String && message.equals("addVictoryPointORANGE"))
        {
            players.get(1).gainPoint(1);
        }
        else if(gameInProgress && message instanceof String && message.equals("addVictoryPointRED"))
        {
            players.get(2).gainPoint(1);
        }
        else if(gameInProgress && message instanceof String && message.equals("addVictoryPointWHITE"))
        {
            players.get(3).gainPoint(1);
        }
    }
    void startFirstGame() {
        startGame();
    }


    private void startGame() {//Work on assigning player colors and move turn per id
        turn = 0;
        longestRoadOwner = 0;
        longestRoad = 0;
        if(this.getNoPlayers()==4) {
            playerPlayingBlue = 1;
            Player bPlayer = new Player("Player 1", "Blue");
            players.add(bPlayer);
            hands.add(new playerHand());
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
            hands.add(new playerHand());
            playerPlayingRed = 3;
            Player rPlayer = new Player("Player 3", "Red");
            players.add(rPlayer);
            hands.add(new playerHand());
            playerPlayingWhite = 4;
            Player wPlayer = new Player("Player 4", "White");
            players.add(wPlayer);
            hands.add(new playerHand());
            int random = (int)(Math.random()*3+1);
            if(random == 1) {
                currentPlayer = playerPlayingBlue;
            }
            else if (random == 2) {
                currentPlayer = playerPlayingOrange;
            }
            else if (random == 3) {
                currentPlayer = playerPlayingRed;
            }
            else if (random == 4) {
                currentPlayer = playerPlayingWhite;
            }
        }
        else if(this.getNoPlayers()==3) {
            playerPlayingBlue = 1;
            Player bPlayer = new Player("Player 1", "Blue");
            players.add(bPlayer);
            hands.add(new playerHand());
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
            hands.add(new playerHand());
            playerPlayingRed = 3;
            Player rPlayer = new Player("Player 3", "Red");
            players.add(rPlayer);
            hands.add(new playerHand());
            int random = (int)(Math.random()*2+1);
            if(random == 1) {
                currentPlayer = playerPlayingBlue;
            }
            else if (random == 2) {
                currentPlayer = playerPlayingOrange;
            }
            else if (random == 3) {
                currentPlayer = playerPlayingRed;
            }
        }
        else if(this.getNoPlayers()==2) {
            playerPlayingBlue = 1;
            Player bPlayer = new Player("Player 1", "Blue");
            players.add(bPlayer);
            hands.add(new playerHand());
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
            hands.add(new playerHand());
            int random = (int)(Math.random()+1);
            if(random == 1) {
                currentPlayer = playerPlayingBlue;
            }
            else if (random == 2) {
                currentPlayer = playerPlayingOrange;
            }
        }
        else if(this.getNoPlayers()==1) {//needs to be removed
            playerPlayingBlue = 1;
            Player bPlayer = new Player("Player 1", "Blue");
            players.add(bPlayer);
            hands.add(new playerHand());
            currentPlayer = playerPlayingBlue;
        }
        winner = -1;
        gameInProgress = true;
        bard = new board();
    }

    private boolean winner() {
        return players.get(currentPlayer).getPoints() >= 10;
    }


    void setNoPlayers(int n) {
        noPlayers = n;
    }
    int getNoPlayers() {
        return noPlayers;
    }

    public String currentPlayerColor() {
        if(currentPlayer == 1) {
            return "BLUE";
        }
        else if(currentPlayer == 2) {
            return "ORANGE";
        }
        else if(currentPlayer == 3) {
            return "RED";
        }
        else {
            return "WHITE";
        }
    }

    public String getScoreboard()
    {
        //need to make dynamic for amount of players - should be easy just lazy
        if(players.size() == 1) {
            return players.get(0).getPointsAsString();
        }
        else if(players.size() == 2)
        {
            return players.get(0).getPointsAsString() + "\n" + players.get(1).getPointsAsString();
        }
        else if(players.size() == 3) {
            return players.get(0).getPointsAsString() + "\n" + players.get(1).getPointsAsString() + "\n" + players.get(2).getPointsAsString();
        }
        else    return players.get(0).getPointsAsString() + "\n" + players.get(1).getPointsAsString() + "\n" + players.get(2).getPointsAsString() + "\n" + players.get(3).getPointsAsString();


    }

    public void setCanStart() {
        canStart = true;
    }
    public boolean isCanStart() {
        return canStart;
    }
    }


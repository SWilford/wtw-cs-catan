import java.util.*;
import java.io.*;

public class catanState implements Serializable {
    public int noPlayers;
    public board bard;
    public ArrayList<Player> players = new ArrayList<>();
    public boolean gameInProgress;
    public boolean playerDisconnected; //True if player has disconnected
    public int currentPlayer; //Stores the id of the player whose turn it is
    public int winner; //The id of the player who won
    public int playerPlayingBlue; //The id of the player who is playing blue
    public int playerPlayingOrange; //The id of the player who is playing orange
    public int playerPlayingRed; //The id of the player who is playing red
    public int playerPlayingWhite; //The id of the player who is playing white


    public void applyMessage(int sender, Object message) {
        if(gameInProgress && message instanceof Tile[][] && sender == currentPlayer) { //Updates for changes to buildings and roads
            Tile[][] move = (Tile[][])message;
            bard.setTiles(move);
            if(winner()) { //Needs to be called in the other updates as well
                gameInProgress = false;
                winner = currentPlayer;
            }
        }
        else if(gameInProgress && message instanceof Player && sender == currentPlayer) { //Updates player for the current player
            Player update = (Player)message;
        }
        else if(!gameInProgress && message.equals("newgame")) {
            startGame();
        }
    }
    void startFirstGame() {
        startGame();
    }


    private void startGame() {//Work on assigning player colors and move turn per id
        if(this.getNoPlayers()==4) {
            playerPlayingBlue = 1;
            Player bPlayer = new Player("Player 1", "Blue");
            players.add(bPlayer);
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
            playerPlayingRed = 3;
            Player rPlayer = new Player("Player 3", "Red");
            players.add(rPlayer);
            playerPlayingWhite = 4;
            Player wPlayer = new Player("Player 4", "White");
            players.add(wPlayer);
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
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
            playerPlayingRed = 3;
            Player rPlayer = new Player("Player 3", "Red");
            players.add(rPlayer);
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
            playerPlayingOrange = 2;
            Player oPlayer = new Player("Player 2", "Orange");
            players.add(oPlayer);
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
}

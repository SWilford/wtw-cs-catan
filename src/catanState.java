import java.util.*;
import java.io.*;

public class catanState implements Serializable{
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
            playerPlayingOrange = 2;
            playerPlayingRed = 3;
            playerPlayingWhite = 4;
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

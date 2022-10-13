import java.util.*;
import java.io.*;

public class catanState implements Serializable{
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
        else if(!gameInProgress && message.equals("newgame")) {
            startGame();
        }
    }
    void startFirstGame() {
        startGame();
    }


    private void startGame() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(1);
        colors.add(2);
        colors.add(3);
        colors.add(4);
        Random rand = new Random();
        for(int j = 0; j < colors.size(); j++) {
            int randI = rand.nextInt(colors.size());
            int temp = colors.get(randI);
            colors.set(randI, colors.get(j));
            colors.set(j, temp);
        }
        playerPlayingBlue = colors.get(0);
        playerPlayingOrange = colors.get(1);
        playerPlayingRed = colors.get(2);
        playerPlayingWhite = colors.get(3);
        if(playerPlayingBlue == 1) {
            currentPlayer = playerPlayingBlue;
        }
        else if(playerPlayingOrange == 1) {
            currentPlayer = playerPlayingOrange;
        }
        else if(playerPlayingRed == 1) {
            currentPlayer = playerPlayingRed;
        }
        else if(playerPlayingWhite == 1) {
            currentPlayer = playerPlayingWhite;
        }
        winner = -1;
        gameInProgress = true;
        bard = new board();
    }

    private boolean winner() {
        return players.get(currentPlayer).getPoints() >= 10;
    }

}

import java.util.*;
import java.io.*;

public class catanState implements Serializable{
    public board board = new board();
    public ArrayList<Player> players = new ArrayList<>();
    public boolean gameInProgress;
    public boolean playerDisconnected; //True if player has disconnected
    public int currentPlayer; //Stores the id of the player whose turn it is
    public int winner; //The id of the player who won

    public void applyMessage(int sender, Object message) {
        if(gameInProgress && message instanceof Tile[][] && sender == currentPlayer) { //Updates for changes to buildings and roads
            Tile[][] move = (Tile[][])message;
            board.setTiles(move);
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
    //Something needs to be created here that will set currentPlayer to the next player in line

    private void startGame() {
        //Something needs to go here to randomly decide the first player - assigns to currentPlayer
        winner = -1;
        gameInProgress = true;

    }

    private boolean winner() {
        return players.get(currentPlayer).getPoints() >= 10;
    }

}

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

    private void makeOwnedHelper(int number, int row, int col, int vertex) {
        if(this.getNoPlayers() == 4) {
            if(number == 1) {
                bard.makeOwned(row,col,vertex,"BLUE");
            }
            else if(number == 2) {
                bard.makeOwned(row,col,vertex,"ORANGE");
            }
            else if(number == 3) {
                bard.makeOwned(row,col,vertex,"RED");
            }
            else if(number == 4) {
                bard.makeOwned(row,col,vertex,"WHITE");
            }
        }
        else if(this.getNoPlayers() == 3) {
            if(number == 1) {
                bard.makeOwned(row,col,vertex,"BLUE");
            }
            else if(number == 2) {
                bard.makeOwned(row,col,vertex,"ORANGE");
            }
            else if(number == 3) {
                bard.makeOwned(row,col,vertex,"RED");
            }
        }
        else if(this.getNoPlayers() == 2) {
            if(number == 1) {
                bard.makeOwned(row,col,vertex,"BLUE");
            }
            else if(number == 2) {
                bard.makeOwned(row,col,vertex,"ORANGE");
            }
        }
        else if(this.getNoPlayers() == 1) {//delete, for testing only
            if(number == 1) {
                bard.makeOwned(1,2,0,"BLUE");
            }
        }
    }

    public void applyMessage(int sender, Object message) {
        if(gameInProgress && message instanceof Tile[][] && sender == currentPlayer) { //Updates for changes to buildings and roads
            Tile[][] move = (Tile[][])message;
            bard.setTiles(move);
            if(winner()) { //Needs to be called in the other updates as well
                gameInProgress = false;
                winner = currentPlayer;
            }
        }
        if(gameInProgress && message instanceof Integer && sender == currentPlayer) {
            int change = (int)message;
            if(change == 1) {
                bard.createSettlement(1,2, 0);
                makeOwnedHelper(sender, 1, 2, 0);
            }
            else if(change == 2) {
                bard.createSettlement(1, 3, 0);
                makeOwnedHelper(sender, 1, 3, 0);
            }
            else if(change == 3) {
                bard.createSettlement(1,4,0);
                makeOwnedHelper(sender, 1, 4, 0);
            }
            else if(change == 4) {
                bard.createSettlement(1,2, 5);
                makeOwnedHelper(sender, 1, 2, 5);
            }
            else if(change == 5) {
                bard.createSettlement(1,3,5);
                makeOwnedHelper(sender, 1, 3, 5);
            }
            else if(change == 6) {
                bard.createSettlement(1, 4, 5);
                makeOwnedHelper(sender, 1, 4, 5);
            }
            else if(change == 7) {
                bard.createSettlement(1,4,1);
                makeOwnedHelper(sender, 1, 4, 1);
            }
            else if(change == 8) {
                bard.createSettlement(1,2,4);
                makeOwnedHelper(sender, 1, 2, 4);
            }
            else if(change == 9) {
                bard.createSettlement(1,3,4);
                makeOwnedHelper(sender, 1, 3, 4);
            }
            else if(change == 10) {
                bard.createSettlement(1,4,4);
                makeOwnedHelper(sender, 1, 4, 4);
            }
            else if(change == 11) {
                bard.createSettlement(1,4,2);
                makeOwnedHelper(sender, 1, 4, 2);
            }
            /*if(winner()) { //Needs to be called in the other updates as well
                gameInProgress = false;
                winner = currentPlayer;
            }*/
        }
        else if(gameInProgress && message.equals("nextplayer") && sender == currentPlayer) {//ends turn and makes turn go to next player
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

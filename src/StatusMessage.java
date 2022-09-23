import java.io.*;

final class StatusMessage implements Serializable {
    public final int playerID; //ID of player
    public final boolean connecting; //If player has connected or not
    public final int[] players;

    public StatusMessage(int pI, boolean c, int[] p) {
        this.playerID = pI;
        this.connecting = c;
        this.players = p;
    }
}

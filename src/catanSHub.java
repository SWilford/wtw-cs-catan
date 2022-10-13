import java.io.IOException;

public class catanSHub extends catanHub {
    private catanState state;


    public catanSHub(int port) throws IOException {
        super(port);
        state = new catanState();
        setAutoreset(true);
    }
    protected void messageReceived(int playerID, Object message) {
        state.applyMessage(playerID, message);
        sendToAll(state);
    }
    protected void playerConnected(int playerID) {
        if(this.getNoPlayers() == 4) {
            if (getPlayerList().length == 4) {
                shutdownServerSocket();
                state.startFirstGame();
                sendToAll(state);
            }
        }
        else if(this.getNoPlayers() == 3) {
            if (getPlayerList().length == 3) {
                shutdownServerSocket();
                state.startFirstGame();
                sendToAll(state);
            }
        }
        else if(this.getNoPlayers() == 2) {
            if (getPlayerList().length == 2) {
                shutdownServerSocket();
                state.startFirstGame();
                sendToAll(state);
            }
        }
    }
    protected void playerDisconnected(int playerID) {
        state.playerDisconnected = true;
        sendToAll(state);
    }


}

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class catanHub {
    private TreeMap<Integer, ConnectionToClient> playerConnecions; //a map associating player names with the connection for each player
    private LinkedBlockingQueue<Message> incomingMessages; //queue of messages sent by the client
    private volatile boolean autoreset; //if set to true oos will be reset before sending
    private ServerSocket serverSocket; //connections
    private Thread serverThread; //accepts connections
    volatile private boolean shutdown; //true when hub not listening

    public catanHub(int port) throws IOException { //creates a hub for the specified port
        playerConnecions = new TreeMap<Integer, ConnectionToClient>();
        incomingMessages = new LinkedBlockingQueue<Message>();
        serverSocket = new ServerSocket(port);
        System.out.println("Listening for client connections on port: "+port);
        serverThread = new ServerThread();
        serverThread.start();
        Thread readerThread = new Thread() {
            public void run() {
                while(true) {
                    try {
                        Message msg = incomingMessages.take();
                        messageReceived(msg.playerConnection, msg.message);
                    }
                    catch (Exception e) {
                        System.out.println("Exception while handling received message:");
                        e.printStackTrace();
                    }
                }
            }
        };
        readerThread.setDaemon(true);
        readerThread.start();
    }

    protected void messageReceived(int playerID, Object message) { //called when message received from a player
        sendToAll(new ForwardedMessage(playerID, message));
    }

    protected void playerConnected(int playerID) { //called after a player is connected
    }

    protected void playerDisconnected(int playerID) { //called after a player is disconnected
    }

    protected void extraHandshake(int playerID, ObjectOutputStream in, ObjectOutputStream out) throws IOException { //called after connection request has been received in order to do extra checking
    }

    synchronized public int[] getPlayerList() { //list of id numbers of connected clients
        int[] players = new int[playerConnecions.size()];
        int i = 0;
        for(int p : playerConnections.keySet())
            players[i++] = p;
        return players;
    }

    public void shutdownServerSocket() { //Stops listening for new connections without disconnecting currently connected clients
        if(serverThread == null)
            return;
        incomingMessages.clear();
        shutdown = true;
        try {
            serverSocket.close();
        }
        catch (IOException e) {
            serverThread = null;
            serverSocket = null;
        }
    }

    public void restartServer(int port) throws IOException { //restarts listening and accepts new connections
        if(serverThread != null && serverThread.isAlive())
            throw new IllegalStateException("Server is already listening for connections.");
        shutdown = false;
        serverSocket = new ServerSocket(port);
        serverThread = new ServerThread();
        serverThread.start();
    }

    public void shutDownHub() { //stops listening for new connections and disconnects all current connections
        shutdownServerSocket();
        sendToAll(new DisconnectMessage("*shutdown*"));
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        for (ConnectionToClient pc : playerConnecions.values())
            pc.close();
    }

    synchronized public void sendToAll(Object message) {
        if(message == null)
            throw new IllegalArgumentException("Null cannot be sent as a message.");
        if(!(message instanceof Serializable))
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        for (ConnectionToClient pc : playerConnecions.values())
            pc.send(message);
    }

    synchronized public boolean sendToOne(int recipientID, Object message) { //Sends a specified message to one client
        if(message == null)
            throw new IllegalArgumentException("Null cannot be sent as a message");
        if(!(message instanceof Serializable))
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        ConnectionToClient pc = playerConnecions.get(recipientID);
        if(pc == null)
            return false;
        else {
            pc.send(message);
            return true;
        }
    }

    public void resetOutput() { //Resets all output streams
        ResetSignal rs = new ResetSignal();
        for(ConnectionToClient pc : playerConnecions.values())
            pc.send(rs);
    }

    public void setAutoreset(boolean auto) { //sets Auto reset
        autoreset = auto;
    }

    public boolean getAutoreset() {
        return autoreset;
    }

}

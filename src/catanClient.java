import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

//Client is a player which can send and receive messages to the hub
abstract public class catanClient {
    protected int[] connectedPlayerIDs = new int[0]; //list of player IDs that are currently connected
    private volatile boolean autoreset; //When true the OOS is reset before each object is sent

    public catanClient(String hubHostName, int hubPort) throws IOException { //hubHostName is the host name of the computer where hub is running, hub port stores the port, throws an ioe if an io exception occurs
        connection = new ConnectionToHub(hubHostName, hubPort);
    }

    abstract protected void messageReceived(Object message); //called when message is received by the hub

    protected void playerConnected(int newPlayerID) { } //called when client is notified that client has connected to hub

    protected void playerDisconnected(int departingPlayerID) { } // called when client is notified that client has disconnected to hub

    protected void connectionClosedByError(String message) { } //called when the connection to the hub is closed

    protected void serverShutdown(String message) { } //called when the connection to the hub is closed

    protected void extraHandshake(ObjectInputStream in, ObjectOutputStream out) throws IOException { } //called after connection to server has been established and completes extra checking to be defined in subclass

    public void disconnect() {
        if (!connection.closed)
            connection.send(new DisconnectMessage("Goodbye Hub"));
    }

    public void send(Object message) {
        if (message == null)
            throw new IllegalArgumentException("Null cannot be sent as a message.");
        if (! (message instanceof Serializable))
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        if (connection.closed)
            throw new IllegalStateException("Message cannot be sent because the connection is closed.");
        connection.send(message);
    }

    public int getID() {
        return connection.id_number;
    }

    public void resetOutput() {
        connection.send(new ResetSignal()); //A ResetSignal in the output stream is seen as a signal to reset
    }

    public void setAutoreset(boolean auto) {
        autoreset = auto;
    }

    public boolean getAutoreset() {
        return autoreset;
    }

    private final ConnectionToHub connection;
    //private class handles connections
    private class ConnectionToHub {
        private final int id_number; //ID number assigned to this client
        private final Socket socket; //The socket that is connected to the hub
        private final ObjectInputStream in; //Stream for sending messages
        private final ObjectOutputStream out; //Stream for receiving messages
        private final SendThread sendThread; //Thread that sends messages
        private final ReceiveThread receiveThread; //Thread for receiving messages
        private final LinkedBlockingQueue<Object> outgoingMessages; //Queue of messages waiting to be sent
        private volatile boolean closed; //set to true when connection is closing

        ConnectionToHub(String host, int port) throws IOException {//Sends hello hub to the hub and hub sends the client id
            outgoingMessages = new LinkedBlockingQueue<Object>();
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Hello Hub");
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            try {
                Object response = in.readObject();
                id_number = ((Integer)response).intValue();
            }
            catch (Exception e){
                throw new IOException("Illegal response from the server."); //Just in case some error happens
            }
            extraHandshake(in, out); //Will throw an IOException if the handshake does not work
            sendThread = new SendThread();
            receiveThread = new ReceiveThread();
            sendThread.start();
            receiveThread.start();
        }

        void close() { //Method to close the connection
            closed = true;
            sendThread.interrupt();
            receiveThread.interrupt();
            try {
                socket.close();
            }
            catch (IOException e) {
            }
        }

        void send(Object message) { //Will send a message to the hub
            outgoingMessages.add(message);
        }

        synchronized void closedByError(String message) { //called by threads that control i/o
            if(!closed) {
                connectionClosedByError(message);
                close();
            }
        }

        private class SendThread extends Thread { //defines a thread sending messages to the hub
            public void run() {
                System.out.println("Client send thread started.");
                try {
                    while(!closed) {
                        Object message = outgoingMessages.take();
                        if(message instanceof ResetSignal) {
                            out.reset();
                        }
                        else {
                            if(autoreset) {
                                out.reset();
                            }
                            out.writeObject(message);
                            out.flush();
                            if(message instanceof DisconnectMessage) {
                                close();
                            }
                        }
                    }
                }
                catch (IOException e) {
                    if(!closed) {
                        closedByError("IO error occurred while trying to send message.");
                        System.out.println("Client send thread terminated by IOException: "+e);
                    }
                }
                catch (Exception e) {
                    if(!closed) {
                        closedByError("Unexpected internal error in send thread: "+e);
                        System.out.println("\n Unexpected error shuts down client send thread:");
                        e.printStackTrace();
                    }
                }
                finally {
                    System.out.println("Client send thread terminated");
                }
            }
        }

        private class ReceiveThread extends Thread { //defines a thread receiving messages
            public void run() {
                System.out.println("Client receive thread started.");
                try {
                    while(!closed) {
                        Object obj = in.readObject();
                        if(obj instanceof DisconnectMessage) {
                            close();
                            serverShutdown(((DisconnectMessage)obj).message);
                        }
                        else if(obj instanceof StatusMessage) {
                            StatusMessage msg = (StatusMessage)obj;
                            connectedPlayerIDs = msg.players;
                            if(msg.connecting) {
                                playerConnected(msg.playerID);
                            }
                            else {
                                playerDisconnected(msg.playerID);
                            }
                        }
                        else {
                            messageReceived(obj);
                        }
                    }
                }
                catch (IOException e) {
                    if(!closed) {
                        closedByError("IO error occurred while waiting to receive message.");
                        System.out.println("Client receive thread terminated by IOException: "+e);
                    }
                }
                catch (Exception e) {
                    if(!closed) {
                        closedByError("Unexpected internal error in receive thread: "+e);
                        System.out.println("\n Unexpected error shuts down client receive thread:");
                        e.printStackTrace();
                    }
                }
                finally {
                    System.out.println("Client receive thread terminated.");
                }
            }
        }
    }
}

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class catanHub {
    private TreeMap<Integer, ConnectionToClient> playerConnections;
    private LinkedBlockingQueue<Message> incomingMessages;

    private volatile boolean autoreset;

}

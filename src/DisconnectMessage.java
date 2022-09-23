import java.io.*;

final class DisconnectMessage implements Serializable{
    final public String message; //The message when disconnecting

    public DisconnectMessage(String m) {
        this.message = m;
    }
}

import java.io.*;

public class ForwardedMessage implements Serializable{
    public final Object message; //The message from the client
    public final int SenderID; //ID of the client that sent the message

    public ForwardedMessage(int s, Object m) {
        this.SenderID = s;
        this.message = m;
    }
}

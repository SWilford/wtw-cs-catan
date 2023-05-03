import java.io.Serializable;

public class buildMessage implements Serializable {
    private int type;
    private int player;
    public buildMessage(int t, int p) {
        this.type = t;
        this.player = p;
    }
    public int getType() {
        return type;
    }

    public int getPlayer() {
        return player;
    }
}

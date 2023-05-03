import java.io.Serializable;

public class gainMessage implements Serializable {
    private resourceCard card;
    private int player;

    public gainMessage(resourceCard c, int p) {
        this.card = c;
        this.player = p;
    }
    public resourceCard getCard() {
        return card;
    }

    public int getPlayer() {
        return player;
    }
}

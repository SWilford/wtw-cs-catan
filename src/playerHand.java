import java.awt.*;
import java.util.ArrayList;

public class playerHand {
    private ArrayList<card> hand;
    public playerHand()
    {
        hand = new ArrayList<card>();
    }

    public void addCard(card c)
    {
        hand.add(c);
    }

    public void removeCard(card c)
    {
        hand.remove(c);
    }

    public void showHand(Graphics g)
    {
        int x = 1000;
        int y = 0;
        for (int i = 0; i<hand.size(); i++) {
            hand.get(i).draw(g, x, y);
            x += 20;
        }
    }
}

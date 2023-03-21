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

    public int size()
    {
        return hand.size();
    }
    public void removeCard(card c)
    {
        hand.remove(c);
    }

    public void showHand(Graphics g, int x1, int y1)
    {
        int x = x1;
        int y = y1;
        for (int i = 0; i<hand.size(); i++) {
            hand.get(i).draw(g, x, y);
            x += 20;
            if(x>=1346)
            {
                x = 1000;
                y+= 250;
            }
        }
    }
}

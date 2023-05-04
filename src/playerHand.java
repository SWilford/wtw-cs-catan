import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class playerHand implements Serializable {
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
    public void removeCard(int t)
    {
        boolean removed = false;
        int i = 0;
        while(!removed) {
            resourceCard cord = (resourceCard)hand.get(i);
            if(cord.getType() == t) {
                hand.remove(cord);
                removed = true;
            }
            i++;
        }
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

    public card getHand(int i) {
        return hand.get(i);
    }
}

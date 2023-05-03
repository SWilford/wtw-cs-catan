import javax.swing.*;
import java.io.Serializable;

public class resourceCard extends card implements Serializable {

    private final int type;


    //1 = wood, 2 = brick, 3 = sheep, 4 = ore, 5 = wheat
    public resourceCard(int i)
    {
        type = i;
        image = new ImageIcon("images/wheatCard.png");
        if(type == 1)
        {
            image = new ImageIcon("images/woodCard.png");
        }
        else if(type == 2)
        {
            image = new ImageIcon("images/brickCard.png");
        }
        else if(type == 3)
        {
            image = new ImageIcon("images/sheepCard.png");
        }
        else if(type == 4)
        {
            image = new ImageIcon("images/oreCard.png");
        }
    }
    public int getType()
    {
        return type;
    }

}

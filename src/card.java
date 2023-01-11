import javax.swing.*;
import java.awt.*;

public class card {
    ImageIcon image;


    public ImageIcon getImage()
    {
        return image;
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image.getImage(), x, y, 130, 250, null);
    }
}

import javax.swing.*;
import java.awt.*;

public class catanButton {
    private Shape shape;
    private String title;
    private Color color, regularColor, highlightColor, textColor;
    private ImageIcon image, regularImage, highlightImage;

    //shape with color
    public catanButton(Shape s, String t, Color rc, Color hc, Color tc)
    {
        shape = s;
        title = t;
        regularColor = rc;
        highlightColor = hc;
        textColor = tc;
        color = regularColor;
    }
    //image
    public catanButton(Shape s, String t, ImageIcon ri, ImageIcon hi)
    {
        shape = s;
        title = t;
        regularColor = null;
        highlightColor = null;
        textColor = null;
        regularImage = ri;
        highlightImage = hi;
        image = regularImage;
    }
    public String getTitle()
    {
        return title;
    }
    public Shape getShape()
    {
        return shape;
    }
    public Color getColor()
    {
        return color;
    }
    public void setColor(Color c)
    {
        color = c;
    }
    public void highlight()
    {
        color = highlightColor;
        image = highlightImage;
    }
    public Color getTextColor()
    {
        return textColor;
    }
    public void unHighlight()
    {
        color = regularColor;
        image = regularImage;
    }
    public void drawButton(Graphics g)
    {
        int x = (int)(this.getShape().getBounds().getX());
        int y = (int)(this.getShape().getBounds().getY());
        int width = (int)(this.getShape().getBounds().getWidth());
        int height = (int)(this.getShape().getBounds().getHeight());
        g.setColor(this.getColor());
        if(image != null)
            g.drawImage(image.getImage(), x, y, width, height, null);
        else if(this.getShape() instanceof Rectangle)
            g.fillRect(x, y, width, height);
        else
            g.fillOval(x, y, width, height);

        if(image == null)
        {
            g.setColor(this.getTextColor());
            g.drawString(this.getTitle(), x, y + (height/2));
        }

    }
}

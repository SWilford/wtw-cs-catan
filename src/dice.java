import javax.swing.*;
import java.awt.*;

public class dice {
    public static final ImageIcon face1 = new ImageIcon("images/face1.png");
    public static final ImageIcon face2 = new ImageIcon("images/face2.png");
    public static final ImageIcon face3 = new ImageIcon("images/face3.png");
    public static final ImageIcon face4 = new ImageIcon("images/face4.png");
    public static final ImageIcon face5 = new ImageIcon("images/face5.png");
    public static final ImageIcon face6 = new ImageIcon("images/face6.png");
    private static int diceFrame, nextDiceFrame, diceOffFrame;
    private boolean rolling;

    private final Shape shape;

    public ImageIcon currentFace;

    public dice(Shape s)
    {
        currentFace = face1;
        shape = s;
        rolling = false;
    }

    public void startRoll()
    {
        rolling = true;
        nextDiceFrame = catanGraphics.getFrames() + 200;
        diceOffFrame = catanGraphics.getFrames() + 800;
    }

    public boolean isRolling()
    {
        return rolling;
    }

    public void rollDice(Graphics g){
            diceFrame = catanGraphics.getFrames();
            int roll = 0;
                if (catanGraphics.getFrames() < nextDiceFrame && rolling) {
                    roll = (int) (Math.random() * 5) + 1;
                    if (roll == 1) {
                        currentFace = face1;
                    } else if (roll == 2) {
                        currentFace = face2;
                    } else if (roll == 3) {
                        currentFace = face3;
                    } else if (roll == 4) {
                        currentFace = face4;
                    } else if (roll == 5) {
                        currentFace = face5;
                    } else {
                        currentFace = face6;
                    }
                    drawDice(g);
                    nextDiceFrame = nextDiceFrame + 50;
                    checkToStopRoll();
                }
    }

    public Shape getShape()
    {
        return shape;
    }
    public void drawDice(Graphics g) {
        int x = (int) (this.getShape().getBounds().getX());
        int y = (int) (this.getShape().getBounds().getY());
        int width = (int) (this.getShape().getBounds().getWidth());
        int height = (int) (this.getShape().getBounds().getHeight());
        g.drawImage(currentFace.getImage(), x, y, width, height, null);
    }

    public void checkToStopRoll()
    {
        if(nextDiceFrame > diceOffFrame)
        {
            rolling = false;
        }
    }
}

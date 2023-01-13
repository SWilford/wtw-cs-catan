import java.io.Serializable;

public class coord implements Serializable {
    private int x, y;
    public coord(int x1, int y1)
    {
        x = x1;
        y = y1;
    }

    public int getX()
    {
        return x;
    }

    public int getY() {
        return y;
    }
}

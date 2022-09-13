public class developmentCard {
    private int type, pointValue;
    private String text;
    //1 = knight, 2 = progress, 3 = VP
    public developmentCard(int t, int p, String o)
    {
        type = t;
        pointValue = p;
        text = o;
    }
    public int getPointValue()
    {
        return pointValue;
    }

    public String getText() {
        return text;
    }
}

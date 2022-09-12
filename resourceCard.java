public class resourceCard extends card{
    private int type;
    //1 = wood, 2 = brick, 3 = sheep, 4 = ore, 5 = wheat
    public resourceCard(int i)
    {
        type = i;
    }
    public String getType()
    {
        return type;
    }


}

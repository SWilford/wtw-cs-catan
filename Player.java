public class Player {
    private int numSettlement, numCity, points, numOre, numBrick, numLumber, numWheat, numSheep, numRoad;
    private String color, name;

    public Player(String c, String n)
    {
        name = n;
        color = c;
        numSettlement = 5;
        numCity = 4;
        numRoad = 15;
    }


    //settlement = 0, city = 1, road = 2
    public static void updateBuildingCount(int i)
    {
        if(i = 0)
            numSettlement--;
        else if(i = 1)
            numCity--;
        else
            numRoad--;
    }

}

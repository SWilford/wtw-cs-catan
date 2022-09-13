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

    public int getSheep()
    {
        return numSheep;
    }
    public int getBrick()
    {
        return numBrick;
    }
    public int getWheat()
    {
        return numWheat;
    }
    public int getOre()
    {
        return numOre;
    }
    public int getLumber()
    {
        return numLumber;
    }


    //settlement = 0, city = 1, road = 2
    public void placeBuilding(int i)
    {
        if(i == 0) {
            numSettlement--;
            numLumber--;
            numBrick--;
            numWheat--;
            numSheep--;
        }
        else if(i == 1) {
            numCity--;
            numSettlement++;
            numOre -= 3;
            numWheat -= 2;
        }
        else {
            numRoad--;
            numBrick--;
            numLumber--;
        }
    }

    public void gainPoint(int v)
    {
        points += v;
    }
    public void losePoint(int v)
    {
        points -= v;
    }

    public int getPoints() {
        return points;
    }
}

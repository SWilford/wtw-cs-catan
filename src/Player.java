import java.io.Serializable;

public class Player implements Serializable {
    private int numSettlement, numCity, points, numOre, numBrick, numLumber, numWheat, numSheep, numRoad;
    private final String color, name;
    private card[] hand;
    private int knights;

    public Player(String c, String n) {
        name = n;
        color = c;
        numSettlement = 5;
        numCity = 4;
        numRoad = 15;
    }

    public int getSheep() {
        return numSheep;
    }

    public int getBrick() {
        return numBrick;
    }

    public int getWheat() {
        return numWheat;
    }

    public int getOre() {
        return numOre;
    }

    public int getLumber() {
        return numLumber;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }


    //settlement = 0, city = 1, road = 2
    public void placeBuilding(int i) {
        if (i == 0) {
            numSettlement--;
            numLumber--;
            numBrick--;
            numWheat--;
            numSheep--;
        } else if (i == 1) {
            numCity--;
            numSettlement++;
            numOre -= 3;
            numWheat -= 2;
        } else {
            numRoad--;
            numBrick--;
            numLumber--;
        }
    }

    public void gainPoint(int v) {
        points += v;
    }

    public void losePoint(int v) {
        points -= v;
    }

    public int getPoints() {
        return points;
    }

    public String getPointsAsString()
    {
        return "" + name + ": " + points;
    }


    public void gainResource(String r, int num) {
        switch (r) { //checks what the title is
            case "lumber" -> //goes through all the cases until it finds the right one
                    numLumber += num;
            case "wheat" -> numWheat += num;
            case "sheep" -> numSheep += num;
            case "brick" -> numBrick += num;
            case "ore" -> numOre += num;
        }
    }

    public void loseResource(String r, int num) {
        switch (r) { //checks what the title is
            case "lumber" -> //goes through all the cases until it finds the right one
                    numLumber -= num;
            case "wheat" -> numWheat -= num;
            case "sheep" -> numSheep -= num;
            case "brick" -> numBrick -= num;
            case "ore" -> numOre -= num;
        }
    }

    public int getNumSettlement() {
        return numSettlement;
    }

    public int getNumCity() {
        return numCity;
    }

    public int getNumRoad() {
        return numRoad;
    }

    public card[] getHand() {
        return hand;
    }

    public void setHand(card[] hand) {
        this.hand = hand;
    }

    public boolean checkWin() {
        return points >= 10;
    }

    public void addKnight()
    {
        knights++;
    }
}

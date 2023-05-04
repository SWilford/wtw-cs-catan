import java.io.Serializable;

public class Player implements Serializable {
    private int numSettlement, numCity, points, numOre, numBrick, numLumber, numWheat, numSheep, numRoad;
    private final String color, name;
    private card[] hand1;
    private int knights;

    private playerHand hand;

    public Player(String c, String n) {
        name = n;
        color = c;
        numSettlement = 5;
        numCity = 4;
        numRoad = 15;
        hand = new playerHand();
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
            hand.removeCard(1);
            numBrick--;
            hand.removeCard(2);
            numWheat--;
            hand.removeCard(5);
            numSheep--;
            hand.removeCard(3);


        } else if (i == 1) {
            numCity--;
            numSettlement++;
            numOre -= 3;
            hand.removeCard(4);
            hand.removeCard(4);
            hand.removeCard(4);
            hand.removeCard(5);
            hand.removeCard(5);
            numWheat -= 2;
        } else {
            numRoad--;
            numBrick--;
            hand.removeCard(1);
            hand.removeCard(2);

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
        if(r.equals("Forest")) { //checks what the title is
            numLumber += num;
            hand.addCard(new resourceCard(1));
        }
    else if(r.equals("Fields")) {
            numWheat += num;
            hand.addCard(new resourceCard(5));
        }
    else if(r.equals("Pasture")) {
            numSheep += num;
            hand.addCard(new resourceCard(3));
        }
    else if(r.equals("Hills")) {
            numBrick += num;
            hand.addCard(new resourceCard(2));
        }
    else if(r.equals("Mountains"))
        {

            numOre += num;
            hand.addCard(new resourceCard(4));
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

    public playerHand getHand() {
        return hand;
    }

    public void setHand(card[] hand) {
        this.hand1 = hand;
    }

    public boolean checkWin() {
        return points >= 10;
    }

    public void addKnight()
    {
        knights++;
    }

    public void startSettlement() {
        numSettlement--;
    }
    public void startRoad() {
        numRoad--;
    }
}

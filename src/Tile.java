public class Tile {
    private final String type;
    private int number;

    private boolean specialHarbor;//If a tile is a special harbor or not
    private int nO, noE, soE, sO, soW, noW; //Stores values for shared vertices of hexes

    public Tile(String t, int n)//Constructor
    {
        type = t;
        number = n;
        nO = 0;
        noE = 0;
        soE = 0;
        sO = 0;
        soW = 0;
        noW = 0; //vertex values defaulted to 0
        specialHarbor = false; //harbor defaulted to 0
    }

    public void setNorth(int nh) {//mutator method for north vertex value
        nO = nh;
    }//mutator for north vertex value

    public void setNorthEast(int ne) {//mutator method for northeast vertex value
        noE = ne;
    }//mutator for northeast vertex value

    public void setSouthEast(int se) {//mutator method for southeast vertex value
        soE = se;
    }

    public void setSouth(int sh) {//mutator method for south vertex value
        sO = sh;
    }//mutator for south vertex value

    public void getSouthWest(int sw) {//mutator method for southwest vertex value
        soW = sw;
    }//mutator for southwest vertex value

    public void getNorthWest(int nw) {//mutator method for northwest vertex value
        noW = nw;
    }//mutator for northwest vertex value

    public void setSpecialHarbor(boolean b) {//mutator for special harbor
        specialHarbor = b;
    }

    public String getType() {
        return type;
    }//accessor for type
    public int getNumber()
    {
        return number;
    }//accessor for number

    public int getNorth() { //accessor for north vertex value
        return nO;
    }

    public int getNorthEast() { //accessor for northeast vertex
        return noE;
    }

    public int getSouthEast() { //accessor for southeast vertex
        return soE;
    }

    public int getSouth() { //accessor for south vertex
        return sO;
    }

    public int getSouthWest() { //accessor for southwest vertex
        return soW;
    }

    public int getNorthWest() { //accessor for northwest vertex
        return noW;
    }

    public boolean getSpecialHarbor() {
        return specialHarbor;
    }

    void setNumber(int n) {//mutator for number
        number = n;
    }
}

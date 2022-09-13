public class Tile {
    private final String type;
    private final int number;

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
    }

    public void setNorth(int nh) {//mutator method for north vertex value
        nO = nh;
    }

    public void setNorthEast(int ne) {//mutator method for northeast vertex value
        noE = ne;
    }

    public void setSouthEast(int se) {//mutator method for southeast vertex value
        soE = se;
    }

    public void setSouth(int sh) {//mutator method for south vertex value
        sO = sh;
    }

    public void getSouthWest(int sw) {//mutator method for southwest vertex value
        soW = sw;
    }

    public void getNorthWest(int nw) {//mutator method for northwest vertex value
        noW = nw;
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
}

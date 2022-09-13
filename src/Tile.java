public class Tile {
    private String type;
    private int number;

    private int nO, noE, soE, sO, soW, noW; //Stores values for shared vertices of hexes

    public Tile(String t, int n, int nD, int neD, int seD, int sD, int swD, int nwD)
    {
        type = t;
        number = n;
        nO = nD;
        noE = neD;
        soE = seD;
        sO = sD;
        soW = swD;
        noW = nwD;
    }

    public String getType() {
        return type;
    }
    public int getNumber()
    {
        return number;
    }

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

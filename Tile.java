public class Tile {
    private String type;
    private int number;

    private int nO, noE, soE, sO, soW, noW; //Stores values for shared vertices of hexes

    public Tile(String t, int n, nD, neD, seD, sD, swD, nwD)
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

    public int getNorth() { //acessor for north vertex value
        return nO;
    }

    public int getNorthEast() { //accessor for north east vertex
        return noE;
    }

    public int getSouthEast() { //accessor for south east vertex
        return soE;
    }

    public int getSouth() { //accessor for south vertex
        rerturn sO;
    }

    public int getSouthWest() { //accessor for south west vertex
        return soW;
    }

    public int getNorthWest() { //accessor for north west vertex
        return noW;
    }
}

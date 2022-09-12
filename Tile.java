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

    public int getNorthEast() { //accessor for north eat vertex
        return noE;
    }

}

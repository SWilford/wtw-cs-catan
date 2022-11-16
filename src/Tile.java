import java.io.Serializable;
import java.util.*;

public class Tile implements Serializable {
    private final String type;
    private final int number;
    private edge[] edges = new edge[6];

    private String specialHarbor;//If a harbor is a special harbor what type
    private int nO, noE, soE, sO, soW, noW; //Stores values for shared vertices of hexes
    private boolean[] settlements = new boolean[6];
    private String[] settlementsOwnedBy = new String[6];
    private boolean[] cities = new boolean[6];

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
        specialHarbor = "NOT"; //harbor defaulted to not a special harbor
    }

    private class edge implements Serializable{
        ArrayList<Integer> connectsEdge = new ArrayList<>();
        private Boolean isRoad;
        private final int name;
        public edge(int n) {
            name = n;
            isRoad = false;
        }
        public void addConnection(int c) {
            connectsEdge.add(c);
        }
        public ArrayList<Integer> getConnectsEdge(){
            return connectsEdge;
        }
        public void makeRoad() {
            isRoad = true;
        }
        public boolean getIsRoad() {
            return isRoad;
        }
        public int getName() {
            return name;
        }
    }

    public void makeOwned(String color, int vertex) {
        settlementsOwnedBy[vertex] = color;
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

    public void setSouthWest(int sw) {//mutator method for southwest vertex value
        soW = sw;
    }//mutator for southwest vertex value

    public void setNorthWest(int nw) {//mutator method for northwest vertex value
        noW = nw;
    }//mutator for northwest vertex value

    public void setSpecialHarbor(String b) {//mutator for special harbor
        specialHarbor = b;
    }

    public int getNorth() {
        return nO;
    }
    public int getNorthEast() {
        return noE;
    }
    public int getSouthEast() {
        return soE;
    }
    public int getSouth() {
        return sO;
    }
    public int getSouthWest() {
        return soW;
    }
    public int getNorthWest() {
        return noW;
    }
    public String getType() {
        return type;
    }//accessor for type
    public int getNumber()
    {
        return number;
    }//accessor for number

    public String getSpecialHarbor() {
        return specialHarbor;
    }

    void buildSettlement(int vertex) {
        settlements[vertex] = true;
    }

    public boolean getSettlement(int vertex) {
        return settlements[vertex];
    }

    void buildCity(int vertex) {
        cities[vertex-1] = true;
    }

    public boolean getCity(int vertex) {
        return cities[vertex-1];
    }

    public void setEdgeName(int i, int n) {
        edges[i] = new edge(n);
    }

    public int getEdgeName(int i) {
        return edges[i].getName();
    }

    public String getOwnedBy(int vertex) {
        return settlementsOwnedBy[vertex];
    }
}

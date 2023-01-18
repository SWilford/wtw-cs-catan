import java.io.Serializable;
import java.util.*;
public class Vertex implements Serializable {
    private final int number;
    private boolean isBuilt;
    private String owner;
    private boolean isCity;
    private ArrayList<Integer> neighborNumbs = new ArrayList<>();
    private boolean buildable;

    private ArrayList<coord> tiles;

    public Vertex(int number) {
        this.number = number;
        isBuilt = false;
        owner = null;
        isCity = false;
        buildable = true;
        tiles = new ArrayList<coord>();
    }

    public void addNeighbor(int n) {
        neighborNumbs.add(n);
    }

    public void setBuildable() {
        buildable = false;
    }

    public void build(String owner) {
        this.owner = owner;
        isBuilt = true;
    }

    public void upgrade(String owner) {
        this.owner = owner;
        isCity = true;
    }

    public void addTile(int r, int c)
    {
        tiles.add(new coord(r, c));
    }

    public boolean hasTile(int r, int c)
    {
        for(int i = 0; i<tiles.size(); i++)
        {
            if(tiles.get(i).getX() == r && tiles.get(i).getY() == c)
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getNeighborNumbs() {
        return neighborNumbs;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isCity() {
        return isCity;
    }

    public ArrayList<Integer> getNeighbors() {
        return neighborNumbs;
    }
}

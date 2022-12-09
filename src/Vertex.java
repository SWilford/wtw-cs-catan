import java.io.Serializable;
import java.util.*;
public class Vertex implements Serializable {
    private final int number;
    private boolean isBuilt;
    private String owner;
    private boolean isCity;
    private ArrayList<Integer> neighborNumbs = new ArrayList<>();
    private boolean buildable;

    public Vertex(int number) {
        this.number = number;
        isBuilt = false;
        owner = null;
        isCity = false;
        buildable = true;
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

    public void upgrade() {
        isCity = true;
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

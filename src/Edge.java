import java.io.Serializable;
import java.util.ArrayList;

public class Edge implements Serializable {

    private final int number;
    private String owner;
    private boolean isBuilt;
    private boolean buildable;

    private ArrayList<Integer> connections = new ArrayList<>();
    private ArrayList<Integer> originatingVertexes = new ArrayList<>();


    public Edge(int number) {
        owner = "none";
        isBuilt = false;
        buildable = false;
        this.number = number;
    }

    public void addConnection(int n) {
        connections.add(n);
    }

    public void addOrigin(int n) {
        originatingVertexes.add(n);
    }

    public void build(String owner) {
        this.owner = owner;
        isBuilt = true;
    }

    public void setBuildable(Boolean b) {
        buildable = b;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }

    public ArrayList<Integer> getOriginatingVertices() {
        return originatingVertexes;
    }
}

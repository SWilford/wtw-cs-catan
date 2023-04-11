import java.io.Serializable;
import java.util.ArrayList;

public class Edge implements Serializable {

    private final int number;
    private String owner;
    private boolean isBuilt;
    private boolean buildable;

    private boolean checked; //for checking the longest road

    private final ArrayList<Integer> connections = new ArrayList<>();
    private final ArrayList<Integer> originatingVertexes = new ArrayList<>();
    private final ArrayList<Integer> connectedRoads = new ArrayList<>();


    public Edge(int number) {
        owner = "none";
        isBuilt = false;
        buildable = false;
        this.number = number;
        checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean b) {
        checked = b;
    }

    public void addConnectedRoad(int n) {
        connectedRoads.add(n);
    }

    public ArrayList<Integer> getConnectedRoads() {
        return connectedRoads;
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

    public int getNumber() {
        return number;
    }

}

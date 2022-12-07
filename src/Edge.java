import java.io.Serializable;

public class Edge implements Serializable {
    private String owner;
    private boolean isBuilt;

    public Edge() {
        owner = null;
        isBuilt = false;
    }

    public void build(String owner) {
        this.owner = owner;
        isBuilt = true;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public String getOwner() {
        return owner;
    }
}

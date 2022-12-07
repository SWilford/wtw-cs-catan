import java.io.Serializable;
import java.util.*;

public class VertexWeb implements Serializable {
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public VertexWeb() {
        for(int i = 0; i < 54; i++) {
            Vertex v = new Vertex(i);
            vertices.add(v);
        }
        //The following should have some way to be optimized, some kind of algorithm.
        vertices.get(0).addNeighbor(4);
        vertices.get(0).addNeighbor(5);

        vertices.get(1).addNeighbor(5);
        vertices.get(1).addNeighbor(6);

        vertices.get(2).addNeighbor(6);
        vertices.get(2).addNeighbor(7);

        vertices.get(3).addNeighbor(1);
        vertices.get(3).addNeighbor(8);

    }
}

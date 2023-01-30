import java.io.Serializable;
import java.util.*;

public class EdgeWeb implements Serializable {
    private ArrayList<Edge> edges = new ArrayList<>();

    public EdgeWeb() {
        for(int i = 0; i < 72; i++) {
            Edge e = new Edge(i);
            edges.add(e);
        }
        //The following should have some way to be optimized, some kind of algorithm.
        edges.get(0).addConnection(1);
        edges.get(0).addConnection(6);

        edges.get(1).addConnection(0);
        edges.get(1).addConnection(2);
        edges.get(1).addConnection(7);

        edges.get(2).addConnection(1);
        edges.get(2).addConnection(3);
        edges.get(2).addConnection(7);

        edges.get(3).addConnection(2);
        edges.get(3).addConnection(4);
        edges.get(3).addConnection(8);

        edges.get(4).addConnection(3);
        edges.get(4).addConnection(5);
        edges.get(4).addConnection(8);

        edges.get(5).addConnection(4);
        edges.get(5).addConnection(9);




    }
}

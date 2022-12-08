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

        vertices.get(4).addNeighbor(0);
        vertices.get(4).addNeighbor(1);
        vertices.get(4).addNeighbor(8);

        vertices.get(5).addNeighbor(1);
        vertices.get(5).addNeighbor(2);
        vertices.get(5).addNeighbor(9);

        vertices.get(6).addNeighbor(2);
        vertices.get(6).addNeighbor(10);

        vertices.get(7).addNeighbor(3);
        vertices.get(7).addNeighbor(11);
        vertices.get(7).addNeighbor(12);

        vertices.get(8).addNeighbor(4);
        vertices.get(8).addNeighbor(12);
        vertices.get(8).addNeighbor(13);

        vertices.get(9).addNeighbor(5);
        vertices.get(9).addNeighbor(13);
        vertices.get(9).addNeighbor(14);

        vertices.get(10).addNeighbor(6);
        vertices.get(10).addNeighbor(14);
        vertices.get(10).addNeighbor(15);

        vertices.get(11).addNeighbor(7);
        vertices.get(11).addNeighbor(18);

        vertices.get(12).addNeighbor(7);
        vertices.get(12).addNeighbor(8);
        vertices.get(12).addNeighbor(17);

        vertices.get(13).addNeighbor(8);
        vertices.get(13).addNeighbor(9);
        vertices.get(13).addNeighbor(18);

        vertices.get(14).addNeighbor(9);
        vertices.get(14).addNeighbor(10);
        vertices.get(14).addNeighbor(19);

        vertices.get(15).addNeighbor(10);
        vertices.get(15).addNeighbor(20);

        vertices.get(16).addNeighbor(11);
        vertices.get(16).addNeighbor(21);
        vertices.get(16).addNeighbor(22);

        vertices.get(17).addNeighbor(12);
        vertices.get(17).addNeighbor(22);
        vertices.get(17).addNeighbor(23);

        vertices.get(18).addNeighbor(13);
        vertices.get(18).addNeighbor(23);
        vertices.get(18).addNeighbor(24);

        vertices.get(19).addNeighbor(14);
        vertices.get(19).addNeighbor(24);
        vertices.get(19).addNeighbor(25);

        vertices.get(20).addNeighbor(15);
        vertices.get(20).addNeighbor(25);
        vertices.get(20).addNeighbor(26);

    }
}

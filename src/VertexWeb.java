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
        vertices.get(0).addNeighbor(3);
        vertices.get(0).addNeighbor(4);

        vertices.get(1).addNeighbor(4);
        vertices.get(1).addNeighbor(5);

        vertices.get(2).addNeighbor(5);
        vertices.get(2).addNeighbor(6);

        vertices.get(3).addNeighbor(0);
        vertices.get(3).addNeighbor(7);

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

        vertices.get(21).addNeighbor(16);
        vertices.get(21).addNeighbor(27);

        vertices.get(22).addNeighbor(16);
        vertices.get(22).addNeighbor(17);
        vertices.get(22).addNeighbor(28);

        vertices.get(23).addNeighbor(17);
        vertices.get(23).addNeighbor(18);
        vertices.get(23).addNeighbor(29);

        vertices.get(24).addNeighbor(18);
        vertices.get(24).addNeighbor(19);
        vertices.get(24).addNeighbor(30);

        vertices.get(25).addNeighbor(19);
        vertices.get(25).addNeighbor(20);
        vertices.get(25).addNeighbor(31);

        vertices.get(26).addNeighbor(20);
        vertices.get(26).addNeighbor(32);

        vertices.get(27).addNeighbor(21);
        vertices.get(27).addNeighbor(33);

        vertices.get(28).addNeighbor(22);
        vertices.get(28).addNeighbor(33);
        vertices.get(28).addNeighbor(34);

        vertices.get(29).addNeighbor(23);
        vertices.get(29).addNeighbor(34);
        vertices.get(29).addNeighbor(35);

        vertices.get(30).addNeighbor(24);
        vertices.get(30).addNeighbor(35);
        vertices.get(30).addNeighbor(36);

        vertices.get(31).addNeighbor(25);
        vertices.get(31).addNeighbor(36);
        vertices.get(31).addNeighbor(37);

        vertices.get(32).addNeighbor(26);
        vertices.get(32).addNeighbor(37);

        vertices.get(33).addNeighbor(27);
        vertices.get(33).addNeighbor(28);
        vertices.get(33).addNeighbor(38);

        vertices.get(34).addNeighbor(28);
        vertices.get(34).addNeighbor(29);
        vertices.get(34).addNeighbor(39);

        vertices.get(35).addNeighbor(29);
        vertices.get(35).addNeighbor(30);
        vertices.get(35).addNeighbor(40);

        vertices.get(36).addNeighbor(30);
        vertices.get(36).addNeighbor(31);
        vertices.get(36).addNeighbor(41);

        vertices.get(37).addNeighbor(31);
        vertices.get(37).addNeighbor(32);
        vertices.get(37).addNeighbor(42);

        vertices.get(38).addNeighbor(33);
        vertices.get(38).addNeighbor(43);

        vertices.get(39).addNeighbor(34);
        vertices.get(39).addNeighbor(43);
        vertices.get(39).addNeighbor(44);

        vertices.get(40).addNeighbor(35);
        vertices.get(40).addNeighbor(44);
        vertices.get(40).addNeighbor(45);

        vertices.get(41).addNeighbor(36);
        vertices.get(41).addNeighbor(45);
        vertices.get(41).addNeighbor(46);

        vertices.get(42).addNeighbor(37);
        vertices.get(42).addNeighbor(46);

        vertices.get(43).addNeighbor(38);
        vertices.get(43).addNeighbor(39);
        vertices.get(43).addNeighbor(47);

        vertices.get(44).addNeighbor(39);
        vertices.get(44).addNeighbor(40);
        vertices.get(44).addNeighbor(48);

        vertices.get(45).addNeighbor(40);
        vertices.get(45).addNeighbor(41);
        vertices.get(45).addNeighbor(49);

        vertices.get(46).addNeighbor(41);
        vertices.get(46).addNeighbor(42);
        vertices.get(46).addNeighbor(50);

        vertices.get(47).addNeighbor(43);
        vertices.get(47).addNeighbor(51);

        vertices.get(48).addNeighbor(44);
        vertices.get(48).addNeighbor(51);
        vertices.get(48).addNeighbor(52);

        vertices.get(49).addNeighbor(45);
        vertices.get(49).addNeighbor(52);
        vertices.get(49).addNeighbor(53);

        vertices.get(50).addNeighbor(46);
        vertices.get(50).addNeighbor(53);

        vertices.get(51).addNeighbor(47);
        vertices.get(51).addNeighbor(48);

        vertices.get(52).addNeighbor(48);
        vertices.get(52).addNeighbor(49);

        vertices.get(53).addNeighbor(49);
        vertices.get(53).addNeighbor(50);

        vertices.get(0).addTile(1, 2);
        vertices.get(1).addTile(1, 3);
        vertices.get(2).addTile(1, 4);
        vertices.get(3).addTile(1, 2);
        vertices.get(4).addTile(1, 2);
        vertices.get(4).addTile(1, 3);
        vertices.get(5).addTile(1, 3);
        vertices.get(5).addTile(1, 4);
        vertices.get(6).addTile(1, 4);
        vertices.get(7).addTile(1, 2);
        vertices.get(7).addTile(2, 2);
        vertices.get(8).addTile(1, 2);
        vertices.get(8).addTile(1, 3);
        vertices.get(8).addTile(2, 3);
        vertices.get(9).addTile(1, 3);
        vertices.get(9).addTile(1, 4);
        vertices.get(9).addTile(2, 4);
        vertices.get(10).addTile(1, 4);
        vertices.get(10).addTile(2, 5);
        vertices.get(11).addTile(2, 2);
        vertices.get(12).addTile(1, 2);
        vertices.get(12).addTile(2, 2);
        vertices.get(12).addTile(2, 3);
        vertices.get(13).addTile(1, 3);
        vertices.get(13).addTile(2, 3);
        vertices.get(13).addTile(2, 4);
        vertices.get(14).addTile(1, 4);
        vertices.get(14).addTile(2, 4);
        vertices.get(14).addTile(2, 5);
        vertices.get(15).addTile(2, 5);
        vertices.get(16).addTile(2, 2);
        vertices.get(16).addTile(3, 1);
        vertices.get(17).addTile(2, 2);
        vertices.get(17).addTile(2, 3);
        vertices.get(17).addTile(3, 2);
        vertices.get(18).addTile(2, 3);
        vertices.get(18).addTile(2, 4);
        vertices.get(18).addTile(3, 3);
        vertices.get(19).addTile(2, 4);
        vertices.get(19).addTile(2, 5);
        vertices.get(19).addTile(3, 4);
        vertices.get(20).addTile(2, 5);
        vertices.get(20).addTile(3, 5);






    }

    public void buildSettlement(int i, String color) {
        vertices.get(i).build(color);
        vertices.get(i).setBuildable();
        ArrayList<Integer> neighbors = vertices.get(i).getNeighbors();
        for(Integer a : neighbors) {
            vertices.get(a).setBuildable();
        }
    }

    public void upgradeSettlement(int i) {
        if(!vertices.get(i).isCity()) {
            vertices.get(i).upgrade();
        }
    }

    public String getOwner(int i) {
        return vertices.get(i).getOwner();
    }

    public boolean isSettled(int i) {
        return vertices.get(i).isBuilt();
    }

    public boolean isBuildable(int i) { //need code to make sure building rule is followed
        return vertices.get(i).isBuildable();
    }

    public boolean isCity(int i) {
        return vertices.get(i).isCity();
    }


    public boolean isNext(int i, int r, int c)
    {
        if(vertices.get(i).hasTile(r, c))
        {
            return true;
        }
        else return false;
    }
}

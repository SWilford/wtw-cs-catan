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

        edges.get(6).addConnection(0);
        edges.get(6).addConnection(10);
        edges.get(6).addConnection(11);

        edges.get(7).addConnection(1);
        edges.get(7).addConnection(2);
        edges.get(7).addConnection(12);
        edges.get(7).addConnection(13);

        edges.get(8).addConnection(3);
        edges.get(8).addConnection(4);
        edges.get(8).addConnection(14);
        edges.get(8).addConnection(15);

        edges.get(9).addConnection(5);
        edges.get(9).addConnection(16);
        edges.get(9).addConnection(17);

        edges.get(10).addConnection(6);
        edges.get(10).addConnection(11);
        edges.get(10).addConnection(18);

        edges.get(11).addConnection(6);
        edges.get(11).addConnection(10);
        edges.get(11).addConnection(12);
        edges.get(11).addConnection(19);

        edges.get(12).addConnection(7);
        edges.get(12).addConnection(11);
        edges.get(12).addConnection(13);
        edges.get(12).addConnection(19);

        edges.get(13).addConnection(7);
        edges.get(13).addConnection(12);
        edges.get(13).addConnection(14);
        edges.get(13).addConnection(20);

        edges.get(14).addConnection(8);
        edges.get(14).addConnection(13);
        edges.get(14).addConnection(15);
        edges.get(14).addConnection(20);

        edges.get(15).addConnection(8);
        edges.get(15).addConnection(14);
        edges.get(15).addConnection(16);
        edges.get(15).addConnection(21);

        edges.get(16).addConnection(9);
        edges.get(16).addConnection(15);
        edges.get(16).addConnection(17);
        edges.get(16).addConnection(21);

        edges.get(17).addConnection(9);
        edges.get(17).addConnection(16);
        edges.get(17).addConnection(22);

        edges.get(18).addConnection(10);
        edges.get(18).addConnection(23);
        edges.get(18).addConnection(24);

        edges.get(19).addConnection(11);
        edges.get(19).addConnection(12);
        edges.get(19).addConnection(25);
        edges.get(19).addConnection(26);

        edges.get(20).addConnection(13);
        edges.get(20).addConnection(14);
        edges.get(20).addConnection(27);
        edges.get(20).addConnection(28);

        edges.get(21).addConnection(15);
        edges.get(21).addConnection(16);
        edges.get(21).addConnection(29);
        edges.get(21).addConnection(30);

        edges.get(22).addConnection(17);
        edges.get(22).addConnection(31);
        edges.get(22).addConnection(32);

        edges.get(23).addConnection(18);
        edges.get(23).addConnection(24);
        edges.get(23).addConnection(33);

        edges.get(24).addConnection(18);
        edges.get(24).addConnection(23);
        edges.get(24).addConnection(25);
        edges.get(24).addConnection(34);

        edges.get(25).addConnection(19);
        edges.get(25).addConnection(24);
        edges.get(25).addConnection(26);
        edges.get(25).addConnection(34);

        edges.get(26).addConnection(19);
        edges.get(26).addConnection(25);
        edges.get(26).addConnection(27);
        edges.get(26).addConnection(35);

        edges.get(27).addConnection(20);
        edges.get(27).addConnection(26);
        edges.get(27).addConnection(28);
        edges.get(27).addConnection(35);

        edges.get(28).addConnection(20);
        edges.get(28).addConnection(27);
        edges.get(28).addConnection(29);
        edges.get(28).addConnection(36);

        edges.get(29).addConnection(21);
        edges.get(29).addConnection(28);
        edges.get(29).addConnection(30);
        edges.get(29).addConnection(36);

        edges.get(30).addConnection(21);
        edges.get(30).addConnection(29);
        edges.get(30).addConnection(31);
        edges.get(30).addConnection(37);

        edges.get(31).addConnection(22);
        edges.get(31).addConnection(30);
        edges.get(31).addConnection(32);
        edges.get(31).addConnection(37);

        edges.get(32).addConnection(22);
        edges.get(32).addConnection(31);
        edges.get(32).addConnection(38);

        edges.get(33).addConnection(23);
        edges.get(33).addConnection(39);

        edges.get(34).addConnection(24);
        edges.get(34).addConnection(25);
        edges.get(34).addConnection(40);
        edges.get(34).addConnection(41);

        edges.get(35).addConnection(26);
        edges.get(35).addConnection(27);
        edges.get(35).addConnection(42);
        edges.get(35).addConnection(43);

        edges.get(36).addConnection(28);
        edges.get(36).addConnection(29);
        edges.get(36).addConnection(44);
        edges.get(36).addConnection(45);

        edges.get(37).addConnection(30);
        edges.get(37).addConnection(31);
        edges.get(37).addConnection(46);
        edges.get(37).addConnection(47);

        edges.get(38).addConnection(32);
        edges.get(38).addConnection(48);

        edges.get(39).addConnection(33);
        edges.get(39).addConnection(40);
        edges.get(39).addConnection(49);

        edges.get(40).addConnection(34);
        edges.get(40).addConnection(39);
        edges.get(40).addConnection(41);
        edges.get(40).addConnection(49);

        edges.get(41).addConnection(34);
        edges.get(41).addConnection(40);
        edges.get(41).addConnection(42);
        edges.get(41).addConnection(50);

        edges.get(42).addConnection(35);
        edges.get(42).addConnection(41);
        edges.get(42).addConnection(43);
        edges.get(42).addConnection(50);

        edges.get(43).addConnection(35);
        edges.get(43).addConnection(42);
        edges.get(43).addConnection(44);
        edges.get(43).addConnection(51);

        edges.get(44).addConnection(36);
        edges.get(44).addConnection(43);
        edges.get(44).addConnection(45);
        edges.get(44).addConnection(51);

        edges.get(45).addConnection(36);
        edges.get(45).addConnection(44);
        edges.get(45).addConnection(46);
        edges.get(45).addConnection(52);

        edges.get(47).addConnection(37);
        edges.get(47).addConnection(46);
        edges.get(47).addConnection(48);
        edges.get(47).addConnection(53);

        edges.get(48).addConnection(38);
        edges.get(48).addConnection(47);
        edges.get(48).addConnection(53);

        edges.get(49).addConnection(39);
        edges.get(49).addConnection(40);
        edges.get(49).addConnection(54);

        edges.get(50).addConnection(41);
        edges.get(50).addConnection(42);
        edges.get(50).addConnection(55);
        edges.get(50).addConnection(56);

        edges.get(51).addConnection(43);
        edges.get(51).addConnection(44);
        edges.get(51).addConnection(57);
        edges.get(51).addConnection(58);

        edges.get(52).addConnection(45);
        edges.get(52).addConnection(46);
        edges.get(52).addConnection(59);
        edges.get(52).addConnection(60);

        edges.get(53).addConnection(47);
        edges.get(53).addConnection(48);
        edges.get(53).addConnection(61);

        edges.get(54).addConnection(49);
        edges.get(54).addConnection(55);
        edges.get(54).addConnection(62);

        edges.get(55).addConnection(50);
        edges.get(55).addConnection(54);
        edges.get(55).addConnection(56);
        edges.get(55).addConnection(62);

        edges.get(56).addConnection(50);
        edges.get(56).addConnection(55);
        edges.get(56).addConnection(57);
        edges.get(56).addConnection(63);

        edges.get(57).addConnection(51);
        edges.get(57).addConnection(56);
        edges.get(57).addConnection(58);
        edges.get(57).addConnection(63);

        edges.get(58).addConnection(51);
        edges.get(58).addConnection(57);
        edges.get(58).addConnection(59);
        edges.get(58).addConnection(64);

        edges.get(59).addConnection(52);
        edges.get(59).addConnection(58);
        edges.get(59).addConnection(60);
        edges.get(59).addConnection(64);

        edges.get(60).addConnection(52);
        edges.get(60).addConnection(59);
        edges.get(60).addConnection(61);
        edges.get(60).addConnection(65);

        edges.get(61).addConnection(53);
        edges.get(61).addConnection(60);
        edges.get(61).addConnection(65);

        edges.get(62).addConnection(54);
        edges.get(62).addConnection(55);
        edges.get(62).addConnection(66);

        edges.get(63).addConnection(56);
        edges.get(63).addConnection(57);
        edges.get(63).addConnection(67);
        edges.get(63).addConnection(68);

        edges.get(64).addConnection(58);
        edges.get(64).addConnection(59);
        edges.get(64).addConnection(69);
        edges.get(64).addConnection(70);

        edges.get(65).addConnection(60);
        edges.get(65).addConnection(61);
        edges.get(65).addConnection(71);

        edges.get(66).addConnection(62);
        edges.get(66).addConnection(67);

        edges.get(67).addConnection(63);
        edges.get(67).addConnection(66);
        edges.get(67).addConnection(68);

        edges.get(68).addConnection(63);
        edges.get(68).addConnection(67);
        edges.get(68).addConnection(69);

        edges.get(69).addConnection(64);
        edges.get(69).addConnection(68);
        edges.get(69).addConnection(70);

        edges.get(70).addConnection(64);
        edges.get(70).addConnection(69);
        edges.get(70).addConnection(71);

        edges.get(71).addConnection(65);
        edges.get(71).addConnection(70);
    }

    public void buildRoad(int i, String color) {
        edges.get(i).build(color);
        edges.get(i).setBuildable();
    }

    public String getOwner(int i) {
        return edges.get(i).getOwner();
    }

    public boolean isBuilt(int i) {
        return edges.get(i).isBuilt();
    }

    public boolean isBuildable(int i) {
        return edges.get(i).isBuildable();
    }

}

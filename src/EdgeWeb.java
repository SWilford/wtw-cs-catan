import java.io.Serializable;
import java.util.*;

public class EdgeWeb implements Serializable {
    private final ArrayList<Edge> edges = new ArrayList<>();

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

        edges.get(46).addConnection(37);
        edges.get(46).addConnection(45);
        edges.get(46).addConnection(47);
        edges.get(46).addConnection(52);

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

        //Once again, the following should be optimized
        edges.get(0).addOrigin(0);
        edges.get(0).addOrigin(3);

        edges.get(1).addOrigin(0);
        edges.get(1).addOrigin(4);

        edges.get(2).addOrigin(1);
        edges.get(2).addOrigin(4);

        edges.get(3).addOrigin(1);
        edges.get(3).addOrigin(5);

        edges.get(4).addOrigin(2);
        edges.get(4).addOrigin(5);

        edges.get(5).addOrigin(2);
        edges.get(5).addOrigin(6);

        edges.get(6).addOrigin(3);
        edges.get(6).addOrigin(7);

        edges.get(7).addOrigin(4);
        edges.get(7).addOrigin(8);

        edges.get(8).addOrigin(5);
        edges.get(8).addOrigin(9);

        edges.get(9).addOrigin(6);
        edges.get(9).addOrigin(10);

        edges.get(10).addOrigin(7);
        edges.get(10).addOrigin(11);

        edges.get(11).addOrigin(7);
        edges.get(11).addOrigin(12);

        edges.get(12).addOrigin(8);
        edges.get(12).addOrigin(12);

        edges.get(13).addOrigin(8);
        edges.get(13).addOrigin(13);

        edges.get(14).addOrigin(9);
        edges.get(14).addOrigin(13);

        edges.get(15).addOrigin(9);
        edges.get(15).addOrigin(14);

        edges.get(16).addOrigin(10);
        edges.get(16).addOrigin(14);

        edges.get(17).addOrigin(10);
        edges.get(17).addOrigin(15);

        edges.get(18).addOrigin(11);
        edges.get(18).addOrigin(16);

        edges.get(19).addOrigin(12);
        edges.get(19).addOrigin(17);

        edges.get(20).addOrigin(13);
        edges.get(20).addOrigin(18);

        edges.get(21).addOrigin(14);
        edges.get(21).addOrigin(19);

        edges.get(22).addOrigin(15);
        edges.get(22).addOrigin(20);

        edges.get(23).addOrigin(16);
        edges.get(23).addOrigin(21);

        edges.get(24).addOrigin(16);
        edges.get(24).addOrigin(22);

        edges.get(25).addOrigin(17);
        edges.get(25).addOrigin(22);

        edges.get(26).addOrigin(17);
        edges.get(26).addOrigin(23);

        edges.get(27).addOrigin(18);
        edges.get(27).addOrigin(23);

        edges.get(28).addOrigin(18);
        edges.get(28).addOrigin(24);

        edges.get(29).addOrigin(19);
        edges.get(29).addOrigin(24);

        edges.get(30).addOrigin(19);
        edges.get(30).addOrigin(25);

        edges.get(31).addOrigin(20);
        edges.get(31).addOrigin(25);

        edges.get(32).addOrigin(20);
        edges.get(32).addOrigin(26);

        edges.get(33).addOrigin(21);
        edges.get(33).addOrigin(27);

        edges.get(34).addOrigin(22);
        edges.get(34).addOrigin(28);

        edges.get(35).addOrigin(23);
        edges.get(35).addOrigin(29);

        edges.get(36).addOrigin(24);
        edges.get(36).addOrigin(30);

        edges.get(37).addOrigin(25);
        edges.get(37).addOrigin(31);

        edges.get(38).addOrigin(26);
        edges.get(38).addOrigin(32);

        edges.get(39).addOrigin(27);
        edges.get(39).addOrigin(33);

        edges.get(40).addOrigin(28);
        edges.get(40).addOrigin(33);

        edges.get(41).addOrigin(28);
        edges.get(41).addOrigin(34);

        edges.get(42).addOrigin(29);
        edges.get(42).addOrigin(34);

        edges.get(43).addOrigin(29);
        edges.get(43).addOrigin(35);

        edges.get(44).addOrigin(30);
        edges.get(44).addOrigin(35);

        edges.get(45).addOrigin(30);
        edges.get(45).addOrigin(36);

        edges.get(46).addOrigin(31);
        edges.get(46).addOrigin(36);

        edges.get(47).addOrigin(31);
        edges.get(47).addOrigin(37);

        edges.get(48).addOrigin(32);
        edges.get(48).addOrigin(37);

        edges.get(49).addOrigin(33);
        edges.get(49).addOrigin(38);

        edges.get(50).addOrigin(34);
        edges.get(50).addOrigin(39);

        edges.get(51).addOrigin(35);
        edges.get(51).addOrigin(40);

        edges.get(52).addOrigin(36);
        edges.get(52).addOrigin(41);

        edges.get(53).addOrigin(37);
        edges.get(53).addOrigin(42);

        edges.get(54).addOrigin(38);
        edges.get(54).addOrigin(43);

        edges.get(55).addOrigin(39);
        edges.get(55).addOrigin(43);

        edges.get(56).addOrigin(39);
        edges.get(56).addOrigin(44);

        edges.get(57).addOrigin(40);
        edges.get(57).addOrigin(44);

        edges.get(58).addOrigin(40);
        edges.get(58).addOrigin(45);

        edges.get(59).addOrigin(41);
        edges.get(59).addOrigin(45);

        edges.get(60).addOrigin(41);
        edges.get(60).addOrigin(46);

        edges.get(61).addOrigin(42);
        edges.get(61).addOrigin(46);

        edges.get(62).addOrigin(43);
        edges.get(62).addOrigin(47);

        edges.get(63).addOrigin(44);
        edges.get(63).addOrigin(48);

        edges.get(64).addOrigin(45);
        edges.get(64).addOrigin(49);

        edges.get(65).addOrigin(46);
        edges.get(65).addOrigin(50);

        edges.get(66).addOrigin(47);
        edges.get(66).addOrigin(51);

        edges.get(67).addOrigin(48);
        edges.get(67).addOrigin(51);

        edges.get(68).addOrigin(48);
        edges.get(68).addOrigin(52);

        edges.get(69).addOrigin(49);
        edges.get(69).addOrigin(52);

        edges.get(70).addOrigin(49);
        edges.get(70).addOrigin(53);

        edges.get(71).addOrigin(50);
        edges.get(71).addOrigin(53);

    }

    public void buildRoad(int i, String color) {
        edges.get(i).build(color);
        edges.get(i).setBuildable(false);
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

    public void makeBuildable(int i) {
        edges.get(i).setBuildable(true);
    }

    public ArrayList<Integer> getOriginatingVertices(int i) {
        return edges.get(i).getOriginatingVertices();
    }

    public ArrayList<Integer> getConnections(int i) {
        return edges.get(i).getConnections();
    }

}

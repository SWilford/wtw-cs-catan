import java.io.Serializable;
import java.util.*;
//test
public class board implements Serializable {
    Tile[][] tiles = new Tile[7][7];//initializing an array of tiles that represent the board
    public board() {
        ArrayList<String> types = new ArrayList<>();//Array List that will contain the name for the type of tile
        for(int i = 0; i < 4; i++) { // fills array list with 4 forest type
            types.add("Forest");
        }
        for(int i = 0; i < 4; i++) {//fills array list with 4 fields type
            types.add("Fields");
        }
        for(int i = 0; i < 4; i++) { //fills array list with 4 pasture type
            types.add("Pasture");
        }
        for(int i = 0; i < 3; i++) { //fills array list with 3 hills type
            types.add("Hills");
        }
        for(int i = 0; i < 3; i++) { //fills array list with 3 mountains type
            types.add("Mountains");
        }
        types.add("Desert"); //adds desert type to array list
        ArrayList<Integer> numbers = new ArrayList<>();//Array list that stores the proper number for resource generating tiles
        numbers.add(2);//adding correct numbers
        numbers.add(3);
        numbers.add(3);
        numbers.add(4);
        numbers.add(4);
        numbers.add(5);
        numbers.add(5);
        numbers.add(6);
        numbers.add(6);
        numbers.add(8);
        numbers.add(8);
        numbers.add(9);
        numbers.add(9);
        numbers.add(10);
        numbers.add(10);
        numbers.add(11);
        numbers.add(11);
        numbers.add(12);

        //setting proper tiles as frame tiles
        tiles[0][0] = new Tile("Frame", -1);
        tiles[0][1] = new Tile("Frame", -1);
        tiles[0][6] = new Tile("Frame", -1);
        tiles[1][0] = new Tile("Frame", -1);
        tiles[1][6] = new Tile("Frame", -1);
        tiles[2][0] = new Tile("Frame", -1);
        tiles[4][0] = new Tile("Frame", -1);
        tiles[5][0] = new Tile("Frame", -1);
        tiles[5][6] = new Tile("Frame", -1);
        tiles[6][0] = new Tile("Frame", -1);
        tiles[6][1] = new Tile("Frame", -1);
        tiles[6][6] = new Tile("Frame", -1);

        //setting the correct tiles as ocean
        tiles[0][3] = new Tile("Ocean", -1);
        tiles[0][5] = new Tile("Ocean", -1);
        tiles[2][6] = new Tile("Ocean", -1);
        tiles[4][6] = new Tile("Ocean", -1);
        tiles[6][5] = new Tile("Ocean", -1);
        tiles[6][3] = new Tile("Ocean", -1);
        tiles[5][1] = new Tile("Ocean", -1);
        tiles[3][0] = new Tile("Ocean", -1);
        tiles[1][1] = new Tile("Ocean", -1);

        //setting the correct tiles as harbors
        tiles[0][2] = new Tile("Harbor", -1);
        tiles[0][4] = new Tile("Harbor", -1);
        tiles[1][5] = new Tile("Harbor", -1);
        tiles[3][6] = new Tile("Harbor", -1);
        tiles[5][5] = new Tile("Harbor", -1);
        tiles[6][4] = new Tile("Harbor", -1);
        tiles[6][2] = new Tile("Harbor", -1);
        tiles[4][1] = new Tile("Harbor", -1);
        tiles[2][1] = new Tile("Harbor", -1);

        Random rand = new Random();
        //setting the proper pieces for the actual board
        for(int i = 0; i < types.size(); i++) {
            int randI = rand.nextInt(types.size());
            String temp = types.get(randI);
            types.set(randI, types.get(i));
            types.set(i, temp);
        }
        Random rand2 = new Random();
        for(int j = 0; j < numbers.size(); j++) {
            int randI = rand2.nextInt(numbers.size());
            int temp = numbers.get(randI);
            numbers.set(randI, numbers.get(j));
            numbers.set(j, temp);
        }
        int numberCounter = 0;
        int typeCounter = 0;
        for(int c = 2; c < 5; c++) {
            if(types.get(typeCounter).equals("Desert")) {
                tiles[1][c] = new Tile("Desert", -1);
                typeCounter++;
            }
            else {
                tiles[1][c] = new Tile(types.get(typeCounter), numbers.get(numberCounter));
                typeCounter++;
                numberCounter++;
            }
        }

        for(int c = 2; c < 6; c++) {
            if(types.get(typeCounter).equals("Desert")) {
                tiles[2][c] = new Tile("Desert", -1);
                typeCounter++;
            }
            else {
                tiles[2][c] = new Tile(types.get(typeCounter), numbers.get(numberCounter));
                typeCounter++;
                numberCounter++;
            }
        }

        for(int c = 1; c < 6; c++) {
            if(types.get(typeCounter).equals("Desert")) {
                tiles[3][c] = new Tile("Desert", -1);
                typeCounter++;
            }
            else {
                tiles[3][c] = new Tile(types.get(typeCounter), numbers.get(numberCounter));
                typeCounter++;
                numberCounter++;
            }
        }

        for(int c = 2; c < 6; c++) {
            if(types.get(typeCounter).equals("Desert")) {
                tiles[4][c] = new Tile("Desert", -1);
                typeCounter++;
            }
            else {
                tiles[4][c] = new Tile(types.get(typeCounter), numbers.get(numberCounter));
                typeCounter++;
                numberCounter++;
            }
        }

        for(int c = 2; c < 5; c++) {
            if(types.get(typeCounter).equals("Desert")) {
                tiles[5][c] = new Tile("Desert", -1);
                typeCounter++;
            }
            else {
                tiles[5][c] = new Tile(types.get(typeCounter), numbers.get(numberCounter));
                typeCounter++;
                numberCounter++;
            }
        }

        tiles[0][4].setSpecialHarbor("specialFields"); //setting the correct harbors as special harbors with their respective resource
        tiles[1][5].setSpecialHarbor("specialMountains");
        tiles[5][5].setSpecialHarbor("specialPasture");
        tiles[4][1].setSpecialHarbor("specialHills");
        tiles[2][1].setSpecialHarbor("specialForest");

        // **Note** For testing purposes each vertex point will be set individually, in the future an algorithm will be created to condense this into a loop
        tiles[0][2].setSouth(4); //setting vertex values for each appropriate tile
        tiles[0][2].setSouthEast(1);
        tiles[0][3].setSouthWest(1);
        tiles[0][3].setSouth(5);
        tiles[0][3].setSouthEast(2);
        tiles[0][4].setSouthWest(2);
        tiles[0][4].setSouth(6);
        tiles[0][4].setSouthEast(3);
        tiles[0][5].setSouthWest(3);
        tiles[0][5].setSouth(7);

        tiles[1][1].setNorthEast(4);
        tiles[1][1].setSouthEast(8);
        tiles[1][1].setSouth(12);
        tiles[1][2].setNorth(1);
        tiles[1][2].setNorthEast(5);
        tiles[1][2].setSouthEast(9);
        tiles[1][2].setSouth(13);
        tiles[1][2].setSouthWest(8);
        tiles[1][2].setNorthWest(4);
        tiles[1][3].setNorth(2);
        tiles[1][3].setNorthEast(6);
        tiles[1][3].setSouthEast(10);
        tiles[1][3].setSouth(14);
        tiles[1][3].setSouthWest(9);
        tiles[1][3].setNorthWest(5);
        tiles[1][4].setNorth(3);
        tiles[1][4].setNorthEast(7);
        tiles[1][4].setSouthEast(11);
        tiles[1][4].setSouth(15);
        tiles[1][4].setSouthWest(10);
        tiles[1][4].setNorthWest(6);
        tiles[1][5].setSouth(16);
        tiles[1][5].setSouthWest(11);
        tiles[1][5].setNorthWest(7);

        tiles[2][1].setNorthEast(12);
        tiles[2][1].setSouthEast(17);
        tiles[2][1].setSouth(22);
        tiles[2][2].setNorth(8);
        tiles[2][2].setNorthEast(13);
        tiles[2][2].setSouthEast(18);
        tiles[2][2].setSouth(23);
        tiles[2][2].setSouthWest(17);
        tiles[2][2].setNorthWest(12);
        tiles[2][3].setNorth(9);
        tiles[2][3].setNorthEast(14);
        tiles[2][3].setSouthEast(19);
        tiles[2][3].setSouth(24);
        tiles[2][3].setSouthWest(18);
        tiles[2][3].setNorthWest(13);
        tiles[2][4].setNorth(10);
        tiles[2][4].setNorthEast(15);
        tiles[2][4].setSouthEast(20);
        tiles[2][4].setSouth(25);
        tiles[2][4].setSouthWest(19);
        tiles[2][4].setNorthWest(14);
        tiles[2][5].setNorth(11);
        tiles[2][5].setNorthEast(16);
        tiles[2][5].setSouthEast(21);
        tiles[2][5].setSouth(26);
        tiles[2][5].setSouthWest(20);
        tiles[2][5].setNorthWest(15);
        tiles[2][6].setSouth(27);
        tiles[2][6].setSouthWest(21);
        tiles[2][6].setNorthWest(16);

        tiles[3][0].setNorthEast(22);
        tiles[3][0].setSouthEast(28);
        tiles[3][1].setNorth(17);
        tiles[3][1].setNorthEast(23);
        tiles[3][1].setSouthEast(29);
        tiles[3][1].setSouth(34);
        tiles[3][1].setSouthWest(28);
        tiles[3][1].setNorthWest(22);
        tiles[3][2].setNorth(18);
        tiles[3][2].setNorthEast(24);
        tiles[3][2].setSouthEast(30);
        tiles[3][2].setSouth(35);
        tiles[3][2].setSouthWest(29);
        tiles[3][2].setNorthWest(23);
        tiles[3][3].setNorth(19);
        tiles[3][3].setNorthEast(25);
        tiles[3][3].setSouthEast(31);
        tiles[3][3].setSouth(36);
        tiles[3][3].setSouthWest(30);
        tiles[3][3].setNorthWest(24);
        tiles[3][4].setNorth(20);
        tiles[3][4].setNorthEast(26);
        tiles[3][4].setSouthEast(32);
        tiles[3][4].setSouth(37);
        tiles[3][4].setSouthWest(31);
        tiles[3][4].setNorthWest(25);
        tiles[3][5].setNorth(21);
        tiles[3][5].setNorthEast(27);
        tiles[3][5].setSouthEast(33);
        tiles[3][5].setSouth(38);
        tiles[3][5].setSouthWest(32);
        tiles[3][5].setNorthWest(26);
        tiles[3][6].setSouthWest(33);
        tiles[3][6].setNorthWest(27);

        tiles[4][1].setNorth(28);
        tiles[4][1].setNorthEast(34);
        tiles[4][1].setSouthEast(39);
        tiles[4][2].setNorth(29);
        tiles[4][2].setNorthEast(35);
        tiles[4][2].setSouthEast(40);
        tiles[4][2].setSouth(44);
        tiles[4][2].setSouthWest(39);
        tiles[4][2].setNorthWest(34);
        tiles[4][3].setNorth(30);
        tiles[4][3].setNorthEast(36);
        tiles[4][3].setSouthEast(41);
        tiles[4][3].setSouth(45);
        tiles[4][3].setSouthWest(40);
        tiles[4][3].setNorthWest(35);
        tiles[4][4].setNorth(31);
        tiles[4][4].setNorthEast(37);
        tiles[4][4].setSouthEast(42);
        tiles[4][4].setSouth(46);
        tiles[4][4].setSouthWest(41);
        tiles[4][4].setNorthWest(36);
        tiles[4][5].setNorth(32);
        tiles[4][5].setNorthEast(38);
        tiles[4][5].setSouthEast(43);
        tiles[4][5].setSouth(47);
        tiles[4][5].setSouthWest(42);
        tiles[4][5].setNorthWest(37);
        tiles[4][6].setNorth(33);
        tiles[4][6].setSouthWest(43);
        tiles[4][6].setNorthWest(38);

        tiles[5][1].setNorth(39);
        tiles[5][1].setNorthEast(44);
        tiles[5][1].setSouthEast(48);
        tiles[5][2].setNorth(40);
        tiles[5][2].setNorthEast(45);
        tiles[5][2].setSouthEast(49);
        tiles[5][2].setSouth(52);
        tiles[5][2].setSouthWest(48);
        tiles[5][2].setNorthWest(44);
        tiles[5][3].setNorth(41);
        tiles[5][3].setNorthEast(46);
        tiles[5][3].setSouthEast(50);
        tiles[5][3].setSouth(53);
        tiles[5][3].setSouthWest(49);
        tiles[5][3].setNorthWest(45);
        tiles[5][4].setNorth(42);
        tiles[5][4].setNorthEast(47);
        tiles[5][4].setSouthEast(51);
        tiles[5][4].setSouth(54);
        tiles[5][4].setSouthWest(50);
        tiles[5][4].setNorthWest(46);
        tiles[5][5].setNorth(43);
        tiles[5][5].setSouthWest(51);
        tiles[5][5].setNorthWest(47);

        tiles[6][2].setNorth(48);
        tiles[6][2].setNorthEast(52);
        tiles[6][3].setNorth(49);
        tiles[6][3].setNorthEast(53);
        tiles[6][3].setNorthWest(52);
        tiles[6][4].setNorth(50);
        tiles[6][4].setNorthEast(54);
        tiles[6][4].setNorthWest(53);
        tiles[6][5].setNorth(51);
        tiles[6][5].setNorthWest(54);

        tiles[1][2].setEdgeName(0, 2);
        tiles[1][2].setEdgeName(1, 8);
        tiles[1][2].setEdgeName(2, 13);
        tiles[1][2].setEdgeName(3, 12);
        tiles[1][2].setEdgeName(4, 7);
        tiles[1][2].setEdgeName(5, 1);

        tiles[1][3].setEdgeName(0, 4);
        tiles[1][3].setEdgeName(1, 9);
        tiles[1][3].setEdgeName(2, 15);
        tiles[1][3].setEdgeName(3, 14);
        tiles[1][3].setEdgeName(4, 8);
        tiles[1][3].setEdgeName(5, 3);

        tiles[1][4].setEdgeName(0, 6);
        tiles[1][4].setEdgeName(1, 10);
        tiles[1][4].setEdgeName(2, 17);
        tiles[1][4].setEdgeName(3, 16);
        tiles[1][4].setEdgeName(4, 9);
        tiles[1][4].setEdgeName(5, 5);

        tiles[2][2].setEdgeName(0, 12);
        tiles[2][2].setEdgeName(1, 20);
        tiles[2][2].setEdgeName(2, 26);
        tiles[2][2].setEdgeName(3, 25);
        tiles[2][2].setEdgeName(4, 19);
        tiles[2][2].setEdgeName(5, 11);

        tiles[2][4].setEdgeName(0, 16);
        tiles[2][4].setEdgeName(1, 22);
        tiles[2][4].setEdgeName(2, 30);
        tiles[2][4].setEdgeName(3, 29);
        tiles[2][4].setEdgeName(4, 21);
        tiles[2][4].setEdgeName(5, 15);

        tiles[2][5].setEdgeName(0, 18);
        tiles[2][5].setEdgeName(1, 23);
        tiles[2][5].setEdgeName(2, 32);
        tiles[2][5].setEdgeName(3, 31);
        tiles[2][5].setEdgeName(4, 22);
        tiles[2][5].setEdgeName(5, 17);

        tiles[3][1].setEdgeName(0, 25);
        tiles[3][1].setEdgeName(1, 35);
        tiles[3][1].setEdgeName(2, 41);
        tiles[3][1].setEdgeName(3, 40);
        tiles[3][1].setEdgeName(4, 34);
        tiles[3][1].setEdgeName(5, 24);

        tiles[3][2].setEdgeName(0, 27);
        tiles[3][2].setEdgeName(1, 36);
        tiles[3][2].setEdgeName(2, 43);
        tiles[3][2].setEdgeName(3, 42);
        tiles[3][2].setEdgeName(4, 35);
        tiles[3][2].setEdgeName(5, 26);

        tiles[3][3].setEdgeName(0, 29);
        tiles[3][3].setEdgeName(1, 37);
        tiles[3][3].setEdgeName(2, 45);
        tiles[3][3].setEdgeName(3, 44);
        tiles[3][3].setEdgeName(4, 36);
        tiles[3][3].setEdgeName(5, 28);

        tiles[3][4].setEdgeName(0, 31);
        tiles[3][4].setEdgeName(1, 38);
        tiles[3][4].setEdgeName(2, 47);
        tiles[3][4].setEdgeName(3, 46);
        tiles[3][4].setEdgeName(4, 37);
        tiles[3][4].setEdgeName(5, 30);

        tiles[3][5].setEdgeName(0, 33);
        tiles[3][5].setEdgeName(1, 39);
        tiles[3][5].setEdgeName(2, 49);
        tiles[3][5].setEdgeName(3, 48);
        tiles[3][5].setEdgeName(4, 38);
        tiles[3][5].setEdgeName(5, 32);

        tiles[4][2].setEdgeName(0, 42);
        tiles[4][2].setEdgeName(1, 51);
        tiles[4][2].setEdgeName(2, 56);
        tiles[4][2].setEdgeName(3, 55);
        tiles[4][2].setEdgeName(4, 50);
        tiles[4][2].setEdgeName(5, 41);

        tiles[4][3].setEdgeName(0, 44);
        tiles[4][3].setEdgeName(1, 52);
        tiles[4][3].setEdgeName(2, 58);
        tiles[4][3].setEdgeName(3, 43);
        tiles[4][3].setEdgeName(4, 51);
        tiles[4][3].setEdgeName(5, 43);

        tiles[4][4].setEdgeName(0, 46);
        tiles[4][4].setEdgeName(1, 53);
        tiles[4][4].setEdgeName(2, 60);
        tiles[4][4].setEdgeName(3, 59);
        tiles[4][4].setEdgeName(4, 52);
        tiles[4][4].setEdgeName(5, 45);

        tiles[4][5].setEdgeName(0, 48);
        tiles[4][5].setEdgeName(1, 54);
        tiles[4][5].setEdgeName(2, 62);
        tiles[4][5].setEdgeName(3, 61);
        tiles[4][5].setEdgeName(4, 53);
        tiles[4][5].setEdgeName(5, 47);

        tiles[5][2].setEdgeName(0, 57);
        tiles[5][2].setEdgeName(1, 64);
        tiles[5][2].setEdgeName(2, 68);
        tiles[5][2].setEdgeName(3, 67);
        tiles[5][2].setEdgeName(4, 63);
        tiles[5][2].setEdgeName(5, 56);

        tiles[5][3].setEdgeName(0, 59);
        tiles[5][3].setEdgeName(1, 65);
        tiles[5][3].setEdgeName(2, 70);
        tiles[5][3].setEdgeName(3, 69);
        tiles[5][3].setEdgeName(4, 64);
        tiles[5][3].setEdgeName(5, 58);

        tiles[5][4].setEdgeName(0, 61);
        tiles[5][4].setEdgeName(1, 66);
        tiles[5][4].setEdgeName(2, 72);
        tiles[5][4].setEdgeName(3, 71);
        tiles[5][4].setEdgeName(4, 65);
        tiles[5][4].setEdgeName(5, 60);


    }

    public int getNumber(int row, int col) {
        return tiles[row][col].getNumber();
    }

    public String getTileType(int row, int col) {
        return tiles[row][col].getType();
    }

    public void setTiles(Tile[][] ts) {
        tiles = ts;
    }

    public void createSettlement(int row, int col, int vertex) {
        tiles[row][col].buildSettlement(vertex);
        int vNum = 0;
        if(vertex == 0) {
            vNum = tiles[row][col].getNorth();
        }
        if(vertex == 1) {
            vNum = tiles[row][col].getNorthEast();
        }
        if(vertex == 2) {
            vNum = tiles[row][col].getSouthEast();
        }
        if(vertex == 3) {
            vNum = tiles[row][col].getSouth();
        }
        if(vertex == 4) {
            vNum = tiles[row][col].getSouthWest();
        }
        if(vertex == 5) {
            vNum = tiles[row][col].getNorthWest();
        }
        for(int r = 0; r < 7; r++) {
            for(int c = 0; c < 7; c++) {
                if(tiles[r][c].getNorth() == vNum) {
                    tiles[r][c].buildSettlement(0);
                }
                else if(tiles[r][c].getNorthEast() == vNum) {
                    tiles[r][c].buildSettlement(1);
                }
                else if(tiles[r][c].getSouthEast() == vNum) {
                    tiles[r][c].buildSettlement(2);
                }
                else if(tiles[r][c].getSouth() == vNum) {
                    tiles[r][c].buildSettlement(3);
                }
                else if(tiles[r][c].getSouthWest() == vNum) {
                    tiles[r][c].buildSettlement(4);
                }
                else if(tiles[r][c].getNorthWest() == vNum) {
                    tiles[r][c].buildSettlement(5);
                }
            }
        }
    }
    public boolean isSettlement(int row, int col, int vertex) {
        return tiles[row][col].getSettlement(vertex);
    }
    public void makeOwned(int row, int col, int vertex, String color) {
        tiles[row][col].makeOwned(color, vertex);
    }

    public String getOwned(int row, int col, int vertex) {
        return tiles[row][col].getOwnedBy(vertex);
    }
}

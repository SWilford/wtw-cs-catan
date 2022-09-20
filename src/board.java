import java.util.*;

public class board {
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
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if((col == 0 || ((col == 1) && ((row == 0) || (row == 6)))) || ((col == 6) && ((row == 0) || (row == 1) || (row == 5) || (row == 6)))) { //Makes non board pieces into frame
                    tiles[row][col] = new Tile("Frame", -1);
                }
                if(((row == 0) && ((col == 2) || (col == 4))) || ((row == 1) && (col == 5)) || ((row == 3) && (col == 6)) || ((row == 5) && (col == 5)) || ((row == 6) && ((col == 2) || (col == 4))) || ((row == 4) && (col == 1)) || ((row == 2) && (col == 1))) { //Sets correct ocean pieces as harbors
                    tiles[row][col] = new Tile("Harbor", -1);
                }
                if(((row == 0) && ((col == 3) || (col == 5))) || ((row == 2) && (col == 6)) || ((row == 4) && (col == 6)) || ((row == 6) && ((col == 2) || (col == 4))) || ((row == 5) && (col == 1)) || ((row == 3) && (col == 1)) || ((row == 1) && (col == 2))) { //Sets correct ocean pieces as ocean
                    tiles[row][col] = new Tile("Ocean",-1);
                }
                else {//**note** this creates random tiles, in the future an option to create set positions must be added
                    int randomType = (int)(Math.random()*(types.size()+1)); //gets random tile type from array list
                    tiles[row][col] = new Tile(types.get(randomType), -1); //-1 is a placeholder for tile number, it is replaced in the next step, assures desert does not get a number
                    types.remove(randomType);//Assures each type of tile will only get their specific respective quantity of type
                }
                if(tiles[row][col].getType().equals("Forest") || tiles[row][col].getType().equals("Pasture") || tiles[row][col].getType().equals("Fields") || tiles[row][col].getType().equals("Hills") || tiles[row][col].getType().equals("Mountains")) { //sets the correct tile number, **note** currently the numbers for tiles are set to random, in the future something will have to be added to make them set based off of the spiral and alphabetical order
                    int randomNumber = (int)(Math.random()*(numbers.size()+1));
                    tiles[row][col].setNumber(numbers.get(randomNumber)); //gets random number from number array list
                    numbers.remove(randomNumber); //assures only the correct amount of each number is assigned
                }
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
    }

    public String getTileType(int row, int col) {
        return tiles[row][col].getType();
    }
}

import java.util.*;

public class board {
    public board() {
        Tile[][] tiles = new Tile[7][7];//initializing an array of tiles that represent the board
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
        ArrayList<String> specialHarbors = new ArrayList<>();//will contain special harbor for each resource type
        specialHarbors.add("specialForest"); //adding special harbor for each type
        specialHarbors.add("specialHills");
        specialHarbors.add("specialPasture");
        specialHarbors.add("specialFields");
        specialHarbors.add("specialMountains");
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
                else {
                    int randomType = (int)(Math.random()*(types.size()+1)); //gets random tile type from array list
                    tiles[row][col] = new Tile(types.get(randomType), -1); //-1 is a placeholder for tile number, it is replaced in the next step, assures desert does not get a number
                    types.remove(randomType);//Assures each type of tile will only get their specific respective quantity of type
                }
                if(tiles[row][col].getType().equals("Forest") || tiles[row][col].getType().equals("Pasture") || tiles[row][col].getType().equals("Fields") || tiles[row][col].getType().equals("Hills") || tiles[row][col].getType().equals("Mountains")) { //sets the correct tile number, **note** currently the numbers for tiles are set to random, in the future something will have to be added to make them set
                    int randomNumber = (int)(Math.random()*(numbers.size()+1));
                    tiles[row][col].setNumber(numbers.get(randomNumber)); //gets random number from number array list
                    numbers.remove(randomNumber); //assures only the correct amount of each number is assigned
                }
            }
        }
    }
}

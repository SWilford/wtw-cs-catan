import java.io.Serializable;
import java.util.*;
//test
public class board implements Serializable {
    Tile[][] tiles = new Tile[7][7];//initializing an array of tiles that represent the board
    VertexWeb web;
    public board() {
        web = new VertexWeb();
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

    public void buildSettlement(int i, String color) {
        web.buildSettlement(i, color);
    }

    public void upgradeSettlement(int i) {
        web.upgradeSettlement(i);
    }

    public boolean isBuildable(int i) {
        return web.isBuildable(i);
    }

    public String getOwner(int i) {
        return web.getOwner(i);
    }

    public boolean isSettled(int i) {
        return web.isSettled(i);
    }

}

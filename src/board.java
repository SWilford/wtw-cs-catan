import java.io.Serializable;
import java.util.*;
//test
public class board implements Serializable {
    Tile[][] tiles = new Tile[7][7];//initializing an array of tiles that represent the board
    VertexWeb web;
    EdgeWeb web2;
    public board() {
        web = new VertexWeb();
        web2 = new EdgeWeb();
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

    public void buildRoad(int i, String color) {
        web2.buildRoad(i, color);
    }

    public void upgradeSettlement(int i, String owner) {
        web.upgradeSettlement(i, owner);
    }

    public boolean isBuildable(int i) {
        return web.isBuildable(i);
    }

    public void makeRoadsBuildable(int i) {
        ArrayList<Integer> temp = web2.getOriginatingVertices(i);
        int temp1 = temp.get(0);
        int temp2 = temp.get(1);
        if(web.isSettled(temp1) || web.isSettled(temp2)) {
            web2.makeBuildable(i);
        }
        ArrayList<Integer> temp3 = web2.getConnections(i);
        if(temp3.size() == 2) {
            int temp4 = temp3.get(0);
            int temp5 = temp3.get(1);
            if(web2.isBuilt(temp4) || web2.isBuilt(temp5)) {
                web2.makeBuildable(i);
            }
        }
        else if (temp3.size()==3) {
            int temp4 = temp3.get(0);
            int temp5 = temp3.get(1);
            int temp6 = temp3.get(2);
            if(web2.isBuilt(temp4) || web2.isBuilt(temp5) || web2.isBuilt(temp6)) {
                web2.makeBuildable(i);
            }
        }
        else {
            int temp4 = temp3.get(0);
            int temp5 = temp3.get(1);
            int temp6 = temp3.get(2);
            int temp7 = temp3.get(3);
            if(web2.isBuilt(temp4) || web2.isBuilt(temp5) || web2.isBuilt(temp6) || web2.isBuilt(temp7)) {
                web2.makeBuildable(i);
            }
        }
    }


    public int longestRoad(String color) {
        /*checkingTest = true;
        int longestRoad = 0;
        for(int i = 0; i < 71; i++) {
            web2.setChecked(i, false);
        }
        for(int i = 0; i < 71; i++) {
            int l = calculateLongestRoad(color, i);
            if(l > longestRoad) {
                longestRoad = l;
            }
        }
        int l = calculateLongestRoad(color, 1);
        if(l > longestRoad) {
            longestRoad = l;
        }*/
        int longestRoad;
        longestRoad = web2.cLR(web2.getEdges(), color);
        return longestRoad;
    }

    int startingBranchNumber = -1;
boolean checkingABranch = false;
    public int calculateLongestRoad(String color, int i) {
        int longestRoad = 0;
            int j = 0;
            int traversalNumber = 0;
            if((isRoadEnd(i) && web2.getOwner(i).equals(color)) || (startingBranchNumber == i && !web2.isChecked(i))) {
                web2.setChecked(i, true);
                web2.setPreviousNumber(i);
                j++;
                //int traversalNumber;
                if(startingBranchNumber != i) {
                    traversalNumber = web2.traversalHelper();
                }
                else {
                    traversalNumber = i;
                }
                //j+=longestHelper(color, traversalNumber);
            }
            j+=longestHelper(color, traversalNumber);
            if(j > longestRoad) {
                longestRoad = j;
            }
        return longestRoad;
    }
    private int longestHelper(String color, int i) {
        int j = 0;
        int traversalNumber = i;
        while(traversalNumber >= 0 && isSingleConnection(traversalNumber) && !web2.isChecked(traversalNumber)) {
            j++;
            web2.setChecked(traversalNumber, true);
            web2.setPreviousNumber(traversalNumber);
            web2.setBranchedPreviousNumber(traversalNumber);
            traversalNumber = web2.traversalHelper();
        }
        if((isRoadEnd(traversalNumber) && !web2.isChecked(traversalNumber)) || web2.isBranchEnd(traversalNumber)) {
            web2.setChecked(traversalNumber, true);
            j++;
        }
        else {
            if(!checkingBranchHelper) {
                web2.setCheckingABranch(false);
            }
            if(web2.isCheckingABranch()) {
                traversalNumber = web2.traversalHelper();
                checkingTest = false;
            }
            ArrayList<Integer> branches = web2.getBranches(traversalNumber);
            int n = 0;
            if(branches.size() > 0 && checkingTest) {
                for (int b : branches) {
                    if (!web2.isChecked(b)) {
                        startingBranchNumber = b;
                        checkingABranch = true;
                        int o = calculateLongestRoad(color, b);
                        if (o > n) {
                            n = o;
                        }
                    }
                }
            }
            else {
                checkingBranchHelper = false;
                int o = longestHelper(color, traversalNumber);
                if(o > n) {
                    n = o;
                }
            }
            j += n;
        }
        return j;
    }

    boolean checkingBranchHelper = true;
    boolean checkingTest = true;

    //a test





    //end of a test

    public boolean isRoadEnd(int i) {
        return web2.isRoadEnd(i);
    }

    public boolean isSingleConnection(int i) {
        return web2.isSingleConnection(i);
    }



    public ArrayList<Integer> getOriginatingVertices(int i) {
        return web2.getOriginatingVertices(i);
    }

    public ArrayList<Integer> getConnections(int i) {
        return web2.getConnections(i);
    }

    public boolean isRoadBuildable(int i) {
        makeRoadsBuildable(i);
        return web2.isBuildable(i);
    }

    public String getOwner(int i) {
        return web.getOwner(i);
    }

    public String getRoadOwner(int i) {
        return web2.getOwner(i);
    }

    public boolean isSettled(int i) {
        return web.isSettled(i);
    }

    public boolean isRoadBuilt(int i) {
        return web2.isBuilt(i);
    }

    public boolean isCity(int i) {
        return web.isCity(i);
    }

    public int getTypeInt(int r, int c)
    {
        if(getTileType(r, c).equals("Forest"))
        {
            return 1;
        }
        else if(getTileType(r, c).equals("Fields"))
        {
            return 5;
        }
        else if(getTileType(r, c).equals("Mountains"))
        {
            return 4;
        }
        else if(getTileType(r, c).equals("Pasture"))
        {
            return 3;
        }
        else return 2;
    }

}

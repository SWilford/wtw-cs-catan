public class board {
    public board() {
        Tile[][] tiles = new Tile[7][7];//initializing an array of tiles that represent the board
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
            }
        }

    }

}

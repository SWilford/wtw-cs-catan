public class board {
    public board() {
        Tile[][] tiles = new Tile[5][6];//initializing an array of tiles that represent the board
        for (Tile[] tile : tiles) {
            for (int col = 0; col < tile.length; col++) {
                tile[col] = new Tile("String", 0);
            }
        }
    }

}

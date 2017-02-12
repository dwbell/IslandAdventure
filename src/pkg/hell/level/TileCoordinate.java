package pkg.hell.level;

public class TileCoordinate {

    private int x, y;
    private final int TILE_SIZE = 16;

    public TileCoordinate(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    //X Getter
    public int x() {
        return x;
    }
    
    //Y Getter
    public int y() {
        return y;
    }

    /****************************************************
     * Name:        xy
     * Parameters:  None
     * Return:      int[]
     * Description: Returns an array with x,y coordinates
     ****************************************************/
    public int[] xy() {
        int[] arr = new int[2];
        arr[0] = x;
        arr[1] = y;
        return arr;
    }

}

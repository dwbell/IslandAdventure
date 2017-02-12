package pkg.hell.graphics;

public class Sprite {

    protected final int SIZE;
    protected int x, y;
    public int[] pixels;
    protected SpriteSheet sheet;

    //Tile Sprites
    public static Sprite grass = new Sprite(16, 2, 4, SpriteSheet.terrain);

    //Small Palm Tree
    public static Sprite palm_tree_bot = new Sprite(16, 0, 5, SpriteSheet.terrain);
    public static Sprite palm_tree_top = new Sprite(16, 0, 4, SpriteSheet.terrain);
    public static Sprite palm_tree_top_2 = new Sprite(16, 1, 5, SpriteSheet.terrain);
    public static Sprite palm_tree_top_coconut = new Sprite(16, 1, 4, SpriteSheet.terrain);
    public static Sprite palm_tree_top_2_coconut = new Sprite(16, 2, 5, SpriteSheet.terrain);

    //Small Berry Bush
    public static Sprite bush = new Sprite(16, 0, 6, SpriteSheet.terrain);
    public static Sprite bush_berries = new Sprite(16, 1, 6, SpriteSheet.terrain);

    //Water Sprites
    public static Sprite water = new Sprite(16, 0, 0, SpriteSheet.terrain);
    public static Sprite waterNorth = new Sprite(16, 1, 0, SpriteSheet.terrain);
    public static Sprite waterSouth = new Sprite(16, 0, 1, SpriteSheet.terrain);
    public static Sprite waterEast = new Sprite(16, 1, 1, SpriteSheet.terrain);
    public static Sprite waterWest = new Sprite(16, 0, 2, SpriteSheet.terrain);

    public static Sprite waterNorthEast = new Sprite(16, 2, 1, SpriteSheet.terrain);
    public static Sprite waterNorthWest = new Sprite(16, 2, 2, SpriteSheet.terrain);
    public static Sprite waterSouthEast = new Sprite(16, 1, 2, SpriteSheet.terrain);
    public static Sprite waterSouthWest = new Sprite(16, 2, 0, SpriteSheet.terrain);

    public static Sprite waterCornerBL = new Sprite(16, 2, 3, SpriteSheet.terrain);
    public static Sprite waterCornerBR = new Sprite(16, 0, 3, SpriteSheet.terrain);
    public static Sprite waterCornerTL = new Sprite(16, 1, 3, SpriteSheet.terrain);
    public static Sprite waterCornerTR = new Sprite(16, 3, 0, SpriteSheet.terrain);
    
    public static Sprite wave_1 = new Sprite(16, 7, 0, SpriteSheet.terrain);
    public static Sprite wave_2 = new Sprite(16, 7, 1, SpriteSheet.terrain);
    public static Sprite wave_3 = new Sprite(16, 7, 2, SpriteSheet.terrain);
    
    

    //Sand Sprites
    public static Sprite sand = new Sprite(16, 3, 1, SpriteSheet.terrain);
    public static Sprite sandNorth = new Sprite(16, 3, 4, SpriteSheet.terrain);
    public static Sprite sandSouth = new Sprite(16, 3, 2, SpriteSheet.terrain);
    public static Sprite sandEast = new Sprite(16, 4, 0, SpriteSheet.terrain);
    public static Sprite sandWest = new Sprite(16, 3, 3, SpriteSheet.terrain);

    public static Sprite sandNorthEast = new Sprite(16, 4, 4, SpriteSheet.terrain);
    public static Sprite sandNorthWest = new Sprite(16, 4, 3, SpriteSheet.terrain);
    public static Sprite sandSouthEast = new Sprite(16, 4, 1, SpriteSheet.terrain);
    public static Sprite sandSouthWest = new Sprite(16, 4, 2, SpriteSheet.terrain);

    public static Sprite sandCornerBL = new Sprite(16, 5, 1, SpriteSheet.terrain);
    public static Sprite sandCornerBR = new Sprite(16, 5, 0, SpriteSheet.terrain);
    public static Sprite sandCornerTL = new Sprite(16, 5, 2, SpriteSheet.terrain);
    public static Sprite sandCornerTR = new Sprite(16, 5, 3, SpriteSheet.terrain);

    //Sand & Water Sprites (Smoothing out terrain)
    public static Sprite sawaNorthEast = new Sprite(16, 6, 0, SpriteSheet.terrain);
    public static Sprite sawaNorthWest = new Sprite(16, 6, 1, SpriteSheet.terrain);
    public static Sprite sawaSouthEast = new Sprite(16, 6, 2, SpriteSheet.terrain);
    public static Sprite sawaSouthWest = new Sprite(16, 6, 3, SpriteSheet.terrain);

    public static Sprite voidSprite = new Sprite(16, 0x0026FF);

    //Player sprites for downward animation (South)
    public static Sprite player_down = new Sprite(16, 0, 0, SpriteSheet.mob);
    public static Sprite player_down_1 = new Sprite(16, 0, 1, SpriteSheet.mob);
    public static Sprite player_down_2 = new Sprite(16, 0, 2, SpriteSheet.mob);

    //Player sprites for right animation (East)
    public static Sprite player_right = new Sprite(16, 1, 0, SpriteSheet.mob);
    public static Sprite player_right_1 = new Sprite(16, 1, 1, SpriteSheet.mob);
    public static Sprite player_right_2 = new Sprite(16, 1, 2, SpriteSheet.mob);

    //Player sprites for left animation (West)
    public static Sprite player_left = new Sprite(16, 2, 0, SpriteSheet.mob);
    public static Sprite player_left_1 = new Sprite(16, 2, 1, SpriteSheet.mob);
    public static Sprite player_left_2 = new Sprite(16, 2, 2, SpriteSheet.mob);

    //Player sprites for up animation (North)
    public static Sprite player_up = new Sprite(16, 3, 0, SpriteSheet.mob);
    public static Sprite player_up_1 = new Sprite(16, 3, 1, SpriteSheet.mob);
    public static Sprite player_up_2 = new Sprite(16, 3, 2, SpriteSheet.mob);

    //Void tile constructor
    public Sprite(int sizeParam, int colorParam) {
        SIZE = sizeParam;
        pixels = new int[SIZE * SIZE];
        setColor(colorParam);
    }

    //Normal tile constructor
    public Sprite(int sizeParam, int xParam, int yParam, SpriteSheet sheetParam) {
        SIZE = sizeParam;
        pixels = new int[SIZE * SIZE];
        this.x = xParam * SIZE;
        this.y = yParam * SIZE;
        this.sheet = sheetParam;
        load();
    }

    /****************************************************
     * Name:        setColor
     * Parameters:  int color
     * Return:      void
     * Description: Fills a sprite with a specified color
     ****************************************************/
    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
    }

    /****************************************************
     * Name:        load
     * Parameters:  n/a
     * Return:      void
     * Description: Loads a SINGLE sprite given an x and
     * y coordinate. x and y are represented by the entire
     * sprite and are NOT individual pixel coordinates.
     ****************************************************/
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getSpriteSheetSize()];
            }
        }
    }

    /****************************************************
     * Name:        getIndividualSpriteSize
     * Parameters:  n/a
     * Return:      int
     * Description: Getter method for getting a single
     * sprite size
     ****************************************************/
    public int getSpriteSize() {
        return SIZE;
    }
}

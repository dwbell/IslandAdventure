package pkg.hell.level;


import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    /****************************************************
     * Name:       generateLevel
     * Parameters:  n/a
     * Return:      void
     * Description: Overridden method from Level, 
     * randomly assigns an integer value to the tiles
     * array.
     ****************************************************/
    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tilesInt[x + y * width] = random.nextInt(4);//changed
            }
        }

    }
}

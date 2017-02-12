package pkg.hell.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

    private final int PASSES = 3;

    public SpawnLevel(String path) {
        super(path);
    }

    @Override
    protected void loadLevel(String path) {
        try {
            //Loading image from designated path
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];

            //Convert image into array of pixels by color
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            System.out.println("Exception! Could not level file.");
            e.printStackTrace();
        }
    }

    /****************************************************
     * Name:        generatelevel 
     * Parameters:  None
     * Return:      void
     * Description: This is sets up the level. It takes 
     * advantage of the tiles array which contains the
     * "Level" image. It can manipulate those tiles by
     * changing the color code which is then interpreted
     * by class Level's getTile method. The PASSES value
     * is in regards to PASSES in smoothing the terrain by
     * applying edge tiles. So far the value is 3 because
     * water, sand, and sandWater pass all apply an effect.
     * (This method is Overriden from Level).
     ****************************************************/
    @Override
    protected void generateLevel() {

        //Loop through level image applying edge smoothing 
        for (int pass = 0; pass < PASSES; pass++) {
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < height; x++) {

                    //Bounds checking
                    if (x > 1 && y > 1 && x < width - 1 && y < height - 1) {

                        //Edge of water and sand pass
                        if (pass == 0) {
                            if (tiles[x + y * width] == water) {
                                int tile = watersEdge(x, y, sand);
                                tiles[x + y * width] = tile;
                            }
                        }

                        //Edge of sand and grass 
                        if (pass == 1) {
                            if (tiles[x + y * width] == sand) {
                                if (x > 1 && y > 1 && x < width - 1 && y < height - 1) {
                                    int tile = sandsEdge(x, y, grass);
                                    tiles[x + y * width] = tile;
                                }
                            }
                        }
                    }

                    //Smoothing sand and water
                    if (pass == 2) {
                        switch (tiles[x + y * width]) {

                            //Checking for waterCorner tile
                            case waterCornerBR: //BR
                            case waterCornerBL: //BL
                            case waterCornerTR: //TR
                            case waterCornerTL: //TL
                                sandWaterEdge(x, y, tiles[x + y * width]);
                                break;

                        }
                    }
                }
            }
        }

        //Last loop through level image to apply sprites
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {

                //Small palm tree
                if (tiles[x + y * width] == palmTreeBot) {
                    palmTreeSmall(x, y);
                }

                //Bush Berry
                if (tiles[x + y * width] == bush) {
                    bushBerry(x, y);
                }
            }
        }
    }

    /****************************************************
     * Name:        palmTreeSmall
     * Parameters:  int x, int y
     * Return:      void
     * Description: Given a single 0xff7F3300 (brown) pixel
     * from level image it will insert a 16x32 size 
     * palm tree, which will have a 1/10th chance of 
     * having coconuts or not. Also gives it a chance to 
     * be a palm tree of a darker color. 
     ****************************************************/
    public void palmTreeSmall(int x, int y) {
        int num = rand.nextInt(9);
        int half = rand.nextInt(2);

        //1/10th total chance of coconuts
        if (num == 0) {
            // 1/2 chance of light or dark coconut tree
            if (half == 1) {
                tiles[x + (y - 1) * width] = palmTreeTopCoconut;
            } else {
                tiles[x + (y - 1) * width] = palmTreeTop2Coconut;
            }
            // 4/9 chance of being a "dark" palm
        } else if (num > 0 && num <= 4) {
            tiles[x + (y - 1) * width] = palmTreeTop2;
            // 5/9 chance of being a "light" palm
        } else {
            tiles[x + (y - 1) * width] = palmTreeTop;
        }
    }

    /****************************************************
     * Name:        bushBerry
     * Parameters:  int x, int y
     * Return:      void
     * Description: Given a single 0xff5B7F00 (dark green)
     * pixel from level image it will insert a 16x16 size 
     * bush, which will have a 1/15th chance of 
     * having berries or not.
     ****************************************************/
    public void bushBerry(int x, int y) {
        int num = rand.nextInt(15);
        if (num == 0) {
            tiles[x + y * width] = bush_berry;
        } else {
            tiles[x + y * width] = bush;
        }
    }

    /****************************************************
     * Name:        watersEdge
     * Parameters:  int x, int y, int tileType
     * Return:      int 
     * Description: This method checks every direction 
     * with respect to the water and sand. Sand marks the
     * edge of an island. Given specific coordinates to 
     * check it will return the correct Tile for the
     * correct location. (This method called from aboves
     * generateLevel method).
     ****************************************************/
    private int watersEdge(int x, int y, int tileType) {

        //NorthEast
        if (tiles[x + (y - 1) * width] == tileType && tiles[(x + 1) + y * width] == tileType) {
            return waterNorthEast;
        }
        //NorthWest
        if (tiles[x + (y - 1) * width] == tileType && tiles[(x - 1) + y * width] == tileType) {
            return waterNorthWest;
        }
        //SouthEast
        if (tiles[x + (y + 1) * width] == tileType && tiles[(x + 1) + y * width] == tileType) {
            return waterSouthEast;
        }
        //SouthWest
        if (tiles[x + (y + 1) * width] == tileType && tiles[(x - 1) + y * width] == tileType) {
            return waterSouthWest;
        }

        //North
        if (tiles[x + (y - 1) * width] == tileType) {
            return waterNorth;
        }
        //South
        if (tiles[x + (y + 1) * width] == tileType) {
            return waterSouth;
        }
        //East
        if (tiles[(x + 1) + y * width] == tileType) {
            return waterEast;
        }
        //West
        if (tiles[(x - 1) + y * width] == tileType) {
            return waterWest;
        }

        //Corner Bottom Right
        if (tiles[(x + 1) + (y + 1) * width] == tileType) {
            return waterCornerBR;
        }
        //Corner Bottom Left
        if (tiles[(x - 1) + (y + 1) * width] == tileType) {
            return waterCornerBL;
        }
        //Corner Top Right
        if (tiles[(x + 1) + (y - 1) * width] == tileType) {
            return waterCornerTR;
        }
        //Corner Top Left
        if (tiles[(x - 1) + (y - 1) * width] == tileType) {
            return waterCornerTL;
        }

        return water;
    }

    /****************************************************
     * Name:        sandsEdge
     * Parameters:  int x, int y, int tileType
     * Return:      int 
     * Description: This method checks every direction 
     * with respect to the sand and grass. It applies
     * the edge effect between sand and grass to give it 
     * a blurred effect. (Same as watersEdge method above)
     ****************************************************/
    public int sandsEdge(int x, int y, int tileType) {

        //NorthEast
        if (tiles[x + (y - 1) * width] == tileType && tiles[(x + 1) + y * width] == tileType) {
            return sandNorthEast;
        }
        //NorthWest
        if (tiles[x + (y - 1) * width] == tileType && tiles[(x - 1) + y * width] == tileType) {
            return sandNorthWest;
        }
        //SouthEast
        if (tiles[x + (y + 1) * width] == tileType && tiles[(x + 1) + y * width] == tileType) {
            return sandSouthEast;
        }
        //SouthWest
        if (tiles[x + (y + 1) * width] == tileType && tiles[(x - 1) + y * width] == tileType) {
            return sandSouthWest;
        }

        //North
        if (tiles[x + (y - 1) * width] == tileType) {
            return sandNorth;
        }
        //South
        if (tiles[x + (y + 1) * width] == tileType) {
            return sandSouth;
        }
        //East
        if (tiles[(x + 1) + y * width] == tileType) {
            return sandEast;
        }
        //West
        if (tiles[(x - 1) + y * width] == tileType) {
            return sandWest;
        }

        //Corner Bottom Right
        if (tiles[(x + 1) + (y + 1) * width] == tileType) {
            return sandCornerBR;
        }
        //Corner Bottom Left
        if (tiles[(x - 1) + (y + 1) * width] == tileType) {
            return sandCornerBL;
        }
        //Corner Top Right
        if (tiles[(x + 1) + (y - 1) * width] == tileType) {
            return sandCornerTR;
        }
        //Corner Top Left
        if (tiles[(x - 1) + (y - 1) * width] == tileType) {
            return sandCornerTL;
        }

        return sand;
    }

    /****************************************************
     * Name:        sandWaterEdge
     * Parameters:  int x, int y, int tile
     * Return:      void
     * Description: This method checks for a water tile
     * that is a corner water tile. Once it finds one of 
     * four possible tiles, it then adjusts correctly to 
     * replace the correct tile with a given water and 
     * sand tile to create the effect of smoothness.
     ****************************************************/
    public void sandWaterEdge(int x, int y, int tile) {

        //BR corner
        if (tile == waterCornerBR) {
            tiles[(x + 1) + (y + 1) * width] = sawaNorthEast;
        }
        //BL corner
        if (tile == waterCornerBL) {
            tiles[(x - 1) + (y + 1) * width] = sawaNorthWest;
        }
        //TR corner
        if (tile == waterCornerTR) {
            tiles[(x + 1) + (y - 1) * width] = sawaSouthWest;
        }
        //TL corner
        if (tile == waterCornerTL) {
            tiles[(x - 1) + (y - 1) * width] = sawaSouthEast;
        }
    }
}

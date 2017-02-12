package pkg.hell.level;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;
import pkg.hell.graphics.Screen;
import pkg.hell.level.animTile.WaveNode;
import pkg.hell.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;//all the levels tiles
    protected Random rand;
    protected List<WaveNode> waveList;
    protected WaveNode waveNode;
    private int clock;

    //Holding the hexadecimal color value for a given tile
    protected final static int grass = 0xff00ff21,
            //Small Coconut Tree
            palmTreeBot = 0xff7F3300,
            palmTreeTop = 0xff7F3301,
            palmTreeTop2 = 0xff7F3303,
            palmTreeTopCoconut = 0xff7F3302,
            palmTreeTop2Coconut = 0xff7F3304,
            //Bush
            bush = 0xff5B7F00,
            bush_berry = 0xff5B7F01,
            //Water
            water = 0xff0026FF,
            wave_1 = 0xff2D94B0,
            wave_2 = 0xff2D94B1,
            wave_3 = 0xff2D94B2,
            waterNorth = 0xff2D94C2,
            waterSouth = 0xff2D94C3,
            waterEast = 0xff2D94C4,
            waterWest = 0xff2D94C5,
            waterNorthEast = 0xff2D94C6,
            waterNorthWest = 0xff2D94C7,
            waterSouthEast = 0xff2D94C8,
            waterSouthWest = 0xff2D94C9,
            waterCornerBR = 0xff2D94CA,
            waterCornerBL = 0xff2D94CB,
            waterCornerTR = 0xff2D94CC,
            waterCornerTL = 0xff2D94CD,
            //Sand
            sand = 0xffffD800,
            sandNorth = 0xffffD801,
            sandSouth = 0xffffD802,
            sandEast = 0xffffD803,
            sandWest = 0xffffD804,
            sandNorthEast = 0xffffD805,
            sandNorthWest = 0xffffD806,
            sandSouthEast = 0xffffD807,
            sandSouthWest = 0xffffD808,
            sandCornerBR = 0xffffD809,
            sandCornerBL = 0xffffD80A,
            sandCornerTR = 0xffffD80B,
            sandCornerTL = 0xffffD80C,
            //Sand and Water
            sawaNorthEast = 0xff3CA4C1,
            sawaNorthWest = 0xff3CA4C2,
            sawaSouthEast = 0xff3CA4C3,
            sawaSouthWest = 0xff3CA4C4;

    //Random level constructor
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    //Level from file constructor
    public Level(String path) {
        clock = 0;
        waveList = new ArrayList();
        rand = new Random(System.currentTimeMillis());
        loadLevel(path);
        generateLevel();
    }

    /****************************************************
     * Name:        generateLevel
     * Parameters:  n/a
     * Return:      void
     * Description: Overridable method
     ****************************************************/
    protected void generateLevel() {

    }

    /****************************************************
     * Name:        loadLevel
     * Parameters:  n/a
     * Return:      void
     * Description: Overridable method that is the basis
     * for loading a level from file
     ****************************************************/
    protected void loadLevel(String path) {

    }

    /****************************************************
     * Name:        update
     * Parameters:  n/a
     * Return:      void
     * Description: 
     ****************************************************/
    public void update(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);

        //Second timer
        if (clock < 60) {
            clock++;
        } else {
            clock = 0;
        }

        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                if (y > 0 && y < width && x > 0 && x < height) {

                    //Called ~every 1/4th a second
                    if (clock % 15 == 0) {
                        if (tiles[x + y * width] == water) {
                            waveAnim(x, y);
                        }
                    }
                }
            }
        }

        // Called ~every 1/10th a second
        if (clock % 10 == 0) {
            for (int i = 0; i < waveList.size(); i++) {
                if (!waveList.get(i).isAlive()) {
                    tiles[waveList.get(i).getX() + waveList.get(i).getY() * width] = water;
                    waveList.remove(i);
                } else {
                    tiles[waveList.get(i).getX() + waveList.get(i).getY() * width] = waveList.get(i).animSequence();
                }
            }
        }
    }

    public void waveAnim(int x, int y) {
        //For each pass, gives each tile a 1/60th chance to be a wave
        int waveChance = rand.nextInt(60);
        if (waveChance == 0) {
            tiles[x + y * width] = newWave(x, y);
        }
    }

    /****************************************************
     * Name:        newWave
     * Parameters:  int x , int y
     * Return:      int
     * Description: Takes x,y coordinate of a specific tile,
     * creates a new WaveNode class and adds it to a list.
     * It returns each new waveNode's starting animation.
     ****************************************************/
    public int newWave(int x, int y) {
        int life = rand.nextInt(550 - 450) + 450;
        waveNode = new WaveNode(x, y, life);
        waveList.add(waveNode);
        return waveNode.startSprite();

    }

    /****************************************************
     * Name:        render
     * Parameters:  int xScroll , int yScroll
     * Return:      void
     * Description: Levels render method which is called
     * from Game class. Renders the level using "corner pins"
     * x0 = left, x1 = right, y0 = top, y1 = bottom to 
     * only render what is visible on screen.
     ****************************************************/
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        //xScroll player position
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        //Grabbing tiles that are within screen bounds
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    /****************************************************
     * Name:        getTile
     * Parameters:  int x, int y
     * Return:      Tile
     * Description: Called from Levels render, this
     * method returns the tile from its x,y position
     ****************************************************/
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.water;
        }

        //Grass
        if (tiles[x + y * width] == grass) {
            return Tile.grass;
        }

        //Small palm tree
        if (tiles[x + y * width] == palmTreeBot) {
            return Tile.palmTreeBot;
        }
        if (tiles[x + y * width] == palmTreeTop) {
            return Tile.palmTreeTop;
        }
        if (tiles[x + y * width] == palmTreeTop2) {
            return Tile.palmTreeTop2;
        }
        if (tiles[x + y * width] == palmTreeTopCoconut) {
            return Tile.palmTreeTopCoconut;
        }
        if (tiles[x + y * width] == palmTreeTop2Coconut) {
            return Tile.palmTreeTop2Coconut;
        }

        //Bush
        if (tiles[x + y * width] == bush) {
            return Tile.bush;
        }
        if (tiles[x + y * width] == bush_berry) {
            return Tile.bush_berry;
        }

        //Water
        if (tiles[x + y * width] == water) {
            return Tile.water;
        }
        if (tiles[x + y * width] == wave_1) {
            return Tile.wave_1;
        }
        if (tiles[x + y * width] == wave_2) {
            return Tile.wave_2;
        }
        if (tiles[x + y * width] == wave_3) {
            return Tile.wave_3;
        }
        if (tiles[x + y * width] == waterNorth) {
            return Tile.waterNorth;
        }
        if (tiles[x + y * width] == waterSouth) {
            return Tile.waterSouth;
        }
        if (tiles[x + y * width] == waterEast) {
            return Tile.waterEast;
        }
        if (tiles[x + y * width] == waterWest) {
            return Tile.waterWest;
        }
        if (tiles[x + y * width] == waterNorthEast) {
            return Tile.waterNorthEast;
        }
        if (tiles[x + y * width] == waterNorthWest) {
            return Tile.waterNorthWest;
        }
        if (tiles[x + y * width] == waterSouthEast) {
            return Tile.waterSouthEast;
        }
        if (tiles[x + y * width] == waterSouthWest) {
            return Tile.waterSouthWest;
        }
        if (tiles[x + y * width] == waterCornerBR) {
            return Tile.waterCornerBR;
        }
        if (tiles[x + y * width] == waterCornerBL) {
            return Tile.waterCornerBL;
        }
        if (tiles[x + y * width] == waterCornerTR) {
            return Tile.waterCornerTR;
        }
        if (tiles[x + y * width] == waterCornerTL) {
            return Tile.waterCornerTL;
        }

        //Sand
        if (tiles[x + y * width] == sand) {
            return Tile.sand;
        }
        if (tiles[x + y * width] == sandNorth) {
            return Tile.sandNorth;
        }
        if (tiles[x + y * width] == sandSouth) {
            return Tile.sandSouth;
        }
        if (tiles[x + y * width] == sandEast) {
            return Tile.sandEast;
        }
        if (tiles[x + y * width] == sandWest) {
            return Tile.sandWest;
        }
        if (tiles[x + y * width] == sandNorthEast) {
            return Tile.sandNorthEast;
        }
        if (tiles[x + y * width] == sandNorthWest) {
            return Tile.sandNorthWest;
        }
        if (tiles[x + y * width] == sandSouthEast) {
            return Tile.sandSouthEast;
        }
        if (tiles[x + y * width] == sandSouthWest) {
            return Tile.sandSouthWest;
        }
        if (tiles[x + y * width] == sandCornerBR) {
            return Tile.sandCornerBR;
        }
        if (tiles[x + y * width] == sandCornerBL) {
            return Tile.sandCornerBL;
        }
        if (tiles[x + y * width] == sandCornerTR) {
            return Tile.sandCornerTR;
        }
        if (tiles[x + y * width] == sandCornerTL) {
            return Tile.sandCornerTL;
        }

        //Sand & Water 
        if (tiles[x + y * width] == sawaNorthEast) {
            return Tile.sawaNorthEast;
        }
        if (tiles[x + y * width] == sawaNorthWest) {
            return Tile.sawaNorthWest;
        }
        if (tiles[x + y * width] == sawaSouthEast) {
            return Tile.sawaSouthEast;
        }
        if (tiles[x + y * width] == sawaSouthWest) {
            return Tile.sawaSouthWest;
        }

        return Tile.voidTile;
    }

    /****************************************************
     * Name:        time
     * Parameters:  n/a
     * Return:      void
     * Description: Overridable method that gives a level
     * an attribute of time
     ****************************************************/
    private void time() {

    }

}

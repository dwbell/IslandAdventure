package pkg.hell.level.tile;

import pkg.hell.level.tile.plantae.*;
import pkg.hell.level.tile.sand.*;
import pkg.hell.level.tile.ocean.*;
import pkg.hell.graphics.Screen;
import pkg.hell.graphics.Sprite;

public class Tile {

    public int x, y;
    public Sprite sprite;

    //Creating static tiles
    public static Tile grass = new GrassTile(Sprite.grass);

    //Small Palm Tree
    public static Tile palmTreeBot = new botPalmTreeTile(Sprite.palm_tree_bot);
    public static Tile palmTreeTop = new topPalmTreeTile(Sprite.palm_tree_top);
    public static Tile palmTreeTop2 = new topPalmTree2Tile(Sprite.palm_tree_top_2);
    public static Tile palmTreeTopCoconut = new topPalmTreeCoconutTile(Sprite.palm_tree_top_coconut);
    public static Tile palmTreeTop2Coconut = new topPalmTree2CoconutTile(Sprite.palm_tree_top_2_coconut);

    //Berry Bush
    public static Tile bush = new bush(Sprite.bush);
    public static Tile bush_berry = new bushBerry(Sprite.bush_berries);

    //Water Tiles
    public static Tile water = new waterTile(Sprite.water);
    public static Tile waterNorth = new northWaterTile(Sprite.waterNorth);
    public static Tile waterSouth = new southWaterTile(Sprite.waterSouth);
    public static Tile waterEast = new eastWaterTile(Sprite.waterEast);
    public static Tile waterWest = new westWaterTile(Sprite.waterWest);

    public static Tile waterNorthEast = new northEastWaterTile(Sprite.waterNorthEast);
    public static Tile waterNorthWest = new northWestWaterTile(Sprite.waterNorthWest);
    public static Tile waterSouthWest = new southWestWaterTile(Sprite.waterSouthWest);
    public static Tile waterSouthEast = new southEastWaterTile(Sprite.waterSouthEast);

    public static Tile waterCornerBR = new waterCornerBRTile(Sprite.waterCornerBR);
    public static Tile waterCornerBL = new waterCornerBLTile(Sprite.waterCornerBL);
    public static Tile waterCornerTR = new waterCornerTRTile(Sprite.waterCornerTR);
    public static Tile waterCornerTL = new waterCornerTLTile(Sprite.waterCornerTL);
    
    public static Tile wave_1 = new wave1_Tile(Sprite.wave_1);
    public static Tile wave_2 = new wave2_Tile(Sprite.wave_2);
    public static Tile wave_3 = new wave3_Tile(Sprite.wave_3);
    

    //Sand Tiles
    public static Tile sand = new sandTile(Sprite.sand);
    public static Tile sandNorth = new northSandTile(Sprite.sandNorth);
    public static Tile sandSouth = new southSandTile(Sprite.sandSouth);
    public static Tile sandEast = new eastSandTile(Sprite.sandEast);
    public static Tile sandWest = new westSandTile(Sprite.sandWest);

    public static Tile sandNorthEast = new northEastSandTile(Sprite.sandNorthEast);
    public static Tile sandNorthWest = new northWestSandTile(Sprite.sandNorthWest);
    public static Tile sandSouthEast = new southEastSandTile(Sprite.sandSouthEast);
    public static Tile sandSouthWest = new southWestSandTile(Sprite.sandSouthWest);

    public static Tile sandCornerBR = new sandCornerBRTile(Sprite.sandCornerBR);
    public static Tile sandCornerBL = new sandCornerBLTile(Sprite.sandCornerBL);
    public static Tile sandCornerTR = new sandCornerTRTile(Sprite.sandCornerTR);
    public static Tile sandCornerTL = new sandCornerTLTile(Sprite.sandCornerTL);

    //Sand & water tiles
    public static Tile sawaNorthEast = new sawaNorthEast(Sprite.sawaNorthEast);
    public static Tile sawaNorthWest = new sawaNorthWest(Sprite.sawaNorthWest);
    public static Tile sawaSouthEast = new sawaSouthEast(Sprite.sawaSouthEast);
    public static Tile sawaSouthWest = new sawaSouthWest(Sprite.sawaSouthWest);
    

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite spriteParam) {
        this.sprite = spriteParam;
    }

    /****************************************************
     * Name:        render
     * Parameters:  n/a
     * Return:      void
     * Description: Tiles render method which is called 
     * from Screen class.
     ****************************************************/
    public void render(int x, int y, Screen screen) {
        
    }
    

    /****************************************************
     * Name:        solid
     * Parameters:  n/a
     * Return:      void
     * Description: Gives an attribute of solid, which 
     * determines ability of collision detection.
     ****************************************************/
    public boolean solid() {
        return false;
    }
}

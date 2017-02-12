package pkg.hell.level.tile.plantae;

import pkg.hell.graphics.Screen;
import pkg.hell.graphics.Sprite;
import pkg.hell.level.tile.Tile;


public class GrassTile extends Tile {

    public GrassTile(Sprite sprite){
        super(sprite);
    }
    
    @Override
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }
}

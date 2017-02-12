package pkg.hell.level.tile.sand;

import pkg.hell.graphics.Screen;
import pkg.hell.graphics.Sprite;
import pkg.hell.level.tile.Tile;


public class sandTile extends Tile {

    public sandTile(Sprite spriteParam) {
        super(spriteParam);
    }
    
    @Override
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }

}

package pkg.hell.level.animTile;

public class WaveNode extends AnimNode {

    public WaveNode(int x, int y, float life) {
        super(x, y, life);
        anim = new int[]{0xff2D94B0, 0xff2D94B1, 0xff2D94B2};
    }
    
    /****************************************************
     * Name:        startSprite()
     * Parameters:  n/a
     * Return:      int
     * Description: Returns the starting hex value for 
     * a wave animation.
     ****************************************************/
    @Override
    public int startSprite(){
        return anim[0];
    }
}

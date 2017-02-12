package pkg.hell.level.animTile;

public abstract class AnimNode {

    private int x;
    private int y;
    private boolean alive;
    private float life;
    private long startTime;

    protected int[] anim;

    public AnimNode(int x, int y, float life) {
        this.x = x;
        this.y = y;
        this.life = life;
        startTime = System.currentTimeMillis();
        alive = true;
    }

    //Getter
    public int getX() {
        return x;
    }

    //Getter
    public int getY() {
        return y;
    }

    //Overridable method
    public int startSprite() {
        return 0xffffff;
    }

    /****************************************************
     * Name:        isAlive()
     * Parameters:  n/a
     * Return:      boolean
     * Description: This method takes advantage of system 
     * clock to determine when a wave node is removed. 
     ****************************************************/
    public boolean isAlive() {
        long currentTime = System.currentTimeMillis();
        long delta = (currentTime - startTime);
        if (delta >= life) {
            alive = false;
        } else {
            alive = true;
        }

        return alive;
    }

    /****************************************************
     * Name:        animSequence
     * Parameters:  n/a
     * Return:      int
     * Description: This processes and returns the correct
     * animation sequence based on time. It takes each frame
     * exactly 1/3rd its life span to change to the next
     * picture.
     ****************************************************/
    public int animSequence() {
        long currentTime = System.currentTimeMillis();
        long delta = (currentTime - startTime);
        float fraction = life / 3;
        if (delta <= fraction) {
            return anim[0];
        } else if (delta <= fraction * 2) {
            return anim[1];

        } else {
            return anim[2];
        }
    }

}

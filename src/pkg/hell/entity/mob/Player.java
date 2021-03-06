package pkg.hell.entity.mob;

import pkg.hell.graphics.Screen;
import pkg.hell.graphics.Sprite;
import pkg.hell.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;

    //Empty constructor
    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_down;
    }

    //Constructor with x,y coords to spawn
    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_down;
    }

    /****************************************************
     * Name:        update
     * Parameters:  n/a
     * Return:      void
     * Description: Players update method, overridden from
     * Entity class. This method checks which key is 
     * pressed and either subtracts or adds to player 
     * position by 1 or - 1. If xa or ya is equal to 0
     * nothing is passed to the move method. 
     ****************************************************/
    @Override
    public void update() {

        //Capping animation cycle
        if (anim < 7500) {
            anim++;
        } else {
            anim = 0;
        }

        //Checking direction
        int xa = 0, ya = 0;
        if (input.up()) {
            ya--;
        }
        if (input.down()) {
            ya++;
        }
        if (input.left()) {
            xa--;
        }
        if (input.right()) {
            xa++;
        }

        //Sending values to move
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    /****************************************************
     * Name:        render
     * Parameters:  Screen screen
     * Return:      void
     * Description: Players render method which is overridden
     * from Entity class. This method is what essentially
     * draws the player given x,y coordinates from entity
     * and a given designated sprite object. Player animation 
     * is controlled in here as well. 
     ****************************************************/
    @Override
    public void render(Screen screen) {
        
        //North
        if (dir == 0) {
            sprite = Sprite.player_up;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player_up_1;
                }else{
                    sprite = Sprite.player_up_2;
                }
            }
        }
        //East
        if (dir == 1) {
            sprite = Sprite.player_right;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player_right_1;
                }else{
                    sprite = Sprite.player_right_2;
                }
            }
        }
        //South
        if (dir == 2) {
            sprite = Sprite.player_down;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player_down_1;
                }else{
                    sprite = Sprite.player_down_2;
                }
            }
        }
        //West
        if (dir == 3) {
            sprite = Sprite.player_left;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player_left_1;
                }else{
                    sprite = Sprite.player_left_2;
                }
            }
        }
        //Actually renders player with appropiate animation
        screen.renderPlayer(x - 16, y - 16, sprite);
    }

}

package pkg.hell;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import javax.swing.JFrame;
import pkg.hell.entity.mob.Player;
import pkg.hell.graphics.Screen;
import pkg.hell.input.Keyboard;
import pkg.hell.input.Mouse;
import pkg.hell.level.Level;
import pkg.hell.level.RandomLevel;  
import pkg.hell.level.SpawnLevel;
import pkg.hell.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = 256;
    private static final int SCREEN_HEIGHT = 256;
    private static final int SCALE = 2;
    private static final String TITLE = "Hell";

    private BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

    //Convert image object into array of pixel colors
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Thread thread;
    private JFrame frame;
    private Level level;
    private Player player;
    private boolean running = false;
    private Screen screen;
    private Keyboard key;

    public Game() {

        Dimension size = new Dimension(SCREEN_WIDTH * SCALE, SCREEN_HEIGHT * SCALE);
        setPreferredSize(size);

        //Initializing Classes
        screen = new Screen(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame = new JFrame();
        key = new Keyboard();
        //level = new RandomLevel(64,64);
        level = new SpawnLevel("/textures/level.png");
        TileCoordinate playerSpawn = new TileCoordinate(25, 25);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        player.init(level);

        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }

    /****************************************************
     * Name:        start 
     * Parameters:  n/a
     * Return:      void
     * Description: Initiates game loop and thread
     ****************************************************/
    public synchronized void start() {

        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    /****************************************************
     * Name:        stop
     * Parameters:  n/a
     * Return:      void
     * Description: Terminates game loop and thread
     ****************************************************/
    public synchronized void stop() {

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /****************************************************
     * Name:        run
     * Parameters:  n/a
     * Return:      void
     * Description: Actual game loop. Includes an FPS 
     * counter set to run at 60 frames per second. This 
     * method is machine independent. 
     ****************************************************/
    public void run() {

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double difference = 0;
        int updates = 0;
        int frames = 0;
        requestFocus();

        while (running) {

            //Math to only call updates ~60 times per second
            long now = System.nanoTime();
            difference += (now - lastTime) / ns;
            lastTime = now;
            while (difference >= 1) {
                update(); //Restricted
                updates++;
                difference--;
            }

            render(); //Unrestricted
            frames++;

            //Triggers once per second to update counters
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + "  UPS: " + updates + "  FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /****************************************************
     * Name:        update
     * Parameters:  n/a
     * Return:      void
     * Description: Restricted to updating ~60 times per
     * second. By using xScroll, yScroll we can animate
     * only what is visible to the player. Saving resources
     ****************************************************/
    public void update() {
        key.update();
        player.update();

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.update(xScroll, yScroll, screen);

    }

    /****************************************************
     * Name:        render
     * Parameters:  n/a
     * Return:      void
     * Description: Unrestricted method in which the game
     * is rendered. Set up using "triple buffering".
     ****************************************************/
    public void render() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        //Setting player to center of screen
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        //Levels render
        level.render(xScroll, yScroll, screen);
        //Putting player on screen
        player.render(screen);

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        //Any graphics to screen within { }
        Graphics g = bs.getDrawGraphics();
        {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            GUI(g);
            
        }
        g.dispose(); //manual garbage collection
        bs.show(); //makes next buffer available
    }

    public void GUI(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(5, 5, 50, 50);
        g.setColor(Color.GRAY);
        g.drawRect(7, 7, 45, 45);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 9));
        g.drawString("Health: ", 10, 16);
    }

    /****************************************************
     * Name:        getScreenWidth
     * Parameters:  n/a
     * Return:      int
     * Description: Getter method, returning screen width
     ****************************************************/
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /****************************************************
     * Name:        getScreenHeight
     * Parameters:  n/a
     * Return:      int
     * Description: Getter method, returning screen height
     ****************************************************/
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /***********
     Main Class
     ************/
    public static void main(String[] args) {

        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Hell");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
        
    }
}

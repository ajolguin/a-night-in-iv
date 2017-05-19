package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Armin on 5/17/17.
 */

public class WinItem extends Sprite {

    private BufferedImage image;
    static Timer timer = new Timer();
    private GameModel game;
    private GameComponent gc;
    static int seconds = 0;
    public WinItem(BufferedImage image, GameComponent gc, MapSection map) {
        super(map);
        this.image = image;
        //this.game = game;
        this.gc = gc;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        //game.message = "You made it home!";
        gc.message = "You made it home!";
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //game.message = "";
                gc.message = "";
            }
        };
        timer.schedule(task,1000);
        return true;
    }

    @Override
    void update(double delta){}
}

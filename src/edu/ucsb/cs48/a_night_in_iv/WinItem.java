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
    public WinItem(BufferedImage image, MapSection map) {
        super(map);
        this.image = image;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        //game.message = "You made it home!";
        map.parent.message = "You made it home!";
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //game.message = "";
                map.parent.message = "";
            }
        };
        timer.schedule(task,1000);
        return true;
    }

    @Override
    void update(double delta){}
}

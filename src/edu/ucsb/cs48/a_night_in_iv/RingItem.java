package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Armin on 5/17/17.
 */

public class RingItem extends Sprite {

    private BufferedImage image;
    static Timer timer = new Timer();
    public RingItem(BufferedImage image, MapSection map) {
        super(map);
        this.image = image;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        map.parent.message = "You found a ring! It has no use. You feel prettier though.";
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                map.parent.message = "";
            }
        };
        timer.schedule(task,5000);
        return true;
    }

    @Override
    void update(double delta){}
}

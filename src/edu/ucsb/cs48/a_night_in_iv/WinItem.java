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
        map.parent.winGame = true;
        return true;
    }

    @Override
    void update(double delta){}
}

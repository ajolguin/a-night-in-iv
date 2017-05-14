package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/6/2017.
 */
public abstract class Sprite {
    abstract void update(double delta);
    abstract boolean tryPassThrough(Player player);
    abstract BufferedImage getImage();
}

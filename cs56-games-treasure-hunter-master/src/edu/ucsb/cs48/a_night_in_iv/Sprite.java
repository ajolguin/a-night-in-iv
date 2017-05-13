package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/6/2017.
 */
public abstract class Sprite {

    public void update(double delta) {
    }

    abstract BufferedImage getImage();

}

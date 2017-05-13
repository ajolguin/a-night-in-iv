package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/7/2017.
 */
public class GenericSprite extends Sprite {
    //private instance variables
    private BufferedImage image;

    //GenericSprite constructor
    public GenericSprite(BufferedImage image){
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
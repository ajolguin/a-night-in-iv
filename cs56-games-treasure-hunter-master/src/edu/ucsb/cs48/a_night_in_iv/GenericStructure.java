package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/7/2017.
 */
public class GenericStructure extends Sprite {

    private BufferedImage image;

    public GenericStructure(BufferedImage image) {
        this.image = image;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        return false;
    }

    @Override
    void update(double delta){}
}
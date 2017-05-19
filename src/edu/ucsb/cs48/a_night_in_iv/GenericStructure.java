package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by Karl Wang (kovlv) on 5/7/2017.
 * Class representing the sprites in the game that act as structures
 * eg: weeds, boulders, houses etc.
 * @see Sprite for method documentations
 */
public class GenericStructure extends Sprite {

    private BufferedImage image;

    public GenericStructure(BufferedImage image, MapSection map) {
        super(map);
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
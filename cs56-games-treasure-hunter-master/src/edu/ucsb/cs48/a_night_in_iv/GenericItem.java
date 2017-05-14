package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/7/2017.
 */
public class GenericItem extends Sprite {

    private BufferedImage image;
    private int boModifier;
    private MapSection map;

    public GenericItem(BufferedImage image, int boModifier, MapSection map) {
        this.image = image;
        this.boModifier = boModifier;
        this.map = map;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        player.modifyBlackout(boModifier);
        map.removeSprite(this);
        return true;
    }

    @Override
    void update(double delta){}
}
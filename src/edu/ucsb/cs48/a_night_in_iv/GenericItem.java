package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by Karl Wang (kovlv) on 5/7/2017.
 * Class representing sprites in the game that the player can interact with
 * eg: soda, alcohol, substances etc.
 * @see Sprite for method documentation
 */
public class GenericItem extends Sprite {

    private BufferedImage image;
    private int boModifier;

    public GenericItem(BufferedImage image, int boModifier, MapSection map) {
        super(map);
        this.image = image;
        this.boModifier = boModifier;
    }

    @Override
    BufferedImage getImage() {
        return image;
    }

    @Override
    boolean tryPassThrough(Player player){
        player.modifyBlackout(boModifier);
        removeFromMap();
        return true;
    }

    @Override
    void update(double delta){}



}
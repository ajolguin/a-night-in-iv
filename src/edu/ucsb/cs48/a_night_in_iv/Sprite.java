package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by Karl Wang (kovlv) on 5/6/2017.
 * Abstract class representing the various sprites that exist in the game
 * @see GenericItem
 * @see GenericStructure
 */
public abstract class Sprite {
    /**
     * Update the sprite accordingly
     * @param delta
     */
    abstract void update(double delta);

    /**
     * determines whether the player is allowed to walk through the sprite or not
     * @param player the player
     * @return true/false depending upon if the player is allowed to move through the sprite or not
     */
    abstract boolean tryPassThrough(Player player);

    /**
     * acquires the specific PNG texture associated with the sprite
     * @return BufferedImage determined by the specific sprites texture
     */
    abstract BufferedImage getImage();
}

package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A brand-new Treasure class by Lisa Liao and Patrick Vidican
 *
 * @author Lisa Liao
 * @author Patrick Vidican
 * @version for CS56, F16, UCSB, 11/19/2016
 */


public class Item extends Sprite{
    // private instance variables
    private boolean found = false;
    private BufferedImage image;

    //Item constructor
    public Item(BufferedImage image) { this.image = image; }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    public void setFound() { this.found = true; }
}

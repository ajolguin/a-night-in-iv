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


public class Treasure extends Sprite{
    // private instance variables
    private String name;
    private boolean found = false;
    private BufferedImage image = null;

    //Treasure constructor
    public Treasure(String name, int YTile, int XTile) {
        this.name = name;
        try {
            image = ImageIO.read(getClass().getResource(GameGui.resourcesDir + "treasure/treasure1.png"));
            // function calls above might not be necessary if we just have one image
        } catch (IOException e) {
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}

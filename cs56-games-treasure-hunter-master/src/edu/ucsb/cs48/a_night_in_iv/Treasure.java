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
        super(YTile, XTile);
        this.name = name;
        try {
            image = ImageIO.read(getClass().getResource(GameGui.resourcesDir + "treasure/treasure1.png"));
            // function calls above might not be necessary if we just have one image
        } catch (IOException e) {
        }
    }

    @Override
    public boolean equals(Object o) {
        // Start: boilerplate code for .equals
        if (this == o) return true; // comparing to itself
        if (o == null) return false; // comparing to nothing
        if (getClass() != o.getClass()) return false; // comparing apples to oranges
        Treasure r = (Treasure) o; // .equals now knows that o is-a Treasure
        // End boilerplate

        return ( this.getXTile() == r.getXTile() )  && ( this.getYTile() == r.getYTile() );
    }

    @Override
    public int hashCode() { // straight outt conrad's tutorials
        int xTileBit = this.getXTile() & 0x0000FFFF;
        int yTileBit = this.getYTile() & 0x0000FFFF;
        return (xTileBit << 16) | yTileBit;
    }

    public BufferedImage getImage() {
        return image;
    }

}

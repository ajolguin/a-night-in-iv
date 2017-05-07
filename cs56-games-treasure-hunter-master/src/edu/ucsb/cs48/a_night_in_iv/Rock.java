package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by kovlv on 5/6/2017.
 */
public class Rock extends Sprite {
    private BufferedImage image = null;

    public Rock(int YTile, int XTile) {
        super(YTile, XTile);
        try {
            image = ImageIO.read(getClass().getResource(GameGui.resourcesDir + "stone2.png"));
            // function calls above might not be necessary if we just have one image
        } catch (IOException e) {
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}

package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/6/2017.
 */
public class Item extends Sprite{
    BufferedImage image;

    Item(int XTile, int YTile){
        super(XTile, YTile);
    }

    @Override
    BufferedImage getImage() {
        return this.image;
    }
}

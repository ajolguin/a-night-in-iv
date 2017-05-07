package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/6/2017.
 */
public abstract class Sprite {
    private int XTile;
    private int YTile;

    Sprite(int XTile, int YTile) {
        this.XTile = XTile;
        this.YTile = YTile;
    }

    public void update() {

    }

    int getXTile() {
        return XTile;
    }

    int getYTile() {
        return YTile;
    }

    abstract BufferedImage getImage();

}

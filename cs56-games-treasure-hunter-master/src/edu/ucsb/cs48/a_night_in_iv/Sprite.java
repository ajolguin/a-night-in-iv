package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;

/**
 * Created by kovlv on 5/6/2017.
 */
public abstract class Sprite {
    private int xTile;
    private int yTile;

    Sprite(int YTile, int XTile) {
        this.xTile = XTile;
        this.yTile = YTile;
    }

    public void update() {
    }

    int getXTile() {
        return xTile;
    }

    int getYTile() {
        return yTile;
    }

    public void setTiles(int yTile, int xTile) {
        this.xTile = xTile;
        this.yTile = yTile;
    }

    abstract BufferedImage getImage();

}

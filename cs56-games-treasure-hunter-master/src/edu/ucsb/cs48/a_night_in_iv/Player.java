package edu.ucsb.cs48.a_night_in_iv;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * A component that draws the Player  by Alex Wood
 *
 * @author Alex Wood (UCSB, CS56 W12, 2/16/2012)
 * @author Danielle Dodd and George Lieu
 * @version for CS56, W16, UCSB, 2/18/2016
 */


public class Player extends Sprite {
    // private instance variables
    private boolean movable = true;
    private int xPos;
    private int yPos;
    private int xTile;
    private int yTile;
    private ArrayList<BufferedImage> sprites;
    private int currentSprite = 0;

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

    //player constructor
    public Player(int yTile, int xTile, int numSprites, int currentSprite, String name) {
        setTiles(yTile, xTile);
        try {
            sprites = new ArrayList<BufferedImage>();
            for (int i = 0; i < numSprites; i++)
                sprites.add(ImageIO.read(getClass().getResource(GameGui.resourcesDir + "player2/" + name + i + ".png")));
            this.currentSprite = currentSprite;
            this.moveTo(xTile * GameComponent.PIXEL_SIZE, yTile * GameComponent.PIXEL_SIZE);
        } catch (Exception e) {
        }
    }

    public void setSprite(int sprite) {
        currentSprite = sprite;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void moveTo(int yPos, int xPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public int getCurrentSpriteIndex() {
        return currentSprite;
    }

    @Override
    BufferedImage getImage() {
        return sprites.get(currentSprite);
    }
}

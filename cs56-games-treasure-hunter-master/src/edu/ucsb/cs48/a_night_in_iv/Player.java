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
    GameModel game;
    // private instance variables
    private boolean movable = true;
    private int xPos;
    private int yPos;
    private int xDir;
    private int yDir;
    private int xTile;
    private int yTile;
    private ArrayList<BufferedImage> sprites;
    private int currentSprite = 0;

    //player constructor
    public Player(int yTile, int xTile, int numSprites, int currentSprite, String name, GameModel game) {
        this.game = game;
        try {
            sprites = new ArrayList<BufferedImage>();
            for (int i = 0; i < numSprites; i++)
                sprites.add(ImageIO.read(getClass().getResource(GameGui.resourcesDir + "player2/" + name + i + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.currentSprite = currentSprite;
        this.setTiles(yTile, xTile);
        this.setPositions(xTile * game.PIXEL_SIZE, yTile * game.PIXEL_SIZE);
    }

    int getXTile() {
        return xTile;
    }

    int getYTile() {
        return yTile;
    }

    public void setTiles(int yTile, int xTile) {
        game.getCurrentMap().setSprite(null, this.yTile, this.xTile);
        game.getCurrentMap().setSprite(this, yTile, xTile);
        this.xTile = xTile;
        this.yTile = yTile;
    }

    public void setPositions(int yPos, int xPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setDirections(int yDir, int xDir) {
        this.xDir = xDir;
        this.yDir = yDir;
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

    public void moveInDirection(int yDir, int xDir) {
        int xTile = this.xTile + xDir;
        int yTile = this.yTile + yDir;

        if (yDir == 0 && xDir == 0)
            return;
        if (isMoving())
            return;

        if (xDir == -1)
            setSprite(4);
        if (xDir == 1)
            setSprite(8);
        if (yDir == -1)
            setSprite(12);
        if (yDir == 1)
            setSprite(0);
        if (xTile < 0 || xTile > (game.mapWidth - 1) || yTile < 0 || yTile > (game.mapHeight - 1)) {
            MapSection newMap = game.getMapInDirection(yDir, xDir);
            if (newMap != null) {
                game.moveMapInDirection(yDir, xDir);
                int newXTile = (getXTile() + xDir) % game.mapWidth;
                if (newXTile < 0) newXTile += game.mapWidth;
                int newYTile = (getYTile() + yDir) % game.mapHeight;
                if (newYTile < 0) newYTile += game.mapHeight;
                setTiles(newYTile, newXTile);
                setPositions(newYTile * game.PIXEL_SIZE, newXTile * game.PIXEL_SIZE);
            }
            return;
        }
        if (game.getCurrentMap().getSprite(yTile, xTile) != null)
            return;

        setDirections(yDir, xDir);
        setTiles(yTile, xTile);
    }

    public boolean isMoving() {
        return yTile * game.PIXEL_SIZE != yPos || xTile * game.PIXEL_SIZE != xPos;
    }

    @Override
    public void update(double delta) {
        if (!isMoving())
            return;

        int newY = yPos + yDir * ((int) (delta * 7));
        int newX = xPos + xDir * ((int) (delta * 7));

        if (yDir > 0 && newY > yTile * game.PIXEL_SIZE || yDir < 0 && newY < yTile * game.PIXEL_SIZE) {
            newY = yTile * game.PIXEL_SIZE;
        }
        if (xDir > 0 && newX > xTile * game.PIXEL_SIZE || xDir < 0 && newX < xTile * game.PIXEL_SIZE)
            newX = xTile * game.PIXEL_SIZE;
        setPositions(newY, newX);
    }

    public int getCurrentSpriteIndex() {
        return currentSprite;
    }


    BufferedImage getPlayerImage() {
        return sprites.get(currentSprite);
    }

    @Override
    BufferedImage getImage() {
        return null;
    }
}

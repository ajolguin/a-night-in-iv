package edu.ucsb.cs48.a_night_in_iv;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Representing the Player of the game
 * Contains both the game mechanics related to the player and sprite data
 *
 * @see Sprite
 */


public class Player extends Sprite {
    GameModel game;

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    private int xPos;
    private int yPos;
    private int xDir;
    private int yDir;
    private int blackout;
    private ArrayList<BufferedImage> sprites;
    private int currentSprite = 0;

    /**
     * Constructs the Player object within the GameModel
     * @param yTile Starting Y coordinate
     * @param xTile Starting X coordinate
     * @param numSprites Number of different animation sprites associated with Player
     * @param currentSprite Initial sprite Player loads as
     * @param name Name of PLayer
     * @param game GameModel object player will exist in
     *
     * @see GameModel
     */
    public Player(int yTile, int xTile, int numSprites, int currentSprite, String name, GameModel game, MapSection map) {
        super(map);
        this.game = game;
        try {
            sprites = new ArrayList<BufferedImage>();
            for (int i = 0; i < numSprites; i++)
                sprites.add(ImageIO.read(getClass().getResource(GameGUI.resourcesDir + "player2/" + name + i + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.currentSprite = currentSprite;
        this.setTiles(yTile, xTile);
        this.setPositions(yTile * game.PIXEL_SIZE, xTile * game.PIXEL_SIZE);
    }

    /**
     * Returns the X coordinate of the player
     * Coordinates determined by map size
     * @return X coordinate of Player
     */
    int getXTile() {
        return xTile;
    }

    /**
     * Returns Y coordinate of player in current map
     * Coordinates are determined by map size
     * @return Y coordinate of Player
     */
    int getYTile() {
        return yTile;
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
                this.setMap(game.getCurrentMap());
                int newXTile = (getXTile() + xDir) % game.mapWidth;
                if (newXTile < 0) newXTile += game.mapWidth;
                int newYTile = (getYTile() + yDir) % game.mapHeight;
                if (newYTile < 0) newYTile += game.mapHeight;
                setTiles(newYTile, newXTile);
                setPositions(newYTile * game.PIXEL_SIZE, newXTile * game.PIXEL_SIZE);
            }
            return;
        }
        Sprite targetSprite = game.getCurrentMap().getSprite(yTile, xTile);
        if (targetSprite != null && !targetSprite.tryPassThrough(this))
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

    void modifyBlackout(int boModifier){
        this.blackout += boModifier;
    }

    int getBlackout(){
        return blackout;
    }

    BufferedImage getPlayerImage() {
        return sprites.get(currentSprite);
    }

    //do not use this method to paint player because it draws only on the tile, not in between.
    @Override
    BufferedImage getImage() {
        return null;
    }

    @Override
    boolean tryPassThrough(Player player) {
        throw new java.lang.RuntimeException("player should not be interacting with him or her self");
    }
}

package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 * Created by Karl Wang (kovlv) on 5/6/2017.
 * Class representing a specific portion (map) of GameComponent
 * Each map is represented in a single frame composed of terrain and sprite layers
 * Each terrain and sprite layer is built here
 */
public class MapSection {
    BufferedImage[][] terrains;
    Sprite[][] sprites;
    int width;
    int height;
    String name;
    GameModel parent;
    public MapSection(String dir, String id, int height, int width, GameModel g) {
        parent = g;
        this.name = id;
        Scanner mapScanner = new Scanner(getClass().getResourceAsStream(dir + "map" + name + ".txt"));
        Scanner spriteScanner = new Scanner(getClass().getResourceAsStream(dir + "sprite" + name + ".txt"));
        this.width = width;
        this.height = height;
        this.terrains = new BufferedImage[height][width];
        this.sprites = new Sprite[height][width];
        readMapData(mapScanner);
        readSpriteData(spriteScanner);
    }

    public void setTerrain(BufferedImage terrain, int YTile, int XTile) {
        this.terrains[YTile][XTile] = terrain;
    }

    public void setSprite(Sprite sprite, int YTile, int XTile) {
        this.sprites[YTile][XTile] = sprite;
    }

    public BufferedImage getTerrain(int YTile, int XTile) {
        return this.terrains[YTile][XTile];
    }

    public Sprite getSprite(int YTile, int XTile) {
        return this.sprites[YTile][XTile];
    }

     /*
       loadMap first reads in the png files of the appropriate tile.
       It scans the text file map.txt and loads the appropriate png image into the instance variable tiles.
       Tiles is later used  by paintComponent to actually make the tiles appear.
     */

    public void update(double delta) {
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                if (sprites[h][w] != null)
                    sprites[h][w].update(delta);
    }

    private void readMapData(Scanner scanner) {
        String temp;

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (!temp.equals("."))
                        this.terrains[y][x] = parent.getTexture(temp);
                }
    }

    private void readSpriteData(Scanner scanner) {
        String temp;

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (!temp.equals(".")) {
                        this.sprites[y][x] = new GenericStructure(parent.getTexture(temp), this);
                    }
                }
    }

    boolean removeSprite (Sprite s) {
        for (int y = 0; y < height; ++y){
            for (int x = 0; x < width; ++x){
                if (sprites[y][x] == s) {
                    setSprite(null, y, x);
                    return true;
                }
            }
        }
        return false;
    }
}
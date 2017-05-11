package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kovlv on 5/6/2017.
 */
public class MapSection {
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

    BufferedImage[][] terrains;
    Sprite[][] sprites;
    int width;
    int height;
    String name;
    GameModel parent;

    public void update() {
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                sprites[h][w].update();
            }
    }
     /*
       loadMap first reads in the png files of the appropriate tile.
       It scans the text file map.txt and loads the appropriate png image into the instance variable tiles.
       Tiles is later used  by paintComponent to actually make the tiles appear.
     */

    public MapSection(String name, GameModel g) {
        parent = g;
        this.name = name;
        String dir = "/resources/";
        Scanner scanner = new Scanner(getClass().getResourceAsStream(dir + name));
        width = scanner.nextInt();
        height = scanner.nextInt();
        this.terrains = new BufferedImage[height][width];
        this.sprites = new Sprite[height][width];
        readMapData(scanner);
    }

    public MapSection(String dir, String id, int height, int width, GameModel g) {
        parent = g;
        this.name = id;
        System.out.println(name);
        Scanner mapScanner = new Scanner(getClass().getResourceAsStream(dir + "map"+name+".txt"));
        Scanner spriteScanner = new Scanner(getClass().getResourceAsStream(dir + "sprite"+name+".txt"));
        this.width = width;
        this.height = height;
        this.terrains = new BufferedImage[height][width];
        this.sprites = new Sprite[height][width];
        readMapData(mapScanner);
        readSpriteData(spriteScanner);
    }

    private void readMapData(Scanner scanner){
        String temp;

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (!temp.equals("."))
                        this.terrains[y][x] = parent.getTexture(temp);
                }
    }

    private void readSpriteData(Scanner scanner){
        String temp;

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (!temp.equals(".")) {
                        this.sprites[y][x] = new GenericSprite(parent.getTexture(temp));
                    }
                }
    }
}


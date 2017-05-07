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
    public BufferedImage[][] getTerrains() {
        return terrains;
    }

    public Character[][] getTerrainTypes() {
        return terrainTypes;
    }

    public Sprite[][] getSprites() {
        return sprites;
    }

    public void setTerrain(BufferedImage terrain, int YTile, int XTile) {
        this.terrains[YTile][XTile] = terrain;
    }

    public void setTerrainType(Character terrainType, int YTile, int XTile) {
        this.terrainTypes[YTile][XTile] = terrainType;
    }

    public void setSprite(Sprite sprite, int YTile, int XTile) {
        this.sprites[YTile][XTile] = sprite;
    }

    public BufferedImage getTerrain(int YTile, int XTile) {
        return this.terrains[YTile][XTile];
    }

    public Character getTerrainType(int YTile, int XTile) {
        return this.terrainTypes[YTile][XTile];
    }

    public Sprite getSprite(int YTile, int XTile) {
        return this.sprites[YTile][XTile];
    }

    BufferedImage[][] terrains;
    Character[][] terrainTypes;
    Sprite[][] sprites;
    int width;
    int height;
    String name;

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

    public MapSection(String name) {
        this.name = name;
        String dir = "/resources/";
        Scanner scanner = new Scanner(getClass().getResourceAsStream(dir + name));
        width = scanner.nextInt();
        height = scanner.nextInt();
        this.terrainTypes = new Character[height][width];
        this.terrains = new BufferedImage[height][width];
        this.sprites = new Sprite[height][width];
        readMapData(scanner);
    }

    public MapSection(String name, int height, int width) {
        this.name = name;
        String dir = "/resources/";
        Scanner scanner = new Scanner(getClass().getResourceAsStream(dir + name));
        this.width = width;
        this.height = height;
        this.terrainTypes = new Character[height][width];
        this.terrains = new BufferedImage[height][width];
        this.sprites = new Sprite[height][width];
        readMapData(scanner);
    }

    private void readMapData(Scanner scanner){
        try {
            BufferedImage grassTile = ImageIO.read(getClass().getResource("/resources/G.png"));
            BufferedImage bushTile = ImageIO.read(getClass().getResource("/resources/B.png"));
            String temp;

            for (int y = 0; y < height; ++y)
                for (int x = 0; x < width; ++x)
                    if (scanner.hasNext()) {
                        temp = scanner.next();
                        if (temp.equals(("G"))) {
                            this.terrains[y][x] = grassTile;
                            this.terrainTypes[y][x] = 'G';
                        }
                        if (temp.equals(("B"))) {
                            this.terrains[y][x] = bushTile;
                            this.terrainTypes[y][x] = 'B';
                        }
                        if (temp.equals(("S"))) {
                            this.terrains[y][x] = grassTile;
                            this.terrainTypes[y][x] = 'S';
                            this.sprites[y][x] = new Rock(y, x);
                        }
                    }
        }catch (IOException e){

        }
    }
}


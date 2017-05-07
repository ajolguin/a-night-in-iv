package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by kovlv on 5/6/2017.
 */
public class GameModel {
    ArrayList<MapSection> Scene;
    public int currentMapX;
    public int currentMapY;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;
    int mapWidth;
    int mapHeight;
    int sceneWidth;
    int sceneHeight;
    MapSection[][] sections;

    public GameModel(int mapWidth, int mapHeight, int sceneWidth, int sceneHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    public GameModel(String name) {
        String dir = "/resources/" + name + "/";

        Scanner scanner = new Scanner(getClass().getResourceAsStream(dir + "scene.txt"));

        sceneWidth = scanner.nextInt();
        sceneHeight = scanner.nextInt();
        mapWidth = scanner.nextInt();
        mapHeight = scanner.nextInt();
        currentMapX = scanner.nextInt();
        currentMapY = scanner.nextInt();
        this.sections = new MapSection[sceneHeight][sceneWidth];

        String temp;

        for (int y = 0; y < sceneHeight; ++y)
            for (int x = 0; x < sceneWidth; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (temp.equals(("0"))) {
                        sections[y][x] = null;
                    } else {
                        sections[y][x] = new MapSection(name + "/map" + temp + ".txt", mapHeight, mapWidth);
                    }
                }
    }

    public void addMapSection(MapSection newMap, int XPos, int YPos) {
        sections[YPos][XPos] = newMap;
    }

    public MapSection getCurrentMap() {
        return sections[currentMapY][currentMapX];
    }

    public MapSection getMap(int Y, int X){
        return sections[Y][X];
    }

    public MapSection getMapInDirection(int Y, int X){
        int newX = currentMapX + X;
        int newY = currentMapY + Y;
        if (newX < 0 || newY < 0 || newX > sceneWidth - 1 || newY > sceneHeight - 1)
            return null;
        else
            return sections[newY][newX];
    }
    public void  moveMapInDirection(int Y, int X){
        int newX = currentMapX + X;
        int newY = currentMapY + Y;
        if (!(newX < 0 || newY < 0 || newX > sceneWidth - 1 || newY > sceneHeight - 1)) {
            currentMapX = newX;
            currentMapY = newY;
        }

    }
}

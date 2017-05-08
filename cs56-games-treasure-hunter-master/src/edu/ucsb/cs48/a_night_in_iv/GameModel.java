package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Files;

/**
 * Created by kovlv on 5/6/2017.
 */
public class GameModel {
    ArrayList<MapSection> Scene;
    Map<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
    public int currentMapX;
    public int currentMapY;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;
    String name;
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
        this.name = name;
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
        //Must load texture first before loading the sections
        loadTextures("./cs56-games-treasure-hunter-master/src/resources/" + name + "/texture/");
        for (int y = 0; y < sceneHeight; ++y)
            for (int x = 0; x < sceneWidth; ++x)
                if (scanner.hasNext()) {
                    temp = scanner.next();
                    if (temp.equals(("0"))) {
                        sections[y][x] = null;
                    } else {
                        sections[y][x] = new MapSection(dir, temp, mapHeight, mapWidth, this);
                    }
                }
    }

    private void loadTextures(String path){
        File folder = new File(path);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                System.out.println(fileEntry.getName());
                String name = fileEntry.getName();
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                    name = name.substring(0, pos);
                }
                try {
                    System.out.println("name is:" + fileEntry.getName() + ":" + name);
                    String filepath = "/resources/" + this.name + "/texture/" + fileEntry.getName();
                    URL fileURL = getClass().getResource( filepath );
                    textures.put(name, ImageIO.read(fileURL));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println(textures);
    }

    public BufferedImage getTexture(String name){
        return textures.get(name);
    }

    public MapSection getCurrentMap() {
        return sections[currentMapY][currentMapX];
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

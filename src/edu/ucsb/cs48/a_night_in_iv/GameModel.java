package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Class that constructs the entire game model based upon the game component built
 * Each individual MapSection is populated with various textures of sprites determined in corresponding .txt files
 *
 * Created by Karl Wang (kovlv) on 5/6/2017.
 * @see GameComponent for how game component builds the map sections
 * @see MapSection for how
 */
public class GameModel {
    static final int PIXEL_SIZE = 32;
    Map<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
    Player player;
    String name;
    boolean gameWon = false;
    boolean gameLost = false;
    String message = "";
    int mapWidth;
    int mapHeight;
    MapSection[][] sections;
    private int currentMapX;
    private int currentMapY;
    private int sceneWidth;
    private int sceneHeight;
    BufferedImage layover;

    public GameModel(String name) {
        this.name = name;
        String dir = "/resources/" + name + "/";

        Scanner scanner = new Scanner(getClass().getResourceAsStream(dir + "layoutVars.txt"));

        sceneWidth = scanner.nextInt();
        sceneHeight = scanner.nextInt();
        mapWidth = scanner.nextInt();
        mapHeight = scanner.nextInt();
        currentMapX = scanner.nextInt();
        currentMapY = scanner.nextInt();
        this.sections = new MapSection[sceneHeight][sceneWidth];

        String temp;
        //Must load texture first before loading the sections
        try {
            loadTextures("/resources/" + name + "/textures/");
        } catch (Exception e){
            e.printStackTrace();
        }

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

        try {
                layover = ImageIO.read(getClass().getResource(GameGUI.resourcesDir + "lay.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void loadTextures(String path) throws URISyntaxException, IOException {


        URI uri = getClass().getResource(path).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath(path);
        } else {
            myPath = Paths.get(uri);
        }

        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> it = walk.iterator(); it.hasNext();){
            Path fileEntry = it.next();
            if(Files.isRegularFile(fileEntry)) {
                String name = fileEntry.getFileName().toString();
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                    name = name.substring(0, pos);
                }
                try {
                    System.out.println("file: " + fileEntry.getFileName() + "\t\tname: " + name);
                    textures.put(name, ImageIO.read(Files.newInputStream(fileEntry)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BufferedImage getTexture(String name) {
        return textures.get(name);
    }

    public MapSection getCurrentMap() {
        return sections[currentMapY][currentMapX];
    }

    public MapSection getMapInDirection(int Y, int X) {
        int newX = currentMapX + X;
        int newY = currentMapY + Y;
        if (newX < 0 || newY < 0 || newX > sceneWidth - 1 || newY > sceneHeight - 1)
            return null;
        else
            return sections[newY][newX];
    }

    public void moveMapInDirection(int Y, int X) {
        int newX = currentMapX + X;
        int newY = currentMapY + Y;
        if (!(newX < 0 || newY < 0 || newX > sceneWidth - 1 || newY > sceneHeight - 1)) {
            currentMapX = newX;
            currentMapY = newY;
        }

    }


}

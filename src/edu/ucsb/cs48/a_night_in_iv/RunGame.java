package edu.ucsb.cs48.a_night_in_iv;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;


/**
 * Class that merges both Menu GUI and GameGUI together in a functional GameLoop
 * Determines the main frame that will be used for the entire game
 * Dynamically changes the components of the frame based on sequential logic in the gameloop
 *
 * @version 5.15.17
 * @see GameGUI
 * @see MenuGUI
 * Created by Matthew R on 5/14/2017.
 */
public class
RunGame {
    static JFrame fullFrame;
    GameGUI gGUI;
    MenuGUI mGUI;
    static boolean gameRunning;
    static boolean atMenus;
    static boolean startGameGUI;
    static boolean blackoutOverTime = true;
    static String sceneName;
    static MediaPlayer songPlayer;
    public final String songDir = "src/resources/music/";
    static String currentSong;

    private RunGame() {
        fullFrame = new JFrame();
        JFXPanel songPanel = new JFXPanel();
        fullFrame.getContentPane().setPreferredSize(new Dimension(24 * GameModel.PIXEL_SIZE, 18 * GameModel.PIXEL_SIZE));
        fullFrame.setTitle("A Night In IV");
        fullFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fullFrame.pack();
        gGUI = new GameGUI();
        mGUI = new MenuGUI();
        currentSong = songDir + "mainmenu.mp3";
        Media song = new Media(new File(currentSong).toURI().toString());
        songPlayer = new MediaPlayer(song);
        songPlayer.setAutoPlay(true);
        Runnable songJob = () -> songPlayer.seek(Duration.ZERO);
        songPlayer.setOnEndOfMedia(songJob);
        songPlayer.play();
    }

    /**
     * Adds first menu (Start Menu) to main frame container
     */
    public void addMenuGUI() {
        fullFrame.add(MenuGUI.sMenu);
        fullFrame.validate();
        fullFrame.setVisible(true);
    }

    /**
     * Adds entire GameGUI to main frame container
     */
    public void addGameGUI() {
        fullFrame.add(gGUI.component);
        gGUI.component.validate();
        gGUI.component.repaint();
        fullFrame.revalidate();
        fullFrame.repaint();
    }

    public void modifySongPlayer(String lvl) {
        songPlayer.dispose();
        if(lvl.equals("level1")) {
            currentSong = songDir + "level1.mp3";
            Media song = new Media(new File(currentSong).toURI().toString());
            songPlayer = new MediaPlayer(song);
        }
        else if(lvl.equals("level2"))
        {
            currentSong = songDir + "level2.mp3";
            Media song = new Media(new File(currentSong).toURI().toString());
            songPlayer = new MediaPlayer(song);
        }
        else if(lvl.equals("mainmenu"))
        {
            currentSong = songDir + "mainmenu.mp3";
            Media song = new Media(new File(currentSong).toURI().toString());
            songPlayer = new MediaPlayer(song);
        }
        Runnable songJob = () -> songPlayer.seek(Duration.ZERO);
        songPlayer.setOnEndOfMedia(songJob);
        songPlayer.play();
    }

    /**
     * Handles what happens to the main frame container when user selects an option on EndLevelMenu
     * @param choice integer value returned from EndLevelMenu's Win or Lose dialogue
     * @see EndLevelMenu
     */
    public void modifyGameFrame(int choice) {
        fullFrame.remove(MenuGUI.eMenu);
        fullFrame.remove(gGUI.component);
        if(choice == MenuGUI.eMenu.MAIN_MENU) {
            fullFrame.add(MenuGUI.sMenu);
            atMenus = true;
            MenuGUI.currentMenu = MenuGUI.sMenu;
            modifySongPlayer("mainmenu");
        }
        else if(choice == MenuGUI.eMenu.RESTART) {
            gGUI.loadGame(sceneName);
            gGUI.component.validate();
            gGUI.component.repaint();
            fullFrame.add(gGUI.component);
            modifySongPlayer(sceneName);
        }
        else if(choice == MenuGUI.eMenu.NEXT_LEVEL) {
            if (gGUI.game.name.equals("level1")) {
                sceneName = "level2";
                blackoutOverTime = false;
                gGUI.loadGame(sceneName);
                gGUI.component.validate();
                gGUI.component.repaint();
                fullFrame.add(gGUI.component);
                modifySongPlayer(sceneName);
            }
        }
        else if(choice == MenuGUI.eMenu.QUIT_GAME)
        {
            gameRunning = false;
            songPlayer.stop();
            return;
        }
        fullFrame.revalidate();
        fullFrame.repaint();
    }

    /**
     * main game loop of entire project
     * allows GameGUI to be continuously redrawn smoothly
     */
    public void gameLoop() {
        gameRunning = true;
        addMenuGUI();
        atMenus = true;
        long lastLoopTime = System.nanoTime();
        final long NANOSEC_PER_FRAME = 1000000000 / 60;

        long lastFpsTime = 0;
        int fps = 0;
        EDTdraw draw = new EDTdraw();

        while(gameRunning)
        {
            //wait here for button press form EDT
            if(startGameGUI) {
                System.out.println("Start");
                gGUI.loadGame(sceneName);
                addGameGUI();
                modifySongPlayer(sceneName);
                startGameGUI = false;
                atMenus = false;
            }
            else if(atMenus){
                System.out.print("");
            }
            else if(gGUI.game.gameWon) {
                songPlayer.stop();
                int choice;
                fullFrame.add(MenuGUI.eMenu);
                if(sceneName.equals("level2"))
                {
                    choice = MenuGUI.eMenu.showFinalWinDialog(fullFrame);
                }
                else {
                    choice = MenuGUI.eMenu.showWinDialog(fullFrame);
                }
                gGUI.game.gameWon = false;
                modifyGameFrame(choice);
            }
            else if(gGUI.game.gameLost) {
                songPlayer.stop();
                fullFrame.add(MenuGUI.eMenu);
                int choice = MenuGUI.eMenu.showLoseDialog(fullFrame);
                gGUI.game.gameLost = false;
                modifyGameFrame(choice);
            }
            else {
                // work out how long its been since the last update, this
                // will be used to calculate how far the entities should
                // move this loop
                long now = System.nanoTime();
                long updateLength = now - lastLoopTime;
                lastLoopTime = now;
                double delta = updateLength / ((double) NANOSEC_PER_FRAME);

                // update the frame counter
                lastFpsTime += updateLength;
                fps++;

                // update our FPS counter if a second has passed since
                // we last recorded
                // also print out current blackout bar variable every second
                if (lastFpsTime >= 1000000000) {
                    System.out.println("(FPS: " + fps + ")");
                    System.out.println("Current blackout bar value: " + gGUI.game.getPlayer().getBlackout());
                    lastFpsTime = 0;
                    fps = 0;
                    if(blackoutOverTime) {
                        gGUI.game.player.modifyBlackout(4);
                    }
                }

                // update the game logic
                gGUI.game.getCurrentMap().update(delta);

                // draw everything
                try {
                    SwingUtilities.invokeAndWait(draw);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // we want each frame to take 10 milliseconds, to do this
                // we've recorded when we started the frame. We add 10 milliseconds
                // to this and then factor in the current time to give
                // us our final value to wait for
                // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
                try {
                    long millis = (lastLoopTime - System.nanoTime() + NANOSEC_PER_FRAME) / 1000000;
                    if (millis > 0)
                        Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // destroys the JFrame object and successfully allows for the program to end
        fullFrame.dispatchEvent(new WindowEvent(fullFrame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * inner class for main game loop
     * allows for repainting to be executed faster
     */
    class EDTdraw implements Runnable {
        @Override
        public void run() {
            gGUI.component.paintImmediately(0, 0, gGUI.game.mapWidth * GameModel.PIXEL_SIZE, gGUI.game.mapHeight * GameModel.PIXEL_SIZE);
        }
    }

    public static void main(String[] args) {
        RunGame fullGame = new RunGame();
        fullGame.gameLoop();
    }

}

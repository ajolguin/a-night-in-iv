package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


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
    static String sceneName;

    private RunGame() {
        fullFrame = new JFrame();
        fullFrame.getContentPane().setPreferredSize(new Dimension(24 * GameModel.PIXEL_SIZE, 18 * GameModel.PIXEL_SIZE));
        fullFrame.setTitle("A Night In IV");
        fullFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fullFrame.pack();
        gGUI = new GameGUI();
        mGUI = new MenuGUI();
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
        }
        else if(choice == MenuGUI.eMenu.RESTART) {
            gGUI.loadGame(sceneName);
            gGUI.component.validate();
            gGUI.component.repaint();
            fullFrame.add(gGUI.component);
        }
        else if(choice == MenuGUI.eMenu.NEXT_LEVEL) {
            if (gGUI.game.name == "level1") {
                gGUI.loadGame(sceneName);
                gGUI.component.validate();
                gGUI.component.repaint();
                fullFrame.add(gGUI.component);
            }
        }
        else if(choice == MenuGUI.eMenu.QUIT_GAME)
        {
            gameRunning = false;
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
                startGameGUI = false;
                atMenus = false;
            }
            else if(atMenus){
                System.out.print("");
            }
            else if(gGUI.game.gameWon) {
                fullFrame.add(MenuGUI.eMenu);
                int choice = MenuGUI.eMenu.showWinDialog(fullFrame);
                gGUI.game.gameWon = false;
                modifyGameFrame(choice);
            }
            else if(gGUI.game.gameLost) {
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
                    gGUI.game.player.modifyBlackout(5);
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

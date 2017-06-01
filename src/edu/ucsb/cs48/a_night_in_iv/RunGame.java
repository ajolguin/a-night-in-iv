package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.*;


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
        System.out.println("Got to addGameGUI");
        fullFrame.add(gGUI.component);
        gGUI.component.validate();
        gGUI.component.repaint();
        fullFrame.revalidate();
        fullFrame.repaint();
    }

    /**
     * Adds the end game menu for when the player completes a level
     */
    public void addWinGUI() {
        fullFrame.add(MenuGUI.wMenu);
        MenuGUI.wMenu.display(fullFrame);
        gGUI.game.winGame = false;
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
            if(startGameGUI && !atMenus) { addGameGUI(); }
            else if(gGUI.game.winGame) { addWinGUI(); }
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

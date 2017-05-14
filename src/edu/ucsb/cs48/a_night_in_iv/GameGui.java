package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;


/**
 * @author Alex Wood (UCSB CS56. W12. 02/16/2012)
 * @author Danielle Dodd and George Lieu
 * @author Lisa Liao and Patrick Vidican
 * @version for UCSB CS56, F16, 11/19/2016
 */

public class GameGui {
    static final String resourcesDir = "/resources/";
    JFrame frame;
    GameComponent component;
    GameModel game;

    public GameGui() {
        frame = new JFrame();
        component = new GameComponent();
        component.setOpaque(true);
        game = new GameModel("scene1");
        generateGenericItem(5, game, "COKE", 5);
        component.setGame(game);

        // Set the name and frame size
        frame.setSize(16 + 24 * game.PIXEL_SIZE, 40 + 18 * game.PIXEL_SIZE);
        frame.setTitle("A Night In IV");
        // Allows for game window to be closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addBindings();

        // adds game components and makes the window visible
        frame.add(component);
        frame.setVisible(true);
        component.validate();
        component.repaint();

        gameLoop();
    }

    public static void main(String[] args) {
        GameGui gui = new GameGui();
    }


    /**
     * assigns specific actions to occur based on user keyboard input
     * @see MoveAction
     */
    public void addBindings() {
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 0, 1), KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 0, -1), KeyStroke.getKeyStroke("UP"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), -1, 0), KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 1, 0), KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_FOCUSED);
    }

    /**
     * creates multiple items spawned randomly within the bounds of the current map
     * @param howMany the number of genericItems to create
     * @param game current game class running
     * @param textureID the sprite ID # associated with the item
     * @param boM the amount that affects the players blackout bar
     */
    public void generateGenericItem(int howMany, GameModel game, String textureID, int boM) {

        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * game.mapWidth);
                yTile = (int) (Math.random() * game.mapHeight);
            }while ( game.getCurrentMap().getSprite(yTile, xTile) != null );
            game.getCurrentMap().setSprite(new GenericItem(game.getTexture(textureID), boM, game.getCurrentMap()), yTile, xTile);
            System.out.println(yTile + " : " + xTile);
        }

    }

    /**
     * main game loop of entire project
     * allows system to be continuously redrawn smoothly
     */

    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        final long NANOSEC_PER_FRAME = 1000000000 / 60;
        boolean gameRunning = true;

        long lastFpsTime = 0;
        int fps = 0;
        EDTdraw draw = new EDTdraw();
        // keep looping round til the game ends
        while (gameRunning) {
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
            if (lastFpsTime >= 1000000000) {
                System.out.println("(FPS: " + fps + ")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            game.getCurrentMap().update(delta);

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

    /**
     * inner class for main game loop
     * allows for repainting to be executed faster
     */
    class EDTdraw implements Runnable {
        @Override
        public void run() {
            component.paintImmediately(0, 0, game.mapWidth * game.PIXEL_SIZE, game.mapHeight * game.PIXEL_SIZE);
        }
    }
}

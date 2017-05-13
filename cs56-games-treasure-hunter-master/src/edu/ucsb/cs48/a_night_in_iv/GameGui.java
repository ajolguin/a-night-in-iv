package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.image.BufferedImage;


/**
 * @author Alex Wood (UCSB CS56. W12. 02/16/2012)
 * @author Danielle Dodd and George Lieu
 * @author Lisa Liao and Patrick Vidican
 * @version for UCSB CS56, F16, 11/19/2016
 */

// all instances of Player as a treasure should be Treasure as a treasure
public class GameGui {
    JFrame frame;
    GameComponent component;
    GameModel game;
    public static final String resourcesDir = "/resources/";

    public GameGui() {
        frame = new JFrame();
        component = new GameComponent();
        component.setOpaque(true);
        game = new GameModel("scene1");
        game.setPlayer(new Player(3, 3, 16, 8, "player", game));
        component.setGame(game);

        // Set the name and frame size
        frame.setSize(16+24*GameComponent.PIXEL_SIZE, 40+18*GameComponent.PIXEL_SIZE);
        frame.setTitle("Treasure Hunter");
        // Allows for game window to be closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Randomly places 3 treasures on game map

        //this.generateRocks(12);
        //this.generateTreasures(5); // change the amount of treasures here

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


    /*
      addBindings takes in the user's keyboard input.

     */
    public void addBindings() {
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 0, 1), KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(),0, -1), KeyStroke.getKeyStroke("UP"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(),-1, 0), KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(),1, 0), KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_FOCUSED);
    }

    public void generateGenericSprite(int howMany, BufferedImage image) {
        if (howMany == 0) System.out.println("make at least one treasure!");

        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * 24);
                yTile = (int) (Math.random() * 18);
            }while ( game.getCurrentMap().getSprite(yTile, xTile) != null );
            game.getCurrentMap().setSprite(new GenericSprite(image), yTile, xTile);
            System.out.println(yTile + " : " + xTile);
        }

    }

    public void gameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final long NANOSEC_PER_FRAME = 1000000000 / 60;
        boolean gameRunning = true;

        long lastFpsTime = 0;
        int fps = 0;
        EDTdraw draw = new EDTdraw();
        // keep looping round til the game ends
        while (gameRunning)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)NANOSEC_PER_FRAME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            game.getCurrentMap().update(delta);

            // draw everyting
            try{
                SwingUtilities.invokeAndWait(draw);
            }catch (Exception e){
                e.printStackTrace();
            }

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try{
                long millis = (lastLoopTime-System.nanoTime() + NANOSEC_PER_FRAME)/1000000;
                if(millis > 0)
                Thread.sleep( millis );
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    class EDTdraw implements Runnable {
        @Override
        public void run(){
            component.paintImmediately(0,0, game.mapWidth * component.PIXEL_SIZE, game.mapHeight * component.PIXEL_SIZE);
        }
    }
}

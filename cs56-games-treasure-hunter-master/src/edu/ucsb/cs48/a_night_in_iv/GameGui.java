package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
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
        game = new GameModel("scene1");
        game.setPlayer(new Player(3, 3, 16, 8, "player"));
        component.setGame(game);

        // Set the name and frame size
        frame.setSize(16+12*GameComponent.PIXEL_SIZE, 40+9*GameComponent.PIXEL_SIZE);
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
    }

    public static void main(String[] args) {
        GameGui gui = new GameGui();
    }


    /*
      addBindings takes in the user's keyboard input.

     */
    public void addBindings() {
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(new MoveAction(game, component, 0, 1), KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game, component, 0, -1), KeyStroke.getKeyStroke("UP"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game, component, -1, 0), KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(game, component, 1, 0), KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_FOCUSED);
    }

    public void generateGenericSprite(int howMany, BufferedImage image) {
        if (howMany == 0) System.out.println("make at least one treasure!");

        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * 12);
                yTile = (int) (Math.random() * 9);
            }while ( game.getCurrentMap().getSprite(yTile, xTile) != null );
            game.getCurrentMap().setSprite(new GenericSprite(image), yTile, xTile);
            System.out.println(yTile + " : " + xTile);
        }

    }
}

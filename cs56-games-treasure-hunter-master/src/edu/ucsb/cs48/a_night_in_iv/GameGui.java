package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.lang.management.PlatformLoggingMXBean;


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
        game.setPlayer(new Player(0, 0, 16, 8, "player"));
        component.setGame(game);

        // Set the name and frame size
        frame.setSize(16+12*GameComponent.PIXEL_SIZE, 40+9*GameComponent.PIXEL_SIZE);
        frame.setTitle("Treasure Hunter");
        // Allows for game window to be closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Randomly places 3 treasures on game map

        this.generateRocks(12);
        this.generateTreasures(5); // change the amount of treasures here

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


    class MoveAction extends AbstractAction {
        int startingSprite = 0;
        int x = 0;
        int y = 0;

        /*
          The MoveAction method changes the player png file so that it appears he turns in the direction the user wants him to walk in.
         */
        public MoveAction(int x, int y) {
            if (x == -1)
                startingSprite = 4;
            if (x == 1)
                startingSprite = 8;
            if (y == -1)
                startingSprite = 12;
            if (y == 1)
                startingSprite = 0;
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Player player = game.getPlayer();
            player.setSprite(startingSprite);

            int xTile = player.getXTile() + x;
            int yTile = player.getYTile() + y;
            /*
               Ensures that the player does not go outside the bounds of the map (0-11 by 0-9).
               If the player is standing on the same tile as a treasure, then the
               message variable will change which makes the "TREASURE # FOUND"
               message box appear
            */
            //limits where the player can move (ie. can move out of the box)
            if (xTile < 0 || xTile > 11 || yTile < 0 || yTile > 8){
                MapSection newMap = game.getMapInDirection(y, x);
                if(newMap != null){
                    game.moveMapInDirection(y, x);
                    int r = (player.getXTile()+x) % game.mapWidth;
                    if (r < 0) r += game.mapWidth;
                    int s = (player.getYTile()+y) % game.mapHeight;
                    if (s < 0) s += game.mapHeight;
                    player.setTiles(s,r);
                    player.moveTo(s * GameComponent.PIXEL_SIZE, r * GameComponent.PIXEL_SIZE);
                }
                player.setMovable(false);
            }
            else if (game.getCurrentMap().getSprite(yTile, xTile) != null)
                player.setMovable(false);
            else if (player.getXPos() != player.getXTile() * GameComponent.PIXEL_SIZE || player.getYPos() != player.getYTile() * GameComponent.PIXEL_SIZE)
                player.setMovable(false);
            else
                player.setMovable(true);


            if (player.isMovable()) {
                //player.setMovable(false);
                for (int i = 0; i < GameComponent.PIXEL_SIZE; i++) {
                    player.moveTo(player.getYPos() + y, player.getXPos() + x);
                    if (x != 0 || y != 0)
                        player.setSprite(startingSprite + i / 10);
                    if (player.getCurrentSpriteIndex() >= startingSprite + 4 && (x != 0 || y != 0))
                        player.setSprite(startingSprite);
                    component.updatePlayer();
                    try {
                        Thread.sleep(2);
                    } catch (Exception ex) {
                    }
                }
                player.setTiles(player.getYTile() + y, player.getXTile() + x);
            }
            component.validate();
            component.repaint();
        }

    }

    /*
      addBindings takes in the user's keyboard input.

     */
    public void addBindings() {
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(new MoveAction(0, 1), KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(0, -1), KeyStroke.getKeyStroke("UP"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(-1, 0), KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(new MoveAction(1, 0), KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_FOCUSED);
    }

    public void generateTreasures(int howMany) {
        if (howMany == 0) System.out.println("make at least one treasure!");

        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * 12);
                yTile = (int) (Math.random() * 9);
            }while ( game.getCurrentMap().getSprite(yTile, xTile) != null );
            game.getCurrentMap().setSprite(new Treasure("treasure" + i, yTile, xTile), yTile, xTile);
            System.out.println(yTile + " : " + xTile);
        }

    }
    public void generateRocks(int howMany) {
        if (howMany == 0) System.out.println("make at least one treasure!");

        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * 12);
                yTile = (int) (Math.random() * 9);
            }while ( game.getCurrentMap().getSprite(yTile, xTile) != null );
            game.getCurrentMap().setSprite(new Rock(yTile, xTile), yTile, xTile);
            System.out.println(yTile + " : " + xTile);
        }

    }
}

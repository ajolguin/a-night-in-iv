package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;

/**
 * Main class that of game
 * Constructs all necessary components for the entire game in specific order.
 * 1st: Game model is made. 2nd: Game component is made. 3rd: Game model is added to game component.
 *
 * Resources folder contains all sprites and data used.
 *
 * Built upon by authors below
 *
 * @author Karl Wang, Matthew Rodriguez, Armin Mahini, Cristobal Caballero, Adrian Olguin , Greg Whitier (CS48)
 * @author Alex Wood (for CS56, W12, UCSB, 2/16/2012)
 * @author Danielle Dodd and George Lieu (CS56)
 * @author Lisa Liao and Patrick Vidican (CS56)
 * @version CS48
 * @see GameComponent
 * @see GameModel
 */


public class GameGUI {
    static final String resourcesDir = "/resources/";
    GameModel game;
    GameComponent component;
    public GameGUI() {
        //frame = new JFrame();
        game = new GameModel("gameData");
        generateGenericItem(5, game, 1, 0, "COKE", 5);
        generateGenericItem(2, game, 0, 0, "MUSHROOM", 20);
        generateWinItem(game, 0, 0, "TROPHY");
        component = new GameComponent();
        component.setOpaque(true);
        component.setGame(game);
        addPlayerMovementBindings();
    }

    /**
     * creates multiple items spawned randomly within the bounds of the map at specified Y and X coordinate within section[][]
     * @param howMany the number of genericItems to create
     * @param game current game class running
     * @param sceneY corresponding Y coordinate for map
     * @param sceneX corresponding X coordinate for map
     * @param textureID the sprite ID # associated with the item
     * @param boM the amount that affects the players blackout bar
     * @see MapSection for how section[][] is constructed
     */
    private void generateGenericItem(int howMany, GameModel game, int sceneY, int sceneX, String textureID, int boM) {
        MapSection genericItemMapSect = game.getMapInDirection(sceneY, sceneX);
        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * game.mapWidth);
                yTile = (int) (Math.random() * game.mapHeight);
            }while ( genericItemMapSect.getSprite(yTile, xTile) != null );
            genericItemMapSect.setSprite(new GenericItem(game.getTexture(textureID), boM, genericItemMapSect), yTile, xTile);
            System.out.println("Item " + textureID + " placed at: (" + yTile + "," + xTile + ") Y/X Coordinate");
        }
    }

    private void generateWinItem(GameModel game, int sceneY, int sceneX, String textureID){
        MapSection genericItemMapSect = game.getMapInDirection(sceneY, sceneX);
        int xTile, yTile;
        do {
            xTile = (int) .5 * game.mapWidth;
            yTile = (int) .5 * game.mapHeight;
        }while ( genericItemMapSect.getSprite(yTile, xTile) != null );
        genericItemMapSect.setSprite(new GenericItem(game.getTexture(textureID), 0, genericItemMapSect), yTile, xTile);
        System.out.println("Item " + textureID + " placed at: (" + yTile + "," + xTile + ") Y/X Coordinate");
    }

    /**
     * adds keyboard binds to player based on X/Y tile increments using keyboard arrow keys
     * @see MoveAction
     */
    private void addPlayerMovementBindings() {
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 0, 1), KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 0, -1), KeyStroke.getKeyStroke("UP"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), -1, 0), KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(new MoveAction(game.getPlayer(), 1, 0), KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}

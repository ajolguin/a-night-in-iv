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

    MoveAction moveActionDown, moveActionUp, moveActionLeft, moveActionRight;

    public GameGUI() {
        component = new GameComponent();
        component.setOpaque(true);
        addPlayerMovementBindings();
    }

    public GameModel loadGame(String sceneName){
        game = generateGameModel(sceneName);
        component.setGame(game);
        updateBindings(game.getPlayer());
        return game;
    }

    public GameModel generateGameModel(String sceneName){
        GameModel newGame = new GameModel(sceneName);
        newGame.setPlayer(new Player( 3, 3, 16, 8, "player", newGame , newGame.getCurrentMap() ));
        generateGenericItem(5, newGame, 0, 1, "COKE", -5);
        generateGenericItem(5, newGame, 0, 0, "MUSHROOM", 20);
        generateWinItem(newGame, 0, 7, "T");
        generateRingItem(newGame, 0, 3, "R");
        return newGame;
    }

    public void updateBindings(Player player) {
        moveActionDown.setPlayer(player);
        moveActionUp.setPlayer(player);
        moveActionLeft.setPlayer(player);
        moveActionRight.setPlayer(player);
    }

    private void generateWinItem(GameModel game, int sceneY, int sceneX, String textureID){
        MapSection genericItemMapSect = game.getMapInDirection(sceneY, sceneX);
        int xTile, yTile;
        xTile = 23;//(int)(.5 * game.mapWidth);
        for(int i = 3; i < 15; i++) {
            yTile = i;
            genericItemMapSect.setSprite(new WinItem(game.getTexture(textureID), genericItemMapSect), yTile, xTile);
            System.out.println("Item " + textureID + " placed at: (" + yTile + "," + xTile + ") Y/X Coordinate");
        }
    }

    private void generateRingItem(GameModel game, int sceneY, int sceneX, String textureID){
        MapSection genericItemMapSect = game.getMapInDirection(sceneY, sceneX);
        int xTile, yTile;
        xTile = 13;//(int)(.5 * game.mapWidth);
        yTile = 0; //(int)(.5 * game.mapHeight);
        genericItemMapSect.setSprite(new RingItem(game.getTexture(textureID), genericItemMapSect), yTile, xTile);
        System.out.println("Item " + textureID + " placed at: (" + yTile + "," + xTile + ") Y/X Coordinate");
    }

    /**
     * adds keyboard binds to player based on X/Y tile increments using keyboard arrow keys
     * @see MoveAction
     */
    private void addPlayerMovementBindings() {
        moveActionDown   = new MoveAction(0, 1);
        moveActionUp     = new MoveAction(0, -1);
        moveActionLeft   = new MoveAction(-1, 0);
        moveActionRight  = new MoveAction(1, 0);

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(moveActionDown,    KeyStroke.getKeyStroke("DOWN"),     JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionUp,      KeyStroke.getKeyStroke("UP"),       JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionLeft,    KeyStroke.getKeyStroke("LEFT"),     JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionRight,   KeyStroke.getKeyStroke("RIGHT"),    JComponent.WHEN_IN_FOCUSED_WINDOW);
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
    static public void generateGenericItem(int howMany, GameModel game, int sceneY, int sceneX, String textureID, int boM) {
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
}

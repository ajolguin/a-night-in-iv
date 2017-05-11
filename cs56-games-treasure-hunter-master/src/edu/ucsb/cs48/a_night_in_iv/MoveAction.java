package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by kovlv on 5/7/2017.
 */
class MoveAction extends AbstractAction {
    private GameModel game;
    private GameComponent component;
    int startingSprite = 0;
    int x = 0;
    int y = 0;

    /*
      The MoveAction method changes the player png file so that it appears he turns in the direction the user wants him to walk in.
     */
    public MoveAction(GameModel game, GameComponent component, int x, int y) {
        this.game = game;
        this.component = component;
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
        if (xTile < 0 || xTile > 23 || yTile < 0 || yTile > 17) {
            MapSection newMap = game.getMapInDirection(y, x);
            if (newMap != null) {
                game.moveMapInDirection(y, x);
                int r = (player.getXTile() + x) % game.mapWidth;
                if (r < 0) r += game.mapWidth;
                int s = (player.getYTile() + y) % game.mapHeight;
                if (s < 0) s += game.mapHeight;
                player.setTiles(s, r);
                player.moveTo(s * component.PIXEL_SIZE, r * component.PIXEL_SIZE);
            }
            player.setMovable(false);
        } else if (game.getCurrentMap().getSprite(yTile, xTile) != null)
            player.setMovable(false);
        else if (player.getXPos() != player.getXTile() * component.PIXEL_SIZE || player.getYPos() != player.getYTile() * component.PIXEL_SIZE)
            player.setMovable(false);
        else
            player.setMovable(true);


        if (player.isMovable()) {
            //player.setMovable(false);
            for (int i = 0; i < component.PIXEL_SIZE; i++) {
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

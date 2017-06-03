package edu.ucsb.cs48.a_night_in_iv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * Main class that that draws the map for the game
 * Built upon by authors below
 *
 * @author Karl Wang, Matthew Rodriguez, Armin Mahini, Cristobal Caballero, Adrian Olguin (CS48)
 * @author Alex Wood (for CS56, W12, UCSB, 2/16/2012)
 * @author Danielle Dodd and George Lieu (CS56)
 * @author Lisa Liao and Patrick Vidican (CS56)
 * @version CS48
 */


public class GameComponent extends JComponent {

    GameModel game;

    /* RepaintManager currently unused, but could be useful later on
    //RepaintManager paintManager = RepaintManager.currentManager(this);

    /**
     * Ties the GameModel with the GameComponent
     * @param game
     * The GameModel that needs to be connected to the map drawn by GameComponent
     * @see GameModel
     */
    public void setGame(GameModel game) {
        this.game = game;
    }

    /**
     * Overrided paintComponent method from JComponent
     * Draws the map layers (Terrain and Sprite) based on the map)
     * @param g
     * @see GameModel
     */
    @Override
    public void paintComponent(Graphics g) {
        MapSection map = game.getCurrentMap();

        // probably draws the tiles
        for (int h = 0; h < map.height; h++)
            for (int w = 0; w < map.width; w++)
                g.drawImage(map.getTerrain(h, w), w * game.PIXEL_SIZE, h * game.PIXEL_SIZE, null);

        for (int h = 0; h < map.height; h++)
            for (int w = 0; w < map.width; w++)
                if (map.getSprite(h, w) != null)
                    g.drawImage(map.getSprite(h, w).getImage(), w * game.PIXEL_SIZE, h * game.PIXEL_SIZE, null);

        Player player = game.getPlayer();
        g.drawImage(player.getPlayerImage(), player.getxPos(), player.getyPos(), null);

        Graphics2D g2 = (Graphics2D) g;

        //draw text box when player wins game (ie when message is the win print statement)
        if( !game.message.equals("")) {
            g2.setColor(new Color(1f,0f,0f,.5f));
            g2.fill(new Rectangle(100,0,600,80));
            g2.setFont(new Font(null,Font.BOLD, 20));
            g2.setColor(Color.YELLOW);
            g2.drawString(game.message, 110, 50);
        }

        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle(230, 546, 308, 23));

        if(game.player.getBlackout() < 50) {
            g2.setColor(Color.GREEN);
            g2.fill(new Rectangle(234,550,3*game.player.getBlackout(),15));
        }else if(game.player.getBlackout() >= 50 && game.player.getBlackout() < 75){
            g2.setColor(Color.YELLOW);
            g2.fill(new Rectangle(234,550,3*game.player.getBlackout(),15));
        }else if(game.player.getBlackout() >= 75){
            g2. setColor(Color.RED);
            g2.fill(new Rectangle(234,550,3*game.player.getBlackout(),15));
        }
        g2.setFont(new Font("Comic Sans", Font.BOLD,13));
        g2.setColor(new Color(138,43,226));
        g2.drawString("B L A C K O U T   B A R",320,562);

    }

    /* Currently unused but might be in the future
     //Draws the player sprite onto a new tile
    public void updatePlayer() {
        paintManager.addDirtyRegion(this, game.getPlayer().getXPos() - 10, game.getPlayer().getYPos() - 10, 60, 60);
    }
    */

}
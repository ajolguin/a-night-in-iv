package edu.ucsb.cs48.a_night_in_iv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * A component that draws the map for the treasure hunter game by Alex Wood
 * Edited by Danielle Dodd and George Lieu
 * Edited by Lisa Liao and Patrick Vidican
 *
 * @author Alex Wood (for CS56, W12, UCSB, 2/16/2012)
 * @author Danielle Dodd and George Lieu
 * @author Lisa Liao and Patrick Vidican
 * @version for UCSB CS56, F16, 11/19/2016
 */


public class GameComponent extends JComponent {

    public void setGame(GameModel game) {
        this.game = game;
    }

    GameModel game;

    public static final int PIXEL_SIZE = 32;

    public String message = "";


    /*
      paintComponent: It draws all of the tiles on the map. Also loads the player sprite.
      When player find the treasure, the message variable value changes and the "TREASURE # FOUND" message box is drawn onto the screen.

    */
    @Override
    public void paintComponent(Graphics g) {
        MapSection map = game.getCurrentMap();

        // probably draws the tiles
        for (int h = 0; h < map.height; h++)
            for (int w = 0; w < map.width; w++)
                g.drawImage(map.getTerrain(h, w), w * PIXEL_SIZE, h * PIXEL_SIZE, null);

        for (int h = 0; h < map.height; h++)
            for (int w = 0; w < map.width; w++)
                if ( map.getSprite(h,w) != null)
                    g.drawImage(map.getSprite(h,w).getImage(), w * PIXEL_SIZE, h * PIXEL_SIZE, null);

        for (int h = 0; h < map.height; h++)
            for (int w = 0; w < map.width; w++)
                if ( map.getItem(h,w) != null)
                    g.drawImage(map.getItem(h,w).getImage(), w * PIXEL_SIZE, h * PIXEL_SIZE, null);

        Player player = game.getPlayer();
        g.drawImage(player.getImage(), player.getXPos(), player.getYPos(), null);

        Graphics2D g2 = (Graphics2D) g;
        if (!message.equals("")) {
            g2.setColor(new Color(1f, 0f, 0f, .5f));
            g2.fill(new Rectangle(100, 0, 250, 100));
            g2.setFont(new Font(null, Font.BOLD, 20));
            g2.setColor(Color.BLACK);
            g2.drawString(message, 110, 50);
        }

    }

    /* Draws the player sprite onto a new tile */
    public void updatePlayer() {
        paintImmediately(game.getPlayer().getXPos() - 10, game.getPlayer().getYPos() - 10, 100, 100);
    }



    /* changes the message instance variable
     */
    public void setMessage(int treasure_number) {
        message = "TREASURE " + treasure_number + " FOUND!";
        new Thread(new MessageThread(this)).start();
    }

    /* changes the message with final
     */
    public void setMessageFinal(boolean answer) {
        if (answer) {
            message = "YOU WIN!";
            new Thread(new MessageThread(this)).start();
            //TODO: fix the problem of the time a message box appear
            //TODO: find a way to pause the game
        }
    }

}

package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Karl Wang (kovlv) on 5/7/2017.
 * Class that represents the movement of the player class
 * @see Player
 */
public class MoveAction extends AbstractAction {
    Player player;
    int x = 0;
    int y = 0;

    /**
     * Changes the players PNG sprite file based on the corresponding increment in X/Y
     * Sprite corresponds to direction the user wants the Player to walk in
     * @param player
     * @param x
     * @param y
     */
    public MoveAction(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!player.isMoving())
            player.moveInDirection(y, x);
    }

}
package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by kovlv on 5/7/2017.
 */
public class MoveAction extends AbstractAction {
    Player player;
    int x = 0;
    int y = 0;

    /*
      The MoveAction method changes the player png file so that it appears he turns in the direction the user wants him to walk in.
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
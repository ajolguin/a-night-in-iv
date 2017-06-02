package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;

/**
 * Created by Matthew R on 5/28/2017.
 * GUI menu component for when player wins
 */
public class EndLevelMenu extends JOptionPane {

    public EndLevelMenu(){
        super();
    }

    public void showWinDialog(JFrame parent){
        String[] options = {"Return to Main Menu", "Restart level", "Next level"};
        this.showOptionDialog(parent,
                "You've survived the perils of IV.",
                "", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
    }

    public void showLoseDialog(JFrame parent){
        String[] options = {"Return to Main Menu", "Restart level"};
        this.showOptionDialog(parent,
                "YOU BOOZE, YOU LOSE!!!",
                "", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
    }
}

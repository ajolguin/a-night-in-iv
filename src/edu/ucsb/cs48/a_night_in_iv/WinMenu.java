package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;

/**
 * Created by Matthew R on 5/28/2017.
 * GUI menu component for when player wins
 */
public class WinMenu extends JOptionPane {

    Object[] options = {"Return to Main Menu", "Restart level", "Quit Game"};
    String message;
    String title;
    public WinMenu()
    {
        title = "";
        message = "You've survived the perils of IV.";
    }

    public void display(JFrame parent){
        this.showOptionDialog(parent,
                message, title, JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[2]);
    }
}

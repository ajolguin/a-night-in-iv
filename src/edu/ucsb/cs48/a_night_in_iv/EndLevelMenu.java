package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew R on 5/28/2017.
 * GUI menu component for when player wins
 */
public class EndLevelMenu extends JOptionPane {

    public final int MAIN_MENU = 0;
    public final int RESTART = 1;
    public final int NEXT_LEVEL = 2;
    public final int QUIT_GAME = 3;

    public final List<String> ol = Arrays.asList("Return to Main Menu", "Restart level", "Next level", "Quit game");

    public EndLevelMenu(){
        super();
    }

    public int showWinDialog(JFrame parent){
        String[] options = {ol.get(0), ol.get(1), ol.get(2), ol.get(3)};
        int selected_option = showOptionDialog(parent,
                "You've survived the perils of IV.                                   SWAG",
                "", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
        return ol.indexOf(options[selected_option]);
    }

    public int showLoseDialog(JFrame parent){
        String[] options = {ol.get(0), ol.get(1), ol.get(3)};
        int selected_option = showOptionDialog(parent,
                "YOU BOOZE, YOU LOSE!!!",
                "", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
        return ol.indexOf(options[selected_option]);
    }
}

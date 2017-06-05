package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.*;

/**
 * GUI component representing the Help menu that appears when the user clicks
 * on the help button on the start menu screen
 * Created by Matthew R on 6/5/2017.
 * @see StartMenu
 */
public class HelpMenu extends JPanel{

    public HelpMenu() {
        //Adding image as background
        BackgroundPanel background = new BackgroundPanel("./src/resources/help.png");
        this.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);
        //add buttons2
        MenuButton backButton = new MenuButton(new BackgroundPanel("./src/resources/buttons2/BackButton1.jpg"), new BackgroundPanel("./src/resources/buttons2/BackButton2.jpg"), new BackgroundPanel("./src/resources/buttons2/BackButton3.jpg"));
        backButton.addActionListener(e -> MenuGUI.backToStart());
        JPanel list = new JPanel();
        list.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 20, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        list.add(backButton, gbc);
        list.setOpaque(false);

        background.setLayout(new BorderLayout());
        background.add(list, BorderLayout.SOUTH);
    }
}

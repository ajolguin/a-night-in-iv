package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelMenu extends JPanel {

    public LevelMenu() {
        //Adding image as background
        BackgroundPanel background = new BackgroundPanel("./src/resources/background.png");
        this.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);
        //add buttons
        MenuButton backButton = new MenuButton(new BackgroundPanel("./src/resources/buttons/BackButton1.jpg"),new BackgroundPanel("./src/resources/buttons/BackButton2.jpg"),new BackgroundPanel("./src/resources/buttons/BackButton3.jpg") );
        backButton.addActionListener(e -> MenuGUI.backToStart());
        MenuButton level1Button = new MenuButton(new BackgroundPanel("./src/resources/buttons/Level1Button1.jpg"),new BackgroundPanel("./src/resources/buttons/Level1Button2.jpg"),new BackgroundPanel("./src/resources/buttons/Level1Button3.jpg") );
        level1Button.addActionListener(e -> MenuGUI.exitMenus("level1"));
        MenuButton level2Button = new MenuButton(new BackgroundPanel("./src/resources/buttons/Level2Button1.jpg"),new BackgroundPanel("./src/resources/buttons/Level2Button2.jpg"),new BackgroundPanel("./src/resources/buttons/Level2Button3.jpg") );
        level2Button.addActionListener(e -> MenuGUI.exitMenus("level2"));
        JPanel list = new JPanel();
        list.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 0, 0, 0);
        gbc.fill = gbc.NONE;
        list.add(backButton, gbc);
        list.add(level1Button, gbc);
        list.add(level2Button, gbc);
        list.setOpaque(false);

        background.setLayout(new BorderLayout());
        background.add(list, BorderLayout.CENTER);
    }

    //code from http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingFrameswithabackgroundimage.htm
    //for giving the JFrame a background image
}


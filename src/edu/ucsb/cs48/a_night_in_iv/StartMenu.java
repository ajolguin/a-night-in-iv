package edu.ucsb.cs48.a_night_in_iv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JPanel{
	
	public StartMenu(){
		//Adding image as background
		BackgroundPanel background = new BackgroundPanel("./src/resources/background.png");
		this.setLayout(new BorderLayout());
		this.add(background, BorderLayout.CENTER);
		//add buttons
		MenuButton levelSelectButton = new MenuButton(new BackgroundPanel("./src/resources/buttons/LevelSelectButton1.jpg"),new BackgroundPanel("./src/resources/buttons/LevelSelectButton2.jpg"),new BackgroundPanel("./src/resources/buttons/LevelSelectButton3.jpg"));
		levelSelectButton.addActionListener( e -> MenuGUI.openLevelSelectMenu() );
		MenuButton helpMenuButton = new MenuButton(new BackgroundPanel("./src/resources/buttons/HelpButton1.jpg"),new BackgroundPanel("./src/resources/buttons/HelpButton2.jpg"),new BackgroundPanel("./src/resources/buttons/HelpButton3.jpg") );
		helpMenuButton.addActionListener( e -> {} );
		JPanel list = new JPanel();
		list.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(50, 0, 0, 0);
		gbc.fill = gbc.NONE;
		list.add(levelSelectButton, gbc);
		list.add(helpMenuButton, gbc);
		list.setOpaque(false);
		
		background.setLayout(new BorderLayout());
		background.add(list, BorderLayout.CENTER);
	}

}


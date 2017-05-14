package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelMenu extends JPanel{
	
	public LevelMenu(){
		//Adding image as background
		BackgroundPanel background = new BackgroundPanel();
		this.setLayout(new BorderLayout());
		this.add(background, BorderLayout.CENTER);
		//add buttons
		JButton level1Button = new JButton("Level 1");
		level1Button.addActionListener(new level1ButtonHandler());
		JButton level2Button = new JButton("Level 2");
		level2Button.addActionListener(new level2ButtonHandler());
		JPanel list = new JPanel();
		list.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(50, 0, 0, 0);
		gbc.fill = gbc.NONE;
		list.add(level1Button, gbc);
		list.add(level2Button, gbc);
		list.setOpaque(false);
		
		background.setLayout(new BorderLayout());
		background.add(list, BorderLayout.CENTER);		
		
				
	}
	
	//code from http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingFrameswithabackgroundimage.htm
	//for giving the JFrame a background image
}
class level1ButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		return;
	}
}

class level2ButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		return;
	}
}


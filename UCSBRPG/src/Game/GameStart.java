import javax.swing.*;
import java.awt.*;

public class GameStart{
	static JFrame game = new JFrame("UCSB RPG");
	static StartMenu startMenu = new StartMenu();
	//for use when these menus are created
	static LevelMenu levelSelect = new LevelMenu();
	//HelpMenu helpMenu = new HelpMenu();
	
	public static void main(String[] args){
			
		game.setSize(600,500);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLayout(new BorderLayout());
		game.add(startMenu, BorderLayout.CENTER);
		game.setVisible(true);
	}
	
	public static void openLevelSelectMenu(){
		game.remove(startMenu);
		game.add(levelSelect, BorderLayout.CENTER);
		game.revalidate();
		game.repaint();
	}
	/*
	public static void openHelpMenu(){
		game.remove(startMenu);
		game.add(helpMenu, BorderLayout.CENTER);
	}
	*/
}
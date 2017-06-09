package edu.ucsb.cs48.a_night_in_iv;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;

/**
 * Represents the entire GUI of all Menus
 * Modifies the frame of RunGame statically depending on button input
 * @see StartMenu
 * @see LevelMenu
 * @see RunGame
 *
 * Created by Matthew R (solipsism648) on 5/14/2017.
 */
public class MenuGUI {
    public static StartMenu sMenu;
    public static LevelMenu lvlMenu;
    public static HelpMenu hMenu;
    public static EndLevelMenu eMenu;
    public static JPanel currentMenu;

    public MenuGUI() {
        sMenu = new StartMenu();
        lvlMenu = new LevelMenu();
        eMenu = new EndLevelMenu();
        hMenu = new HelpMenu();
        currentMenu = sMenu;
    }

    public static void openLevelSelectMenu(){
        RunGame.fullFrame.remove(sMenu);
        RunGame.fullFrame.add(lvlMenu);
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
        currentMenu = lvlMenu;
    }

    public static void openHelpMenu() {
        RunGame.fullFrame.remove(sMenu);
        RunGame.fullFrame.add(hMenu);
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
        currentMenu = hMenu;
    }

    public static void backToStart() {
        RunGame.fullFrame.remove(currentMenu);
        RunGame.fullFrame.add(sMenu);
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
        currentMenu = sMenu;
    }

    public static void exitMenus(String sceneName) {
        RunGame.sceneName = sceneName;
        RunGame.fullFrame.getContentPane().removeAll();
        if(sceneName.equals("level2")){
            RunGame.blackoutOverTime = false;
        }
        if(!sceneName.equals("level2")){
            RunGame.blackoutOverTime = true;
        }
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
        RunGame.startGameGUI = true;
    }
}

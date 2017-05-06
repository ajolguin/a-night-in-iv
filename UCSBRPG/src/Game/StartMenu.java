import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JPanel{
	
	public StartMenu(){
		//Adding image as background
		ContentPanel background = new ContentPanel();
		this.setLayout(new BorderLayout());
		this.add(background, BorderLayout.CENTER);
		//add buttons
		JButton levelSelectButton = new JButton("Level Select");
		levelSelectButton.addActionListener(new levelSelectButtonHandler());
		JButton helpMenuButton = new JButton("Help");
		helpMenuButton.addActionListener(new helpMenuButtonHandler());
		
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
	
	//code from http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingFrameswithabackgroundimage.htm
	//for giving the JFrame a background image
static class ContentPanel extends JPanel {
  Image bgimage = null;
		
 ContentPanel() {
    MediaTracker mt = new MediaTracker(this); 
	bgimage = Toolkit.getDefaultToolkit().getImage("start.jpg");			
    mt.addImage(bgimage, 0);
    try {
      mt.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
	
}
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int imwidth = bgimage.getWidth(null);
    int imheight = bgimage.getHeight(null);
    g.drawImage(bgimage, 1, 1, null);
  }
}
}

class levelSelectButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		GameStart.openLevelSelectMenu();
	}
}

class helpMenuButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		//GameStart.openHelpMenu();
	}
}


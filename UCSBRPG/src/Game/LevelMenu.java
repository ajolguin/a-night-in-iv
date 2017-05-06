import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelMenu extends JPanel{
	
	public LevelMenu(){
		//Adding image as background
		ContentPanel background = new ContentPanel();
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


import javax.swing.*;
import java.awt.*;
/*Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/

//ActionListener probably does nothing yet, it is here in preparation for when we have working buttons
public class StartMenu{// implements ActionListener{
	//Should this stuff go in a "public go(){}" class?
	public static void main(String[] args){
		JFrame startMenu = new JFrame("UCSB-RPG");
		startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Adding image as background
		ContentPanel background = new ContentPanel();
		startMenu.add(background);
		startMenu.setSize(600, 500);
		//add buttons
		JButton levelSelectButton = new JButton("Play Game");
		levelSelectButton.setSize(60, 40);
		JButton helpMenuButton = new JButton("Help");
		helpMenuButton.setSize(60,40);
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		list.add(levelSelectButton);
		list.add(helpMenuButton);
		list.setOpaque(false);
		
		background.setLayout(new BorderLayout());
		background.add(list, BorderLayout.CENTER);		
		startMenu.setVisible(true);
				
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


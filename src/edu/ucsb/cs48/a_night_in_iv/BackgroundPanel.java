package edu.ucsb.cs48.a_night_in_iv;
import javax.swing.*;
import java.awt.*;
import java.lang.String;
	//code from http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingFrameswithabackgroundimage.htm
	//for giving the JFrame a background image
public class BackgroundPanel extends JPanel {
  Image bgimage = null;
		
 public BackgroundPanel(String image) {
    MediaTracker mt = new MediaTracker(this); 
	bgimage = Toolkit.getDefaultToolkit().getImage(image);
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

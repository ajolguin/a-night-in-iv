package edu.ucsb.cs48.a_night_in_iv;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.lang.String;
//code borrowed from https://github.com/kdeloach/labs/blob/master/java/yahtzee/src/Dice.java
public class MenuButton extends JComponent implements MouseListener {
	private Dimension size = new Dimension(100, 70);
	private BackgroundPanel defaultImage, mouseOverImage, clickImage, currentImage;
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();;
	
	public MenuButton(BackgroundPanel defaultImage, BackgroundPanel mouseOverImage, BackgroundPanel clickImage){
		super();
		this.setLayout(new BorderLayout());
		this.defaultImage = defaultImage;
		this.mouseOverImage = mouseOverImage;
		this.clickImage = clickImage;
		
		this.add(defaultImage, BorderLayout.CENTER);
		this.setOpaque(false);
		enableInputMethods(true);
		addMouseListener(this);
		setSize(size.width, size.height);
		
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
		this.remove(defaultImage);
		this.add(mouseOverImage, BorderLayout.CENTER);	
		revalidate();
		repaint();
	}
	public void mouseExited(MouseEvent e){
		this.remove(mouseOverImage);
		this.add(defaultImage, BorderLayout.CENTER);
		repaint();
	}
	public void mousePressed(MouseEvent e){

		this.remove(mouseOverImage);
		this.add(clickImage);
		revalidate();
		repaint();
	}
	public void mouseReleased(MouseEvent e){
		notifyListeners(e);
		this.remove(clickImage);
		this.add(defaultImage);
		revalidate();
		repaint();
	}
	public void addActionListener(ActionListener listener){
		listeners.add(listener);
	}
	private void notifyListeners(MouseEvent e){
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(), e.getWhen(), e.getModifiers());
		synchronized(listeners)
        {
            for (int i = 0; i < listeners.size(); i++)
            {
                ActionListener tmp = listeners.get(i);
                tmp.actionPerformed(event);
            }
        }
	}
	/*public void setSize(int width, int height){
		this.size = new Dimension(width, height);
	}*/
	public Dimension getPreferredSize(){
		return new Dimension(getWidth(), getHeight());
	}
	public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
}
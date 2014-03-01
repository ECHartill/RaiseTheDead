package control;

import java.awt.Dimension;
import java.awt.Toolkit;

import gui.display.MainPanel;

import javax.swing.JFrame;

public class RaiseTheDead extends JFrame
{
	private static final long serialVersionUID = -706494922840246589L;
	private static RaiseTheDead mf = new RaiseTheDead();
	private MainPanel mp = new MainPanel();

	public RaiseTheDead()
	{
		this.add(mp);
	}
	
	public static void main(String[] args)
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//place window in middle of screen - window is square, based on screen width
		int w = (int)(d.getWidth()/2);
		int x = (int)d.getWidth()/2 - w/2;
		int y = (int)d.getHeight()/2 - w/2;
		mf.setSize(w, w);
		mf.setLocation(x, y);
	}
}

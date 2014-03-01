package gui.units;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class UnitsFrame extends JFrame
{
	private static final long serialVersionUID = 227862112007748473L;
	private JPanel panel = new JPanel();
	
	public UnitsFrame()
	{
		this.add(panel);
	}

}

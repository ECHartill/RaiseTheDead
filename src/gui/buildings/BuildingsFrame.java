package gui.buildings;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BuildingsFrame extends JFrame
{
	private static final long serialVersionUID = -4790812838945083992L;
	protected JPanel panel = new JPanel();

	public BuildingsFrame()
	{
		super();
		this.setVisible(true);
		this.setSize(400, 400);
		this.setLocation(65, 10);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.add(panel);
	}
}

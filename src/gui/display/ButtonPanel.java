package gui.display;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	private static final long serialVersionUID = -1869535166207654639L;
	private BoxLayout bl;
	
	private JButton exit = new JButton("Exit");
	private JButton fight = new JButton("Fight");
	private JButton endTurn = new JButton("End Turn");
	
	public ButtonPanel()
	{
		bl = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(bl);
		this.add(exit);
		this.add(fight);
		this.add(endTurn);
	}
	
	
	public JButton getExit()
	{
		return this.exit;
	}
	public JButton getFight()
	{
		return this.fight;
	}
	public JButton getEndTurn()
	{
		return this.endTurn;
	}
}

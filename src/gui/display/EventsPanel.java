package gui.display;

import java.awt.Graphics;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.StringConstants;

public class EventsPanel extends JPanel
{
	private static final long serialVersionUID = -218230046802433288L;
	private TextArea output = new TextArea(55, 25);
	private JScrollPane scroll = new JScrollPane(output);

	public EventsPanel()
	{
		this.add(scroll);
		this.add(output);
		output.setText(StringConstants.GAME_NAME);
		output.append(StringConstants.CASH + "0");
		output.setEditable(false);
	}
	
	public void paintComponents(Graphics g)
	{
//		g.drawString(GlobalSettings.GAME_NAME, 0, 0);
	}
	
	public void addText(String t)
	{
		output.append(t);
	}

	/**
	 * getter and setter for text area
	 * @return
	 */
	public TextArea getOutput()
	{
		return output;
	}

	public void setOutput(TextArea output)
	{
		this.output = output;
	}
}

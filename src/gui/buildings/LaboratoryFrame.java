package gui.buildings;

import javax.swing.BoxLayout;

import control.StringConstants;

public class LaboratoryFrame extends BuildingsFrame
{
	private static final long serialVersionUID = -1794349322955357301L;
	
	private BoxLayout bl;
	
	public LaboratoryFrame()
	{
		super();
		setTitle(StringConstants.LABORATORY);
		
		bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);
		
	}

}

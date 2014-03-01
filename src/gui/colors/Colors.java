package gui.colors;

import java.awt.Color;

public class Colors
{
	private static Colors instance = null;
	
	public static Colors getInstance()
	{
		if (instance == null)
		{
			instance = new Colors();
		}
		return instance;
	}
	
	public Color getBorder()
	{
		return Color.BLACK;
	}
	
	public Color getComputer()
	{
		return Color.yellow;
	}
	
	//TODO: need lighter blue; make all colors custom
	public Color getHuman()
	{
		return Color.BLUE;
	}
	
	public Color getCenter()
	{
		return Color.pink;
	}
	
	public Color getBuilding()
	{
		return Color.red;
	}
}

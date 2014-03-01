package gui.display;

import gui.colors.Colors;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import control.GlobalSettings;

import models.buildings.Building;
import models.map.Map;
import models.map.MapSquare;
import models.map.MapSquare.OwnedBy;

public class DrawPanel extends JPanel
{
	private static final long serialVersionUID = -950718375757439961L;
	private Map map;

	//constructor is empty for now
	public DrawPanel(Map map)
	{
		this.map = map;
	}

	/**
	 * draw the map grid. Center square is centered in the display
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D)g;
		Point center = null;
		Colors colors = Colors.getInstance();

		//clear the display arraylist
		map.getDisplay().clear();
		
		//get the center square
		for(int i = 0; i < map.getMap().size(); i++)
		{
			if(map.getMap().get(i).isCenter())
			{
				center = new Point(map.getMap().get(i).getX(), map.getMap().get(i).getY());
			}
		}

		//find the left and top
		int left = (int)center.getX() - GlobalSettings.DISPLAY_SIZE/2;
		int top = (int)center.getY() - GlobalSettings.DISPLAY_SIZE/2;

		//add squares to be displayed to a temporary holder
		for(int x = 0; x < map.getMap().size(); x++)
		{
			if(map.getMap().get(x).getX() >= left &&
					map.getMap().get(x).getX() <= left + GlobalSettings.DISPLAY_SIZE &&
					map.getMap().get(x).getY() >= top &&
					map.getMap().get(x).getY() <= top + GlobalSettings.DISPLAY_SIZE)
			{
				MapSquare t = map.getMap().get(x);
				t.setDisplayX(t.getX() - left);
				t.setDisplayY(t.getY() - top);
				map.getDisplay().add(t);
			}
		}

		for(int x = 0; x < map.getDisplay().size(); x++)
		{
			//Color for player- or computer-controlled squares
			if(map.getDisplay().get(x).getOwner() == OwnedBy.PLAYER)
			{
				gg.setColor(colors.getHuman());
				gg.fillRect((map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE), 
						GlobalSettings.SQUARE_SIZE, GlobalSettings.SQUARE_SIZE);
				gg.setColor(colors.getBorder());
			}
			else if(map.getDisplay().get(x).getOwner() == OwnedBy.COMPUTER)
			{
				gg.setColor(colors.getComputer());
				gg.fillRect((map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE), 
						GlobalSettings.SQUARE_SIZE, GlobalSettings.SQUARE_SIZE);
				gg.setColor(colors.getBorder());
			}
			
			//color for center square of display
			if(map.getDisplay().get(x).isCenter())
			{
				gg.setColor(colors.getCenter());
				gg.fillRect((map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE), 
						GlobalSettings.SQUARE_SIZE, GlobalSettings.SQUARE_SIZE);
				gg.setColor(colors.getBorder());
			}
			
			//text color for buildings
			if(map.getDisplay().get(x).hasBuilding())
			{
				String buildingName = this.getBuildingName(map.getDisplay().get(x).getBuilding());
				gg.setColor(colors.getBuilding());
				gg.drawString(" " + buildingName, 
						(map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE) + GlobalSettings.SQUARE_SIZE/2);
				gg.setColor(colors.getBorder());
			}
			//if not a building, write coordinates
			else
			{
				gg.drawString("(" + map.getDisplay().get(x).getX() + "," + map.getDisplay().get(x).getY() + ")", 
						(map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE) + GlobalSettings.SQUARE_SIZE/2);
			}
			gg.drawRect((map.getDisplay().get(x).getDisplayX() * GlobalSettings.SQUARE_SIZE), (map.getDisplay().get(x).getDisplayY() * GlobalSettings.SQUARE_SIZE), 
					GlobalSettings.SQUARE_SIZE, GlobalSettings.SQUARE_SIZE);
		}
	}
	
	private String getBuildingName(Building b)
	{
		return b.getNameAbbr();
	}

	/**
	 * Move the center square according to direction
	 * 
	 * @param direction
	 */
	public void moveCenter(String direction)
	{
		map.moveCenter(this, direction);
		this.repaint();
	}
	
	public MapSquare getCenter()
	{
		return map.getCenter();
	}
	
	public ArrayList<MapSquare> getDisplay()
	{
		return map.getDisplay();
	}
	public ArrayList<MapSquare> getMap()
	{
		return map.getMap();
	}
}

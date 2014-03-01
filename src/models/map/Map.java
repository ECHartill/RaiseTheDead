package models.map;

import gui.display.DrawPanel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import models.RTDModel;
import models.buildings.Graveyard;
import models.buildings.Laboratory;
import models.map.MapSquare.OwnedBy;


import control.GlobalSettings;

public class Map extends RTDModel
{
	private ArrayList<MapSquare> squares = new ArrayList<MapSquare>();
	private ArrayList<MapSquare> display = new ArrayList<MapSquare>();

	public ArrayList<MapSquare> getMap()
	{
		return squares;
	}
	public void setSquares(ArrayList<MapSquare> squares)
	{
		this.squares = squares;
	}

	public ArrayList<MapSquare> getDisplay()
	{
		return display;
	}

	public Map()
	{
		MapSquare square = null;

		//create the grid
		for(int x = 0; x < GlobalSettings.GRID_SIZE; x++)
		{
			for(int y = 0; y < GlobalSettings.GRID_SIZE; y++)
			{
				square = new MapSquare();
				square.setX(x);
				square.setY(y);
				if(x == 20 && y == 20)
				{
					square.setOwner(OwnedBy.PLAYER);
				}
				if(x == 10 && y == 10)
				{
					square.setOwner(OwnedBy.COMPUTER);
				}
				
				squares.add(square);
			}
		}
		
		//then add random locations to the map
		this.generateRandomLocations();
		this.save();
	}
	
	public MapSquare getCenter()
	{
		MapSquare centerSquare = null;
		for(int x = 0; x < squares.size(); x++)
		{
			if(squares.get(x).isCenter())
			{
				centerSquare = squares.get(x);
				break;
			}
		}
		
		return centerSquare;
	}
	
	public void moveCenter(DrawPanel dp, String direction)
	{
		int centerX = 0;
		int centerY = 0;
		MapSquare centerSquare = this.getCenter();

		//if we're on the border, don't do anything
		if((centerSquare.getX() <= 0 && direction.equals(GlobalSettings.WEST))||
				(centerSquare.getX() >= GlobalSettings.GRID_SIZE - 1 && direction.equals(GlobalSettings.EAST)) ||
				(centerSquare.getY() <= 0 && direction.equals(GlobalSettings.NORTH)) ||
				(centerSquare.getY() >= GlobalSettings.GRID_SIZE - 1 && direction.equals(GlobalSettings.SOUTH)))
		{
			return;
		}
		//otherwise reset the center square
		else
		{
			//get old center's coordinates
			centerX = centerSquare.getX();
			centerY = centerSquare.getY();
			//clear the center
			centerSquare.setCenter(false);
			//adjust the coordinates of the center to get the new center square
			if(direction.equals(GlobalSettings.EAST))
			{
				centerX++;
			}
			else if(direction.equals(GlobalSettings.WEST))
			{
				centerX--;
			}
			else if(direction.equals(GlobalSettings.SOUTH))
			{
				centerY++;
			}
			else if(direction.equals(GlobalSettings.NORTH))
			{
				centerY--;
			}

			//set the flag for the new center square
			for(int x = 0; x < squares.size(); x++)
			{
				if(squares.get(x).getX() == centerX && squares.get(x).getY() == centerY)
				{
					squares.get(x).setCenter(true);
				}
			}
		}
	}
	
	private void generateRandomLocations()
	{
		// add the lab
		Laboratory lab = new Laboratory();
		int labLocation = this.setLab();
		squares.get(labLocation).setBuilding(lab);
		squares.get(labLocation).setCenter(true);
		
		// add the graveyard next to the lab
		Graveyard graveyard = new Graveyard();
		int graveyardLocation = this.setGraveyard(labLocation);
		squares.get(graveyardLocation).setBuilding(graveyard);
	}
	
	private int setLab()
	{
		return (int)(Math.random() * Math.pow(GlobalSettings.GRID_SIZE, 2));
	}
	private int setGraveyard(int labLocation)
	{
		int graveyardLocation = 0;
		MapSquare labSquare = squares.get(labLocation);
		boolean looping = true;
		
		while(looping)
		{
			int direction = (int)(Math.random() * 4);
			if(!(labSquare.getX() <= 0 && direction == 0) && //west
					!(labSquare.getX() >= GlobalSettings.GRID_SIZE - 1 && direction == 1) && //east
					!(labSquare.getY() <= 0 && direction == 2) && //north
					!(labSquare.getY() >= GlobalSettings.GRID_SIZE - 1 && direction == 3)) //south
			{
				if(direction == 0)
				{
					graveyardLocation = labLocation - 1;
				}
				if(direction == 1)
				{
					graveyardLocation = labLocation + 1;
				}
				if(direction == 2)
				{
					graveyardLocation = labLocation + GlobalSettings.GRID_SIZE;
				}
				if(direction == 3)
				{
					graveyardLocation = labLocation - GlobalSettings.GRID_SIZE;
				}
				looping = false;
			}
		}
		return graveyardLocation;
	}
	
	protected void save()
	{
//		String createTableSql = "";
		String saveSquaresSql = "INSERT INTO map (id, x_coord, y_coord, player_owns) VALUES (1, 2, 2, false)";
		
		PreparedStatement ps;
		
		try
		{
			ps = con.prepareStatement(saveSquaresSql);
			ps.execute();
		}
		catch(SQLException sqle)
		{
			
		}
	}
}

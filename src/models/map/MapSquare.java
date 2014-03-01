package models.map;

import models.buildings.Building;
import models.units.Unit;

public class MapSquare
{
	private int x = 0;
	private int y = 0;
	private int displayX = 0;
	private int displayY = 0;
	private Building building;
	private Unit unit;
	
	private Enum<OwnedBy> owner;
	public enum OwnedBy {PLAYER, COMPUTER, NEUTRAL};
	
	private int taxBonus = 1;
	
	private boolean isCenter = false;
	
	//map coordinates
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setCenter(boolean isCenter)
	{
		this.isCenter = isCenter;
	}
	public boolean isCenter()
	{
		return this.isCenter;
	}
	
	//display coordinates
	public int getDisplayX()
	{
		return displayX;
	}
	public void setDisplayX(int displayX)
	{
		this.displayX = displayX;
	}
	public int getDisplayY()
	{
		return displayY;
	}
	public void setDisplayY(int displayY)
	{
		this.displayY = displayY;
	}
	
	public void setBuilding(Building b)
	{
		this.building = b;
	}
	public Building getBuilding()
	{
		return this.building;
	}
	public boolean hasBuilding()
	{
		return this.building != null;
	}
	public void setUnit(Unit u)
	{
		this.unit = u;
	}
	public Unit getUnit()
	{
		return this.unit;
	}
	public boolean hasUnit()
	{
		return this.unit != null;
	}
	public Enum<OwnedBy> getOwner()
	{
		return owner;
	}
	public void setOwner(Enum<OwnedBy> owner)
	{
		this.owner = owner;
	}
	public int getTaxBonus()
	{
		return taxBonus;
	}
	public void setTaxBonus(int taxBonus)
	{
		this.taxBonus = taxBonus;
	}

}

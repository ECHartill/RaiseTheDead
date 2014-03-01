package control;

import java.util.ArrayList;

import models.buildings.Building;
import models.map.Map;
import models.map.MapSquare;
import models.units.Unit;

public class Engine
{
	private ArrayList<Unit> units;
	private ArrayList<Building> buildings;
	private Map map;
	private int taxes = 0;

	public Engine()
	{
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		map = new Map();
	}
	public void endTurn()
	{
		clearQueue();
		collectTaxes();
		collectReseach();
	}
	
	/**
	 * calculate how much is generated in taxes for this turn
	 */
	private void collectTaxes()
	{
		int turnTaxes = 0;
		
		for(int x = 0; x < map.getMap().size(); x++)
		{
			if(map.getMap().get(x).getOwner() == MapSquare.OwnedBy.PLAYER)
			{
				turnTaxes += map.getMap().get(x).getTaxBonus();
			}
		}
		
		//turnTazes can be modified by buildings
		this.taxes = turnTaxes;
	}
	
	/**
	 * clear the moved flag from all units and buildings
	 */
	private void clearQueue()
	{
		for(int x = 0; x < units.size(); x++)
		{
			units.get(x).setMoved(false);
		}
		for(int x = 0; x < buildings.size(); x++)
		{
			buildings.get(x).setCreating(false);
		}
	}
	
	private void collectReseach()
	{
		
	}
	
	public Map getMap()
	{
		return this.map;
	}
	public int getTaxes()
	{
		return this.taxes;
	}
}

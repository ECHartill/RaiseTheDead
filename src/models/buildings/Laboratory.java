package models.buildings;

import java.util.ArrayList;

import control.StringConstants;

import models.units.Unit;
import models.units.Zombie;

public class Laboratory extends Building
{
	private ArrayList<Unit> units = new ArrayList<Unit>();

	public Laboratory()
	{
		name = StringConstants.LABORATORY;
		name_abbr = StringConstants.LABORATORY_ABBR;
		//Don't know how I want to do this yet
		units.add(new Zombie());
	}
}

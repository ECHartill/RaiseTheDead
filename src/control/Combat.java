package control;

import models.units.Unit;
import models.units.Zombie;

public class Combat
{
	//TODO: this is no good - need to diagram combat so I have a better understanding of it
	/**
	 * Unit a is the attacker
	 * Unit b is defending
	 * @param a
	 * @param d
	 */
	public static void twoFight(Unit a, Unit d)
	{
		if(a.attack(d))
		{
			System.out.println("Attacker wins");
		}
		else
		{
			System.out.println("Defender wins");
		}
		
	}
	
	public static void main(String[] args)
	{
		Zombie att = new Zombie();
		Zombie def = new Zombie();
		
		att.setBase(3);
		def.setBase(2);
		
		Combat.twoFight(att, def);
	}
}

package models.units;

public class Unit
{
	protected int base;
	protected int experience;
	protected int hp;
	protected int attack;
	protected int defense;
	protected boolean moved;

	public Unit(){}
	public Unit(int hp, int attack, int defense)
	{
		this.base = 0;
		this.experience = 0;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.moved = false;
	}
	
	/**
	 * This unit attacks another unit. If the attack score is greater than the defense score, 
	 * then the attacker (this unit) wins. A tie goes to the defender.
	 * 
	 * The return is true if the attack is successful
	 * 
	 * @param defender
	 * @return
	 */
	public boolean attack(Unit defender)
	{
		int att = 1;
		int def = 1;
		
		for(int a = 0; a <= this.base; a++)
		{
			att += (int)(Math.random() * 5);
			System.out.println("ATT: " + att);
		}
		for(int d = 0; d <= defender.getBase(); d++)
		{
			def += (int)(Math.random() * 5);
			System.out.println("DEF: " + def);
		}

		//return true if the attacker wins
		return (att > def);
	}
	
	public void setMoved(boolean m)
	{
		this.moved = m;
	}
	public boolean hasMoved()
	{
		return moved;
	}
	public int getBase()
	{
		return base;
	}
	public void setBase(int base)
	{
		this.base = base;
	}
	public int getExperience()
	{
		return experience;
	}
	public void setExperience(int experience)
	{
		this.experience = experience;
	}
	public int getHp()
	{
		return hp;
	}
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	public int getAttack()
	{
		return attack;
	}
	public void setAttack(int attack)
	{
		this.attack = attack;
	}
	public int getDefense()
	{
		return defense;
	}
	public void setDefense(int defense)
	{
		this.defense = defense;
	}
}

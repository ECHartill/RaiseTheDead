package models.buildings;

public class Building
{
	private boolean creating;
	protected String name;
	protected String name_abbr;
	
	public boolean isCreating()
	{
		return creating;
	}
	public void setCreating(boolean creating)
	{
		this.creating = creating;
	}
	public String getNameAbbr()
	{
		return this.name_abbr;
	}
	public String getName()
	{
		return this.name;
	}
}

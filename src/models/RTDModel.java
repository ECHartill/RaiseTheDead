package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class RTDModel
{
	protected Connection con = null;
	
	public RTDModel()
	{
		try
		{
//			Class.forName("org.sqlite.JDBC");
//			con = DriverManager.getConnection("jdbc:sqlite:Users/spiralgyre/Documents/workspace/RaiseTheDead/sqlite/raisethedead.db");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/delivernation");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("There was a problem opening the driver: " + cnfe.getMessage());
			cnfe.printStackTrace();
		}
		catch(SQLException sqle)
		{
			System.out.println("There was a problem getting the connection: " + sqle.getMessage());
			sqle.printStackTrace();
		}
	}
	
	protected abstract void save();
}

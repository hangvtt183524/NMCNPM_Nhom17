package controller;

import java.sql.SQLException;

// this class is a childclass of Prepare_Query class
// Instance of this class will execute queries like insert, update, ... that not return a ResultSet

public class RecordInformation extends Prepare_Query{
	
	public RecordInformation()
	{
		super();
	}
	
	public void query_change(String sql)
	{
		try {
			this.stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

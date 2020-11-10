package controller;

import java.sql.ResultSet;
import java.sql.SQLException;


// this class is a childclass of Prepare_Query class
// Instance of this class will execute queries like select that RecordInformation couldn't service (queries that return a ResultSet)

public class ShowInformation extends Prepare_Query{
	
	private ResultSet rs;
	
	public ShowInformation()
	{
		super();
	}
	
	public ResultSet getResultSet()
	{
		return this.rs;
	}
	
	public void closeResultSet()
	{
		try {
			this.rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet query_get(String sql)
	{
		try {
			this.rs = this.stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.rs;
	}
}

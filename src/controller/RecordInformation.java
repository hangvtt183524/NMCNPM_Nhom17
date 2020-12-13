package controller;

import java.sql.SQLException;

// this class is a childclass of Prepare_Query class
// Instance of this class will execute queries like insert, update, ... that not return a ResultSet

public class RecordInformation extends Prepare_Query{
	
	public RecordInformation()
	{
		super();
	}
	
	public void closeState()
	{
		try {
			this.prestat.close();
			this.closeStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void query_change(String sql)
	{
		try {
			// thuc hien theo cau truy van dc truyen vao qua bien sql
			this.prestat = this.conn.getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

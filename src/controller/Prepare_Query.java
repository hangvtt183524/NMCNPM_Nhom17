package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// this is a parent class of RecordInformation class and ShowInformation class
// this class controls a object (Statement stat) in order to ready execute a command which effect to records in database

public class Prepare_Query {
	protected ConnectDB conn;
	protected Statement stat;
	
	public Prepare_Query()
	{
		try {
			conn = new ConnectDB();
			this.stat = this.conn.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Statement getStatement()
	{
		return this.stat;
	}
	
	public void closeStatement()
	{
		try {
			this.stat.close();
			this.conn.closeConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// this is a parent class of RecordInformation class and ShowInformation class
// this class controls a object (PreparedStatement stat) in order to ready execute a command which effect to records in database

public class Prepare_Query {
	protected ConnectDB conn;
	protected PreparedStatement prestat;
	
	public Prepare_Query()
	{
		try {
			conn = new ConnectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public PreparedStatement getPreStatement()
	{
		return this.prestat;
	}
	
	public void closeStatement()
	{
		try {
			this.conn.closeConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

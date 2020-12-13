package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// this class controls a link from app to database
public class ConnectDB {
	private Connection conn;
	
	public ConnectDB() throws SQLException
	{
		// jdbc use an approciate driver to get connection to database
		this.conn = DriverManager.getConnection("jdbc:ucanaccess://database//QL_Covid19.mdb");
	}
	
	public void closeConnect() throws SQLException
	{
		// dong connect khi da thuc hien xong truy van
		this.conn.close();
	}
	
	public Connection getConnection()
	{
		return this.conn;
	}
	
}

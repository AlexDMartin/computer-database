package com.excilys.persistance.dao;

import java.sql.*;
import java.util.*;

public class Connector{
	public Connection connection;
	public String dbName = "computer-database-db";
	private String userName = "admincdb";
	private String password = "qwerty1234";
	private String dbms = "mysql";
	private String serverName = "localhost";
	private int portNumber = 3306;
	
	public Connector() {}
	
	public void executeTransaction(Connection con, String dbName, String query) throws SQLException {
		this.openConnection();
		this.execute(this.connection, dbName, query);
    	this.connection.close();
	}
	
	private void openConnection() throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + "://" +
                   this.serverName +
                   ":" + this.portNumber + "/",
                   connectionProps);
        
	    System.out.println("[CONNECTED] jdbc:" + this.dbms + "://" +
                this.serverName +
                ":" + this.portNumber + "/");
	    
	    this.connection = conn;
	}
	
	private void execute(Connection con, String dbName, String query) throws SQLException {

		    Statement stmt = null;
		  
		    try {
		        stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		        	// Put mapping here.
		        	
		        	int id = rs.getInt("ID");
		        	String name = rs.getString("NAME");
		        	
		        	System.out.println(id + " | " + name);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error : " + e.getMessage());
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    }
		}
}

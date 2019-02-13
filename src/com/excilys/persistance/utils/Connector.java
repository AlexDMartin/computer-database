package com.excilys.persistance.utils;

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
	
	/**
	 * @author Alex Martin
	 * @param connection
	 * @param databaseName
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet executeTransaction(Connection connection, String databaseName, String query) throws SQLException {
		this.openConnection();
		ResultSet result = this.execute(this.connection, databaseName, query);
    	return result;
	}
	
	/**
	 * @author Alex Martin
	 * @param connection
	 * @param databaseName
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public int executeUpdate(Connection connection, String databaseName, String query) throws SQLException {
		try {
			Statement statement = connection.createStatement();
	        return statement.executeUpdate(query);
	    } catch (SQLException e) {
	        System.out.println("Error : " + e.getMessage());
	    }
		return 0;
	}
	
	/**
	 * @author Alex Martin
	 * @throws SQLException
	 */
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
	
	/**
	 * @author Alex Martin
	 */
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @author Alex Martin
	 * @param connection
	 * @param databaseName
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	private ResultSet execute(Connection connection, String databaseName, String query) throws SQLException {
			try {
				Statement statement = connection.createStatement();
		        return statement.executeQuery(query);
		    } catch (SQLException e) {
		        System.out.println("Error : " + e.getMessage());
		    }
		    return null;
	}	
}

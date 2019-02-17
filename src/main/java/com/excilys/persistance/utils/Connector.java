package com.excilys.persistance.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.LoggerFactory;

public class Connector {
	
	private static Connector connectorInstance = null;
	
	private Connection connection;
	private String databaseName = "computer-database-db";
	private String userName = "admincdb";
	private String password = "qwerty1234";
	private String dbms = "mysql";
	private String serverName = "localhost";
	private int portNumber = 3306;

	private Connector() {
		try {
			LoggerFactory.getLogger(this.getClass()).info("Connector instance created");
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", this.userName);
			connectionProps.put("password", this.password);
			conn = DriverManager.getConnection(
					"jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/" + this.databaseName, connectionProps);
			LoggerFactory.getLogger(this.getClass())
					.info("[CONNECTED] jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/");

			this.connection = conn;
		} catch (SQLException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
	}
	
	public static Connector getInstance() {
		if(connectorInstance == null) {
			connectorInstance = new Connector();
		}
		return connectorInstance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
//	/**
//	 * @author Alex Martin
//	 * @param query
//	 * @return ResultSet
//	 * @throws SQLException
//	 */
//	public ResultSet executeTransaction(String query) throws SQLException {
//		ResultSet result = null;
//		try {
//			this.openConnection();
//			Statement statement = connection.createStatement();
//			return statement.executeQuery(query);
//		} catch (SQLException e) {
//			System.out.println("Error : " + e.getMessage());
//		}
//		return result;
//	}
//
//	/**
//	 * @author Alex Martin
//	 * @param query
//	 * @return ResultSet
//	 * @throws SQLException
//	 */
//	public int executeUpdate(String query) throws SQLException {
//		int result = 0;
//		try {
//			this.openConnection();
//			Statement statement = this.connection.createStatement();
//			statement.executeUpdate(query);
//			result = 1;
//		} catch (SQLException e) {
//			System.out.println("Error : " + e.getMessage());
//		}
//		return result;
//	}

	public void finalize() {
		try {
			this.connection.close();
			LoggerFactory.getLogger(this.getClass()).info("Connection closed");
		} catch (SQLException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
	}
}

package com.excilys.persistance.utils;

import java.sql.*;
import java.util.*;

import org.slf4j.LoggerFactory;

public class Connector {
	private Connection connection;
	private String dbName = "computer-database-db";
	private String userName = "admincdb";
	private String password = "qwerty1234";
	private String dbms = "mysql";
	private String serverName = "localhost";
	private int portNumber = 3306;

	/**
	 * @author Alex Martin
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet executeTransaction(String query) throws SQLException {
		ResultSet result = null;
		try {
			this.openConnection();
			Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		return result;
	}

	/**
	 * @author Alex Martin
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public int executeUpdate(String query) throws SQLException {
		int result = 0;
		try {
			this.openConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			result = 1;
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		return result;
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

		conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/",
				connectionProps);

		LoggerFactory.getLogger(this.getClass())
				.info("[CONNECTED] jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/");

		this.connection = conn;
	}

	/**
	 * @author Alex Martin
	 */
	public void closeConnection() {
		try {
			this.connection.close();
			LoggerFactory.getLogger(this.getClass()).info("Connection closed");
		} catch (SQLException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
	}
}

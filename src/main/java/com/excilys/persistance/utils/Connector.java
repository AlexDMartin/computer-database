package com.excilys.persistance.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 * The Class Connector.
 */
public class Connector {

  /** The connector instance. */
  private static Connector connectorInstance = null;

  /** The connection. */
  private Connection connection;

  /** The database name. */
  private String databaseName = "computer-database-db";

  /** The user name. */
  private String userName = "admincdb";

  /** The password. */
  private String password = "qwerty1234";

  /** The dbms. */
  private String dbms = "mysql";

  /** The server name. */
  private String serverName = "localhost";

  /** The port number. */
  private int portNumber = 3306;

  /**
   * Instantiates a new connector.
   */
  private Connector() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }
    try {
      LoggerFactory.getLogger(this.getClass()).info("Connector instance created");
      Connection conn = null;
      Properties connectionProps = new Properties();
      connectionProps.put("user", this.userName);
      connectionProps.put("password", this.password);
      conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":"
          + this.portNumber + "/" + this.databaseName, connectionProps);

      this.connection = conn;
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  /**
   * Gets the single instance of Connector.
   *
   * @return single instance of Connector
   */
  public static Connector getInstance() {
    if (connectorInstance == null) {
      connectorInstance = new Connector();
    }
    return connectorInstance;
  }

  /**
   * Gets the connection.
   *
   * @return the connection
   */
  public Connection getConnection() {
    return this.connection;
  }

  /**
   * Closes the connection.
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

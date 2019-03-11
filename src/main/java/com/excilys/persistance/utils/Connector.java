package com.excilys.persistance.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton implementation of Connector.
 */
public class Connector {

  /** The connector instance. */
  private static Connector connectorInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(Connector.class);
  /** Connection. */
  private static Connection connection;
  /** Hikari Config. */
  private static HikariConfig config;
  /** Hikari DataSource. */
  private static HikariDataSource dataSource;

  /**
   * Singleton implementation of Connector.
   */
  private Connector() {
    Connector.config = new HikariConfig("/hikari.properties");
    Connector.dataSource = new HikariDataSource(Connector.config);
    try {
      Connector.connection = Connector.dataSource.getConnection();
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
  }

  /**
   * Singleton implementation of Connector.
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
   * @throws SQLException Exception throwed when the connector fail to return a connection
   */
  public Connection getConnection() throws SQLException {
    return Connector.connection;
  }
}

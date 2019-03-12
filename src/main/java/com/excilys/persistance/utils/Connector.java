package com.excilys.persistance.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Connector {

  private static Logger logger = LoggerFactory.getLogger(Connector.class);
  private static Connection connection;
  private static HikariConfig config = new HikariConfig("/hikari.properties");
  private static HikariDataSource dataSource = new HikariDataSource(Connector.config);

  /**
   * Singleton implementation of Connector.
   */
  private Connector() {
    try {
      Connector.connection = Connector.dataSource.getConnection();
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
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

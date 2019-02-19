package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.persistance.utils.Connector;

public class ComputerDao implements Dao<Computer> {

  private static ComputerDao computerDaoInstance = null;

  static final String GET_ONE = "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer WHERE ID = ? LIMIT 1";
  static final String GET_ALL = "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID  FROM computer ORDER BY ID";
  static final String SAVE = "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";
  static final String UPDATE = "UPDATE computer SET NAME = ?, INTRODUCED = ? , DISCONTINUED = ? , COMPANY_ID = ? WHERE ID = ?";
  static final String DELETE = "DELETE FROM computer WHERE ID = ?";

  private ComputerDao() {
  }

  public static ComputerDao getInstance() {
    if (computerDaoInstance == null) {
      computerDaoInstance = new ComputerDao();
    }
    return computerDaoInstance;
  }

  @Override
  public Optional<Computer> get(long id) {
    LoggerFactory.getLogger(this.getClass()).info("ComputerDao 'get' called");
    Computer resultItem = null;

    try {
      PreparedStatement getStatement = Connector.getInstance().getConnection()
          .prepareStatement(GET_ONE);
      getStatement.setLong(1, id);
      ResultSet rs = getStatement.executeQuery();
      resultItem = ComputerMapper.getInstance().map(rs).get(0);
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return Optional.of(resultItem);
  }

  @Override
  public List<Computer> getAll() {
    LoggerFactory.getLogger(this.getClass()).info("ComputerDao 'getAll' called");
    List<Computer> resultItems = null;

    try {
      PreparedStatement getAllStatement = Connector.getInstance().getConnection()
          .prepareStatement(GET_ALL);
      ResultSet rs = getAllStatement.executeQuery();
      resultItems = ComputerMapper.getInstance().map(rs);
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return resultItems;
  }

  @Override
  public void save(Computer computer) {
    LoggerFactory.getLogger(this.getClass()).info("ComputerDao 'save' called");

    try {
      PreparedStatement saveStatement = Connector.getInstance().getConnection()
          .prepareStatement(SAVE);
      saveStatement.setString(1, computer.getName());
      saveStatement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
      saveStatement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
      saveStatement.setInt(4, computer.getCompany().getId());

      int resultCode = saveStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  @Override
  public void update(Computer computer) {
    LoggerFactory.getLogger(this.getClass()).info("ComputerDao 'update' called");

    try {
      PreparedStatement updateStatement = Connector.getInstance().getConnection()
          .prepareStatement(UPDATE);
      updateStatement.setString(1, computer.getName());
      updateStatement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
      updateStatement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
      updateStatement.setInt(4, computer.getCompany().getId());
      updateStatement.setInt(5, computer.getId());

      int resultCode = updateStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Update operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  @Override
  public void delete(Computer computer) {
    LoggerFactory.getLogger(this.getClass()).info("ComputerDao 'delete' called");

    try {
      PreparedStatement deleteStatement = Connector.getInstance().getConnection()
          .prepareStatement(DELETE);
      deleteStatement.setLong(1, computer.getId());

      int resultCode = deleteStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Delete operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }
}

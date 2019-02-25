package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import com.excilys.persistance.utils.Connector;

/**
 * The Class CompanyDao.
 */
public class CompanyDao implements Dao<Company> {

  /** The company dao instance. */
  private static CompanyDao companyDaoInstance = null;

  /** The Constant GET_ONE. */
  static final String GET_ONE = "SELECT ID, NAME FROM company WHERE ID = ? LIMIT 1";

  /** The Constant GET_ALL. */
  static final String GET_ALL = "SELECT ID, NAME FROM company ORDER BY ID";

  /** The Constant SAVE. */
  static final String SAVE = "INSERT INTO company (NAME) VALUES (?)";

  /** The Constant UPDATE. */
  static final String UPDATE = "UPDATE company NAME = ? WHERE ID = ?";

  /** The Constant DELETE. */
  static final String DELETE = "DELETE FROM company WHERE ID = ?";

  /**
   * Instantiates a new company dao.
   */
  private CompanyDao() {
  }

  /**
   * Gets the single instance of CompanyDao.
   *
   * @return single instance of CompanyDao
   */
  public static CompanyDao getInstance() {
    if (companyDaoInstance == null) {
      companyDaoInstance = new CompanyDao();
    }
    return companyDaoInstance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Company> get(long id) {
    LoggerFactory.getLogger(this.getClass()).info("CompanyDao 'get' called");
    Company resultItem = null;

    try {
      PreparedStatement getStatement = Connector.getInstance().getConnection()
          .prepareStatement(GET_ONE);
      getStatement.setLong(1, id);
      ResultSet rs = getStatement.executeQuery();
      resultItem = CompanyMapper.getInstance().map(rs).get(0);
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return Optional.of(resultItem);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Company> getAll() {
    LoggerFactory.getLogger(this.getClass()).info("CompanyDao 'getAll' called");
    List<Company> resultItems = null;

    try {
      PreparedStatement getAllStatement = Connector.getInstance().getConnection()
          .prepareStatement(GET_ALL);
      ResultSet rs = getAllStatement.executeQuery();
      resultItems = CompanyMapper.getInstance().map(rs);
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return resultItems;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Company company) throws Exception {
    LoggerFactory.getLogger(this.getClass()).info("CompanyDao 'save' called");

    try {
      PreparedStatement saveStatement = Connector.getInstance().getConnection()
          .prepareStatement(UPDATE);
      saveStatement.setString(1, company.getName());

      int resultCode = saveStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Company company) {
    LoggerFactory.getLogger(this.getClass()).info("Company update called");

    try {
      PreparedStatement updateStatement = Connector.getInstance().getConnection()
          .prepareStatement(UPDATE);
      updateStatement.setString(1, company.getName());
      updateStatement.setLong(1, company.getId());

      int resultCode = updateStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  public void delete(Company company) {
    LoggerFactory.getLogger(this.getClass()).info("Company delete called");

    try {
      PreparedStatement deleteStatement = Connector.getInstance().getConnection()
          .prepareStatement(DELETE);
      deleteStatement.setLong(1, company.getId());

      int resultCode = deleteStatement.executeUpdate();
      LoggerFactory.getLogger(this.getClass()).info("Delete operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

}

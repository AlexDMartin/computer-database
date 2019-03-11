package com.excilys.dao;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import com.excilys.persistance.utils.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CompanyDao.
 */
public class CompanyDao implements Dao<Company> {

  /** The company dao instance. */
  private static CompanyDao companyDaoInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(CompanyDao.class);
  /** Connection. */
  private static Connector connector = Connector.getInstance();
  /** Company Mapper. */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** The Constant GET_ONE. */
  private static final String GET_ONE = "SELECT ID, NAME FROM company WHERE ID = ? LIMIT 1";
  /** The Constant GET_ALL. */
  private static final String GET_ALL = "SELECT ID, NAME FROM company ORDER BY ID";
  /** The Constant SAVE. */
  private static final String SAVE = "INSERT INTO company (NAME) VALUES (?)";
  /** The Constant UPDATE. */
  private static final String UPDATE = "UPDATE company NAME = ? WHERE ID = ?";
  /** The Constant DELETE. */
  private static final String DELETE = "DELETE FROM company WHERE ID = ?";

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Company> get(long id) {
    Company resultItem = null;

    try (PreparedStatement getStatement = connector.getConnection().prepareStatement(GET_ONE)) {
      getStatement.setLong(1, id);
      ResultSet rs = getStatement.executeQuery();
      resultItem = companyMapper.map(rs).get(0);
    } catch (SQLException sqlException) {
      logger.warn(sqlException.getMessage());
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
    List<Company> resultItems = null;

    try (PreparedStatement getAllStatement = connector.getConnection().prepareStatement(GET_ALL)) {
      ResultSet rs = getAllStatement.executeQuery();
      resultItems = companyMapper.map(rs);
    } catch (SQLException sqlException) {
      logger.warn(sqlException.getMessage());
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
    try (PreparedStatement saveStatement = connector.getConnection().prepareStatement(SAVE)) {
      saveStatement.setString(1, company.getName());

      int resultCode = saveStatement.executeUpdate();
      logger.info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException sqlException) {
      logger.warn(sqlException.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Company company) {
    try {
      PreparedStatement updateStatement = connector.getConnection().prepareStatement(UPDATE);
      updateStatement.setString(1, company.getName());
      updateStatement.setLong(1, company.getId());

      int resultCode = updateStatement.executeUpdate();
      logger.info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException sqlException) {
      logger.warn(sqlException.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  public void delete(Company company) {
    try (PreparedStatement deleteStatement = connector.getConnection().prepareStatement(DELETE)) {
      deleteStatement.setLong(1, company.getId());

      int resultCode = deleteStatement.executeUpdate();
      logger.info("Delete operated on " + resultCode + " row(s)");
    } catch (SQLException sqlException) {
      logger.warn(sqlException.getMessage());
    }
  }

  /**
   * Instantiates a new company dao.
   */
  private CompanyDao() {}

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

}

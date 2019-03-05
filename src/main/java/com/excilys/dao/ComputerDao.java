package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.controller.PaginationController;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.persistance.utils.Connector;

/**
 * The Class ComputerDao.
 */
public class ComputerDao implements Dao<Computer> {

  /** The computer dao instance. */
  private static ComputerDao computerDaoInstance = null;
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
  /** Connector. */
  private static Connector connector = Connector.getInstance();
  /** ComputerMapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  
  /** The Constant GET_ONE. */
  private static final String GET_ONE =
      "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer WHERE ID = ? LIMIT 1";

  /** The Constant GET_ALL. */
  private static final String GET_ALL =
      "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer ORDER BY ID";

  /** The Constant GET_PAGINATED. */
  private static final String GET_PAGINATED =
      "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer "
      + "ORDER BY ID "
      + "LIMIT ? "
      + "OFFSET ?";
  
  /** The Constant GET_SEARCHED_PAGINATED */
  private static final String GET_SEARCHED_PAGINATED=
      "SELECT computer.ID, computer.NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer LEFT JOIN company ON computer.ID = company.ID "
      + "WHERE computer.NAME LIKE ? OR company.NAME LIKE ? "
      + "ORDER BY computer.ID "
      + "LIMIT ? "
      + "OFFSET ?";

  /** The Constant SAVE. */
  private static final String SAVE =
      "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";

  /** The Constant UPDATE. */
  private static final String UPDATE =
      "UPDATE computer SET "
      + "NAME = ?"
      + ", INTRODUCED = ? "
      + ", DISCONTINUED = ? "
      + ", COMPANY_ID = ? "
      + "WHERE ID = ?";

  /** The Constant DELETE. */
  private static final String DELETE = "DELETE FROM computer WHERE ID = ?";

  /**
   * Instantiates a new computer dao.
   */
  private ComputerDao() {}

  /**
   * Gets the single instance of ComputerDao.
   *
   * @return single instance of ComputerDao
   */
  public static ComputerDao getInstance() {
    if (computerDaoInstance == null) {
      computerDaoInstance = new ComputerDao();
    }
    return computerDaoInstance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Computer> get(long id) {
    Computer resultItem = null;

    try {
      PreparedStatement getStatement =
          connector.getConnection().prepareStatement(GET_ONE);
      getStatement.setLong(1, id);
      ResultSet rs = getStatement.executeQuery();
      resultItem = computerMapper.map(rs).get(0);
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return Optional.of(resultItem);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Computer> getAll() {
    List<Computer> resultItems = null;

    try {
      PreparedStatement getAllStatement =
         connector.getConnection().prepareStatement(GET_ALL);
      ResultSet rs = getAllStatement.executeQuery();
      resultItems = computerMapper.map(rs);
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return resultItems;
  }

  /**
   * Gets a list of computer based on a pagination.
   * 
   * @param paginationController A pagination controller
   */
  public List<Computer> getAllPaginated(PaginationController paginationController) {
    List<Computer> resultItems = new ArrayList<Computer>();

    try {
      PreparedStatement getAllPaginatedStatement =
          connector.getConnection().prepareStatement(GET_PAGINATED);
      getAllPaginatedStatement.setInt(1, paginationController.getLimit());
      getAllPaginatedStatement.setInt(2, paginationController.getOffset());
      ResultSet rs = getAllPaginatedStatement.executeQuery();
      resultItems = computerMapper.map(rs);
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return resultItems;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Computer computer) {
    try {
      PreparedStatement saveStatement =
          connector.getConnection().prepareStatement(SAVE);
      saveStatement.setString(1, computer.getName());
      saveStatement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
      saveStatement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
      saveStatement.setInt(4, computer.getCompany().getId());

      int resultCode = saveStatement.executeUpdate();
      logger.info("Save operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Computer computer) {
    try {
      PreparedStatement updateStatement =
          connector.getConnection().prepareStatement(UPDATE);
      updateStatement.setString(1, computer.getName());
      updateStatement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
      updateStatement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
      updateStatement.setInt(4, computer.getCompany().getId());
      updateStatement.setInt(5, computer.getId());

      int resultCode = updateStatement.executeUpdate();
      logger.info("Update operated on " + resultCode + " row(s)");
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
  public void delete(Computer computer) {
    try {
      PreparedStatement deleteStatement =
          connector.getConnection().prepareStatement(DELETE);
      deleteStatement.setLong(1, computer.getId());

      int resultCode = deleteStatement.executeUpdate();
      logger.info("Delete operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
  }

  public List<Computer> getAllSearchedPaginated(String filter, PaginationController paginationController) {
    List<Computer> resultItems = new ArrayList<Computer>();

    try {
      PreparedStatement getAllSearchedPaginatedStatement =
          connector.getConnection().prepareStatement(GET_SEARCHED_PAGINATED);
      String filterWithPercents = "%" + filter + "%";
      getAllSearchedPaginatedStatement.setString(1, filterWithPercents);
      getAllSearchedPaginatedStatement.setString(2, filterWithPercents);
      getAllSearchedPaginatedStatement.setInt(3, paginationController.getLimit());
      getAllSearchedPaginatedStatement.setInt(4, paginationController.getOffset());
      ResultSet rs = getAllSearchedPaginatedStatement.executeQuery();
      resultItems = computerMapper.map(rs);
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return resultItems;
  }
}

package com.excilys.dao;

import com.excilys.controller.PaginationController;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.persistance.utils.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDao implements Dao<Computer> {

  @Autowired
  private Connector connector;
  @Autowired
  private ComputerMapper computerMapper;
  private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);

  private ComputerDao() {}

  private static final String GET_ONE =
      "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer WHERE ID = ? LIMIT 1";

  private static final String GET_ALL =
      "SELECT ID, NAME, INTRODUCED, DISCONTINUED, COMPANY_ID FROM computer ORDER BY ID";

  private static final String GET_PAGINATED = "SELECT computer.ID, " + "computer.NAME, "
      + "INTRODUCED, " + "DISCONTINUED, " + "COMPANY_ID " + "FROM computer LEFT JOIN company "
      + "ON computer.ID = company.ID " + "ORDER BY %s " + "LIMIT ? " + "OFFSET ?";

  private static final String GET_SEARCHED_PAGINATED =
      "SELECT computer.ID, computer.NAME, INTRODUCED, DISCONTINUED, "
          + "COMPANY_ID FROM computer LEFT JOIN company ON computer.ID = company.ID "
          + "WHERE computer.NAME LIKE ? OR company.NAME LIKE ? " + "ORDER BY %s " + "LIMIT ? "
          + "OFFSET ?";

  private static final String SAVE =
      "INSERT INTO computer (NAME, INTRODUCED, DISCONTINUED, COMPANY_ID) VALUES (?,?,?,?)";

  private static final String UPDATE = "UPDATE computer SET " + "NAME = ?" + ", INTRODUCED = ? "
      + ", DISCONTINUED = ? " + ", COMPANY_ID = ? " + "WHERE ID = ?";

  private static final String DELETE = "DELETE FROM computer WHERE ID = ?";

  private static final String COUNT_ALL_COMPUTERS = "SELECT COUNT(ID) FROM computer";

  private static final String COUNT_ALL_COMPUTERS_BY_CRITERIA = "SELECT COUNT(computer.ID) "
      + "FROM computer " + "LEFT JOIN company ON computer.ID = company.ID "
      + "WHERE computer.NAME LIKE ? OR company.NAME LIKE ? ";

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Computer> get(long id) {
    Computer resultItem = null;

    try (PreparedStatement getStatement = connector.getConnection().prepareStatement(GET_ONE)) {
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

    try (PreparedStatement getAllStatement = connector.getConnection().prepareStatement(GET_ALL)) {
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
    List<Computer> resultItems = new ArrayList<>();
    String getPaginatedOrdered = String.format(GET_PAGINATED,
        paginationController.getSortColumn() + " " + paginationController.getAscendency());

    try (PreparedStatement getAllPaginatedStatement =
        connector.getConnection().prepareStatement(getPaginatedOrdered)) {
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
    try (PreparedStatement saveStatement = connector.getConnection().prepareStatement(SAVE)) {
      saveStatement.setString(1, computer.getName());
      saveStatement.setTimestamp(2, new Timestamp(computer.getIntroduced().getTime()));
      saveStatement.setTimestamp(3, new Timestamp(computer.getDiscontinued().getTime()));
      saveStatement.setInt(4, computer.getCompany().getId());

      int resultCode = saveStatement.executeUpdate();
      String message = String.format("Save operated on %d row(s)", resultCode);
      logger.info(message);
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
    try (PreparedStatement updateStatement = connector.getConnection().prepareStatement(UPDATE)) {
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
    try (PreparedStatement deleteStatement = connector.getConnection().prepareStatement(DELETE)) {
      deleteStatement.setLong(1, computer.getId());

      int resultCode = deleteStatement.executeUpdate();
      logger.info("Delete operated on " + resultCode + " row(s)");
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
  }

  /**
   * Queries the database to return a computer list filtered and paginated.
   * 
   * @param filter The string by which the user is filtering with
   * @param paginationController That will adapt the request to look out for the displayed computers
   *        only
   * @return
   */
  public List<Computer> getAllSearchedPaginated(String filter,
      PaginationController paginationController) {
    List<Computer> resultItems = new ArrayList<>();
    String getSearchedPaginatedOrdered = String.format(GET_SEARCHED_PAGINATED,
        paginationController.getSortColumn() + " " + paginationController.getAscendency());

    try (PreparedStatement getAllSearchedPaginatedStatement =
        connector.getConnection().prepareStatement(getSearchedPaginatedOrdered)) {
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

  /**
   * Counts all Computers.
   * 
   * @return the total of computers
   */
  public int countAllComputer() {
    int count = 0;

    try (
        PreparedStatement countAllComputersStatement =
            connector.getConnection().prepareStatement(COUNT_ALL_COMPUTERS);
        ResultSet rs = countAllComputersStatement.executeQuery();) {

      if (rs.next()) {
        count = Integer.parseInt(rs.getString(1));
      }
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return count;
  }

  /**
   * Counts all Computers by criteria.
   * 
   * @param criteria the filter string
   * @return the total of computers
   */
  public int countAllComputerByCriteria(String criteria) {
    int count = 0;
    String filter = "%" + criteria + "%";
    try (PreparedStatement countAllComputersByCriteriaStatement =
        connector.getConnection().prepareStatement(COUNT_ALL_COMPUTERS_BY_CRITERIA)) {
      countAllComputersByCriteriaStatement.setString(1, filter);
      countAllComputersByCriteriaStatement.setString(2, filter);
      ResultSet rs = countAllComputersByCriteriaStatement.executeQuery();
      if (rs.next()) {
        count = Integer.parseInt(rs.getString(1));
      }
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return count;
  }
}

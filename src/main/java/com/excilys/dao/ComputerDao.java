package com.excilys.dao;

import com.excilys.controller.PaginationController;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDao implements Dao<Computer> {

  @Autowired
  private ComputerMapper computerMapper;

  private JdbcTemplate jdbcTemplate;

  @Autowired
  private ComputerDao(DataSource dataSource) {
    this.setJdbcTemplate(dataSource);
  }

  private void setJdbcTemplate(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

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
    Computer computer = this.jdbcTemplate.query(GET_ONE, computerMapper, id).get(0);
    return Optional.of(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Computer> getAll() {
    return this.jdbcTemplate.query(GET_ALL, computerMapper);
  }

  /**
   * Gets a list of computer based on a pagination.
   * 
   * @param paginationController A pagination controller
   */
  public List<Computer> getAllPaginated(PaginationController paginationController) {
    String getPaginatedOrdered = String.format(GET_PAGINATED,
        paginationController.getSortColumn() + " " + paginationController.getAscendency());

    return this.jdbcTemplate.query(getPaginatedOrdered, 
        computerMapper,
        paginationController.getLimit(),
        paginationController.getOffset()
    );
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Computer computer) {
    this.jdbcTemplate.update(SAVE, computer.getName(), computer.getIntroduced(),
        computer.getDiscontinued(), computer.getCompany().getId());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Computer computer) {
    this.jdbcTemplate.update(UPDATE, computer.getName(), computer.getIntroduced(),
        computer.getDiscontinued(), computer.getCompany().getId(), computer.getId());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  public void delete(Computer computer) {
    this.jdbcTemplate.update(DELETE, computer.getId());
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
    String getSearchedPaginatedOrdered = String.format(GET_SEARCHED_PAGINATED,
        paginationController.getSortColumn() + " " + paginationController.getAscendency());
    String filterWithPercents = "%" + filter + "%";

    return this.jdbcTemplate.query(getSearchedPaginatedOrdered, computerMapper, filterWithPercents,
        filterWithPercents, paginationController.getLimit(), paginationController.getOffset());
  }

  /**
   * Counts all Computers.
   * 
   * @return the total of computers
   */
  public int countAllComputer() {
    return jdbcTemplate.queryForObject(COUNT_ALL_COMPUTERS, Integer.class);
  }

  /**
   * Counts all Computers by criteria.
   * 
   * @param criteria the filter string
   * @return the total of computers
   */
  public int countAllComputerByCriteria(String criteria) {
    String filter = "%" + criteria + "%";

    return jdbcTemplate.queryForObject(COUNT_ALL_COMPUTERS_BY_CRITERIA, Integer.class, filter,
        filter);
  }
}

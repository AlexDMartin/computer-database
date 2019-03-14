package com.excilys.dao;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDao implements Dao<Company> {
  
  @Autowired
  private CompanyMapper companyMapper;
  
  private JdbcTemplate jdbcTemplate;
  
  private static final String GET_ONE = "SELECT ID, NAME FROM company WHERE ID = ? LIMIT 1";
  private static final String GET_ALL = "SELECT ID, NAME FROM company ORDER BY ID";
  private static final String SAVE = "INSERT INTO company (NAME) VALUES (?)";
  private static final String UPDATE = "UPDATE company NAME = ? WHERE ID = ?";
  private static final String DELETE = "DELETE FROM company WHERE ID = ?";
  
  @Autowired
  private CompanyDao(DataSource dataSource) {
    setJdbcTemplate(dataSource);
  }
  
  
  private void setJdbcTemplate(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Company> get(long id) {
    Company company = this.jdbcTemplate.query(GET_ONE, companyMapper, id).get(0);
    return Optional.of(company);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Company> getAll() {
    return this.jdbcTemplate.query(GET_ALL, companyMapper);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Company company) throws Exception {
    this.jdbcTemplate.update(
        SAVE,
        company.getName());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Company company) {
    this.jdbcTemplate.update(
        UPDATE,
        company.getId(),
        company.getName());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  @Transactional
  public void delete(Company company) {
    this.jdbcTemplate.update(
        DELETE,
        company.getId());
  }
}

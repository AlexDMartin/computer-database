package com.excilys.dao;

import com.excilys.dao.model.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDao implements Dao<Company> {

  private static final Logger logger = LoggerFactory.getLogger(CompanyDao.class);
  private static final String GET_ONE = "from Company where id = :id";
  private static final String GET_ALL = "from Company";
  private static final String SAVE = "insert into Company (NAME) values (:name)";
  private static final String UPDATE = "update Company set NAME = : WHERE ID = ?";
  private static final String DELETE = "delete Company where id = ?";

  private SessionFactory sessionFactory;
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private CompanyDao(DataSource dataSource, SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
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
  public Optional<Company> get(int id) {
    Company company = null;
    
    try (Session session = this.sessionFactory.openSession()) {
      Query<Company> query = session.createQuery(GET_ONE, Company.class);
      query.setParameter("id", id);
      company = query.uniqueResult();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
    }
    
    return Optional.of(company);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Company> getAll() {
    List<Company> companies = new ArrayList<>();
   
    try (Session session = this.sessionFactory.openSession()) {
      Query<Company> query = session.createQuery(GET_ALL, Company.class);
      companies = query.list();
    } catch (HibernateException hibernateException) {
      logger.warn(hibernateException.getMessage());
    }
    
    return companies;
  }

  
  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Company company) throws Exception {
    int insertedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      insertedEntities = session.createQuery(SAVE).setParameter("name", company.getName())
          .executeUpdate();
      tx.commit();
      session.close();
    } catch (HibernateException hibernateException) {
      logger.warn(hibernateException.getMessage());
    }

    if (insertedEntities <= 0) {
      logger.warn("No row inserted");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Company company) {
    int updatedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      updatedEntities = session.createQuery(UPDATE).setParameter("name", company.getName())
           .executeUpdate();
      tx.commit();
      session.close();
    } catch (HibernateException hibernateException) {
      logger.warn(hibernateException.getMessage());
    }

    if (updatedEntities <= 0) {
      logger.warn("No row updated");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  @Transactional
  public void delete(Company company) {
    this.jdbcTemplate.update(DELETE, company.getId());
  }
}

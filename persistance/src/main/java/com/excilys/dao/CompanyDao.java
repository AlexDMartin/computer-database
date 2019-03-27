package com.excilys.dao;

import com.excilys.dao.model.Company;
import com.excilys.exception.DatabaseCallException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao implements Dao<Company> {

  private static final String GET_ONE = "from Company where id = :id";
  private static final String GET_ALL = "from Company";
  private static final String SAVE = "insert into Company (NAME) values (:name)";
  private static final String UPDATE = "update Company set NAME = : WHERE ID = ?";
  private static final String DELETE = "delete Company where id = ?";

  private SessionFactory sessionFactory;

  @Autowired
  private CompanyDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Company> get(int id) throws DatabaseCallException {
    Company company = null;
    
    try (Session session = this.sessionFactory.openSession()) {
      Query<Company> query = session.createQuery(GET_ONE, Company.class);
      query.setParameter("id", id);
      company = query.uniqueResult();
    } catch (HibernateException e) {
      throw new DatabaseCallException(e.getMessage());
    }
    
    return Optional.of(company);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Company> getAll() throws DatabaseCallException {
    List<Company> companies = new ArrayList<>();
   
    try (Session session = this.sessionFactory.openSession()) {
      Query<Company> query = session.createQuery(GET_ALL, Company.class);
      companies = query.list();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }
    
    return companies;
  }

  
  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Company company) throws DatabaseCallException {
    int insertedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      insertedEntities = session.createQuery(SAVE).setParameter("name", company.getName())
          .executeUpdate();
      tx.commit();
      session.close();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    if (insertedEntities <= 0) {
      throw new DatabaseCallException("No row inserted");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Company company) throws DatabaseCallException {
    int updatedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      updatedEntities = session.createQuery(UPDATE).setParameter("name", company.getName())
           .executeUpdate();
      tx.commit();
      session.close();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    if (updatedEntities <= 0) {
      throw new DatabaseCallException("No row updated");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#delete(java.lang.Object)
   */
  @Override
  public void delete(Company company) throws DatabaseCallException {
    int deletedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      deletedEntities =
          session.createQuery(DELETE).setParameter("cpuId", company.getId()).executeUpdate();
      tx.commit();
      session.close();

    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    if (deletedEntities <= 0) {
      throw new DatabaseCallException("No row deleted");
    }
  }
}

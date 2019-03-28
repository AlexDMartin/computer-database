package com.excilys.dao;

import com.excilys.dao.model.Computer;
import com.excilys.exception.DatabaseCallException;
import com.excilys.web.controller.ListPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDao implements Dao<Computer> {

  private static final String GET_ONE = "from Computer where id = :id";
  private static final String GET_ALL = "from Computer";
  private static final String GET_PAGINATED =
      "select computer "
      + "from Computer computer "
      + "left join Company company "
      + "on company.id = computer.id ";
  private static final String GET_SEARCHED_PAGINATED =
      "select computer "
      + "from Computer computer "
      + "left join Company company "
      + "on company.id = computer.id "
      + "where computer.id like :criteria "
      + "or company.id like :criteria ";
  private static final String UPDATE =
      "update Computer computer "
      + "set computer.name = :name, "
      + "computer.introduced = :introduced, "
      + "computer.discontinued = :discontinued, "
      + "computer.company = :cpaId  "
      + "where id = :cpuId ";
  private static final String DELETE = "delete Computer where id = :cpuId";
  private static final String COUNT_ALL_COMPUTERS = "select count(id) from Computer computer";
  private static final String COUNT_ALL_COMPUTERS_BY_CRITERIA =
      "select count(computer.id) "
      + "from Computer computer "
      + "left join Company company "
      + "on company.id = computer.id "
      + "where computer.name like :criteria "
      + "or company.name like :criteria";

  private SessionFactory sessionFactory;

  private ComputerDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#get(long)
   */
  @Override
  public Optional<Computer> get(int id) throws DatabaseCallException {
    Computer computer = null;

    try (Session session = this.sessionFactory.openSession()) {
      Query<Computer> query = session.createQuery(GET_ONE, Computer.class);
      query.setParameter("id", id);
      computer = query.uniqueResult();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    return Optional.of(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#getAll()
   */
  @Override
  public List<Computer> getAll() throws DatabaseCallException {
    List<Computer> computers = new ArrayList<>();

    try (Session session = this.sessionFactory.openSession()) {
      Query<Computer> query = session.createQuery(GET_ALL, Computer.class);
      computers = query.list();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    return computers;
  }

  /**
   * Gets a list of computer based on a pagination.
   * 
   * @param listPage A pagination controller
   */
  public List<Computer> getAllPaginated(ListPage listPage)
      throws DatabaseCallException {
    List<Computer> computers = new ArrayList<>();

    try (Session session = this.sessionFactory.openSession()) {
      Query<Computer> query = session.createQuery(GET_PAGINATED, Computer.class)
          // .setParameter("sortColumn", paginationController.getSortColumn())
          // .setParameter("ascendency", paginationController.getAscendency())
          .setFirstResult(listPage.getOffset())
          .setMaxResults(listPage.getLimit());
      computers = query.list();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    return computers;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#save(java.lang.Object)
   */
  @Override
  public void save(Computer computer) throws DatabaseCallException {

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      session.saveOrUpdate(computer);
      
      tx.commit();
      session.close();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.dao.Dao#update(java.lang.Object)
   */
  @Override
  public void update(Computer computer) throws DatabaseCallException {
    int updatedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      updatedEntities = session.createQuery(UPDATE).setParameter("name", computer.getName())
          .setParameter("introduced", computer.getIntroduced())
          .setParameter("discontinued", computer.getDiscontinued())
          .setParameter("cpaId", computer.getCompany().getId())
          .setParameter("cpuId", computer.getId()).executeUpdate();
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
  @Transactional
  @Override
  public void delete(Computer computer) throws DatabaseCallException {
    int deletedEntities = 0;

    try (Session session = sessionFactory.openSession()) {
      Transaction tx = session.beginTransaction();

      deletedEntities =
          session.createQuery(DELETE).setParameter("cpuId", computer.getId()).executeUpdate();
      tx.commit();
      session.close();

    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    if (deletedEntities <= 0) {
      throw new DatabaseCallException("No row deleted");
    }
  }

  /**
   * Queries the database to return a computer list filtered and paginated.
   * 
   * @param filter The string by which the user is filtering with
   * @param listPage That will adapt the request to look out for the displayed computers
   *        only
   * @return
   */
  public List<Computer> getAllSearchedPaginated(String filter,
      ListPage listPage) throws DatabaseCallException {
    List<Computer> computers = new ArrayList<>();

    try (Session session = this.sessionFactory.openSession()) {
      Query<Computer> query = session.createQuery(GET_SEARCHED_PAGINATED, Computer.class)
          // .setParameter("sortColumn", paginationController.getSortColumn())
          // .setParameter("ascendency", paginationController.getAscendency())
          .setFirstResult(listPage.getOffset())
          .setMaxResults(listPage.getLimit());
      computers = query.list();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }

    return computers;
  }

  /**
   * Counts all Computers.
   * 
   * @return the total of computers
   */
  public int countAllComputer() throws DatabaseCallException {
    Long count = null;
    try (Session session = this.sessionFactory.openSession()) {
      Query<Long> query = session.createQuery(COUNT_ALL_COMPUTERS, Long.class);
      count = query.uniqueResult();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }
    return Math.toIntExact(count);
  }

  /**
   * Counts all Computers by criteria.
   * 
   * @param criteria the filter string
   * @return the total of computers
   */
  public int countAllComputerByCriteria(String criteria) throws DatabaseCallException {
    Long count = null;
    String filter = "%" + criteria + "%";
    try (Session session = this.sessionFactory.openSession()) {
      Query<Long> query = session.createQuery(COUNT_ALL_COMPUTERS_BY_CRITERIA, Long.class);
      query.setParameter("criteria", filter);
      count = query.uniqueResult();
    } catch (HibernateException hibernateException) {
      throw new DatabaseCallException(hibernateException.getMessage());
    }
    return Math.toIntExact(count);
  }
}

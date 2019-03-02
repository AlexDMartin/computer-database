package com.excilys.dao;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

  /** The dao factory instance. */
  private static DaoFactory daoFactoryInstance = null;

  /**
   * Instantiates a new dao factory.
   */
  private DaoFactory() {}

  /**
   * Gets the single instance of DaoFactory.
   *
   * @return single instance of DaoFactory
   */
  public static DaoFactory getInstance() {
    if (daoFactoryInstance == null) {
      daoFactoryInstance = new DaoFactory();
    }
    return daoFactoryInstance;
  }

  /**
   * Gets the company dao.
   *
   * @return the company dao
   */
  public CompanyDao getCompanyDao() {
    return CompanyDao.getInstance();
  }

  /**
   * Gets the computer dao.
   *
   * @return the computer dao
   */
  public ComputerDao getComputerDao() {
    return ComputerDao.getInstance();
  }
}

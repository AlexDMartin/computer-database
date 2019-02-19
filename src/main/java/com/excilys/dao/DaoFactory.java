package com.excilys.dao;

public class DaoFactory {

  private static DaoFactory daoFactoryInstance = null;

  private DaoFactory() {
  }

  public static DaoFactory getInstance() {
    if (daoFactoryInstance == null) {
      daoFactoryInstance = new DaoFactory();
    }
    return daoFactoryInstance;
  }

  public CompanyDao getCompanyDao() {
    return CompanyDao.getInstance();
  }

  public ComputerDao getComputerDao() {
    return ComputerDao.getInstance();
  }
}

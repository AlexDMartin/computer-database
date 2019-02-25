package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;

/**
 * The Class ComputerService.
 */
public class ComputerService implements CallableService<Computer> {

  /** The computer service instance. */
  private static ComputerService computerServiceInstance = null;

  /**
   * Instantiates a new computer service.
   */
  private ComputerService() {
  }

  /**
   * Gets the single instance of ComputerService.
   *
   * @return single instance of ComputerService
   */
  public static ComputerService getInstance() {
    if (computerServiceInstance == null) {
      computerServiceInstance = new ComputerService();
    }
    return computerServiceInstance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<Computer> get(long id) {
    return DaoFactory.getInstance().getComputerDao().get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<Computer> getAll() {
    return DaoFactory.getInstance().getComputerDao().getAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(Computer t) throws Exception {
    try {
      DaoFactory.getInstance().getComputerDao().save(t);
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#update(java.lang.Object)
   */
  @Override
  public void update(Computer computer) {
    DaoFactory.getInstance().getComputerDao().update(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#delete(java.lang.Object)
   */
  @Override
  public void delete(Computer computer) {
    DaoFactory.getInstance().getComputerDao().delete(computer);
  }

}

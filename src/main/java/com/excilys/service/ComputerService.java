package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;

public class ComputerService implements CallableService<Computer> {

  private static ComputerService computerServiceInstance = null;

  private ComputerService() {
  }

  public static ComputerService getInstance() {
    if (computerServiceInstance == null) {
      computerServiceInstance = new ComputerService();
    }
    return computerServiceInstance;
  }

  @Override
  public Optional<Computer> get(long id) {
    return DaoFactory.getInstance().getComputerDao().get(id);
  }

  @Override
  public List<Computer> getAll() {
    return DaoFactory.getInstance().getComputerDao().getAll();
  }

  @Override
  public void save(Computer t) throws Exception {
    try {
      DaoFactory.getInstance().getComputerDao().save(t);
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
  }

  @Override
  public void update(Computer computer) {
    DaoFactory.getInstance().getComputerDao().update(computer);
  }

  @Override
  public void delete(Computer computer) {
    DaoFactory.getInstance().getComputerDao().delete(computer);
  }

}

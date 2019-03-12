package com.excilys.service;

import com.excilys.controller.PaginationController;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.model.Computer;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerService implements CallableService<Computer> {

  @Autowired
  private ComputerDao computerDao;
  private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

  private ComputerService() {}

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<Computer> get(long id) {
    return computerDao.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<Computer> getAll() {
    return computerDao.getAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  public List<Computer> getAllPaginated(PaginationController paginationController) {
    return computerDao.getAllPaginated(paginationController);
  }


  public List<Computer> getAllSearchedPaginated(String filter,
      PaginationController paginationController) {
    return computerDao.getAllSearchedPaginated(filter, paginationController);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(Computer t) throws Exception {
    try {
      computerDao.save(t);
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#update(java.lang.Object)
   */
  @Override
  public void update(Computer computer) {
    computerDao.update(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#delete(java.lang.Object)
   */
  @Override
  public void delete(Computer computer) {
    computerDao.delete(computer);
  }

  public int countAllComputer() {
    return computerDao.countAllComputer();
  }

  public int countAllComputerByCriteria(String criteria) {
    return computerDao.countAllComputerByCriteria(criteria);
  }

}

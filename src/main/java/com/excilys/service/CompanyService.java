package com.excilys.service;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.model.Company;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements CallableService<Company> {

  @Autowired
  private CompanyDao companyDao;
  private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

  private CompanyService() {}

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<Company> get(long id) {
    return companyDao.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<Company> getAll() {
    return companyDao.getAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(Company t) throws Exception {
    try {
      companyDao.save(t);
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
  public void update(Company company) {
    companyDao.update(company);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#delete(java.lang.Object)
   */
  @Override
  public void delete(Company company) {
    companyDao.delete(company);
  }

}

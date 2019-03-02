package com.excilys.service;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;

/**
 * The Class CompanyService.
 */
public class CompanyService implements CallableService<Company> {

  /** The company service instance. */
  private static CompanyService companyServiceInstance = null;

  /**
   * Instantiates a new company service.
   */
  private CompanyService() {}

  /**
   * Gets the single instance of CompanyService.
   *
   * @return single instance of CompanyService
   */
  public static CompanyService getInstance() {
    if (companyServiceInstance == null) {
      companyServiceInstance = new CompanyService();
    }
    return companyServiceInstance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<Company> get(long id) {
    return DaoFactory.getInstance().getCompanyDao().get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<Company> getAll() {
    return DaoFactory.getInstance().getCompanyDao().getAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(Company t) throws Exception {
    try {
      DaoFactory.getInstance().getCompanyDao().save(t);
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
  public void update(Company company) {
    DaoFactory.getInstance().getCompanyDao().update(company);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#delete(java.lang.Object)
   */
  @Override
  public void delete(Company company) {
    DaoFactory.getInstance().getCompanyDao().delete(company);
  }

}

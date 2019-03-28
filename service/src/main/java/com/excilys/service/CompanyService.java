package com.excilys.service;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import com.excilys.dto.CompanyDto;
import com.excilys.exception.DatabaseCallException;
import com.excilys.exception.validation.ValidationException;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements CallableService<CompanyDto> {

  private CompanyDao companyDao;
  private CompanyMapper companyMapper;

  private CompanyService(CompanyDao companyDao, CompanyMapper companyMapper) {
    this.companyDao = companyDao;
    this.companyMapper = companyMapper;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<CompanyDto> find(int id) throws ServiceException {

    Optional<Company> company;
    try {
      company = companyDao.get(id);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    Optional<CompanyDto> companyDto = Optional.empty();
    try {
      if (company.isPresent()) {
        companyDto = Optional.of((CompanyDto) companyMapper.entityToDto(company.get()));
      }
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    return companyDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<CompanyDto> findAll() throws ServiceException {

    List<Company> companies = new ArrayList<>();
    try {
      companies = companyDao.getAll();
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    List<CompanyDto> companiesDtoList = new ArrayList<>();
    try {
      for (Company company : companies) {
        companiesDtoList.add(((CompanyDto) companyMapper.entityToDto(company)));
      }
    } catch (CompanyValidationException companyValidationException) {
      throw new ServiceException(companyValidationException.getMessage());
    }

    return companiesDtoList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(CompanyDto companyDto) throws ServiceException {
    Optional<Company> company = Optional.empty();
    try {
      company = Optional.of(companyMapper.dtoToEntity(companyDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (company.isPresent()) {
        companyDao.save(company.get());
      }
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#update(java.lang.Object)
   */
  @Override
  public void update(CompanyDto companyDto) throws ServiceException {
    Optional<Company> company = Optional.empty();
    try {
      company = Optional.of(companyMapper.dtoToEntity(companyDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (company.isPresent()) {
        companyDao.update(company.get());
      }
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#delete(java.lang.Object)
   */
  @Override
  public void delete(CompanyDto companyDto) throws ServiceException {
    Optional<Company> company = Optional.empty();
    try {
      company = Optional.of(companyMapper.dtoToEntity(companyDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (company.isPresent()) {
        companyDao.delete(company.get());
      }
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

}

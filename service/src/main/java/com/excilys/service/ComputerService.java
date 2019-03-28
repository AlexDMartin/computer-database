package com.excilys.service;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.Dto;
import com.excilys.exception.DatabaseCallException;
import com.excilys.exception.validation.ValidationException;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.service.exception.ServiceException;
import com.excilys.web.controller.ListPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ComputerService implements CallableService<ComputerDto> {

  private ComputerDao computerDao;
  private ComputerMapper computerMapper;

  private ComputerService(ComputerDao computerDao, ComputerMapper computerMapper) {
    this.computerDao = computerDao;
    this.computerMapper = computerMapper;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#get(long)
   */
  @Override
  public Optional<ComputerDto> find(int id) throws ServiceException {
    Optional<Computer> computer;
    try {
      computer = computerDao.get(id);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    Optional<ComputerDto> computerDto = Optional.empty();
    try {
      if (computer.isPresent()) {
        computerDto = Optional.of((ComputerDto) computerMapper.entityToDto(computer.get()));
      }
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    return computerDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#getAll()
   */
  @Override
  public List<ComputerDto> findAll() throws ServiceException {
    List<Computer> computers = new ArrayList<>();
    try {
      computers = computerDao.getAll();
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    List<ComputerDto> computerDtoList = new ArrayList<>();
    try {
      for (Computer computer : computers) {
        computerDtoList.add(((ComputerDto) computerMapper.entityToDto(computer)));
      }
    } catch (ComputerValidationException computerValidationException) {
      throw new ServiceException(computerValidationException.getMessage());
    }

    return computerDtoList;
  }

  /**
   * 
   * 
   * @param paginationController
   * @return
   * @throws ServiceException
   */
  public List<ComputerDto> findAllPaginated(ListPage listPage)
      throws ServiceException {
    List<Computer> computers = new ArrayList<>();
    try {
      computers = computerDao.getAllPaginated(listPage);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    List<ComputerDto> computerDtoList = new ArrayList<>();
    try {
      for (Computer computer : computers) {
        computerDtoList.add(((ComputerDto) computerMapper.entityToDto(computer)));
      }
    } catch (ComputerValidationException computerValidationException) {
      throw new ServiceException(computerValidationException.getMessage());
    }

    return computerDtoList;
  }


  /**
   * 
   * @param filter
   * @param listPage
   * @return
   * @throws ServiceException
   */
  public List<ComputerDto> findByFilterPaginated(String filter,
      ListPage listPage) throws ServiceException {
    List<Computer> computers = new ArrayList<>();
    try {
      computers = computerDao.getAllSearchedPaginated(filter, listPage);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }

    List<ComputerDto> computerDtoList = new ArrayList<>();
    try {
      for (Computer computer : computers) {
        computerDtoList.add(((ComputerDto) computerMapper.entityToDto(computer)));
      }
    } catch (ComputerValidationException computerValidationException) {
      throw new ServiceException(computerValidationException.getMessage());
    }

    return computerDtoList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.service.CallableService#save(java.lang.Object)
   */
  @Override
  public void save(ComputerDto computerDto) throws ServiceException {
    Optional<Computer> computer = Optional.empty();
    try {
      computer = Optional.of(computerMapper.dtoToEntity((Dto) computerDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (computer.isPresent()) {
        computerDao.save(computer.get());
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
  public void update(ComputerDto computerDto) throws ServiceException {
    Optional<Computer> computer = Optional.empty();
    try {
      computer = Optional.of(computerMapper.dtoToEntity((Dto) computerDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (computer.isPresent()) {
        computerDao.update(computer.get());
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
  public void delete(ComputerDto computerDto) throws ServiceException {
    Optional<Computer> computer = Optional.empty();
    try {
      computer = Optional.of(computerMapper.dtoToEntity((Dto) computerDto));
    } catch (ValidationException validationException) {
      throw new ServiceException(validationException.getMessage());
    }

    try {
      if (computer.isPresent()) {
        computerDao.delete(computer.get());
      }
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

  /**
   * Returns the number of computers in the whole table.
   * 
   * @return the count of all computers
   * @throws ServiceException Returned when the request fail
   */
  public int findCount() throws ServiceException {
    try {
      return computerDao.countAllComputer();
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

  /**
   * Returns the number of computers filtered by a criteria.
   * 
   * @param filter the filter
   * @return the count of all computers sorted by criteria
   * @throws ServiceException
   */
  public int findCountByFilter(String filter) throws ServiceException {
    try {
      return computerDao.countAllComputerByCriteria(filter);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

}

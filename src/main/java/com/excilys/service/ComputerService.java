package com.excilys.service;

import com.excilys.controller.PaginationController;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.exception.DatabaseCallException;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dto.ComputerDto;
import com.excilys.exception.validation.ValidationException;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerService implements CallableService<ComputerDto> {

  private ComputerDao computerDao;
  private ComputerMapper computerMapper;

  @Autowired
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
  public Optional<ComputerDto> get(int id) throws ServiceException {
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
  public List<ComputerDto> getAll() throws ServiceException {
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
   * @param paginationController
   * @return
   * @throws ServiceException
   */
  public List<ComputerDto> getAllPaginated(PaginationController paginationController)
      throws ServiceException {
    List<Computer> computers = new ArrayList<>();
    try {
      computers = computerDao.getAllPaginated(paginationController);
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
   * @param paginationController
   * @return
   * @throws ServiceException
   */
  public List<ComputerDto> getAllSearchedPaginated(String filter,
      PaginationController paginationController) throws ServiceException {
    List<Computer> computers = new ArrayList<>();
    try {
      computers = computerDao.getAllSearchedPaginated(filter, paginationController);
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
      computer = Optional.of(computerMapper.dtoToEntity(computerDto));
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
      computer = Optional.of(computerMapper.dtoToEntity(computerDto));
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
      computer = Optional.of(computerMapper.dtoToEntity(computerDto));
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
   * @return
   * @throws ServiceException
   */
  public int countAllComputer() throws ServiceException {
    try {
      return computerDao.countAllComputer();
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

  /**
   * Returns the number of computers filtered by a criteria.
   * 
   * @param criteria
   * @return
   * @throws ServiceException
   */
  public int countAllComputerByCriteria(String criteria) throws ServiceException {
    try {
      return computerDao.countAllComputerByCriteria(criteria);
    } catch (DatabaseCallException databaseCallException) {
      throw new ServiceException(databaseCallException.getMessage());
    }
  }

}

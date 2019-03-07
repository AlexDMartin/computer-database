package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.dao.CompanyDao;
import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.ValidationException;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.validation.ComputerValidation;

/**
 * Contains all method to map different types to Company.
 */
public class ComputerMapper implements Mapper<Computer> {

  /** Singleton implementation of ComputerMapper. */
  private static ComputerMapper computerMapperInstance = null;
  /** Logger. */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** DaoFactory. */
  private static CompanyDao companyDao = DaoFactory.getInstance().getCompanyDao();
  /** ComputerValidation. */
  private static ComputerValidation computerValidation = ComputerValidation.getInstance();
  /** CompanyMapper */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();

  /**
   * Take a ResulSet and returns a list of Computer, useful to map items directly after a Database
   * call.
   * 
   * @param resultSet A resultSet
   * @return List&lt;Computer&gt;
   */
  @Override
  public List<Computer> map(ResultSet resultSet) {
    List<Computer> list = new ArrayList<>();

    try {
      while (resultSet.next()) {
        ComputerBuilder cb = new ComputerBuilder();

        Computer computer = null;
        Company company = null;
        if (resultSet.getInt("COMPANY_ID") > 0) {
          company = companyDao.get(resultSet.getInt("COMPANY_ID")).get();
        }

        computer = cb.addId(resultSet.getInt("ID")).addName(resultSet.getString("NAME"))
            .addIntroduced(resultSet.getDate("INTRODUCED"))
            .addDiscontinued(resultSet.getDate("DISCONTINUED")).addCompany(company).build();

        list.add(computer);
      }
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return list;
  }

  /**
   * Transforms a Company entity into a CompanyDTO.
   * 
   * @param computer The computer that needs to be transformed
   * @return CompanyDTO
   */
  @Override
  public Dto entityToDto(Computer computer) throws ComputerValidationException {
    ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();
    try {

      computerValidation.validateId(computer.getId());
      computerDtoBuilder.addId(Integer.toString(computer.getId()));
      
      computerValidation.validateName(computer.getName());
      computerDtoBuilder.addName(computer.getName());

      String formattedIntroduced = (computer.getIntroduced() != null)
          ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getIntroduced())
          : null;
      computerValidation.validateIntroductionDate(formattedIntroduced);
          

      String formattedDiscontinued = (computer.getDiscontinued() != null)
          ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getDiscontinued())
          : null;

      computerValidation.validateDiscontinuationDate(formattedDiscontinued);
      computerValidation.validatePrecedence(computer.getIntroduced(), computer.getDiscontinued());
      
      computerDtoBuilder.addIntroduced(formattedIntroduced);
      computerDtoBuilder.addDiscontinued(formattedIntroduced);  
      
      CompanyDto companyDto = computer.getCompany() != null 
          ? (CompanyDto) companyMapper.entityToDto(computer.getCompany())
          : null;
      
      computerDtoBuilder.addCompanyDto(companyDto);

    } catch (ValidationException validationException) {
      
    }

    return computerDtoBuilder.build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * 
   * @param dto A computer Data Transfer Object
   * @return Company
   * @throws ValidationException 
   */
  @Override
  public Computer dtoToEntity(Dto dto) throws ValidationException{

    ComputerDto computerDto = (ComputerDto) dto;
    ComputerBuilder computerBuilder = new ComputerBuilder();

      try { 
        
        computerValidation.validateId(Integer.parseInt(computerDto.getId()));
       
        computerBuilder.addId(
            computerDto.getId() != null 
            ? Integer.parseInt(computerDto.getId()) 
            : null
        );
  
        computerValidation.validateName(computerDto.getName());
        computerBuilder.addName(computerDto.getName());
  
  
        computerValidation.validateIntroductionDate(computerDto.getIntroduced());
        computerValidation.validateDiscontinuationDate(computerDto.getDiscontinued());
  
        Date parsedIntroduced = computerDto.getIntroduced() == null || computerDto.getIntroduced().isEmpty() 
            ? null
            : new SimpleDateFormat("yyyy-MM-dd").parse(computerDto.getIntroduced())
        ;
        Date parsedDiscontinued = computerDto.getDiscontinued() == null || computerDto.getDiscontinued().isEmpty()
            ? null
            : new SimpleDateFormat("yyyy-MM-dd").parse(computerDto.getDiscontinued())
        ;
  
        computerValidation.validatePrecedence(parsedIntroduced, parsedDiscontinued);
        computerBuilder
          .addIntroduced(parsedIntroduced)
          .addDiscontinued(parsedDiscontinued);
  
        Company company  = companyMapper.dtoToEntity(computerDto.getCompanyDto());
        
        computerBuilder.addCompany(company);
    
     } catch (ValidationException validationException) {
       logger.warn(validationException.getMessage());
       throw validationException;
     } catch (ParseException parseException) {
      logger.warn(parseException.getMessage());
      throw new ComputerValidationException(parseException.getMessage());
    }

    return computerBuilder.build();
  }

  /**
   * Singleton implementation of ComputerMapper.
   */
  private ComputerMapper() {}

  /**
   * Singleton implementation of ComputerMapper.
   *
   * @return single instance of ComputerMapper
   */
  public static ComputerMapper getInstance() {
    if (computerMapperInstance == null) {
      computerMapperInstance = new ComputerMapper();
    }
    return computerMapperInstance;
  }
}

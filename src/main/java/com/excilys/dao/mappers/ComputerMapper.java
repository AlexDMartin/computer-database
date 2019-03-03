package com.excilys.dao.mappers;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.validator.Validator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains all method to map different types to Company.
 */
public class ComputerMapper implements Mapper<Computer> {

  /** Singleton implementation of ComputerMapper. */
  private static ComputerMapper computerMapperInstance = null;

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

  /** Logger. */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** DaoFactory. */
  private DaoFactory daoFactory = DaoFactory.getInstance();
  /** Validator. */
  private Validator validator = Validator.getInstance();

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
          company = this.daoFactory.getCompanyDao().get(resultSet.getInt("COMPANY_ID")).get();
        }

        computer = cb.addId(resultSet.getInt("ID")).addName(resultSet.getString("NAME"))
            .addIntroduced(resultSet.getDate("INTRODUCED"))
            .addDiscontinued(resultSet.getDate("DISCONTINUED"))
            .addCompany(company).build();

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
  public Dto entityToDto(Computer computer) {
    ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

    String formattedIntroduced = (computer.getIntroduced() != null)
        ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getIntroduced())
        : null;
    String formattedDiscontinued = (computer.getDiscontinued() != null)
        ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getDiscontinued())
        : null;
    CompanyDto companyDto = null;
    if (computer.getCompany() != null) {
      String formattedCompanyId = (new Integer(computer.getCompany().getId()) != null)
          ? Integer.toString(computer.getCompany().getId())
          : null;
      String formattedCompanyName = computer.getCompany().getName();
      companyDto =
          new CompanyDtoBuilder().addId(formattedCompanyId).addName(formattedCompanyName).build();
    }

    return computerDtoBuilder.addId(Integer.toString(computer.getId())).addName(computer.getName())
        .addIntroduced(formattedIntroduced).addDiscontinued(formattedDiscontinued)
        .addCompanyDto(companyDto).build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * 
   * @param dto A computer Data Transfer Object
   * @return Company
   */
  @Override
  public Computer dtoToEntity(Dto dto) {

    ComputerDto computerDto = (ComputerDto) dto;
    ComputerBuilder computerBuilder = new ComputerBuilder();

    try {
      validator.validateId(computerDto.getId());
      if (computerDto.getId() != null) {
        computerBuilder.addId(Integer.parseInt(computerDto.getId()));
      }

      validator.validateName(computerDto.getName());
      computerBuilder.addName(computerDto.getName());

      Date parsedIntroduced = null;
      Date parsedDiscontinued = null;
      try {
        parsedIntroduced = new SimpleDateFormat("yyyy-MM-dd").parse(computerDto.getIntroduced());
        parsedDiscontinued =
            new SimpleDateFormat("yyyy-MM-dd").parse(computerDto.getDiscontinued());
      } catch (ParseException e) {
        logger.warn(e.getMessage());
      }

      validator.validateDate(computerDto.getIntroduced());
      validator.validateDate(computerDto.getDiscontinued());
      validator.validatePrecedence(parsedIntroduced, parsedDiscontinued);
      computerBuilder.addIntroduced(parsedIntroduced).addDiscontinued(parsedDiscontinued);


      Company company = null;
      validator.validateCompany((CompanyDto) computerDto.getCompanyDto());
      if (computerDto.getCompanyDto() != null) {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        company = companyBuilder.addId(Integer.parseInt(computerDto.getCompanyDto().getId()))
            .addName(computerDto.getCompanyDto().getName()).build();
      }
      computerBuilder.addCompany(company);
    } catch (ValidationException e) {
      logger.warn(e.getMessage());
    }

    return computerBuilder.build();
  }

}

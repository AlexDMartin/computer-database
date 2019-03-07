package com.excilys.dao.mappers;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.validation.Validation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains all method to map different types to Company.
 */
public class CompanyMapper implements Mapper<Company> {

  /** Singleton implementation of CompanyMapper. */
  private static CompanyMapper companyMapperInstance = null;

  /**
   * Singleton implementation of CompanyMapper.
   */
  private CompanyMapper() {}

  /**
   * Singleton implementation of CompanyMapper.
   *
   * @return single instance of CompanyMapper
   */
  public static CompanyMapper getInstance() {
    if (companyMapperInstance == null) {
      companyMapperInstance = new CompanyMapper();
    }
    return companyMapperInstance;
  }

  /** Logger. */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** Validator. */
  private Validation validator = Validation.getInstance();

  /**
   * Take a ResulSet and returns a list of Company, useful to map items directly after a Database
   * call.
   * 
   * @param resultSet Any ResultSet
   * @return List&lt;Company&gt;
   */
  @Override
  public List<Company> map(ResultSet resultSet) {
    List<Company> result = new ArrayList<>();

    try {
      while (resultSet.next()) {
        CompanyBuilder cb = new CompanyBuilder();

        Company company =
            cb.addId(resultSet.getInt("ID")).addName(resultSet.getString("NAME")).build();

        result.add(company);
      }
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }
    return result;
  }

  /**
   * Transforms a Company entity into a CompanyDTO.
   * 
   * @param company A company entity
   * @return CompanyDTO
   */
  @Override
  public Dto entityToDto(Company company) {
    CompanyDtoBuilder companyDtoBuilder = new CompanyDtoBuilder();
    return companyDtoBuilder.addId(Integer.toString(company.getId())).addName(company.getName())
        .build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * 
   * @param dto A company Data transfer object
   * @return Company
   */
  @Override
  public Company dtoToEntity(Dto dto) {
    CompanyDto companyDto = (CompanyDto) dto;

    try {
      validator.validateId(companyDto.getId());
      validator.validateName(companyDto.getName());
    } catch (ValidationException e) {
      logger.warn(e.getMessage());
    }

    CompanyBuilder builder = new CompanyBuilder();
    return builder.addId(Integer.parseInt(companyDto.getId())).addName(companyDto.getName())
        .build();
  }
}

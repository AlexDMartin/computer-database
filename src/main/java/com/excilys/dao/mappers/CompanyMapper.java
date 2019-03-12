package com.excilys.dao.mappers;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.validation.CompanyValidation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<Company> {

  @Autowired
  private CompanyValidation companyValidation;
  private static final Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

  private CompanyMapper() {}

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
   * @return CompanyDto
   * @throws CompanyValidationException Exception throwed if the validation fails
   */
  @Override
  public Dto entityToDto(Company company) throws CompanyValidationException {

    companyValidation.validateId(company.getId());
    companyValidation.validateName(company.getName());

    return new CompanyDtoBuilder().addId(Integer.toString(company.getId()))
        .addName(company.getName()).build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * 
   * @param dto A company Data transfer object
   * @return Company
   * @throws CompanyValidationException Throwed when the company isn't valid
   */
  @Override
  public Company dtoToEntity(Dto dto) throws CompanyValidationException {
    CompanyDto companyDto = (CompanyDto) dto;

    companyValidation.validateId(Integer.parseInt(companyDto.getId()));
    companyValidation.validateName(companyDto.getName());

    return new CompanyBuilder().addId(Integer.parseInt(companyDto.getId()))
        .addName(companyDto.getName()).build();
  }
}

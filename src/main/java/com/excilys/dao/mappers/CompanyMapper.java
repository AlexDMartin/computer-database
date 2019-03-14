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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<Company>, RowMapper<Company> {

  @Autowired
  private CompanyValidation companyValidation;

  private CompanyMapper() {}

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

  @Override
  public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new CompanyBuilder().addId(rs.getInt("ID")).addName(rs.getString("NAME")).build();
  }
}

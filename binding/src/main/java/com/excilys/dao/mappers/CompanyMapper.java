package com.excilys.dao.mappers;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.validation.CompanyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<Company> {

  private CompanyValidation companyValidation;
  
  @Autowired
  private CompanyMapper(CompanyValidation companyValidation) {
    this.companyValidation = companyValidation;
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

    companyValidation.validate(company);

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

    Company company = new CompanyBuilder().addId(Integer.parseInt(companyDto.getId()))
        .addName(companyDto.getName()).build();

    companyValidation.validate(company);

    return company;
  }
}

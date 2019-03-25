package com.excilys.dao.mappers;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.validation.ComputerValidation;
import com.excilys.validation.exception.ValidationException;
import com.excilys.validation.exception.computer.ComputerValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper implements Mapper<Computer> {

  private static final Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
  private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

  private ComputerValidation computerValidation;
  private CompanyMapper companyMapper;

  @Autowired
  private ComputerMapper(ComputerValidation computerValidation, CompanyMapper companyMapper) {
    this.computerValidation = computerValidation;
    this.companyMapper = companyMapper;
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

      computerDtoBuilder.addId(Integer.toString(computer.getId()));

      computerDtoBuilder.addName(computer.getName());

      String formattedIntroduced = (computer.getIntroduced() != null)
          ? new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(computer.getIntroduced())
          : null;


      String formattedDiscontinued = (computer.getDiscontinued() != null)
          ? new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(computer.getDiscontinued())
          : null;

      computerDtoBuilder.addIntroduced(formattedIntroduced);
      computerDtoBuilder.addDiscontinued(formattedDiscontinued);

      CompanyDto companyDto = computer.getCompany() != null
          ? (CompanyDto) companyMapper.entityToDto(computer.getCompany())
          : null;

      computerValidation.validate(computer);

      computerDtoBuilder.addCompanyDto(companyDto);

    } catch (ValidationException validationException) {
      logger.warn(validationException.getMessage());
    }

    return computerDtoBuilder.build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * 
   * @param dto A computer Data Transfer Object
   * @return Company
   * @throws ValidationException The exception thrown when the Computer is not valid
   */
  @Override
  public Computer dtoToEntity(Dto dto) throws ValidationException {

    ComputerDto computerDto = (ComputerDto) dto;
    ComputerBuilder computerBuilder = new ComputerBuilder();

    if (computerDto.getId() != null) {
      computerBuilder.addId(Integer.parseInt(computerDto.getId()));
    }

    computerBuilder.addName(computerDto.getName());

    Date parsedIntroduced = null;
    Date parsedDiscontinued = null;
    try {
      parsedIntroduced =
          computerDto.getIntroduced() == null || computerDto.getIntroduced().isEmpty() ? null
              : new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(computerDto.getIntroduced());
      parsedDiscontinued =
          computerDto.getDiscontinued() == null || computerDto.getDiscontinued().isEmpty() ? null
              : new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(computerDto.getDiscontinued());
    } catch (ParseException parseException) {
      logger.warn(parseException.getMessage());
    }

    computerBuilder.addIntroduced(parsedIntroduced).addDiscontinued(parsedDiscontinued);

    Company company = companyMapper.dtoToEntity(computerDto.getCompanyDto());

    computerBuilder.addCompany(company);

    Computer computer = computerBuilder.build();

    computerValidation.validate(computer);

    return computerBuilder.build();
  }
}

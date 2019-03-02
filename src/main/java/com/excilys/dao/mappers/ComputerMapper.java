package com.excilys.dao.mappers;

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

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.CompanyDTOBuilder;
import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTOBuilder;
import com.excilys.dto.DTO;
import com.excilys.validator.Validator;

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
   * Take a ResulSet and returns a list of Computer,
   * useful to map items directly after a Database call.
   * @param ResultSet
   * @return List<Computer>
   */
  @Override
  public List<Computer> map(ResultSet rs) {
    List<Computer> list = new ArrayList<>();

    try {
      while (rs.next()) {
        ComputerBuilder cb = new ComputerBuilder();
        
        Computer computer = null;
        Company company = null;
        if(rs.getInt("COMPANY_ID") > 0) {
          company = this.daoFactory.getCompanyDao().get(rs.getInt("COMPANY_ID")).get();
        }
        
        computer = cb
            .addId(rs.getInt("ID"))
            .addName(rs.getString("NAME"))
            .addIntroduced(rs.getDate("INTRODUCED"))
            .addDiscontinued(rs.getDate("DISCONTINUED"))
            .addCompany(company)
            .build();

        list.add(computer);
      }
    } catch (SQLException e) {
      logger.warn(e.getMessage());
    }

    return list;
  }

  /**
   * Transforms a Company entity into a CompanyDTO.
   * @param Company
   * @return CompanyDTO
   */
  @Override
  public DTO entityToDTO(Computer computer) {
    ComputerDTOBuilder computerDTOBuilder = new ComputerDTOBuilder();
    
    String formattedIntroduced = (computer.getIntroduced() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getIntroduced()) : null;
    String formattedDiscontinued = (computer.getDiscontinued() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(computer.getDiscontinued()) : null;
    CompanyDTO companyDTO = null;
    if(computer.getCompany() != null) {
      String formattedCompanyId = (new Integer(computer.getCompany().getId()) != null) ? Integer.toString(computer.getCompany().getId()): null;
      String formattedCompanyName = computer.getCompany().getName();
      companyDTO = new CompanyDTOBuilder()
        .addId(formattedCompanyId)
        .addName(formattedCompanyName)
        .build();     
    }
    
    return computerDTOBuilder
        .addId(Integer.toString(computer.getId()))
        .addName(computer.getName())
        .addIntroduced(formattedIntroduced)
        .addDiscontinued(formattedDiscontinued)
        .addCompanyDTO(companyDTO)
        .build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * @param CompanyDTO
   * @return Company
   */
  @Override
  public Computer DTOToEntity(DTO dto) { 
    
    ComputerDTO computerDTO = (ComputerDTO) dto;
    ComputerBuilder computerBuilder = new ComputerBuilder();
     
    try {
    	validator.validateId(computerDTO.getId());
        if(computerDTO.getId() != null) {
        	computerBuilder.addId(Integer.parseInt(computerDTO.getId()));
        }
        
        validator.validateName(computerDTO.getName());
        computerBuilder.addName(computerDTO.getName());
        
        Date parsedIntroduced = null;
        Date parsedDiscontinued = null;
        try {
            parsedIntroduced = new SimpleDateFormat("yyyy-MM-dd").parse(computerDTO.getIntroduced());
            parsedDiscontinued= new SimpleDateFormat("yyyy-MM-dd").parse(computerDTO.getDiscontinued());
        } catch (ParseException e) {
        	logger.warn(e.getMessage());
        }

        validator.validateDate(computerDTO.getIntroduced());
        validator.validateDate(computerDTO.getDiscontinued());
        validator.validatePrecedence(parsedIntroduced, parsedDiscontinued);
        computerBuilder
	        .addIntroduced(parsedIntroduced)
	        .addDiscontinued(parsedDiscontinued);
        
        
        Company company = null;
        if(computerDTO.getCompanyDTO() != null) {
    	 CompanyBuilder companyBuilder = new CompanyBuilder();
    	    company = companyBuilder
    	        .addId(Integer.parseInt(computerDTO.getCompanyDTO().getId()))
    	        .addName(computerDTO.getCompanyDTO().getName())
    	        .build();
        }
        validator.validateCompany(company);
        computerBuilder.addCompany(company);
      } catch (ValidationException e) {
        logger.warn(e.getMessage());
      } 
    
    return computerBuilder.build();
  }

}

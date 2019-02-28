package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.CompanyDTOBuilder;
import com.excilys.dto.DTO;

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
  
  /**
   * Take a ResulSet and returns a list of Company,
   * useful to map items directly after a Database call.
   * @param ResultSet
   * @return List<Company>
   */
  @Override
  public List<Company> map(ResultSet resultSet) {
    List<Company> result = new ArrayList<>();
    
    try {
      while (resultSet.next()) {
        CompanyBuilder cb = new CompanyBuilder();
        
        Company company = cb
            .addId(resultSet.getInt("ID"))
            .addName(resultSet.getString("NAME"))
            .build();
        
        result.add(company);
      }
    } catch (SQLException e) {
     logger.warn(e.getMessage());
    }
    return result;
  }

  /**
   * Transforms a Company entity into a CompanyDTO.
   * @param Company
   * @return CompanyDTO
   */
  @Override
  public DTO entityToDTO(Company company) {
    CompanyDTOBuilder companyDTOBuilder = new CompanyDTOBuilder();
    return companyDTOBuilder
        .addId(Integer.toString(company.getId()))
        .addName(company.getName())
        .build();
  }

  /**
   * Transforms a CompanyDTO into a Company Entity.
   * @param CompanyDTO
   * @return Company
   */
  @Override
  public Company DTOToEntity(DTO dto) {
    CompanyDTO companyDTO = (CompanyDTO) dto;
    CompanyBuilder builder = new CompanyBuilder();
    return builder
        .addId(Integer.parseInt(companyDTO.getId()))
        .addName(companyDTO.getName())
        .build();
  }
}

package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyMapper.
 */
public class CompanyMapper implements Mapper<Company> {

  /** The company mapper instance. */
  private static CompanyMapper companyMapperInstance = null;

  /**
   * Instantiates a new company mapper.
   */
  private CompanyMapper() {
  }

  /**
   * Gets the single instance of CompanyMapper.
   *
   * @return single instance of CompanyMapper
   */
  public static CompanyMapper getInstance() {
    if (companyMapperInstance == null) {
      companyMapperInstance = new CompanyMapper();
    }
    return companyMapperInstance;
  }

  /**
   * Map.
   *
   * @param rs the rs
   * @return List<Company>
   */
  @Override
  public List<Company> map(ResultSet rs) {
    if (rs == null) {
      return null;
    }
    List<Company> result = new ArrayList<>();
    try {
      while (rs.next()) {
        CompanyBuilder cb = new CompanyBuilder();
        
        Company company = cb
            .addId(rs.getInt("ID"))
            .addName(rs.getString("NAME"))
            .build();
        
        result.add(company);
      }
    } catch (Exception e) {
     LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
    return result;
  }
  
  
}

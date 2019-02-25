package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;

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
   * @author Alex Martin
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
        Company resultItem = new Company();
        resultItem.setId(rs.getInt("ID"));
        resultItem.setName(rs.getString("NAME"));
        result.add(resultItem);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}

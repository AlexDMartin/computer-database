package com.excilys.dao.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

  private static ComputerMapper computerMapperInstance = null;

  private ComputerMapper() {
  }

  public static ComputerMapper getInstance() {
    if (computerMapperInstance == null) {
      computerMapperInstance = new ComputerMapper();
    }
    return computerMapperInstance;
  }

  /**
   * @author Alex Martin
   * @param ResultSet
   * @return List<Computer>
   */
  @Override
  public List<Computer> map(ResultSet rs) {
    LoggerFactory.getLogger(this.getClass()).info("Computer mapping triggered");
    List<Computer> list = new ArrayList<>();

    try {
      while (rs.next()) {
        int id = rs.getInt("ID");
        String name = rs.getString("NAME");
        Date introduced = rs.getDate("INTRODUCED");
        Date discontinued = rs.getDate("DISCONTINUED");
        Company company = null;
        int companyId = rs.getInt("COMPANY_ID");
        if (companyId != 0) {
          company = DaoFactory.getInstance().getCompanyDao().get(companyId).get();
        }

        Computer computerItem = new Computer();
        computerItem.setId(id);
        computerItem.setName(name);
        computerItem.setIntroduced(introduced);
        computerItem.setDiscontinued(discontinued);
        computerItem.setCompany(company);

        list.add(computerItem);
      }
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return list;
  }

}

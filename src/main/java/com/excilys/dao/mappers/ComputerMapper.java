package com.excilys.dao.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTOBuilder;
import com.excilys.dto.DTO;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper implements Mapper<Computer> {

  /** The computer mapper instance. */
  private static ComputerMapper computerMapperInstance = null;

  /**
   * Instantiates a new computer mapper.
   */
  private ComputerMapper() {
  }

  /**
   * Gets the single instance of ComputerMapper.
   *
   * @return single instance of ComputerMapper
   */
  public static ComputerMapper getInstance() {
    if (computerMapperInstance == null) {
      computerMapperInstance = new ComputerMapper();
    }
    return computerMapperInstance;
  }

  /**
   * Map.
   *
   * @param rs the rs
   * @return List<Computer>
   */
  @Override
  public List<Computer> map(ResultSet rs) {
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
        
        ComputerBuilder cb = new ComputerBuilder();
        Computer computer = null;
        computer = cb
            .addId(id)
            .addName(name)
            .addIntroduced(introduced)
            .addDiscontinued(discontinued)
            .addCompany(company)
            .build();

        list.add(computer);
      }
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    return list;
  }

  @Override
  public DTO entityToDTO(Computer computer) {
    ComputerDTOBuilder computerDTOBuilder = new ComputerDTOBuilder();
    return computerDTOBuilder
        .addId(Integer.toString(computer.getId()))
        .addName(computer.getName())
        .addIntroduced(computer.getIntroduced().toString())
        .addDiscontinued(computer.getDiscontinued().toString())
        .addCompanyId(Integer.toString(computer.getCompany().getId()))
        .addCompanyName(computer.getCompany().getName())
        .build();
  }

  @Override
  public Computer DTOToEntity(DTO dto) { 
    
    ComputerDTO computerDTO = (ComputerDTO) dto;
    
    CompanyBuilder companyBuilder = new CompanyBuilder();
    Company company = companyBuilder
        .addId(Integer.parseInt(computerDTO.getCompanyId()))
        .addName(computerDTO.getCompanyName())
        .build();
    ComputerBuilder computerBuilder = new ComputerBuilder();
    return computerBuilder
        .addId(Integer.parseInt(computerDTO.getId()))
        .addName(computerDTO.getName())
        .addIntroduced(new Date(01,01,1984)) // TODO : Parse
        .addDiscontinued(new Date(01,01,1985)) // TODO : Parse 
        .addCompany(company) 
        .build();
  }

}

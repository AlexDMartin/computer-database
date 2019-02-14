package com.excilys.persistance.mappers;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Computer;

public class ComputerMapper implements Mapper<Computer>{

	/**
	 * @author Alex Martin
	 * @param ResultSet
	 * @return List<Computer>  
	 */
	@Override
	public List<Computer> map(ResultSet rs) {
		LoggerFactory.getLogger(this.getClass()).info("Mapping computers");
		if(rs == null) {
			return null;
		}
		List<Computer> result = new ArrayList<>();
		try{
			while(rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("NAME");
					Timestamp introduced = rs.getTimestamp("INTRODUCED");
					Timestamp discontinued = rs.getTimestamp("DISCONTINUED");
					int companyId = rs.getInt("COMPANY_ID");
				
					Computer resultItem = new Computer();
					resultItem.setId(id);
					resultItem.setName(name);
					resultItem.setIntroduced(introduced);
					resultItem.setDiscontinued(discontinued);
					resultItem.setCompanyId(companyId);
					
					result.add(resultItem);
				}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;	
	}

}

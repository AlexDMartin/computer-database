package com.excilys.persistance.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.persistance.model.Computer;

public class ComputerMapper implements Mapper<Computer>{

	@Override
	public List<Computer> map(ResultSet rs) {
		if(rs == null) {
			return null;
		}
		List<Computer> result = new ArrayList<>();
		try{
			while(rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("NAME");
					Date introduced = rs.getDate("INTRODUCED");
					Date discontinued = rs.getDate("DISCONTINUED");
					// Handle Many-to-one connection to company here
				
					Computer resultItem = new Computer();
					resultItem.setId(id);
					resultItem.setName(name);
					resultItem.setIntroduced(introduced);
					resultItem.setDiscontinued(discontinued);
					
					result.add(resultItem);
				}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;	
	}

}

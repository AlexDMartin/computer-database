package com.excilys.persistance.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Company;

public class CompanyMapper implements Mapper<Company>{

	/**
	 * @author Alex Martin
	 * @param ResultSet
	 * @return List<Company>  
	 */
	@Override
	public List<Company> map(ResultSet rs) {
		LoggerFactory.getLogger(this.getClass()).info("Mapping compan(y/ies)");
		if(rs == null) {
			return null;
		}
		List<Company> result = new ArrayList<>();
		try{
			while(rs.next()) {
					Company resultItem = new Company();
					resultItem.setId(rs.getInt("ID"));
					resultItem.setName(rs.getString("NAME"));
					result.add(resultItem);
			}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;	
	}
}

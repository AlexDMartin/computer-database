package com.excilys.persistance.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.excilys.persistance.model.Company;

public class CompanyMapper implements Mapper<Company>{

	@Override
	public List<Company> map(ResultSet rs) {
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

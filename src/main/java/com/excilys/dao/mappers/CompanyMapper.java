package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;

public class CompanyMapper implements Mapper<Company>{

	private static CompanyMapper companyMapperInstance = null;
	
	private CompanyMapper() {}
	
	public static CompanyMapper getInstance() {
		if(companyMapperInstance == null) {
			companyMapperInstance = new CompanyMapper();
		}
		return companyMapperInstance;
	}
	
	/**
	 * @author Alex Martin
	 * @param ResultSet
	 * @return List<Company>  
	 */
	@Override
	public List<Company> map(ResultSet rs) {
		LoggerFactory.getLogger(this.getClass()).info("Company mapping triggered");
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

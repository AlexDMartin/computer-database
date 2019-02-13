package com.excilys.gui.interaction;

import java.util.List;
import java.util.Scanner;

import com.excilys.persistance.dao.CompanyDao;
import com.excilys.persistance.model.Company;

public class ListCompanyInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		CompanyDao companyDao = new CompanyDao();
		System.out.println("--- Company List ---");
		List<Company> companyList = companyDao.getAll();
		if(companyList != null) {
			for(Company company: companyList) {
				System.out.println(company);
			}
		}
		return new GUIOutput(1, UserChoice.NONE);
	}

}

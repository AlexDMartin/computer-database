package com.excilys.gui.interaction;

import java.util.List;

import com.excilys.persistance.model.Company;
import com.excilys.service.CompanyService;

public class ListCompanyInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		CompanyService companyService = new CompanyService();
		System.out.println("--- Company List ---");
		List<Company> companyList = companyService.getAll();
		if(companyList != null) {
			for(Company company: companyList) {
				System.out.println(company);
			}
		}
		return new GUIOutput(1, UserChoice.NONE);
	}

}

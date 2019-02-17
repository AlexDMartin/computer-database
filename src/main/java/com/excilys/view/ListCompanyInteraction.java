package com.excilys.view;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;
import com.excilys.service.CompanyService;

public class ListCompanyInteraction implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		CompanyService companyService = new CompanyService();
		System.out.println("--- Company List ---");
		List<Company> companyList = companyService.getAll();
		if (companyList != null) {
			for (Company company : companyList) {
				System.out.println(company);
			}
		}
		LoggerFactory.getLogger(this.getClass()).info("Company list displayed");
		return new GUIOutput(1, UserChoice.NONE);
	}

}

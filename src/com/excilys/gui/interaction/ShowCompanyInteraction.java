package com.excilys.gui.interaction;

import java.util.Optional;

import com.excilys.persistance.model.Company;
import com.excilys.service.CompanyService;

public class ShowCompanyInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			CompanyService companyService = new CompanyService();
			Optional<Company> company = companyService.get(param.getId());
			System.out.println(company.get());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		return null;
	}

}

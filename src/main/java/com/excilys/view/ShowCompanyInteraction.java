package com.excilys.view;

import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;
import com.excilys.service.CompanyService;

public class ShowCompanyInteraction implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			CompanyService companyService = new CompanyService();
			Optional<Company> company = companyService.get(param.getId());
			LoggerFactory.getLogger(this.getClass()).info(company.get() + " retrieved");
			System.out.println(company.get());
		} catch (IndexOutOfBoundsException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
		return null;
	}

}

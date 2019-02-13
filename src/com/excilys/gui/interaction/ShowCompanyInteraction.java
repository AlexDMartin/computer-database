package com.excilys.gui.interaction;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.persistance.dao.CompanyDao;
import com.excilys.persistance.model.Company;

public class ShowCompanyInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			CompanyDao companyDao = new CompanyDao();
			Optional<Company> company = companyDao.get(param.getId());
			System.out.println(company.get());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		return null;
	}

}

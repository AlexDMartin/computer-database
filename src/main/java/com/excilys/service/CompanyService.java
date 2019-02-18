package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;

public class CompanyService implements CallableService<Company> {

	private static CompanyService companyServiceInstance = null;
	
	private CompanyService() {}

	public static CompanyService getInstance() {
		if(companyServiceInstance == null) {
			companyServiceInstance = new CompanyService();
		}
		return companyServiceInstance;
	}
	
	@Override
	public Optional<Company> get(long id) {
		return DaoFactory.getInstance().getCompanyDao().get(id);
	}

	@Override
	public List<Company> getAll() {
		return DaoFactory.getInstance().getCompanyDao().getAll();
	}

	@Override
	public void save(Company t) throws Exception {
		try {
			DaoFactory.getInstance().getCompanyDao().save(t);
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
	}

	@Override
	public void update(Company company) {
		DaoFactory.getInstance().getCompanyDao().update(company);

	}

	@Override
	public void delete(Company company) {
		DaoFactory.getInstance().getCompanyDao().delete(company);
	}

}

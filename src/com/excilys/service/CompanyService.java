package com.excilys.service;

import java.util.List;
import java.util.Optional;

import com.excilys.persistance.dao.DaoFactory;
import com.excilys.persistance.model.Company;

public class CompanyService implements CallableService<Company>{

	DaoFactory daoFactory;
	
	@Override
	public Optional<Company> get(long id) {
		return daoFactory.getCompanyDao().get(id);
	}

	@Override
	public List<Company> getAll() {
		return daoFactory.getCompanyDao().getAll();
	}

	@Override
	public void save(Company t) throws Exception {
		try {
			daoFactory.getCompanyDao().save(t);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Company t) {
		daoFactory.getCompanyDao().update(t);
		
	}

	@Override
	public int delete(Company t) {
		return daoFactory.getCompanyDao().delete(t);
	}

}

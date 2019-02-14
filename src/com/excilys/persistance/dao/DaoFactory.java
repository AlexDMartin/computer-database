package com.excilys.persistance.dao;

public class DaoFactory {
	public CompanyDao getCompanyDao() {
		return new CompanyDao();
	}
	
	public ComputerDao getComputerDao() {
		return new ComputerDao();
	}
}

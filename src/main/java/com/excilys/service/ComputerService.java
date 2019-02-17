package com.excilys.service;

import java.util.List;
import java.util.Optional;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;

public class ComputerService implements CallableService<Computer>{

	private DaoFactory daoFactory;
	
	public ComputerService() {
		this.daoFactory = new DaoFactory();
	}
	
	@Override
	public Optional<Computer> get(long id) {
		return daoFactory.getComputerDao().get(id);
	}

	@Override
	public List<Computer> getAll() {
		return daoFactory.getComputerDao().getAll();
	}

	@Override
	public void save(Computer t) throws Exception {
		try {
			daoFactory.getComputerDao().save(t);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Computer t) {
		daoFactory.getComputerDao().update(t);
	}

	@Override
	public void delete(Computer t) {
		daoFactory.getComputerDao().delete(t);
	}
	
}

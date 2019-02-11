package com.excilys.persistance.dao;

import java.sql.SQLException;
import java.util.*;

import com.excilys.persistance.Company;

public class CompanyDao implements Dao<Company>{

	public CompanyDao() {}
	
	@Override
	public Optional<Company> get(long id) {
		String transactionQuery = "select * from `computer-database-db`.`company` where id = " + id + " limit 1;";
		try{
			Connector connector = new Connector();
			connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Company> getAll() {
		String transactionQuery = "select * from `computer-database-db`.`company`;";
		try{
			Connector connector = new Connector();
			connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public void save(Company t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Company t) {
		// TODO Auto-generated method stub
		
	}

}

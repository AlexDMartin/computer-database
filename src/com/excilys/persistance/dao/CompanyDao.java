package com.excilys.persistance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.excilys.persistance.Company;

public class CompanyDao implements Dao<Company>{
	private Connector connector;
	private CompanyMapper mapper;
	
	public CompanyDao() {
		this.connector = new Connector();
		this.mapper = new CompanyMapper();
	}
	
	@Override
	public Optional<Company> get(long id) {
		String transactionQuery = "select * from `computer-database-db`.`company` where id = " + id + " limit 1;";
		try{
			this.connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Company> getAll() {
		List<Company> resultList = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`company`;";
		try{
			ResultSet resultSet = this.connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
			resultList = this.mapper.map(resultSet) ;
			this.connector.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return resultList;		
	}

	@Override
	public void save(Company t) {
				
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

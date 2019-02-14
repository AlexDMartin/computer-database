package com.excilys.persistance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.persistance.mappers.CompanyMapper;
import com.excilys.persistance.model.Company;
import com.excilys.persistance.utils.Connector;

public class CompanyDao implements Dao<Company>{
	
	private Connector connector;
	private CompanyMapper mapper;
	
	public CompanyDao() {
		this.connector = new Connector();
		this.mapper = new CompanyMapper();
	}
	
	@Override
	public Optional<Company> get(long id) {
		List<Company> resultList = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`company` where id = " + id + " limit 1;";
		try{
			ResultSet resultSet = this.connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
			resultList = this.mapper.map(resultSet) ;
			this.connector.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return Optional.of(resultList.get(0));
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
	public void save(Company t) throws Exception{
		String transactionQuery = "insert into `computer-database-db`.`company` (NAME) values (";
		
		if(t.getName() == null) {	
			throw new Exception("Company\'s name should not be null");
		}
		transactionQuery += t.getName() + ")";
		
		try {
			this.connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void update(Company t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Company t) {
		return 0;
		// TODO Auto-generated method stub
		
	}

}

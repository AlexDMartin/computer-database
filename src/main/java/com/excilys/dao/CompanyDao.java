package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
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
		LoggerFactory.getLogger(this.getClass()).info("Company get called");
		List<Company> requestResult = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`company` where id = " + id + " limit 1;";
		try{
			ResultSet resultSet = this.connector.executeTransaction(transactionQuery);
			requestResult = this.mapper.map(resultSet) ;
			this.connector.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return Optional.of(requestResult.get(0));
	}

	@Override
	public List<Company> getAll() {
		LoggerFactory.getLogger(this.getClass()).info("Company getAll called");
		List<Company> requestResult = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`company`;";
		try{
			ResultSet resultSet = this.connector.executeTransaction(transactionQuery);
			requestResult = this.mapper.map(resultSet) ;
			this.connector.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return requestResult;		
	}

	@Override
	public void save(Company t) throws Exception{
		LoggerFactory.getLogger(this.getClass()).info("Company save called");
		String query = "insert into `computer-database-db`.`company` (NAME) values (";
		
		if(t.getName() == null) {	
			throw new Exception("Company\'s name should not be null");
		}
		query += t.getName() + ")";
		
		try {
			this.connector.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void update(Company t) {
		LoggerFactory.getLogger(this.getClass()).info("Company update called");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Company t) {
		LoggerFactory.getLogger(this.getClass()).info("Company delete called");
		// TODO Auto-generated method stub
		
	}

}

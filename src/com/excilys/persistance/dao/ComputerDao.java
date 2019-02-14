package com.excilys.persistance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.persistance.mappers.ComputerMapper;
import com.excilys.persistance.model.Computer;
import com.excilys.persistance.utils.Connector;

public class ComputerDao implements Dao<Computer>{

	private Connector connector;
	private ComputerMapper mapper;
	
	public ComputerDao() {
		this.connector = new Connector();
		this.mapper = new ComputerMapper();
	}
	
	@Override
	public Optional<Computer> get(long id) {
		List<Computer> requestResult = new ArrayList<>();
		try{
			String query = "select * from `computer-database-db`.`computer` where id = " + id + " limit 1;";	
			ResultSet resultSet = this.connector.executeTransaction(query);
			requestResult = this.mapper.map(resultSet) ;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return Optional.of(requestResult.get(0));
	}

	@Override
	public List<Computer> getAll() {
		List<Computer> requestResult = new ArrayList<>();
		try{
			String transactionQuery = "select * from `computer-database-db`.`computer`;";
			ResultSet resultSet = this.connector.executeTransaction(transactionQuery);
			requestResult = this.mapper.map(resultSet) ;
			this.connector.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return requestResult;		
	}

	@Override
	public void save(Computer t) {
		try {
			String fields = "NAME";
			String values = "\'"+ t.getName() +"\'";
			if(t.getIntroduced() != null) {
				fields += ", INTRODUCED";
				values += ", TIMESTAMP(\'"+ t.getIntroduced().toString() +"\')";
			}
			if(t.getIntroduced() != null) {
				fields += ", DISCONTINUED";
				values += ", TIMESTAMP(\'"+ t.getDiscontinued().toString() +"\')";
			}
			if(t.getCompanyId() > 0) {
				fields += ", COMPANY_ID";
				values += ", " + t.getCompanyId();
			}
			
			String query = "insert into `computer-database-db`.`computer` ("+ fields +") values ("+ values + ");";
			this.connector.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Computer t) {	
		try {
			String transactionQuery = "update `computer-database-db`.`computer` set "
					+ "NAME = \'" + t.getName() + "\' ";
			if(t.getIntroduced() != null) {
				transactionQuery += ", INTRODUCED = TIMESTAMP(\'"+ t.getIntroduced().toString() +"\')";
			}
			if(t.getIntroduced() != null) {
				transactionQuery += ", DISCONTINUED = TIMESTAMP(\'"+ t.getIntroduced().toString() +"\')";
			}
			if(t.getCompanyId() > 0) {
				transactionQuery += ", COMPANY_ID = "+ t.getCompanyId();
			}
			transactionQuery += " where ID = " + t.getId() + ";";
		
			this.connector.executeUpdate(transactionQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int delete(Computer t) {
		int requestResult = 0;
		try {
			String query = "delete from `computer-database-db`.`computer` where ID = " + t.getId()+ ";";
			requestResult = this.connector.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return requestResult;	
	}

}

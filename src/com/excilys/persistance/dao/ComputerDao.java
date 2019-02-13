package com.excilys.persistance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.excilys.persistance.mappers.ComputerMapper;
import com.excilys.persistance.model.Computer;
import com.excilys.persistance.utils.Connector;
import com.excilys.persistance.utils.DateFormator;

public class ComputerDao implements Dao<Computer>{

	private Connector connector;
	private ComputerMapper mapper;
	
	public ComputerDao() {
		this.connector = new Connector();
		this.mapper = new ComputerMapper();
	}
	
	/**
	 * 
	 */
	@Override
	public Optional<Computer> get(long id) {
		List<Computer> resultList = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`computer` where id = " + id + " limit 1;";
		try{
			ResultSet resultSet = this.connector.executeTransaction(connector.connection, connector.dbName, transactionQuery);
			resultList = this.mapper.map(resultSet) ;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return Optional.of(resultList.get(0));
	}

	@Override
	public List<Computer> getAll() {
		List<Computer> resultList = new ArrayList<>();
		String transactionQuery = "select * from `computer-database-db`.`computer`;";
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
	public void save(Computer t) {
		
		String fields = "NAME";
		String values = "\'"+ t.getName() +"\'";
//		if(false) {
//			fields += "INTRODUCED";
//			values += ", TIMESTAMP(\'"+ DateFormator.formatDate(t.getIntroduced())+"\')";
//		}
//		if(false) {
//			fields += "DISCONTINUED";
//			values += ", TIMESTAMP(\'"+ DateFormator.formatDate(t.getDiscontinued())+"\';";
//		}
		
		String transactionQuery = "insert into `computer-database-db`.`computer` ("+ fields +") values ("+ values + ");";
		
		try {
			this.connector.executeUpdate(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Computer t) {
		String transactionQuery = "update `computer-database-db`.`computer` set "
				+ "NAME = \'" + t.getName() + "\' ";
//		if(false) {
//			transactionQuery += "INTRODUCED = TIMESTAMP(\'"+ DateFormator.formatDate(t.getIntroduced())+"\')";
//		}
//		if(false) {
//			transactionQuery += "DISCONTINUED = TIMESTAMP(\'"+ DateFormator.formatDate(t.getDiscontinued())+"\'";
//		}
		transactionQuery += "where id = " + t.getId() + ";";
		System.out.println(transactionQuery);
		try {
			this.connector.executeUpdate(connector.connection, connector.dbName, transactionQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int delete(Computer t) {
		String transactionQuery = "delete from `computer-database-db`.`computer` where ID = " + t.getId()+ ";";
		int requestResult = 0;
		try {
			System.out.println("before");
			System.out.println("connector.connection" + connector.connection);
			System.out.println("connector.dbName"+ connector.dbName);
			requestResult = this.connector.executeUpdate(connector.connection, connector.dbName, transactionQuery);
			System.out.println("after");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return requestResult;
		
		
	}

}

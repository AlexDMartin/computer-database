package com.excilys.gui.interaction;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Computer;

public class CreateComputerInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		Computer computer = new Computer();
		System.out.println("--- Create a Computer ---");
	
		System.out.println("Enter name :");
		String name = readString(param.getScanner());
		computer.setName(name);
		
		System.out.println("Enter company id (or let blank):");
		int companyId = readInt(param.getScanner());
		computer.setCompanyId(companyId);
		
		readLine(param.getScanner());
		
		System.out.println("Enter introduce date ("+ computer.getIntroduced() +"):");
		String introducedString = readLine(param.getScanner());
		Timestamp introduced = formatDate(introducedString);
		computer.setIntroduced(introduced);
		if(introduced != null) {
			computer.setIntroduced(introduced);
		}
		
		System.out.println("Enter discontinued date ("+ computer.getDiscontinued() +"):");
		String discontinuedString = readLine(param.getScanner());
		Timestamp discontinued = formatDate(discontinuedString);
		if(discontinued != null) {
			computer.setDiscontinued(discontinued);
		}
		
		try {
			ComputerDao computerDao = new ComputerDao();			
			computerDao.save(computer);
			return new GUIOutput(1, UserChoice.NONE);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}		
		
		System.out.println(computer);
		return new GUIOutput(1, UserChoice.NONE);
	}
	
	private Timestamp formatDate(String date) {
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    java.util.Date parsedDate = dateFormat.parse(date);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch(Exception e) { 
		    System.out.println("Failed to parse date : " + e.getMessage());
		}
		return null;
	}
}

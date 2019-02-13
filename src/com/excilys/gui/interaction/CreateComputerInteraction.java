package com.excilys.gui.interaction;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Company;
import com.excilys.persistance.model.Computer;

public class CreateComputerInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		Computer computer = new Computer();
		System.out.println("--- Create a Computer ---");
		// Name.
		System.out.println("Enter name :");
		String name = readString(param.getScanner());
		computer.setName(name);
		
		// Company.
		System.out.println("Enter company id (or let blank):");
		int companyId = readInt(param.getScanner());
		
		// I'm not responsible for this.
		Company fakeCompany = new Company();
		fakeCompany.setId(companyId);
		computer.setCompany(fakeCompany);
		
		// Dates will be implemented later
		// Introduced.
//		try {
//			System.out.println("Enter Introduce Date (YYYY-MM-DD hh:mm:ss or let blank):");
//			String introducedDate = readString(param.getScanner()); 
//			Date dateIntroduced = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(introducedDate);
//			computer.setIntroduced(dateIntroduced);
//		} catch (ParseException e) {
//			System.out.println(e.getMessage());
//		}  
//	    
//	    
//		// Discontinued.
//		try {
//			System.out.println("Enter Discontinue Date (YYYY-MM-DD hh:mm:ss or let blank):");
//			String discontinuedDate = readString(param.getScanner());
//			Date dateDiscontinued = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(discontinuedDate);
//			computer.setDiscontinued(dateDiscontinued);
//		} catch (ParseException e) {
//			System.out.println(e.getMessage());
//		}  
		
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

}

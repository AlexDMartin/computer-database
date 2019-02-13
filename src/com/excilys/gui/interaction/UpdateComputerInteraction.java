package com.excilys.gui.interaction;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Company;
import com.excilys.persistance.model.Computer;

public class UpdateComputerInteraction extends UserImputable implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("--- Update a Computer ---");
		Computer updateComputer = new Computer();
		try {
			ComputerDao computerDao = new ComputerDao();
			System.out.println(param.getId());
			updateComputer = computerDao.get(param.getId()).get();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		
		if(updateComputer != null) {
			// Name.
			System.out.println("Enter name ("+ updateComputer.getName() +"):");
			String name = readString(param.getScanner());
			updateComputer.setName(name);
			
			// Company.
			System.out.println("Enter company id ("+ updateComputer.getCompany().getId() +"):");
			int companyId = readInt(param.getScanner());
			Company fakeCompany = new Company();
			fakeCompany.setId(companyId);
			updateComputer.setCompany(fakeCompany);
		}
		
		try {
			ComputerDao computerDao = new ComputerDao();			
			computerDao.update(updateComputer);
			return new GUIOutput(1, UserChoice.NONE);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}		
		
		return new GUIOutput(0, UserChoice.NONE);
	}

}

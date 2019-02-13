package com.excilys.gui.interaction;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Computer;

public class DeleteComputerInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			ComputerDao computerDao = new ComputerDao();

			Computer deleteComputer = new Computer();
			deleteComputer.setId(param.getId());
	
			int errorCode = computerDao.delete(deleteComputer);
			return new GUIOutput(errorCode, UserChoice.NONE);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		return new GUIOutput(0, UserChoice.NONE);
	}
}

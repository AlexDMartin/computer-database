package com.excilys.gui.interaction;

import java.util.List;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Computer;

public class ListComputerInteraction implements GUIInteraction{
	public GUIOutput execute(GUIInput param) {
		ComputerDao computerDao = new ComputerDao();
		System.out.println("--- Computer List ---");	
		List<Computer> computerList = computerDao.getAll();
		if(computerList != null) {
			for(Computer computer: computerList) {
				System.out.println(computer);
			}
			return new GUIOutput(1, UserChoice.NONE);
		}
		return  new GUIOutput(0, UserChoice.NONE);
	}
}

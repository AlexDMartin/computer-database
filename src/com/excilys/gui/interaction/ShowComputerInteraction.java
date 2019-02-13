package com.excilys.gui.interaction;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.persistance.dao.ComputerDao;
import com.excilys.persistance.model.Computer;

public class ShowComputerInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			ComputerDao computerDao = new ComputerDao();
			Optional<Computer> computer = computerDao.get(param.getId());
			System.out.println(computer.get());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		return null;
	}

}

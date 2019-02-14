package com.excilys.gui.interaction;

import java.util.Optional;

import com.excilys.persistance.model.Computer;
import com.excilys.service.ComputerService;

public class ShowComputerInteraction implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			ComputerService computerService = new ComputerService();
			Optional<Computer> computer = computerService.get(param.getId());
			System.out.println(computer.get());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}
		return null;
	}

}

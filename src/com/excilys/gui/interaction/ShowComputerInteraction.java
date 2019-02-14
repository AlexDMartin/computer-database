package com.excilys.gui.interaction;

import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Computer;
import com.excilys.service.ComputerService;

public class ShowComputerInteraction implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			ComputerService computerService = new ComputerService();
			Optional<Computer> computer = computerService.get(param.getId());
			LoggerFactory.getLogger(this.getClass()).info(computer.get() + " retrieved");
			System.out.println(computer.get());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("This id doesn\'t exist.");
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
		}
		return null;
	}

}

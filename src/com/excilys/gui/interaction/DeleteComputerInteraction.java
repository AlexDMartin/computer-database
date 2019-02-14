package com.excilys.gui.interaction;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Computer;
import com.excilys.service.ComputerService;

public class DeleteComputerInteraction implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			ComputerService computerService = new ComputerService();
			Computer deleteComputer = new Computer();

			deleteComputer.setId(param.getId());
			int errorCode = computerService.delete(deleteComputer);

			LoggerFactory.getLogger(this.getClass()).info("Computer deleted");
			return new GUIOutput(errorCode, UserChoice.NONE);
		} catch (IndexOutOfBoundsException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
			return new GUIOutput(0, UserChoice.NONE);
		}
	}
}

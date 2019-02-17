package com.excilys.gui.interaction;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Computer;
import com.excilys.service.ComputerService;

public class ListComputerInteraction implements GUIInteraction {
	public GUIOutput execute(GUIInput param) {
		ComputerService computerService = new ComputerService();
		System.out.println("--- Computer List ---");
		List<Computer> computerList = computerService.getAll();
		if (computerList != null) {
			for (Computer computer : computerList) {
				System.out.println(computer);
			}
			LoggerFactory.getLogger(this.getClass()).info("Computer list displayed");
			return new GUIOutput(1, UserChoice.NONE);
		}
		return new GUIOutput(0, UserChoice.NONE);
	}
}

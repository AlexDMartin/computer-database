package com.excilys.controller;

public class CreateComputerController {
	private static CreateComputerController createComputerControllerInstance = null;

	private CreateComputerController() {}

	public static CreateComputerController getInstance() {
		if (createComputerControllerInstance == null) {
			createComputerControllerInstance = new CreateComputerController();
		}
		return createComputerControllerInstance;
	}
}

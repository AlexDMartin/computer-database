package com.excilys.controller;

public class UpdateComputerController {
	private static UpdateComputerController updateComputerControllerInstance = null;

	private UpdateComputerController() {}

	public static UpdateComputerController getInstance() {
		if (updateComputerControllerInstance == null) {
			updateComputerControllerInstance = new UpdateComputerController();
		}
		return updateComputerControllerInstance;
	}
}

package com.excilys.controller;

public class DeleteComputerController {
	private static DeleteComputerController deleteComputerControllerInstance = null;

	private DeleteComputerController() {}

	public static DeleteComputerController getInstance() {
		if (deleteComputerControllerInstance == null) {
			deleteComputerControllerInstance = new DeleteComputerController();
		}
		return deleteComputerControllerInstance;
	}
}

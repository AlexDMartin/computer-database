package com.excilys.controller;

import org.slf4j.LoggerFactory;

public class MenuController {
	private static MenuController menuControllerInstance = null;
	
	private MenuController() {}
	
	public static MenuController getInstance() {
		if (menuControllerInstance == null) {
			menuControllerInstance = new MenuController();
		}
		return menuControllerInstance;
	}

	public void resolve(int input) {
		LoggerFactory.getLogger(this.getClass()).info("User typed : "+ input);
		
	}
}

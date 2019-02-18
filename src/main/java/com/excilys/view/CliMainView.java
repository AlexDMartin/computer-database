package com.excilys.view;

import java.util.Scanner;

import org.slf4j.LoggerFactory;

import com.excilys.controller.MenuController;

public class CliMainView {

	private static CliMainView cliMainViewInstance = null;
	
	private CliMainView() {
		LoggerFactory.getLogger(this.getClass()).info("Displaying main menu");
		
		System.out.println("-------------------\n " + "1 / List Computers\n " + "2 / List Companies\n "
				+ "3 / Show Details\n " + "4 / Create a Computer\n " + "5 / Update a Computer\n "
				+ "6 / Delete a Computer\n ");
		
		MenuController.getInstance().resolve();	
	}
	
	public static CliMainView getInstance() {
		if (cliMainViewInstance == null) {
			cliMainViewInstance = new CliMainView();
		}
		return cliMainViewInstance;
	}
	
}

package com.excilys.view;

public class CliMainView {

	private static CliMainView cliMainViewInstance = null;
	
	private CliMainView() {
		System.out.println("test");
	}
	
	public static CliMainView getInstance() {
		if (cliMainViewInstance == null) {
			cliMainViewInstance = new CliMainView();
		}
		return cliMainViewInstance;
	}
	
}

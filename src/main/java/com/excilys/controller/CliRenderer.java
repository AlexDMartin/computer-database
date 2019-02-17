package com.excilys.controller;

import org.slf4j.LoggerFactory;

import com.excilys.view.CliMainView;
import com.excilys.view.Main;

public class CliRenderer {

	public static void main(String[] args) {
		LoggerFactory.getLogger(Main.class).info("Main Started");
		CliMainView.getInstance();
	}

}

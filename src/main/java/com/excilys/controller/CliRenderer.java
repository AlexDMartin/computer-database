package com.excilys.controller;

import org.slf4j.LoggerFactory;

import com.excilys.view.CliMainView;

public class CliRenderer {
	
	public static void main(String[] args) {
		LoggerFactory.getLogger(CliRenderer.class).info("Main Started");

		CliMainView.getInstance();
	}

}

package com.excilys.gui.interaction;

import java.util.Scanner;

import com.excilys.persistance.model.Computer;

public class GUIInput {
	private Scanner scanner ;
	private int id = 0;
	private Computer computer;
	
	public GUIInput(Scanner scanner) {
		this.scanner = scanner;
	}	
	
	public GUIInput(Scanner scanner, int id) {
		this.scanner = scanner;
		this.id = id;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
}

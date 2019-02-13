package com.excilys.gui.interaction;

import java.util.Scanner;

public class GUIInput {
	private Scanner scanner ;
	private int id = 0;
	
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
}

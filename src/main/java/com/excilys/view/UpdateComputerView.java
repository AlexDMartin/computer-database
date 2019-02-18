package com.excilys.view;

import com.excilys.dao.model.Computer;

public class UpdateComputerView {
	
	private static UpdateComputerView updateComputerViewInstance = null;

	private UpdateComputerView() {}

	public static UpdateComputerView getInstance() {
		if (updateComputerViewInstance == null) {
			updateComputerViewInstance = new UpdateComputerView();
		}
		return updateComputerViewInstance;
	}
	
	public void askForId() {
		System.out.println("Enter the id of the computer you want to update :");
	}
	
	public void askForNewName(Computer computer) {
		System.out.println("Enter new name ("+ computer.getName() +"): ");
	}
	
	public void askForNewIntroduced(Computer computer) {
		System.out.println("Enter new introduced date ("+ computer.getIntroduced() +"): ");
	}
	
	public void askForNewDiscontinued(Computer computer) {
		System.out.println("Enter new discontinued date ("+ computer.getDiscontinued() +"): ");
	}
	
	public void askForNewCompany(Computer computer) {
		System.out.println("Enter new company id ("+ computer.getCompany() +"): ");
	}
}

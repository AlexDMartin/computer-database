package com.excilys.controller;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Computer;
import com.excilys.view.ListComputerView;

public class ListComputerController {

	private static ListComputerController listComputerControllerInstance = null;
	
	private ListComputerController() {
		LoggerFactory.getLogger(this.getClass()).info("Listing Computers");
		List<Computer> computerList = DaoFactory.getInstance().getComputerDao().getAll();
		ListComputerView.getInstance().render(computerList);
	}
	
	public static ListComputerController getInstance() {
		if(listComputerControllerInstance == null) {
			listComputerControllerInstance = new ListComputerController();
		}
		return listComputerControllerInstance;
	}
	
}

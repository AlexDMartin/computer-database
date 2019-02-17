package com.excilys.gui.interaction;

import java.sql.Timestamp;

import org.slf4j.LoggerFactory;

import com.excilys.persistance.model.Computer;
import com.excilys.persistance.utils.DateFormator;
import com.excilys.service.ComputerService;

public class CreateComputerInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		try {
			Computer computer = new Computer();
			System.out.println("--- Create a Computer ---");

			System.out.println("Enter name :");
			String name = readString(param.getScanner());
			computer.setName(name);

			System.out.println("Enter company id (or let blank):");
			int companyId = readInt(param.getScanner());
			computer.setCompanyId(companyId);

			readLine(param.getScanner());

			System.out.println("Enter introduce date (" + computer.getIntroduced() + "):");
			String introducedString = readLine(param.getScanner());
			Timestamp introduced = DateFormator.formatDate(introducedString);
			computer.setIntroduced(introduced);
			if (introduced != null) {
				computer.setIntroduced(introduced);
			}

			System.out.println("Enter discontinued date (" + computer.getDiscontinued() + "):");
			String discontinuedString = readLine(param.getScanner());
			Timestamp discontinued = DateFormator.formatDate(discontinuedString);
			if (discontinued != null) {
				computer.setDiscontinued(discontinued);
			}

			ComputerService computerService = new ComputerService();
			computerService.save(computer);
			LoggerFactory.getLogger(this.getClass()).info("Computer creation: " + computer);
			return new GUIOutput(1, UserChoice.NONE);
		} catch (IndexOutOfBoundsException e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
			return new GUIOutput(0, UserChoice.NONE);
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
			return new GUIOutput(0, UserChoice.NONE);
		}
	}

}

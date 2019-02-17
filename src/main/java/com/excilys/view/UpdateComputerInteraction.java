package com.excilys.view;

import java.sql.Timestamp;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.model.Computer;
import com.excilys.persistance.utils.DateFormator;
import com.excilys.service.ComputerService;

public class UpdateComputerInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("--- Update a Computer ---");
		Computer updateComputer = new Computer();
		try {
			ComputerService computerService = new ComputerService();
			System.out.println(param.getId());
			updateComputer = computerService.get(param.getId()).get();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}

		if (updateComputer != null) {
			System.out.println("Enter name (" + updateComputer.getName() + "):");
			String name = readString(param.getScanner());
			updateComputer.setName(name);

			System.out.println("Enter company id (" + updateComputer.getCompanyId() + "):");
			int companyId = readInt(param.getScanner());
			updateComputer.setCompanyId(companyId);

			readLine(param.getScanner());

			System.out.println("Enter introduce date (" + updateComputer.getIntroduced() + "):");
			String introducedString = readLine(param.getScanner());
			Timestamp introduced = DateFormator.formatDate(introducedString);
			updateComputer.setIntroduced(introduced);
			if (introduced != null) {
				updateComputer.setIntroduced(introduced);
			}

			System.out.println("Enter discontinued date (" + updateComputer.getDiscontinued() + "):");
			String discontinuedString = readLine(param.getScanner());
			Timestamp discontinued = DateFormator.formatDate(discontinuedString);
			if (discontinued != null) {
				updateComputer.setDiscontinued(discontinued);
			}
		}

		try {
			ComputerDao computerDao = new ComputerDao();
			computerDao.update(updateComputer);
			return new GUIOutput(1, UserChoice.NONE);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("This index do not exist [" + e.getMessage() + "]");
		}

		return new GUIOutput(0, UserChoice.NONE);
	}
}

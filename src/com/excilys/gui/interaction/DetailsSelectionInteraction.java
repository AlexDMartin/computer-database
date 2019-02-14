package com.excilys.gui.interaction;

import org.slf4j.LoggerFactory;

public class DetailsSelectionInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		LoggerFactory.getLogger(this.getClass()).info("Displaying details menu");
		System.out.println("--- Show Details ---\n" + "1 / Computer\n" + "2 / Company");
		int detailSelection = readInt(param.getScanner());
		UserChoice userChoice = null;
		switch (detailSelection) {
		case 1:
			userChoice = UserChoice.COMPUTER;
			break;
		case 2:
			userChoice = UserChoice.COMPANY;
			break;
		default:
			detailSelection = -1;
			break;
		}
		LoggerFactory.getLogger(this.getClass()).warn("User selected" + userChoice);
		return new GUIOutput(detailSelection, userChoice);
	}

}

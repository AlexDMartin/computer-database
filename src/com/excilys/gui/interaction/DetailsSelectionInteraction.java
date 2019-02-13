package com.excilys.gui.interaction;

public class DetailsSelectionInteraction extends UserImputable implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("--- Show Details ---\n"
				+ "1 / Computer\n"
				+ "2 / Company");
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
		return new GUIOutput(detailSelection, userChoice);
	}
	
}

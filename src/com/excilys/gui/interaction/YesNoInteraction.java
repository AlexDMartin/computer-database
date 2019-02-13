package com.excilys.gui.interaction;

import java.util.Scanner;

public class YesNoInteraction extends UserImputable implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("Are you sure?");
		String yesno = readString(param.getScanner());
		UserChoice userChoice = null;
		switch(yesno.charAt(0)) {
			case 'y':
				userChoice = UserChoice.YES;
				break;
			case 'n':
				userChoice = UserChoice.NO;
				break;
		}
		return new GUIOutput(1, userChoice);
	}

}

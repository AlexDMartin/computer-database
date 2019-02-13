package com.excilys.gui.interaction;

import java.util.Scanner;

public class IdSelectionInteraction extends UserImputable implements GUIInteraction{

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("Select the id");
		int id = readInt(param.getScanner());
		return new GUIOutput(id, UserChoice.NONE);
	}

}

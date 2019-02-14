package com.excilys.gui.interaction;

import org.slf4j.LoggerFactory;

public class MenuInteraction extends UserImputable implements GUIInteraction {
	public GUIOutput execute(GUIInput param) {
		LoggerFactory.getLogger(this.getClass()).info("Displaying main menu");
		System.out.println("------\nPick your poison :\n " + "1 / List Computers\n " + "2 / List Companies\n "
				+ "3 / Show Details\n " + "4 / Create a Computer\n " + "5 / Update a Computer\n "
				+ "6 / Delete a Computer\n ");

		return new GUIOutput(readInt(param.getScanner()), UserChoice.NONE);
	}
}

package com.excilys.gui.interaction;

import java.util.Scanner;

public class MenuInteraction extends UserImputable implements GUIInteraction {
	public GUIOutput execute(GUIInput param) {
		System.out.println("Faites votre choix :\n "
				+ "1 / List Computers\n "
				+ "2 / List Companies\n "
				+ "3 / Show Details\n "
				+ "4 / Create a Computer\n "
				+ "5 / Update a Computer\n "
				+ "6 / Delete a Computer\n ");
		
		return new GUIOutput(readInt(param.getScanner()), UserChoice.NONE);
	}
}

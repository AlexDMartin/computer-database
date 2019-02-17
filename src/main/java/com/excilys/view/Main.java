package com.excilys.view;

import java.util.Scanner;

import org.slf4j.LoggerFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		LoggerFactory.getLogger(Main.class).info("Main Started");
		Scanner scanner = new Scanner(System.in);
		GUIInteractionFactory guii = new GUIInteractionFactory();
		boolean exited = false;

		while (!exited) {
			guii.getGUIInteraction(Interaction.CLEAR).execute(new GUIInput(scanner));
			int menuSelection = guii.getGUIInteraction(Interaction.MAIN_MENU).execute(new GUIInput(scanner))
					.getErrorCode();
			switch (menuSelection) {
			case 1:
				guii.getGUIInteraction(Interaction.LIST_COMPUTER).execute(new GUIInput(scanner));
				break;
			case 2:
				guii.getGUIInteraction(Interaction.LIST_COMPANY).execute(new GUIInput(scanner));
				break;
			case 3:
				GUIOutput details = guii.getGUIInteraction(Interaction.DETAILS).execute(new GUIInput(scanner));
				int id = 0;
				switch (details.getUserChoice()) {
				case COMPUTER:
					id = guii.getGUIInteraction(Interaction.ID).execute(new GUIInput(scanner)).getErrorCode();
					guii.getGUIInteraction(Interaction.COMPUTER).execute(new GUIInput(scanner, id));
					break;
				case COMPANY:
					id = guii.getGUIInteraction(Interaction.ID).execute(new GUIInput(scanner)).getErrorCode();
					guii.getGUIInteraction(Interaction.COMPANY).execute(new GUIInput(scanner, id));
					break;
				default:
					break;
				}
				break;
			case 4:
				guii.getGUIInteraction(Interaction.CREATE).execute(new GUIInput(scanner));
				break;
			case 5:
				id = guii.getGUIInteraction(Interaction.ID).execute(new GUIInput(scanner)).getErrorCode();
				guii.getGUIInteraction(Interaction.UPDATE).execute(new GUIInput(scanner, id));
				break;
			case 6:
				id = guii.getGUIInteraction(Interaction.ID).execute(new GUIInput(scanner)).getErrorCode();
				guii.getGUIInteraction(Interaction.COMPUTER).execute(new GUIInput(scanner, id));
				GUIOutput yesno = guii.getGUIInteraction(Interaction.YESNO).execute(new GUIInput(scanner));
				if (yesno.getUserChoice() == UserChoice.YES) {
					guii.getGUIInteraction(Interaction.DELETE).execute(new GUIInput(scanner, id)).getErrorCode();
				}
				break;
			default:
				exited = true;
				guii.getGUIInteraction(Interaction.CLEAR).execute(new GUIInput(scanner));
				break;
			}
			LoggerFactory.getLogger(Main.class).info("Main loop restarted");
		}

		scanner.close();
	}

}

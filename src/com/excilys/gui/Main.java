package com.excilys.gui;

import java.util.Scanner;

import com.excilys.gui.interaction.GUIInput;
import com.excilys.gui.interaction.GUIInteractionFactory;
import com.excilys.gui.interaction.GUIOutput;
import com.excilys.gui.interaction.Interaction;
import com.excilys.gui.interaction.UserChoice;


public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		GUIInteractionFactory guii = new GUIInteractionFactory();
		boolean exited = false;
		
		while(!exited) {
			guii.getGUIInteraction(Interaction.CLEAR).execute(new GUIInput(scanner));
			int menuSelection = guii.getGUIInteraction(Interaction.MAIN_MENU).execute(new GUIInput(scanner)).getErrorCode();
			switch(menuSelection) {
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
					if(yesno.getUserChoice() == UserChoice.YES) {
						int deletionCode = guii.getGUIInteraction(Interaction.DELETE).execute(new GUIInput(scanner, id)).getErrorCode(); 
						if(deletionCode == 1) {
							System.out.println("Deletion Successful!");
						}
					}
					break;
				default:
					exited = true;
					guii.getGUIInteraction(Interaction.CLEAR).execute(new GUIInput(scanner));
					break;
			}

//					switch(detailSelection) {
//						case 1:
//							// Return the computer with the selected id.
//							try {
//								Optional<Computer> computer = computerDao.get(idSelection);
//								System.out.println(computer.get());
//							} catch(IndexOutOfBoundsException e) {
//								System.out.println("This index do not exist [" + e.getMessage() + "]");
//							}
//							break;
//						case 2:
//							// Return the company with the selected id.
//							try {
//								Optional<Company> company = companyDao.get(idSelection);
//								System.out.println(company.get());
//							} catch(IndexOutOfBoundsException e) {
//								System.out.println("This index do not exist [" + e.getMessage() + "]");
//							}
//							break;
//					}
//					break;
//				case 4:
//					// Get new Computer from user input.
//					System.out.println("--- New Computer creation ---\n"
//							+ "Enter computer\'s name :\n");
//					String nameInput = readUserInput(input);
//					
//					// Create the new Computer object.
//					Computer computer = new Computer();
//					computer.setName(nameInput);
//					computerDao.save(computer);
//					break;
//				case 5:
//					System.out.println("--- Update a computer ---\n"
//							+ "Which computer do you want to update?");
//					long idUpdateSelector = Long.parseLong(readUserInput(input));
//					// Preview the selected computer to update.
//					try {
//						Optional<Computer> updateComputer = computerDao.get(idUpdateSelector);
//						System.out.println(updateComputer.get());
//					} catch(IndexOutOfBoundsException e) {
//						System.out.println("This index do not exist [" + e.getMessage() + "]");
//					}
//					System.out.println("Are you sure you want to update this computer? [y/N]");
//					break;
//				case 6:
//					System.out.println("--- Delete a computer ---\n"
//							+ "Which computer do you want to delete?");
//					// Preview the selected computer to delete.
//					long idDeletionSelector = Long.parseLong(readUserInput(input));
//					try {
//						Optional<Computer> deleteComputer = computerDao.get(idDeletionSelector);
//						System.out.println(deleteComputer.get());
//						System.out.println("Are you sure you want to delete this computer? [y/N]");
//						char idDeletionValidator = readUserInput(input).charAt(0);
//						if(idDeletionValidator == 'y') {
//							int result = computerDao.delete(deleteComputer.get());	
//							if(result != 0) {
//								System.out.println("Deletion successful!");
//							}
//						} else {
//							System.out.println("Mission aborted!");
//						}
//					} catch(IndexOutOfBoundsException e) {
//						System.out.println("This index do not exist [" + e.getMessage() + "]");
//					}
//					break;
//				default:
//					break;
//			}
		}
		
		scanner.close();
	}
	
	
}

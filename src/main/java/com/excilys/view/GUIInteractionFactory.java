package com.excilys.view;

public class GUIInteractionFactory {
	/**
	 * @author Alex Martin
	 * @param interaction
	 * @return GUIInteraction
	 */
	public GUIInteraction getGUIInteraction(Interaction interaction) {
		switch (interaction) {
		case MAIN_MENU:
			return new MenuInteraction();
		case LIST_COMPUTER:
			return new ListComputerInteraction();
		case LIST_COMPANY:
			return new ListCompanyInteraction();
		case DETAILS:
			return new DetailsSelectionInteraction();
		case YESNO:
			return new YesNoInteraction();
		case COMPANY:
			return new ShowCompanyInteraction();
		case COMPUTER:
			return new ShowComputerInteraction();
		case ID:
			return new IdSelectionInteraction();
		case UPDATE:
			return new UpdateComputerInteraction();
		case CREATE:
			return new CreateComputerInteraction();
		case DELETE:
			return new DeleteComputerInteraction();
		case CLEAR:
			return new ClearInteraction();
		default:
			return new ClearInteraction();
		}
	}

}

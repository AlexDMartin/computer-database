package com.excilys.view;

import org.slf4j.LoggerFactory;

public class IdSelectionInteraction extends UserImputable implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		System.out.println("--- Select the id ---");
		int id = readInt(param.getScanner());
		LoggerFactory.getLogger(this.getClass()).info("User selected id #:" + id);
		return new GUIOutput(id, UserChoice.NONE);
	}
}

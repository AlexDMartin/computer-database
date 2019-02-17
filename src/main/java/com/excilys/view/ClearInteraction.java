package com.excilys.view;

import org.slf4j.LoggerFactory;

public class ClearInteraction implements GUIInteraction {

	@Override
	public GUIOutput execute(GUIInput param) {
		try {

			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
			return new GUIOutput(1, UserChoice.NONE);
		} catch (final Exception e) {
			LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
			return new GUIOutput(0, UserChoice.NONE);
		}
	}

}

package com.excilys.gui.interaction;

public class GUIOutput {
	private int errorCode;
	private UserChoice userChoice;

	public GUIOutput(int errorCode, UserChoice userChoice) {
		this.errorCode = errorCode;
		this.userChoice = userChoice;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public UserChoice getUserChoice() {
		return userChoice;
	}

	public void setUserChoice(UserChoice userChoice) {
		this.userChoice = userChoice;
	}
}

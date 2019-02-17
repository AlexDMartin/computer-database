package com.excilys.gui.interaction;

import java.util.Scanner;

public class UserImputable {
	public String readString(Scanner scan) {
		return scan.next();
	}

	public String readLine(Scanner scan) {
		return scan.nextLine();
	}

	public int readInt(Scanner scan) {
		return scan.nextInt();
	}
}

package com.senla.ui.base;

import java.util.Scanner;

public class MenuController {
	private Builder builder;
	private Navigator navigator;

	public MenuController() {
		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
	}

	public void run() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			navigator.printMenu();
			Integer index = scanner.nextInt();
			navigator.navigate(index);
		}

	}
}

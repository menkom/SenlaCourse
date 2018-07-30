package com.senla.ui.base;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.senla.util.DisplayOperator;

public class MenuController {
	private Builder builder;
	private Navigator navigator;

	public MenuController() {
		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
	}

	public void run() {
		try (Scanner scanner = new Scanner(System.in)) {
			Integer index = 0;
			while (index != navigator.getCurrentMenu().getMenuItems().size() - 1) {
				navigator.printMenu();
				try {
					index = scanner.nextInt();
				} catch (InputMismatchException e) {
					DisplayOperator.printMessage("You have to enter menu number.");
				}
				navigator.navigate(index);
			}
		}
	}
}

package com.senla.ui.base;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.senla.util.DisplayOperator;

public class MenuController {
	private Builder builder;
	private Navigator navigator;

	private static boolean keepRunning;

	public MenuController() {
		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
		keepRunning = true;
	}

	public static void changeKeepRunning() {
		keepRunning = !keepRunning;
	}

	public void run() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (keepRunning) {
			navigator.printMenu();
			Integer index = navigator.getCurrentMenu().getMenuItems().size() - 1;
			try {
				index = scanner.nextInt();
			} catch (InputMismatchException e) {
				DisplayOperator.printMessage("You have to enter menu number.");
			}
			navigator.navigate(index);
		}

	}
}

package com.senla.ui.base;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.senla.util.DisplayOperator;

public class MenuController {
	private static final Logger logger = Logger.getLogger(MenuController.class);

	private Builder builder;
	private Navigator navigator;

	public MenuController() {
		this.builder = new Builder();
		this.builder.buildMenu();
		this.navigator = new Navigator(builder.getRootMenu());
	}

	public void run() {
		Integer index = 0;
		try (Scanner scanner = new Scanner(System.in)) {
			while (index != navigator.getCurrentMenu().getMenuItems().size() - 1) {
				navigator.printMenu();
				try {
					index = Integer.parseInt(scanner.nextLine());
					navigator.navigate(index);
				} catch (InputMismatchException e) {
					DisplayOperator.printMessage("You have to enter menu number.");
					logger.error(e);
				} catch (NoSuchElementException e) {
					DisplayOperator.printMessage("Wrong menu number.");
					logger.error(e);
				}
			}
		}

	}
}

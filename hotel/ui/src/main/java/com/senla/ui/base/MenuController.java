package com.senla.ui.base;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import com.senla.ui.util.Input;
import com.senla.ui.util.DisplayOperator;

public class MenuController {
	private static final String NEED_MENU_NUMBER = "You have to enter menu number.";
	private static final String WRONG_MENU_NUMBER = "Wrong menu number.";
	private static final String ERROR_NOT_NUMBER = "You have to enter number.";

	private static final Logger logger = Logger.getLogger(MenuController.class);
	private static boolean inProgress;

	private Builder builder;
	private Navigator navigator;

	public MenuController() {
		this.builder = new Builder();
		this.builder.buildMenu();
		this.navigator = new Navigator(builder.getRootMenu());
		MenuController.inProgress = true;
	}

	public void run() {
		Integer index = 0;
		while (inProgress) {
			navigator.printMenu();
			try {
				index = Input.inputInteger();
				navigator.navigate(index);
			} catch (InputMismatchException e) {
				DisplayOperator.printMessage(NEED_MENU_NUMBER);
				logger.error(e);
			} catch (NoSuchElementException e) {
				DisplayOperator.printMessage(WRONG_MENU_NUMBER);
				logger.error(e);
			} catch (NumberFormatException e) {
				DisplayOperator.printMessage(ERROR_NOT_NUMBER);
				logger.error(e);
			}
		}

	}

	public static boolean isInProgress() {
		return inProgress;
	}

	public static void setInProgress(boolean inProgress) {
		MenuController.inProgress = inProgress;
	}
}

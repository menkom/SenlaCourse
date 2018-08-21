package com.senla.ui.base;

import org.apache.log4j.Logger;

import com.senla.ui.util.DisplayOperator;

public class Navigator {

	private static final Logger logger = Logger.getLogger(Navigator.class);

	private Menu currentMenu;

	public Navigator(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);

		if (currentMenu == null) {
			DisplayOperator.printMessage("Throw error.");
		}
		DisplayOperator.printMessage(currentMenu.getName());
		for (int i = 0; i < getCurrentMenu().getMenuItems().size(); i++) {
			DisplayOperator.printMessage(i + ". " + getCurrentMenu().getMenuItems().get(i).getTitle());
		}
	}

	public void navigate(int index) {
		try {
			if (currentMenu.getMenuItems().get(index) != null) {
				if (currentMenu.getMenuItems().get(index).getAction() != null) {
					currentMenu.getMenuItems().get(index).doAction();
				} else {
					currentMenu = currentMenu.getMenuItems().get(index).getNextMenu();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			DisplayOperator.printMessage("There is no menu item with number " + index + ". It have to be from 0 to "
					+ (currentMenu.getMenuItems().size() - 1) + ".");
			logger.error(e);
		}
	}
}

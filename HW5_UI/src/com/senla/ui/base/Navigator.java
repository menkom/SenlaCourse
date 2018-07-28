package com.senla.ui.base;

import com.senla.util.DisplayOperator;

public class Navigator {

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
		if (currentMenu == null) {
			DisplayOperator.printMessage("Throw error.");
		}
		DisplayOperator.printMessage(currentMenu.getName());
		for (int i = 0; i < getCurrentMenu().getMenuItems().length; i++) {
			DisplayOperator.printMessage(i + ". " + getCurrentMenu().getMenuItems()[i].getTitle());
		}
	}

	public void navigate(int index) {
		try {
			if (currentMenu.getMenuItems()[index] != null) {
				if (currentMenu.getMenuItems()[index].getAction() != null) {
					currentMenu.getMenuItems()[index].doAction();
				} else {
					currentMenu = currentMenu.getMenuItems()[index].getNextMenu();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

			DisplayOperator.printMessage("There is no menu item with number " + index);
		}

	}
}

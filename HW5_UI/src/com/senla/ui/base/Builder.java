package com.senla.ui.base;

import com.senla.ui.action.ExitAction;

public class Builder {

	private Menu rootMenu;

	public void buildMenu() {
		Menu mainMenu = new Menu("Main menu");

		this.rootMenu = mainMenu;

		Menu roomsMenu = new Menu("Room options");

		MenuItem roomsMenuItem = new MenuItem();
		roomsMenuItem.setTitle("Room options");
		roomsMenuItem.setNextMenu(roomsMenu);
		mainMenu.addMenuItem(roomsMenuItem);

		MenuItem backMenuItem = new MenuItem();
		backMenuItem.setTitle("Go back");
		backMenuItem.setNextMenu(mainMenu);
		roomsMenu.addMenuItem(backMenuItem);

		MenuItem exit = new MenuItem();
		exit.setTitle("Exit");
		exit.setAction(new ExitAction());
		mainMenu.addMenuItem(exit);

	}

	public Menu getRootMenu() {
		return rootMenu;
	}
}

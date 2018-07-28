package com.senla.ui.base;

import com.senla.util.ArrayOperator;

public class Menu {

	private String name;
	private MenuItem[] menuItems;

	public Menu(String menuName) {
		this.name = menuName;
		menuItems = new MenuItem[0];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(MenuItem[] menuItems) {
		this.menuItems = menuItems;
	}

	public void addMenuItem(MenuItem menuItem) {
		menuItems = (MenuItem[]) ArrayOperator.add(menuItems, menuItem);
	}
}

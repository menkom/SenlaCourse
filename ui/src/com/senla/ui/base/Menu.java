package com.senla.ui.base;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String name;
	private List<MenuItem> menuItems;

	public Menu(String menuName) {
		this.name = menuName;
		this.menuItems = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
}

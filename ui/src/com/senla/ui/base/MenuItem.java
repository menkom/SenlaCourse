package com.senla.ui.base;

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem() {
		super();
		this.title = "";
		this.action = null;
		this.nextMenu = null;
	}

	public MenuItem(String title, Menu nextMenu) {
		super();
		this.title = title;
		this.action = null;
		this.nextMenu = nextMenu;
	}

	public MenuItem(String title, IAction action) {
		super();
		this.title = title;
		this.action = action;
		this.nextMenu = null;
	}

	public void doAction() {
		if (getAction() != null) {
			getAction().execute();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

}

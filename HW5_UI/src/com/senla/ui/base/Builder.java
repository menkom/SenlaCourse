package com.senla.ui.base;

import com.senla.ui.action.ExitAction;
import com.senla.ui.action.OrderRoom;
import com.senla.ui.action.ShowActiveOrdersSortByName;
import com.senla.ui.action.ShowAllClients;
import com.senla.ui.action.ShowAllRoomsSortByPrice;
import com.senla.ui.action.ShowAllServicesSortByPrice;
import com.senla.ui.action.ShowFreeRoomsByDateSortByPrice;
import com.senla.ui.action.ShowLastThreeOrdersByRoom;
import com.senla.ui.action.ShowNumberOfClients;
import com.senla.ui.action.ShowNumberOfFreeRooms;
import com.senla.ui.action.ShowOrderPrice;
import com.senla.ui.action.ShowOrderServices;

public class Builder {

	private Menu rootMenu;

	private void buildRoomSubMenu(Menu upLevelMenu) {

		Menu roomsMenu = new Menu("Room options");

		MenuItem roomsMenuItem = new MenuItem();
		roomsMenuItem.setTitle("Room options");
		roomsMenuItem.setNextMenu(roomsMenu);
		upLevelMenu.addMenuItem(roomsMenuItem);

		MenuItem showAllRoomsSortByPriceMenuItem = new MenuItem();
		showAllRoomsSortByPriceMenuItem.setTitle("Show all rooms sorted by price.");
		showAllRoomsSortByPriceMenuItem.setAction(new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByPriceMenuItem = new MenuItem();
		showFreeRoomsByDateSortByPriceMenuItem.setTitle("Show free rooms by date sorted by price.");
		showFreeRoomsByDateSortByPriceMenuItem.setAction(new ShowFreeRoomsByDateSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByPriceMenuItem);

		MenuItem showNumberOfFreeRoomsMenuItem = new MenuItem();
		showNumberOfFreeRoomsMenuItem.setTitle("Show number of free rooms at this moment.");
		showNumberOfFreeRoomsMenuItem.setAction(new ShowNumberOfFreeRooms());
		roomsMenu.addMenuItem(showNumberOfFreeRoomsMenuItem);

		MenuItem showRoomInfoMenuItem = new MenuItem();
		showRoomInfoMenuItem.setTitle("Show room info.");
		showRoomInfoMenuItem.setAction(new ShowRoomInfo());
		roomsMenu.addMenuItem(showRoomInfoMenuItem);

		
		MenuItem orderRoomMenuItem = new MenuItem();
		orderRoomMenuItem.setTitle("Order room.");
		orderRoomMenuItem.setAction(new OrderRoom());
		roomsMenu.addMenuItem(orderRoomMenuItem);
		
		MenuItem backMenuItem = new MenuItem();
		backMenuItem.setTitle("Go back");
		backMenuItem.setNextMenu(upLevelMenu);
		roomsMenu.addMenuItem(backMenuItem);

	}

	private void buildClientSubMenu(Menu upLevelMenu) {

		Menu clientsMenu = new Menu("Client options");

		MenuItem clientsMenuItem = new MenuItem();
		clientsMenuItem.setTitle("Client options");
		clientsMenuItem.setNextMenu(clientsMenu);
		upLevelMenu.addMenuItem(clientsMenuItem);

		MenuItem showAllClientsMenuItem = new MenuItem();
		showAllClientsMenuItem.setTitle("Show all clients.");
		showAllClientsMenuItem.setAction(new ShowAllClients());
		clientsMenu.addMenuItem(showAllClientsMenuItem);

		MenuItem showNumberOfClientsMenuItem = new MenuItem();
		showNumberOfClientsMenuItem.setTitle("Show number of clients.");
		showNumberOfClientsMenuItem.setAction(new ShowNumberOfClients());
		clientsMenu.addMenuItem(showNumberOfClientsMenuItem);

		MenuItem backMenuItem = new MenuItem();
		backMenuItem.setTitle("Go back");
		backMenuItem.setNextMenu(upLevelMenu);
		clientsMenu.addMenuItem(backMenuItem);
	}

	private void buildServiceSubMenu(Menu upLevelMenu) {

		Menu servicesMenu = new Menu("Service options");

		MenuItem servicesMenuItem = new MenuItem();
		servicesMenuItem.setTitle("Service options");
		servicesMenuItem.setNextMenu(servicesMenu);
		upLevelMenu.addMenuItem(servicesMenuItem);

		MenuItem showAllServicesSortByPriceMenuItem = new MenuItem();
		showAllServicesSortByPriceMenuItem.setTitle("Show all services.");
		showAllServicesSortByPriceMenuItem.setAction(new ShowAllServicesSortByPrice());
		servicesMenu.addMenuItem(showAllServicesSortByPriceMenuItem);

		MenuItem backMenuItem = new MenuItem();
		backMenuItem.setTitle("Go back");
		backMenuItem.setNextMenu(upLevelMenu);
		servicesMenu.addMenuItem(backMenuItem);
	}

	private void buildOrderSubMenu(Menu upLevelMenu) {

		Menu ordersMenu = new Menu("Order options");

		MenuItem ordersMenuItem = new MenuItem();
		ordersMenuItem.setTitle("Order options");
		ordersMenuItem.setNextMenu(ordersMenu);
		upLevelMenu.addMenuItem(ordersMenuItem);

		MenuItem showActiveOrdersSortByNameMenuItem = new MenuItem();
		showActiveOrdersSortByNameMenuItem.setTitle("Show active orders.");
		showActiveOrdersSortByNameMenuItem.setAction(new ShowActiveOrdersSortByName());
		ordersMenu.addMenuItem(showActiveOrdersSortByNameMenuItem);

		MenuItem showOrderPriceMenuItem = new MenuItem();
		showOrderPriceMenuItem.setTitle("Show order price.");
		showOrderPriceMenuItem.setAction(new ShowOrderPrice());
		ordersMenu.addMenuItem(showOrderPriceMenuItem);

		MenuItem showLastThreeOrdersByRoomMenuItem = new MenuItem();
		showLastThreeOrdersByRoomMenuItem.setTitle("Show last three room orders.");
		showLastThreeOrdersByRoomMenuItem.setAction(new ShowLastThreeOrdersByRoom());
		ordersMenu.addMenuItem(showLastThreeOrdersByRoomMenuItem);

		MenuItem showOrderServicesMenuItem = new MenuItem();
		showOrderServicesMenuItem.setTitle("Show order services.");
		showOrderServicesMenuItem.setAction(new ShowOrderServices());
		ordersMenu.addMenuItem(showOrderServicesMenuItem);

		MenuItem backMenuItem = new MenuItem();
		backMenuItem.setTitle("Go back");
		backMenuItem.setNextMenu(upLevelMenu);
		ordersMenu.addMenuItem(backMenuItem);
	}

	public void buildMenu() {
		Menu mainMenu = new Menu("Main menu");

		this.rootMenu = mainMenu;

		// Main menu

		buildRoomSubMenu(mainMenu);
		buildClientSubMenu(mainMenu);
		buildServiceSubMenu(mainMenu);
		buildOrderSubMenu(mainMenu);

		MenuItem exit = new MenuItem();
		exit.setTitle("Exit");
		exit.setAction(new ExitAction());
		mainMenu.addMenuItem(exit);

	}

	public Menu getRootMenu() {
		return rootMenu;
	}
}

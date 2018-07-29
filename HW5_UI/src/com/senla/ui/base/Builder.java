package com.senla.ui.base;

import com.senla.ui.action.AddClient;
import com.senla.ui.action.AddOrderService;
import com.senla.ui.action.AddRoom;
import com.senla.ui.action.AddService;
import com.senla.ui.action.ChangeRoomPrice;
import com.senla.ui.action.ChangeServicePrice;
import com.senla.ui.action.ExitAction;
import com.senla.ui.action.FreeRoom;
import com.senla.ui.action.OrderRoom;
import com.senla.ui.action.ShowActiveOrdersSortByFinishDate;
import com.senla.ui.action.ShowActiveOrdersSortByName;
import com.senla.ui.action.ShowAllClients;
import com.senla.ui.action.ShowAllRoomsSortByCapacity;
import com.senla.ui.action.ShowAllRoomsSortByPrice;
import com.senla.ui.action.ShowAllServicesSortByPrice;
import com.senla.ui.action.ShowFreeRoomsByDateSortByCapacity;
import com.senla.ui.action.ShowFreeRoomsByDateSortByPrice;
import com.senla.ui.action.ShowFreeRoomsByDateSortByStar;
import com.senla.ui.action.ShowFreeRoomsSortByCapacity;
import com.senla.ui.action.ShowFreeRoomsSortByPrice;
import com.senla.ui.action.ShowFreeRoomsSortByStar;
import com.senla.ui.action.ShowLastThreeOrdersByRoom;
import com.senla.ui.action.ShowNumberOfClients;
import com.senla.ui.action.ShowNumberOfFreeRooms;
import com.senla.ui.action.ShowOrderPrice;
import com.senla.ui.action.ShowOrderServices;
import com.senla.ui.action.ShowRoomInfo;

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

		MenuItem showAllRoomsSortByCapacityMenuItem = new MenuItem();
		showAllRoomsSortByCapacityMenuItem.setTitle("Show all rooms sorted by capacity.");
		showAllRoomsSortByCapacityMenuItem.setAction(new ShowAllRoomsSortByCapacity());
		roomsMenu.addMenuItem(showAllRoomsSortByCapacityMenuItem);

		MenuItem showAllRoomsSortByStarMenuItem = new MenuItem();
		showAllRoomsSortByStarMenuItem.setTitle("Show all rooms sorted by star.");
		showAllRoomsSortByStarMenuItem.setAction(new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByCapacityMenuItem = new MenuItem();
		showFreeRoomsSortByCapacityMenuItem.setTitle("Show free rooms sorted by capacity.");
		showFreeRoomsSortByCapacityMenuItem.setAction(new ShowFreeRoomsSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsSortByCapacityMenuItem);

		MenuItem showFreeRoomsSortByStarMenuItem = new MenuItem();
		showFreeRoomsSortByStarMenuItem.setTitle("Show free rooms sorted by star.");
		showFreeRoomsSortByStarMenuItem.setAction(new ShowFreeRoomsSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByPriceMenuItem = new MenuItem();
		showFreeRoomsSortByPriceMenuItem.setTitle("Show free rooms sorted by price.");
		showFreeRoomsSortByPriceMenuItem.setAction(new ShowFreeRoomsSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByCapacityMenuItem = new MenuItem();
		showFreeRoomsByDateSortByCapacityMenuItem.setTitle("Show free rooms by date sorted by capacity.");
		showFreeRoomsByDateSortByCapacityMenuItem.setAction(new ShowFreeRoomsByDateSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByCapacityMenuItem);

		MenuItem showFreeRoomsByDateSortByPriceMenuItem = new MenuItem();
		showFreeRoomsByDateSortByPriceMenuItem.setTitle("Show free rooms by date sorted by price.");
		showFreeRoomsByDateSortByPriceMenuItem.setAction(new ShowFreeRoomsByDateSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByStarMenuItem = new MenuItem();
		showFreeRoomsByDateSortByStarMenuItem.setTitle("Show free rooms by date sorted by star.");
		showFreeRoomsByDateSortByStarMenuItem.setAction(new ShowFreeRoomsByDateSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByStarMenuItem);

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

		MenuItem closeOrderMenuItem = new MenuItem();
		closeOrderMenuItem.setTitle("Close order.");
		closeOrderMenuItem.setAction(new FreeRoom());
		roomsMenu.addMenuItem(closeOrderMenuItem);

		MenuItem addRoomMenuItem = new MenuItem();
		addRoomMenuItem.setTitle("Add new room.");
		addRoomMenuItem.setAction(new AddRoom());
		roomsMenu.addMenuItem(addRoomMenuItem);

		MenuItem changeRoomPriceMenuItem = new MenuItem();
		changeRoomPriceMenuItem.setTitle("Change room price.");
		changeRoomPriceMenuItem.setAction(new ChangeRoomPrice());
		roomsMenu.addMenuItem(changeRoomPriceMenuItem);

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

		MenuItem addClientMenuItem = new MenuItem();
		addClientMenuItem.setTitle("Add new client.");
		addClientMenuItem.setAction(new AddClient());
		clientsMenu.addMenuItem(addClientMenuItem);

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

		MenuItem addServiceMenuItem = new MenuItem();
		addServiceMenuItem.setTitle("Add new service.");
		addServiceMenuItem.setAction(new AddService());
		servicesMenu.addMenuItem(addServiceMenuItem);

		MenuItem changeServicePriceMenuItem = new MenuItem();
		changeServicePriceMenuItem.setTitle("Change service price.");
		changeServicePriceMenuItem.setAction(new ChangeServicePrice());
		servicesMenu.addMenuItem(changeServicePriceMenuItem);

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
		showActiveOrdersSortByNameMenuItem.setTitle("Show active orders sort by client name.");
		showActiveOrdersSortByNameMenuItem.setAction(new ShowActiveOrdersSortByName());
		ordersMenu.addMenuItem(showActiveOrdersSortByNameMenuItem);

		MenuItem showActiveOrdersSortByFinishDateMenuItem = new MenuItem();
		showActiveOrdersSortByFinishDateMenuItem.setTitle("Show active orders sort by finish date.");
		showActiveOrdersSortByFinishDateMenuItem.setAction(new ShowActiveOrdersSortByFinishDate());
		ordersMenu.addMenuItem(showActiveOrdersSortByFinishDateMenuItem);

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

		MenuItem addOrderServiceMenuItem = new MenuItem();
		addOrderServiceMenuItem.setTitle("Add service to order.");
		addOrderServiceMenuItem.setAction(new AddOrderService());
		ordersMenu.addMenuItem(addOrderServiceMenuItem);

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

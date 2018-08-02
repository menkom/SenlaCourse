package com.senla.ui.base;

import com.senla.ui.action.ExitAction;
import com.senla.ui.action.client.AddClient;
import com.senla.ui.action.client.ShowAllClients;
import com.senla.ui.action.client.ShowNumberOfClients;
import com.senla.ui.action.order.AddOrderService;
import com.senla.ui.action.order.ShowActiveOrdersSortByFinishDate;
import com.senla.ui.action.order.ShowActiveOrdersSortByName;
import com.senla.ui.action.order.ShowLastThreeOrdersByRoom;
import com.senla.ui.action.order.ShowOrderPrice;
import com.senla.ui.action.order.ShowOrderServices;
import com.senla.ui.action.room.AddRoom;
import com.senla.ui.action.room.ChangeRoomPrice;
import com.senla.ui.action.room.FreeRoom;
import com.senla.ui.action.room.OrderRoom;
import com.senla.ui.action.room.ShowAllRoomsSortByCapacity;
import com.senla.ui.action.room.ShowAllRoomsSortByPrice;
import com.senla.ui.action.room.ShowFreeRoomsByDateSortByCapacity;
import com.senla.ui.action.room.ShowFreeRoomsByDateSortByPrice;
import com.senla.ui.action.room.ShowFreeRoomsByDateSortByStar;
import com.senla.ui.action.room.ShowFreeRoomsSortByCapacity;
import com.senla.ui.action.room.ShowFreeRoomsSortByPrice;
import com.senla.ui.action.room.ShowFreeRoomsSortByStar;
import com.senla.ui.action.room.ShowNumberOfFreeRooms;
import com.senla.ui.action.room.ShowRoomInfo;
import com.senla.ui.action.service.AddService;
import com.senla.ui.action.service.ChangeServicePrice;
import com.senla.ui.action.service.ShowAllServicesSortByPrice;

public class Builder {

	private Menu rootMenu;

	private void buildRoomSubMenu(Menu upLevelMenu) {

		Menu roomsMenu = new Menu("Room options");

		MenuItem roomsMenuItem = new MenuItem("Room options", roomsMenu);
		upLevelMenu.addMenuItem(roomsMenuItem);

		MenuItem showAllRoomsSortByPriceMenuItem = new MenuItem("Show all rooms sorted by price.",
				new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByPriceMenuItem);

		MenuItem showAllRoomsSortByCapacityMenuItem = new MenuItem("Show all rooms sorted by capacity.",
				new ShowAllRoomsSortByCapacity());
		roomsMenu.addMenuItem(showAllRoomsSortByCapacityMenuItem);

		MenuItem showAllRoomsSortByStarMenuItem = new MenuItem("Show all rooms sorted by star.",
				new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByCapacityMenuItem = new MenuItem("Show free rooms sorted by capacity.",
				new ShowFreeRoomsSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsSortByCapacityMenuItem);

		MenuItem showFreeRoomsSortByStarMenuItem = new MenuItem("Show free rooms sorted by star.",
				new ShowFreeRoomsSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByPriceMenuItem = new MenuItem("Show free rooms sorted by price.",
				new ShowFreeRoomsSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByCapacityMenuItem = new MenuItem("Show free rooms by date sorted by capacity.",
				new ShowFreeRoomsByDateSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByCapacityMenuItem);

		MenuItem showFreeRoomsByDateSortByPriceMenuItem = new MenuItem("Show free rooms by date sorted by price.",
				new ShowFreeRoomsByDateSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByStarMenuItem = new MenuItem("Show free rooms by date sorted by star.",
				new ShowFreeRoomsByDateSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByStarMenuItem);

		MenuItem showNumberOfFreeRoomsMenuItem = new MenuItem("Show number of free rooms at this moment.",
				new ShowNumberOfFreeRooms());
		roomsMenu.addMenuItem(showNumberOfFreeRoomsMenuItem);

		MenuItem showRoomInfoMenuItem = new MenuItem("Show room info.", new ShowRoomInfo());
		roomsMenu.addMenuItem(showRoomInfoMenuItem);

		MenuItem orderRoomMenuItem = new MenuItem("Order room.", new OrderRoom());
		roomsMenu.addMenuItem(orderRoomMenuItem);

		MenuItem closeOrderMenuItem = new MenuItem("Close order.", new FreeRoom());
		roomsMenu.addMenuItem(closeOrderMenuItem);

		MenuItem addRoomMenuItem = new MenuItem("Add new room.", new AddRoom());
		roomsMenu.addMenuItem(addRoomMenuItem);

		MenuItem changeRoomPriceMenuItem = new MenuItem("Change room price.", new ChangeRoomPrice());
		roomsMenu.addMenuItem(changeRoomPriceMenuItem);

		MenuItem backMenuItem = new MenuItem("Go back", upLevelMenu);
		roomsMenu.addMenuItem(backMenuItem);

	}

	private void buildClientSubMenu(Menu upLevelMenu) {

		Menu clientsMenu = new Menu("Client options");

		MenuItem clientsMenuItem = new MenuItem("Client options", clientsMenu);
		upLevelMenu.addMenuItem(clientsMenuItem);

		MenuItem showAllClientsMenuItem = new MenuItem("Show all clients.", new ShowAllClients());
		clientsMenu.addMenuItem(showAllClientsMenuItem);

		MenuItem showNumberOfClientsMenuItem = new MenuItem("Show number of clients.", new ShowNumberOfClients());
		clientsMenu.addMenuItem(showNumberOfClientsMenuItem);

		MenuItem addClientMenuItem = new MenuItem("Add new client.", new AddClient());
		clientsMenu.addMenuItem(addClientMenuItem);

		MenuItem backMenuItem = new MenuItem("Go back", upLevelMenu);
		clientsMenu.addMenuItem(backMenuItem);
	}

	private void buildServiceSubMenu(Menu upLevelMenu) {

		Menu servicesMenu = new Menu("Service options");

		MenuItem servicesMenuItem = new MenuItem("Service options", servicesMenu);
		upLevelMenu.addMenuItem(servicesMenuItem);

		MenuItem showAllServicesSortByPriceMenuItem = new MenuItem("Show all services.",
				new ShowAllServicesSortByPrice());
		servicesMenu.addMenuItem(showAllServicesSortByPriceMenuItem);

		MenuItem addServiceMenuItem = new MenuItem("Add new service.", new AddService());
		servicesMenu.addMenuItem(addServiceMenuItem);

		MenuItem changeServicePriceMenuItem = new MenuItem("Change service price.", new ChangeServicePrice());
		servicesMenu.addMenuItem(changeServicePriceMenuItem);

		MenuItem backMenuItem = new MenuItem("Go back", upLevelMenu);
		servicesMenu.addMenuItem(backMenuItem);
	}

	private void buildOrderSubMenu(Menu upLevelMenu) {

		Menu ordersMenu = new Menu("Order options");

		MenuItem ordersMenuItem = new MenuItem("Order options", ordersMenu);
		upLevelMenu.addMenuItem(ordersMenuItem);

		MenuItem showActiveOrdersSortByNameMenuItem = new MenuItem("Show active orders sort by client name.",
				new ShowActiveOrdersSortByName());
		ordersMenu.addMenuItem(showActiveOrdersSortByNameMenuItem);

		MenuItem showActiveOrdersSortByFinishDateMenuItem = new MenuItem("Show active orders sort by finish date.",
				new ShowActiveOrdersSortByFinishDate());
		ordersMenu.addMenuItem(showActiveOrdersSortByFinishDateMenuItem);

		MenuItem showOrderPriceMenuItem = new MenuItem("Show order price.", new ShowOrderPrice());
		ordersMenu.addMenuItem(showOrderPriceMenuItem);

		MenuItem showLastThreeOrdersByRoomMenuItem = new MenuItem("Show last three room orders.",
				new ShowLastThreeOrdersByRoom());
		ordersMenu.addMenuItem(showLastThreeOrdersByRoomMenuItem);

		MenuItem showOrderServicesMenuItem = new MenuItem("Show order services.", new ShowOrderServices());
		ordersMenu.addMenuItem(showOrderServicesMenuItem);

		MenuItem addOrderServiceMenuItem = new MenuItem("Add service to order.", new AddOrderService());
		ordersMenu.addMenuItem(addOrderServiceMenuItem);

		MenuItem backMenuItem = new MenuItem("Go back", upLevelMenu);
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

		MenuItem exit = new MenuItem("Exit", new ExitAction());
		mainMenu.addMenuItem(exit);

	}

	public Menu getRootMenu() {
		return rootMenu;
	}
}

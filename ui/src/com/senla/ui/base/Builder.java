package com.senla.ui.base;

import com.senla.ui.action.ExitAction;
import com.senla.ui.action.client.AddClient;
import com.senla.ui.action.client.ExportClientAction;
import com.senla.ui.action.client.ShowAllClients;
import com.senla.ui.action.client.ShowNumberOfClients;
import com.senla.ui.action.order.AddOrderService;
import com.senla.ui.action.order.CloneOrder;
import com.senla.ui.action.order.ShowActiveOrdersSortByFinishDate;
import com.senla.ui.action.order.ShowActiveOrdersSortByName;
import com.senla.ui.action.order.ShowLastOrdersByRoom;
import com.senla.ui.action.order.ShowOrderPrice;
import com.senla.ui.action.order.ShowOrderServices;
import com.senla.ui.action.room.AddRoom;
import com.senla.ui.action.room.ChangeRoomPrice;
import com.senla.ui.action.room.ChangeRoomStatus;
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

	private static final String MAIN_MENU_TEXT = "Main menu";

	private static final String ROOM_OPTIONS = "Room options";
	private static final String SHOW_ALL_ROOMS_SORT_PRICE = "Show all rooms sorted by price.";
	private static final String SHOW_ALL_ROOMS_SORT_CAPACITY = "Show all rooms sorted by capacity.";
	private static final String SHOW_ALL_ROOMS_SORT_STAR = "Show all rooms sorted by star.";
	private static final String SHOW_FREE_ROOMS_SORT_CAPACITY = "Show free rooms sorted by capacity.";
	private static final String SHOW_FREE_ROOMS_SORT_STAR = "Show free rooms sorted by star.";
	private static final String SHOW_FREE_ROOMS_SORT_PRICE = "Show free rooms sorted by price.";
	private static final String SHOW_FREE_ROOMS_BY_DATE_SORT_CAPACITY = "Show free rooms by date sorted by capacity.";
	private static final String SHOW_FREE_ROOMS_BY_DATE_SORT_PRICE = "Show free rooms by date sorted by price.";
	private static final String SHOW_FREE_ROOMS_BY_DATE_SORT_STAR = "Show free rooms by date sorted by star.";
	private static final String SHOW_NUMBER_FREE_ROOMS = "Show number of free rooms at this moment.";
	private static final String SHOW_ROOM_INFO = "Show room info.";
	private static final String SHOW_ORDER_ROOM = "Order room.";
	private static final String CLOSE_ORDER = "Close order.";
	private static final String ADD_NEW_ROOM = "Add new room.";
	private static final String CHANGE_ROOM_PRICE = "Change room price.";
	private static final String CHANGE_ROOM_STATUS = "Change room status.";
	private static final String BACK_MENU_TEXT = "Go back";

	private static final String CLIENT_OPTIONS = "Client options";
	private static final String SHOW_ALL_CLIENTS = "Show all clients.";
	private static final String SHOW_NUMBER_OF_CLIENTS = "Show number of clients.";
	private static final String ADD_NEW_CLIENT = "Add new client.";
	private static final String EXPORT_CLIENT = "Export client";

	private static final String SERVICE_OPTIONS = "Service options";
	private static final String SHOW_ALL_SERVICES = "Show all services sort by price.";
	private static final String ADD_SERVICE = "Add new service.";
	private static final String CHANGE_SERVICE_PRICE = "Change service price.";

	private static final String ORDER_OPTIONS = "Order options";
	private static final String SHOW_ACTIVE_ORDERS_SORT_NAME = "Show active orders sort by client name.";
	private static final String SHOW_ACTIVE_ORDERS_SORT_FINISH_DATE = "Show active orders sort by finish date.";
	private static final String SHOW_ORDER_PRICE = "Show order price.";
	private static final String SHOW_LAST_ROOM_ORDERS = "Show last room orders.";
	private static final String SHOW_ORDER_SERVICES = "Show order services.";
	private static final String ADD_SERVICE_ORDER = "Add service to order.";
	private static final String CLONE_ORDER = "Clone order.";

	private static final String EXIT_MENU_TEXT = "Exit";

	private Menu rootMenu;

	private void buildRoomSubMenu(Menu upLevelMenu) {

		Menu roomsMenu = new Menu(ROOM_OPTIONS);

		MenuItem roomsMenuItem = new MenuItem(ROOM_OPTIONS, roomsMenu);
		upLevelMenu.addMenuItem(roomsMenuItem);

		MenuItem showAllRoomsSortByPriceMenuItem = new MenuItem(SHOW_ALL_ROOMS_SORT_PRICE,
				new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByPriceMenuItem);

		MenuItem showAllRoomsSortByCapacityMenuItem = new MenuItem(SHOW_ALL_ROOMS_SORT_CAPACITY,
				new ShowAllRoomsSortByCapacity());
		roomsMenu.addMenuItem(showAllRoomsSortByCapacityMenuItem);

		MenuItem showAllRoomsSortByStarMenuItem = new MenuItem(SHOW_ALL_ROOMS_SORT_STAR, new ShowAllRoomsSortByPrice());
		roomsMenu.addMenuItem(showAllRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByCapacityMenuItem = new MenuItem(SHOW_FREE_ROOMS_SORT_CAPACITY,
				new ShowFreeRoomsSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsSortByCapacityMenuItem);

		MenuItem showFreeRoomsSortByStarMenuItem = new MenuItem(SHOW_FREE_ROOMS_SORT_STAR,
				new ShowFreeRoomsSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsSortByStarMenuItem);

		MenuItem showFreeRoomsSortByPriceMenuItem = new MenuItem(SHOW_FREE_ROOMS_SORT_PRICE,
				new ShowFreeRoomsSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByCapacityMenuItem = new MenuItem(SHOW_FREE_ROOMS_BY_DATE_SORT_CAPACITY,
				new ShowFreeRoomsByDateSortByCapacity());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByCapacityMenuItem);

		MenuItem showFreeRoomsByDateSortByPriceMenuItem = new MenuItem(SHOW_FREE_ROOMS_BY_DATE_SORT_PRICE,
				new ShowFreeRoomsByDateSortByPrice());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByPriceMenuItem);

		MenuItem showFreeRoomsByDateSortByStarMenuItem = new MenuItem(SHOW_FREE_ROOMS_BY_DATE_SORT_STAR,
				new ShowFreeRoomsByDateSortByStar());
		roomsMenu.addMenuItem(showFreeRoomsByDateSortByStarMenuItem);

		MenuItem showNumberOfFreeRoomsMenuItem = new MenuItem(SHOW_NUMBER_FREE_ROOMS, new ShowNumberOfFreeRooms());
		roomsMenu.addMenuItem(showNumberOfFreeRoomsMenuItem);

		MenuItem showRoomInfoMenuItem = new MenuItem(SHOW_ROOM_INFO, new ShowRoomInfo());
		roomsMenu.addMenuItem(showRoomInfoMenuItem);

		MenuItem orderRoomMenuItem = new MenuItem(SHOW_ORDER_ROOM, new OrderRoom());
		roomsMenu.addMenuItem(orderRoomMenuItem);

		MenuItem closeOrderMenuItem = new MenuItem(CLOSE_ORDER, new FreeRoom());
		roomsMenu.addMenuItem(closeOrderMenuItem);

		MenuItem addRoomMenuItem = new MenuItem(ADD_NEW_ROOM, new AddRoom());
		roomsMenu.addMenuItem(addRoomMenuItem);

		MenuItem changeRoomPriceMenuItem = new MenuItem(CHANGE_ROOM_PRICE, new ChangeRoomPrice());
		roomsMenu.addMenuItem(changeRoomPriceMenuItem);

		MenuItem changeRoomStatusMenuItem = new MenuItem(CHANGE_ROOM_STATUS, new ChangeRoomStatus());
		roomsMenu.addMenuItem(changeRoomStatusMenuItem);

		MenuItem backMenuItem = new MenuItem(BACK_MENU_TEXT, upLevelMenu);
		roomsMenu.addMenuItem(backMenuItem);

	}

	private void buildClientSubMenu(Menu upLevelMenu) {

		Menu clientsMenu = new Menu(CLIENT_OPTIONS);

		MenuItem clientsMenuItem = new MenuItem(CLIENT_OPTIONS, clientsMenu);
		upLevelMenu.addMenuItem(clientsMenuItem);

		MenuItem showAllClientsMenuItem = new MenuItem(SHOW_ALL_CLIENTS, new ShowAllClients());
		clientsMenu.addMenuItem(showAllClientsMenuItem);

		MenuItem showNumberOfClientsMenuItem = new MenuItem(SHOW_NUMBER_OF_CLIENTS, new ShowNumberOfClients());
		clientsMenu.addMenuItem(showNumberOfClientsMenuItem);

		MenuItem addClientMenuItem = new MenuItem(ADD_NEW_CLIENT, new AddClient());
		clientsMenu.addMenuItem(addClientMenuItem);

		MenuItem exportClientMenuItem = new MenuItem(EXPORT_CLIENT, new ExportClientAction());
		clientsMenu.addMenuItem(exportClientMenuItem);

		MenuItem backMenuItem = new MenuItem(BACK_MENU_TEXT, upLevelMenu);
		clientsMenu.addMenuItem(backMenuItem);
	}

	private void buildServiceSubMenu(Menu upLevelMenu) {

		Menu servicesMenu = new Menu(SERVICE_OPTIONS);

		MenuItem servicesMenuItem = new MenuItem(SERVICE_OPTIONS, servicesMenu);
		upLevelMenu.addMenuItem(servicesMenuItem);

		MenuItem showAllServicesSortByPriceMenuItem = new MenuItem(SHOW_ALL_SERVICES, new ShowAllServicesSortByPrice());
		servicesMenu.addMenuItem(showAllServicesSortByPriceMenuItem);

		MenuItem addServiceMenuItem = new MenuItem(ADD_SERVICE, new AddService());
		servicesMenu.addMenuItem(addServiceMenuItem);

		MenuItem changeServicePriceMenuItem = new MenuItem(CHANGE_SERVICE_PRICE, new ChangeServicePrice());
		servicesMenu.addMenuItem(changeServicePriceMenuItem);

		MenuItem backMenuItem = new MenuItem(BACK_MENU_TEXT, upLevelMenu);
		servicesMenu.addMenuItem(backMenuItem);
	}

	private void buildOrderSubMenu(Menu upLevelMenu) {

		Menu ordersMenu = new Menu(ORDER_OPTIONS);

		MenuItem ordersMenuItem = new MenuItem(ORDER_OPTIONS, ordersMenu);
		upLevelMenu.addMenuItem(ordersMenuItem);

		MenuItem showActiveOrdersSortByNameMenuItem = new MenuItem(SHOW_ACTIVE_ORDERS_SORT_NAME,
				new ShowActiveOrdersSortByName());
		ordersMenu.addMenuItem(showActiveOrdersSortByNameMenuItem);

		MenuItem showActiveOrdersSortByFinishDateMenuItem = new MenuItem(SHOW_ACTIVE_ORDERS_SORT_FINISH_DATE,
				new ShowActiveOrdersSortByFinishDate());
		ordersMenu.addMenuItem(showActiveOrdersSortByFinishDateMenuItem);

		MenuItem showOrderPriceMenuItem = new MenuItem(SHOW_ORDER_PRICE, new ShowOrderPrice());
		ordersMenu.addMenuItem(showOrderPriceMenuItem);

		MenuItem showLastOrdersByRoomMenuItem = new MenuItem(SHOW_LAST_ROOM_ORDERS, new ShowLastOrdersByRoom());
		ordersMenu.addMenuItem(showLastOrdersByRoomMenuItem);

		MenuItem showOrderServicesMenuItem = new MenuItem(SHOW_ORDER_SERVICES, new ShowOrderServices());
		ordersMenu.addMenuItem(showOrderServicesMenuItem);

		MenuItem addOrderServiceMenuItem = new MenuItem(ADD_SERVICE_ORDER, new AddOrderService());
		ordersMenu.addMenuItem(addOrderServiceMenuItem);

		MenuItem cloneOrderMenuItem = new MenuItem(CLONE_ORDER, new CloneOrder());
		ordersMenu.addMenuItem(cloneOrderMenuItem);

		MenuItem backMenuItem = new MenuItem(BACK_MENU_TEXT, upLevelMenu);
		ordersMenu.addMenuItem(backMenuItem);
	}

	public void buildMenu() {
		Menu mainMenu = new Menu(MAIN_MENU_TEXT);

		this.rootMenu = mainMenu;

		buildRoomSubMenu(mainMenu);
		buildClientSubMenu(mainMenu);
		buildServiceSubMenu(mainMenu);
		buildOrderSubMenu(mainMenu);

		MenuItem exit = new MenuItem(EXIT_MENU_TEXT, new ExitAction());
		mainMenu.addMenuItem(exit);

	}

	public Menu getRootMenu() {
		return rootMenu;
	}
}

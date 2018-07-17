package com.senla;

import com.senla.datahelp.DataFiller;
import com.senla.hotel.Hotel;
import com.senla.util.DateOperator;
import com.senla.util.DisplayOperator;

public class Executive {

	public static void main(String[] args) throws Throwable {

		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Hotel hotel = new Hotel();

		// hotel.load(dbPath);

		new DataFiller(hotel).runDataIO();

		DisplayOperator.printService(hotel.getClientService());
		DisplayOperator.printService(hotel.getRoomService());
		DisplayOperator.printService(hotel.getServiceService());
		DisplayOperator.printService(hotel.getOrderService());

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "All rooms sorted by capacity" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getAllRoomsSortByCapacity());

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "Free rooms sort by star" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getFreeRoomsSortByStar());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE + "List of current orders (clients and rooms)"
				+ DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getClientRoomSortByName());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage("Number of free rooms : " + hotel.getNumberOfFreeRooms());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage("Number of clients : " + hotel.getNumberOfFreeRooms());

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "Rooms free at 16/07/2018 date" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getFreeRoomsByDateSortByCapacity(DateOperator.getStringToDate("16/07/2018")));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Price for order #3" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage(Integer.toString(hotel.getOrderPrice(3)));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Info about Room #44" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printRoomInfo(hotel.getRoomByNum(44));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Last three orders" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getLastThreeOrdersByRoom(44));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Services to Order #1" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getOrderServices(1));

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE + "All services" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printArray(hotel.getAllServicesSortByPrice());

		hotel.save(dbPath);

	}

}

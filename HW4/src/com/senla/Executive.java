package com.senla;

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

		hotel.load(dbPath);

		// new DataFiller(hotel).runDataIO();

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

		hotel.save(dbPath);

	}

}

package com.senla.exec;

import java.text.SimpleDateFormat;

import com.senla.datahelp.DataFiller;
import com.senla.hotel.facade.Hotel;
import com.senla.util.DisplayOperator;

public class Executive {

	public static void main(String[] args) throws Throwable {

		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Hotel.getInstance().load(dbPath);

		// new DataFiller().runDataIO();

		DisplayOperator.printClients(Hotel.getInstance().getAllClients());
		DisplayOperator.printRooms(Hotel.getInstance().getAllRoomsSortByPrice());

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "All rooms sorted by capacity" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printRooms(Hotel.getInstance().getAllRoomsSortByCapacity());

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "Free rooms sort by star" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printRooms(Hotel.getInstance().getFreeRoomsSortByStar());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE + "List of current orders (clients and rooms)"
				+ DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printOrders(Hotel.getInstance().getActiveOrdersSortByName());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage("Number of free rooms : " + Hotel.getInstance().getNumberOfFreeRooms());

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage("Number of clients : " + Hotel.getInstance().getNumberOfClients());

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		DisplayOperator.printMessage(
				DisplayOperator.SEPARATOR_LINE + "Rooms free at 16/07/2018 date" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printRooms(Hotel.getInstance().getFreeRoomsByDateSortByCapacity(formatter.parse("16/07/2018")));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Price for order #3" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printMessage(Integer.toString(Hotel.getInstance().getOrderPrice(3)));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Info about Room #44" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printRoomInfo(Hotel.getInstance().getRoomByNum(44));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Last three orders" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printOrders(Hotel.getInstance().getLastThreeOrdersByRoom(44));

		DisplayOperator
				.printMessage(DisplayOperator.SEPARATOR_LINE + "Services to Order #1" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printServices(Hotel.getInstance().getOrderServices(1));

		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE + "All services" + DisplayOperator.SEPARATOR_LINE);
		DisplayOperator.printServices(Hotel.getInstance().getAllServicesSortByPrice());

		// Hotel.getInstance().save(dbPath);

	}

}

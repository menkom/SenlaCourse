package com.senla;

import com.senla.hotel.Hotel;
import com.senla.util.DisplayOperator;

public class Executive {

	public static void main(String[] args) throws Throwable {

		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Hotel hotel = new Hotel();

		hotel.load(dbPath);

//		 new DataFiller(hotel).runDataIO();

		DisplayOperator.printService(hotel.getClientService());
		DisplayOperator.printService(hotel.getRoomService());
		DisplayOperator.printService(hotel.getServiceService());
		DisplayOperator.printService(hotel.getOrderService());

		hotel.save(dbPath);

	}

}

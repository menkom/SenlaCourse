package com.senla;

import com.senla.hotel.Hotel;
import com.senla.util.DispayOperator;

public class Executive {

	public static void main(String[] args) throws Throwable {

		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Hotel hotel = new Hotel();

		hotel.load(dbPath);

		// new DataFiller(hotel).runDataIO();

		new DispayOperator().printService(hotel.getClientService());
		new DispayOperator().printService(hotel.getRoomService());
		new DispayOperator().printService(hotel.getServiceService());
		new DispayOperator().printService(hotel.getOrderService());

		hotel.save(dbPath);

	}

}

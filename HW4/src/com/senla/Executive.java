package com.senla;

import com.senla.datahelp.DataFiller;
import com.senla.hotel.Hotel;
import com.senla.util.DispayOperator;

public class Executive {

	public static void main(String[] args) throws Throwable {
		Hotel hotel = new Hotel();

//		 hotel.load();

		new DataFiller(hotel).runDataIO();

		new DispayOperator().printService(hotel.getClientService());
		new DispayOperator().printService(hotel.getRoomService());
		new DispayOperator().printService(hotel.getServiceService());
		new DispayOperator().printService(hotel.getOrderService());

		hotel.save();

	}

}

package com.senla;

import java.io.IOException;
import java.text.ParseException;

import com.senla.datahelp.DataFiller;
import com.senla.hotel.Hotel;
import com.senla.util.DispayOperator;

public class Executive {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Hotel hotel = new Hotel();

		try {
			new DataFiller(hotel).runDataIO();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new DispayOperator().printService(hotel.getClientService());
		new DispayOperator().printService(hotel.getOrderService());
		new DispayOperator().printService(hotel.getRoomService());
		new DispayOperator().printService(hotel.getServiceService());

	}

}

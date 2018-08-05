package com.senla.exec;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.property.HotelProperty;
import com.senla.ui.base.MenuController;

public class Executive {

	public static void main(String[] args) {

		HotelProperty.getInstance();
		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Hotel.getInstance().load(dbPath);

		MenuController menuController = new MenuController();
		menuController.run();

		Hotel.getInstance().save(dbPath);

	}

}

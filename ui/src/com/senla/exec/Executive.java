package com.senla.exec;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.property.HotelProperty;
import com.senla.ui.base.MenuController;
import com.senla.util.DisplayOperator;

public class Executive {

	private static final String ERROR_LOAD = "Error while data loading.";
	private static final String ERROR_SAVE = "Error while data saving.";

	public static void main(String[] args) {

		HotelProperty.getInstance();
		String dbPath = "./";

		if (args.length > 0) {
			dbPath = args[0];
		}

		Boolean loaded = Hotel.getInstance().load(dbPath);
		if (!loaded) {
			DisplayOperator.printMessage(ERROR_LOAD);
		}

		MenuController menuController = new MenuController();
		menuController.run();

		Boolean saved = Hotel.getInstance().save(dbPath);
		if (!saved) {
			DisplayOperator.printMessage(ERROR_SAVE);
		}

	}

}

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

		Boolean loaded = Hotel.getInstance().load(HotelProperty.getInstance().getRawFilePath());
		if (!loaded) {
			DisplayOperator.printMessage(ERROR_LOAD);
		}

		MenuController menuController = new MenuController();
		menuController.run();

		Boolean saved = Hotel.getInstance().save(HotelProperty.getInstance().getRawFilePath());
		if (!saved) {
			DisplayOperator.printMessage(ERROR_SAVE);
		}
	}

}

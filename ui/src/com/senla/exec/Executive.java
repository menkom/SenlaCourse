package com.senla.exec;

import com.senla.hotel.facade.Hotel;
import com.senla.util.DisplayOperator;

public class Executive {

	private static final String ERROR_LOAD = "Error while data loading.";
	private static final String ERROR_SAVE = "Error while data saving.";

	public static void main(String[] args) {
		Boolean loaded = Hotel.getInstance().load();
		if (!loaded) {
			DisplayOperator.printMessage(ERROR_LOAD);
		}

		loaded = Hotel.getInstance().importCsv();

		if (!loaded) {
			DisplayOperator.printMessage(ERROR_LOAD);
		} else {
			DisplayOperator.printMessage("Csv loaded.");
		}

//		MenuController menuController = new MenuController();
//		menuController.run();

		Boolean saved = Hotel.getInstance().save();
		if (!saved) {
			DisplayOperator.printMessage(ERROR_SAVE);
		}
		Hotel.getInstance().exportCsv();

	}

}

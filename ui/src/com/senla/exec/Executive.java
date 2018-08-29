package com.senla.exec;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.ui.base.MenuController;
import com.senla.ui.util.DisplayOperator;

public class Executive {

	private static final String ERROR_LOAD = "Error while data loading.";
	private static final String ERROR_SAVE = "Error while data saving.";

	private static IHotel hotel;

	public static void main(String[] args) {
		Boolean loaded;

		hotel = (IHotel) DependencyInjection.getInstance().getInterfacePair(IHotel.class);

//		loaded = Hotel.getInstance().load();
//		if (!loaded) {
//			DisplayOperator.printMessage(ERROR_LOAD);
//		}

		loaded = hotel.importCsv();

		if (!loaded) {
			DisplayOperator.printMessage(ERROR_LOAD);
		} else {
			DisplayOperator.printMessage("Csv loaded.");
		}

		MenuController menuController = new MenuController();
		menuController.run();

		Boolean saved = hotel.save();
		if (!saved) {
			DisplayOperator.printMessage(ERROR_SAVE);
		}
		hotel.exportCsv();

	}

}

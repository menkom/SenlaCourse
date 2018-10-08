package com.senla.exec;

import com.senla.di.DependencyInjection;
import com.senla.hotel.facade.api.IHotel;
import com.senla.ui.base.MenuController;
import com.senla.ui.util.DisplayOperator;

public class Executive {

	private static final String ERROR_SAVE = "Error while data saving.";

	private static IHotel hotel;

	public static void main(String[] args) {

		hotel = (IHotel) DependencyInjection.getInstance().getInterfacePair(IHotel.class);

		MenuController menuController = new MenuController();
		menuController.run();

		Boolean saved = hotel.save();
		if (!saved) {
			DisplayOperator.printMessage(ERROR_SAVE);
		}
		hotel.exportCsv();

	}

}

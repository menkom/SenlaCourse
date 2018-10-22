package com.senla.exec;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.MenuController;

public class Executive {

	public static void main(String[] args) {
		try {
			MenuController menuController = new MenuController();
			menuController.run();
		} finally {
			Hotel.getInstance().close();
		}
	}

}

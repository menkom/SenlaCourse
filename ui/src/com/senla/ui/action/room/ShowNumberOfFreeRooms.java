package com.senla.ui.action.room;

import com.senla.hotel.facade.Hotel;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowNumberOfFreeRooms implements IAction {

	private static final String ERROR_ROOMS_COUNTING = "Error during rooms counting.";
	private static final String ROOMS_NUMBER = "Number of free rooms :";

	@Override
	public void execute() {

		Integer freeRooms = Hotel.getInstance().getNumberOfFreeRooms();
		if (freeRooms == null) {
			DisplayOperator.printMessage(ERROR_ROOMS_COUNTING);
		} else {
			DisplayOperator.printMessage(ROOMS_NUMBER + freeRooms);
		}

	}

}

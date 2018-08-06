package com.senla.ui.action.room;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowAllRoomsSortByPrice implements IAction {

	private static final String ERROR_ROOMS_SEARCH = "Error during rooms search.";
	private static final String NO_ROOMS = "No rooms found.";

	@Override
	public void execute() {
		List<Room> rooms = Hotel.getInstance().getAllRoomsSortByPrice();
		if (rooms == null) {
			DisplayOperator.printMessage(ERROR_ROOMS_SEARCH);
		} else {
			if (rooms.size() > 0) {
				DisplayOperator.printRooms(rooms);
			} else {
				DisplayOperator.printMessage(NO_ROOMS);
			}
		}
	}

}

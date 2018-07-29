package com.senla.ui.action;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowFreeRoomsSortByCapacity implements IAction {

	@Override
	public void execute() {
		List<Room> rooms = Hotel.getInstance().getFreeRoomsSortByCapacity();
		if (rooms.size() > 0) {
			DisplayOperator.printRooms(rooms);
		} else {
			DisplayOperator.printMessage("No rooms found.");
		}
	}

}

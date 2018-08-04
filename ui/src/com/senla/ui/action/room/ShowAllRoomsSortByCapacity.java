package com.senla.ui.action.room;

import java.util.List;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.model.Room;
import com.senla.ui.base.IAction;
import com.senla.util.DisplayOperator;

public class ShowAllRoomsSortByCapacity implements IAction {

	@Override
	public void execute() {
		List<Room> rooms = Hotel.getInstance().getAllRoomsSortByCapacity();
		if (rooms.size() > 0) {
			DisplayOperator.printRooms(rooms);
		} else {
			DisplayOperator.printMessage("No rooms found.");

		}
	}

}

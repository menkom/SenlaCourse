package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Room;

public class RoomSortByCapacity implements Comparator<Room> {

	public int compare(Room room1, Room room2) {
		// 1.Because of add increment twice not by 1 we can have null elements in array so
		// we need to check on null.
		// 2.null element goes to end
		if (room1 == null) {
			return 1;
		} else if (room2 == null) {
			return -1;
		} else {
			return Integer.compare(room1.getCapacity(), room2.getCapacity());
		}

	}

}

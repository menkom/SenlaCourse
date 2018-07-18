package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Room;

public class RoomSortByPrice implements Comparator<Room> {

	public int compare(Room room1, Room room2) {
		if (room1 == null) {
			return 1;
		}
		if (room2 == null) {
			return -1;
		}
		return Integer.compare(room1.getPrice(), room2.getPrice());
	}

}

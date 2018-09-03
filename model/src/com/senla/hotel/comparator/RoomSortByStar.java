package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Room;

public class RoomSortByStar implements Comparator<Room> {

	public int compare(Room room1, Room room2) {
		if (room1 == null) {
			return -1;
		}
		if (room2 == null) {
			return 1;
		}
		if (room1.getStar() == null) {
			return -1;
		} else if (room2.getStar() == null) {
			return 1;
		} else {
			return room1.getStar().compareTo(room2.getStar());
		}
	}

}

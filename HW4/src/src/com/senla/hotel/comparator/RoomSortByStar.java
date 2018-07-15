package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Room;

public class RoomSortByStar implements Comparator<Room> {

	public int compare(Room room1, Room room2) {
		return room1.getStar().compareTo(room2.getStar());
	}

}

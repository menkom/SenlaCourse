package com.senla.converter;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomConverter {

	public static final String SEPARATOR = ";";

	public static Room getRoomFromString(String line) {
		String[] array = line.split(SEPARATOR);

		return new Room(Integer.parseInt(array[0]), Integer.parseInt(array[1]), RoomStar.valueOf(array[2]),
				RoomStatus.valueOf(array[3]), Integer.parseInt(array[4]));
	}

}

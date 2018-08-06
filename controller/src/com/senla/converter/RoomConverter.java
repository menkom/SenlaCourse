package com.senla.converter;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomConverter {

	public static final String SEPARATOR = ";";

	public static Room getRoomFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Room result = new Room(Integer.parseInt(array[1]), Integer.parseInt(array[2]), RoomStar.valueOf(array[3]),
				RoomStatus.valueOf(array[4]), Integer.parseInt(array[5]));

		result.setId(Integer.parseInt(array[0]));
		return result;
	}

}

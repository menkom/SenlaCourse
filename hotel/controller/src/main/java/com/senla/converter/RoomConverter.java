package com.senla.converter;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomConverter {

	public static final String SEPARATOR = ";";

	public static Room getRoomFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Room result = new Room();

		result.setId(Integer.parseInt(array[0]));
		result.setNumber(Integer.parseInt(array[1]));
		result.setCapacity(Integer.parseInt(array[2]));
		result.setStar(RoomStar.valueOf(array[3]));
		result.setStatus(RoomStatus.valueOf(array[4]));
		result.setPrice(Integer.parseInt(array[5]));

		return result;
	}

}

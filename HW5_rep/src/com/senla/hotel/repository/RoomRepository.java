package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Room;

public class RoomRepository {

	private List<Room> rooms;

	private static RoomRepository roomRepository;

	private RoomRepository() {
		super();
		this.rooms = new ArrayList<>();
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void add(Room element) {
		getRooms().add(element);
	}

	public void delete(Room element) {
		for (Room room : getRooms()) {
			if (room == element) {
				room = null;
			}
		}
	}

	public Room getRoomByNum(int number) {
		for (Room room : getRooms()) {
			if ((room != null) && (room.getNumber() == number)) {
				return room;
			}
		}
		return null;
	}

	public String[] toStringArray() {
		String[] result = new String[getRooms().size()];

		int i = 0;
		for (Room element : getRooms()) {
			if (element != null) {
				result[i] = element.toString();
				i++;
			}
		}
		return result;
	}

	public static RoomRepository getInstance() {
		if (roomRepository == null) {
			roomRepository = new RoomRepository();
		}
		return roomRepository;
	}

}

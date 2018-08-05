package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Room;

public class RoomRepository {

	private static RoomRepository roomRepository;

	private List<Room> rooms;

	private RoomRepository() {
		super();
		this.rooms = new ArrayList<Room>();
	}

	public static RoomRepository getInstance() {
		if (roomRepository == null) {
			roomRepository = new RoomRepository();
		}
		return roomRepository;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public Boolean add(Room room) {
		return getRooms().add(room);
	}

	public void delete(Integer roomNum) {
		for (int i = 0; i < getRooms().size(); i++) {
			if (getRooms().get(i).getNumber() == roomNum) {
				getRooms().remove(i);
				break;
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

}

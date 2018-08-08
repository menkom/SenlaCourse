package com.senla.hotel.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Room;
import com.senla.util.IdGenerator;

public class RoomRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8325685493155801835L;

	transient private static RoomRepository roomRepository;

	private Integer lastId;
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
		Integer id = IdGenerator.generateId(lastId);
		room.setId(id);
		Boolean result = getRooms().add(room);
		if (result) {
			lastId = id;
		}
		return result;
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

	public Room getRoomById(Integer id) {
		for (Room room : getRooms()) {
			if ((room != null) && (room.getId() == id)) {
				return room;
			}
		}
		return null;
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

}

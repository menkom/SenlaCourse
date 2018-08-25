package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
import com.senla.hotel.model.Room;
import com.senla.util.IdGenerator;

public class RoomRepository {

	private static RoomRepository roomRepository;

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

	public boolean add(Room room) {
		boolean result = false;
		if (room.getId() != null) {
			result = rooms.add(room);
		} else {

			Integer id = IdGenerator.generateId(lastId);
			room.setId(id);
			result = getRooms().add(room);
			if (result) {
				lastId = id;
			}
		}
		return result;
	}

	public boolean addAll(List<Room> rooms) {
		boolean result = getRooms().addAll(rooms);
		getInstance().setLastId(IdGenerator.getLastId(getInstance().getRooms()));
		return result;
	}

	public boolean delete(Integer roomNum) {
		boolean result = false;
		for (int i = 0; i < getRooms().size(); i++) {
			if (getRooms().get(i).getNumber().equals(roomNum)) {
				getRooms().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean deleteById(Integer id) {
		boolean result = false;
		for (int i = 0; i < rooms.size() - 1; i++) {
			if (rooms.get(i).getId().equals(id)) {
				rooms.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Room getRoomByNum(Integer number) {
		for (Room room : getRooms()) {
			if ((room != null) && (room.getNumber().equals(number))) {
				return room;
			}
		}
		return null;
	}

	public Room getRoomById(Integer id) {
		for (Room room : getRooms()) {
			if ((room != null) && (room.getId().equals(id))) {
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

	public boolean update(Room room) {
		boolean result = false;
		if (room != null) {
			for (int i = 0; i < getRooms().size(); i++) {
				if ((getRooms().get(i) != null) && (getRooms().get(i).getId().equals(room.getId()))) {
					getRooms().set(i, room);
				}
			}
		}
		result = true;
		return result;
	}

	public boolean exportCsv(String csvFilePath) {
		return CsvParser.exportToCsv(getRooms(), csvFilePath);
	}

	public boolean importCsv(String csvFilePath) {
		return addAll(CsvParser.importFromCsv(Room.class, csvFilePath));
	}

}

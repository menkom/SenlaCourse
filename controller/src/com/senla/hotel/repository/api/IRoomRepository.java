package com.senla.hotel.repository.api;

import java.util.List;

import com.senla.hotel.model.Room;

public interface IRoomRepository {

	List<Room> getRooms();

	boolean add(Room room);

	boolean addAll(List<Room> rooms);

	boolean delete(Integer roomNum);

	boolean deleteById(Integer id);

	Room getRoomByNum(Integer number);

	Room getRoomById(Integer id);

	Integer getLastId();

	void setLastId(Integer lastId);

	boolean update(Room room);

	boolean exportCsv(String csvFilePath);

	boolean importCsv(String csvFilePath);

}

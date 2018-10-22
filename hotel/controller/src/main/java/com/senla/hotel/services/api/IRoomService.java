package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public interface IRoomService {

	void add(Room room);

	void addAll(List<Room> rooms);

	void update(Room room);

	List<Room> getRooms(EnumRoomSort roomSort);

	Long getNumberOfFreeRooms();

	Room getRoomById(int id);

	void changeRoomStatus(int number, RoomStatus roomStatus);

	void changeRoomPrice(int number, int price);

	List<Room> getFreeRooms(EnumRoomSort roomSort);

	List<Room> getFreeRooms(Date date, EnumRoomSort roomSort);

	boolean exportRoomCSV(int roomNum, String fileName) throws IOException;

	boolean importRoomsCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

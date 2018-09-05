package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public interface IRoomService {

	public boolean add(Room room);

	public boolean addAll(List<Room> rooms);

	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price);

	public boolean update(Room room);

	public List<Room> getRooms();

	public List<Room> getAllRooms(Comparator<Room> comparator);

	public int getNumberOfFreeRooms();

	public Room getRoomByNum(int number);

	public Room getRoomById(int id);

	public boolean changeRoomStatus(int number, RoomStatus roomStatus);

	public boolean changeRoomPrice(int number, int price);

	public List<Room> getFreeRooms(Comparator<Room> comparator);

	public List<Room> getFreeRooms(Date date, Comparator<Room> comparator);

	public boolean exportRoomCSV(int roomNum, String fileName) throws IOException;

	public boolean importRoomsCSV(String file) throws IOException;

	public boolean exportCsv(String csvFilePath) throws IOException;

	public boolean importCsv(String csvFilePath) throws IOException;

}

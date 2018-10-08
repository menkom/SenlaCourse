package com.senla.hotel.services.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public interface IRoomService {

	public boolean add(Room room) throws SQLException;

	public boolean addAll(List<Room> rooms) throws SQLException;

	public boolean addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) throws SQLException;

	public boolean update(Room room) throws SQLException;

	public List<Room> getRooms() throws SQLException;

	public List<Room> getAllRooms(EnumRoomSort roomSort) throws SQLException;

	public int getNumberOfFreeRooms() throws SQLException;

	public Room getRoomById(int id) throws SQLException;

	public boolean changeRoomStatus(int number, RoomStatus roomStatus) throws SQLException;

	public boolean changeRoomPrice(int number, int price) throws SQLException;

	public List<Room> getFreeRooms(EnumRoomSort roomSort) throws SQLException;

	public List<Room> getFreeRooms(Date date, EnumRoomSort roomSort) throws SQLException;

	public boolean exportRoomCSV(int roomNum, String fileName) throws IOException, SQLException;

	public boolean importRoomsCSV(String file) throws IOException, SQLException;

	public boolean exportCsv(String csvFilePath) throws IOException, SQLException;

	public boolean importCsv(String csvFilePath) throws IOException, SQLException;

}

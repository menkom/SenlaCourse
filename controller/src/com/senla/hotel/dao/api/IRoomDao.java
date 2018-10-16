package com.senla.hotel.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.model.Room;

public interface IRoomDao<T extends Room> extends IGenericDao<T> {

	int getNumberOfFreeRooms(Connection connection) throws SQLException;

	List<Room> getFreeRooms(Connection connection, EnumRoomSort roomSort) throws SQLException;

	List<Room> getFreeRooms(Connection connection, Date date, EnumRoomSort roomSort) throws SQLException;

}
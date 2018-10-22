package com.senla.hotel.dao.api;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.model.Room;

public interface IRoomDao<T extends Room> extends IGenericDao<T> {

	Long getNumberOfFreeRooms(Session session);

	List<Room> getFreeRooms(Session session, EnumRoomSort roomSort);

	List<Room> getFreeRooms(Session session, Date date, EnumRoomSort roomSort);

}
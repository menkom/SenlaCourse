package com.senla.hotel.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Room;

public class RoomDao extends GenericDao<Room> implements IRoomDao<Room> {

	@Override
	protected Class<Room> getTClass() {
		return Room.class;
	}

	@Override
	public Long getNumberOfFreeRooms(Session session) {
		return (Long) session.createCriteria(getTClass()).add(Restrictions.eq("status", RoomStatus.AVAILABLE))
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getFreeRooms(Session session, EnumRoomSort roomSort) {
		return (List<Room>) session.createCriteria(getTClass()).add(Restrictions.eq("status", RoomStatus.AVAILABLE))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getFreeRooms(Session session, Date date, EnumRoomSort roomSort) {
		return (List<Room>) session.createCriteria(getTClass()).add(Restrictions.eq("status", RoomStatus.AVAILABLE))
				.list();
	}

}

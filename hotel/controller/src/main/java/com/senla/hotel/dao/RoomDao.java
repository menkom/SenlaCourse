package com.senla.hotel.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Order;
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

		DetachedCriteria distinct = DetachedCriteria.forClass(Order.class).add(Restrictions.le("startDate", date))
				.add(Restrictions.or(Restrictions.ge("finishDate", date), Restrictions.isNull("finishDate")))
				.setProjection(Projections.property("room"));

		List<Room> result = (List<Room>) session.createCriteria(getTClass())
				.add(Subqueries.notIn("room", distinct))
				.addOrder(org.hibernate.criterion.Order.asc(roomSort.getTableField()))
				.list();

		return result;
	}

}

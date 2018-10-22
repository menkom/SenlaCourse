package com.senla.hotel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class OrderDao extends GenericDao<Order> implements IOrderDao<Order> {

	public OrderDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getServices(Session session, Order order, String sortColumn) {
		Query query = session.createQuery(
				"select srv from Service as srv, ServiceOrderJoin as so WHERE so.service.id=srv.id and so.order.id= :orderId order by(:sortColumn)");
		query.setParameter("orderId", order.getId());
		if ((sortColumn == null) || (sortColumn.equals(""))) {
			query.setParameter("sortColumn", "");
		} else {
			query.setParameter("sortColumn", sortColumn);
		}
		return (List<Service>) query.list();
	}

	@Override
	protected Class<Order> getTClass() {
		return Order.class;
	}

	@Override
	public void addOrderService(Session session, Order order, Service service) {
		order.addService(service);
		update(session, order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getActiveOrders(Session session, EnumOrderSort orderSort) {
		return (List<Order>) session.createCriteria(getTClass()).add(Restrictions.isNull("finishDate"))
				.addOrder(org.hibernate.criterion.Order.asc(orderSort.getTableField())).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByRoom(Session session, Room room) {
		Query query = session.createQuery("select ord from Order as ord WHERE ord.room.id=:roomId");
		query.setParameter("roomId", room.getId());
		return (List<Order>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getLastOrdersByRoom(Session session, Room room, int maxOrders, EnumOrderSort orderSort) {
		Query query = session
				.createQuery("select ord from Order as ord WHERE ord.room.id=:roomId order by ord.startDate");
		query.setParameter("roomId", room.getId());
		return (List<Order>) query.setMaxResults(maxOrders).list();
	}

}

package com.senla.hotel.dao.api;

import java.util.List;

import org.hibernate.Session;

import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public interface IOrderDao<T extends Order> extends IGenericDao<T> {

	List<Service> getServices(Session session, Order order, String sortColumn);

	void addOrderService(Session session, Order order, Service service);

	List<Order> getActiveOrders(Session session, EnumOrderSort orderSort);

	List<Order> getOrdersByRoom(Session session, Room room);

	List<Order> getLastOrdersByRoom(Session session, Room room, int maxOrders, EnumOrderSort orderSort);

}
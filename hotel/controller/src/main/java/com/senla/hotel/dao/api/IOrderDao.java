package com.senla.hotel.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public interface IOrderDao<T extends Order> extends IGenericDao<T> {

	List<Service> getServices(Connection connection, int orderId, String sortColumn) throws SQLException;

	boolean addOrderService(Connection connection, int orderId, Service service) throws SQLException;

	List<Order> getActiveOrders(Connection connection, EnumOrderSort orderSort) throws SQLException;

	List<Order> getOrdersByRoom(Connection connection, int roomId) throws SQLException;

	List<Order> getLastOrdersByRoom(Connection connection, int roomId, int maxOrders, EnumOrderSort orderSort)
			throws SQLException;

}
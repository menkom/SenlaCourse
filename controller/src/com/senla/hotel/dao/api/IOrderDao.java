package com.senla.hotel.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public interface IOrderDao<T extends Order> extends IGenericDao<T> {

	List<Service> getServices(Connection connection, int orderId) throws SQLException;

}
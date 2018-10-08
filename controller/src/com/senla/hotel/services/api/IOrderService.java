package com.senla.hotel.services.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public interface IOrderService {

	boolean add(Order order) throws SQLException;

	boolean addAll(List<Order> orders) throws SQLException;

	boolean addOrder(int num, int clientId, int roomId, Date startDate, Date finishDate) throws SQLException;

	boolean update(Order order) throws SQLException;

	public List<Order> getOrders() throws SQLException;

	Order getOrderById(int id) throws SQLException;

	boolean orderRoom(int orderNum, int roomId, int clientId, Date dateStart, Date dateFinish) throws SQLException;

	boolean freeRoom(int orderId) throws SQLException;

	boolean addOrderService(int orderId, int serviceId) throws SQLException;

	Integer getOrderPrice(int orderNum) throws SQLException;

	List<Order> getActiveOrders(EnumOrderSort orderSort) throws SQLException;

	List<Order> getOrdersByRoom(int roomId) throws SQLException;

	List<Order> getLastOrdersByRoom(int roomId, int maxOrders, EnumOrderSort orderSort) throws SQLException;

	List<Service> getOrderServices(int orderNum, EnumServiceSort serviceSort) throws SQLException;

	Order cloneOrder(int orderId) throws CloneNotSupportedException, SQLException;

	boolean exportOrderCSV(int orderId, String fileName) throws IOException, SQLException;

	boolean importOrdersCSV(String file) throws IOException, SQLException;

	boolean exportCsv(String csvFilePath) throws IOException, SQLException;

	boolean importCsv(String csvFilePath) throws IOException, SQLException;

}

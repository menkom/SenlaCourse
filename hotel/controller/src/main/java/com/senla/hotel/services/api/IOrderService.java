package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.List;

import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumServiceSort;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public interface IOrderService {

	void add(Order order);

	void addAll(List<Order> orders);

	void update(Order order);

	public List<Order> getOrders();

	Order getOrderById(int id);

	void freeRoom(int orderId);

	void addOrderService(Order order, Service service);

	Integer getOrderPrice(Order order);

	List<Order> getActiveOrders(EnumOrderSort orderSort);

	List<Order> getOrdersByRoom(Room room);

	List<Order> getLastOrdersByRoom(Room room, int maxOrders, EnumOrderSort orderSort);

	List<Service> getOrderServices(Order order, EnumServiceSort serviceSort);

	Order cloneOrder(Order order) throws CloneNotSupportedException;

	boolean exportOrderCSV(int orderId, String fileName) throws IOException;

	boolean importOrdersCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

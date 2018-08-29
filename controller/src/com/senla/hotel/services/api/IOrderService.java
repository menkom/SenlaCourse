package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public interface IOrderService {

	boolean add(Order order);

	boolean addAll(List<Order> orders);

	boolean addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate);

	boolean update(Order order);

	public List<Order> getOrders();

	Order getOrderByNum(int num);

	Order getOrderById(int id);

	boolean orderRoom(int orderNum, int roomNum, String clientName, Date dateStart, Date dateFinish);

	boolean freeRoom(int orderNum);

	boolean addOrderService(int orderNum, int serviceCode);

	Integer getOrderPrice(int orderNum);

	List<Order> getActiveOrders(Comparator<Order> comparator);

	List<Order> getOrdersByRoom(int num);

	List<Order> getLastOrdersByRoom(int num, int maxOrders, Comparator<Order> comparator);

	List<Service> getOrderServices(int orderNum, Comparator<Service> comparator);

	Order cloneOrder(int orderNum) throws CloneNotSupportedException;

	boolean exportOrderCSV(int orderNum, String fileName) throws IOException;

	boolean importOrdersCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath);

	boolean importCsv(String csvFilePath);

}

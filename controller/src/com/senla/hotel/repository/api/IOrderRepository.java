package com.senla.hotel.repository.api;

import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public interface IOrderRepository {

	List<Order> getOrders();

	boolean add(Order order);

	boolean addAll(List<Order> orders);

	boolean delete(Integer orderNum);

	boolean deleteById(Integer id);

	Order getOrderByNum(Integer num);

	Order getOrderById(Integer id);

	boolean addOrderService(int num, Service service);

	boolean update(Order order);

	boolean exportCsv(String csvFilePath);

	boolean importCsv(String csvFilePath);

}

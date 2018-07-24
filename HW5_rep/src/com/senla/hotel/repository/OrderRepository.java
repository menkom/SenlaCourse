package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public class OrderRepository {

	private List<Order> orders;

	private static OrderRepository orderRepository;

	private OrderRepository() {
		super();
		orders = new ArrayList<>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void add(Order element) {
		getOrders().add(element);
	}

	public void delete(Order element) {
		for (Order order : getOrders()) {
			if (order == element) {
				order = null;
			}
		}
	}

	public Order getOrderByNum(int num) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getNum() == num)) {
				return order;
			}
		}
		return null;
	}

	public void addOrderService(int num, Service service) {
		getOrderByNum(num).addService(service);
	}

	public String[] toStringArray() {
		String[] result = new String[getOrders().size()];

		int i = 0;
		for (Order element : getOrders()) {
			if (element != null) {
				result[i] = element.toString();
				i++;
			}
		}
		return result;
	}

	public static OrderRepository getInstance() {
		if (orderRepository == null) {
			orderRepository = new OrderRepository();
		}
		return orderRepository;
	}

}

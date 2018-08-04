package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;

public class OrderRepository {

	private static OrderRepository orderRepository;

	private List<Order> orders;

	private OrderRepository() {
		super();
		this.orders = new ArrayList<Order>();
	}

	public static OrderRepository getInstance() {
		if (orderRepository == null) {
			orderRepository = new OrderRepository();
		}
		return orderRepository;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void add(Order order) {
		getOrders().add(order);
	}

	public void delete(Integer orderNum) {
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getNum() == orderNum) {
				getOrders().remove(i);
				break;
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

}

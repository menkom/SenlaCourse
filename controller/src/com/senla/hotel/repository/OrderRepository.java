package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.util.IdGenerator;

public class OrderRepository {

	private static OrderRepository orderRepository;

	private Integer lastId;
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

	public Boolean add(Order order) {
		Integer id = IdGenerator.generateId(lastId);
		order.setId(id);

		Boolean result = getOrders().add(order);
		if (result) {
			lastId = id;
		}
		return result;
	}

	public Boolean delete(Integer orderNum) {
		Boolean result = false;
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getNum() == orderNum) {
				getOrders().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Order getOrderByNum(Integer num) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getNum() == num)) {
				return order;
			}
		}
		return null;
	}

	public Order getOrderById(Integer id) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getId() == id)) {
				return order;
			}
		}
		return null;
	}

	public Boolean addOrderService(int num, Service service) {
		return getOrderByNum(num).addService(service);
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

}

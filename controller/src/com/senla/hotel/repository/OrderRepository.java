package com.senla.hotel.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
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
		Boolean result = false;
		if (order.getId() != null) {
			result = orders.add(order);
		} else {
			Integer id = IdGenerator.generateId(lastId);
			order.setId(id);
			result = getOrders().add(order);
			if (result) {
				lastId = id;
			}
		}
		return result;
	}

	public Boolean delete(Integer orderNum) {
		Boolean result = false;
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getNum().equals(orderNum)) {
				getOrders().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Boolean deleteById(Integer id) {
		Boolean result = false;
		for (int i = 0; i < orders.size() - 1; i++) {
			if (orders.get(i).getId().equals(id)) {
				orders.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Order getOrderByNum(Integer num) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getNum().equals(num))) {
				return order;
			}
		}
		return null;
	}

	public Order getOrderById(Integer id) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getId().equals(id))) {
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

	public Boolean update(Order order) {
		Boolean result = false;
		if (order != null) {
			for (int i = 0; i < getOrders().size(); i++) {
				if ((getOrders().get(i) != null) && (getOrders().get(i).getId().equals(order.getId()))) {
					getOrders().set(i, order);
				}
			}
		}
		result = true;
		return result;
	}

	public boolean exportCsv(String csvFilePath) throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException, IOException {
		boolean result = false;

		CsvParser.exportToCsv(getOrders(), csvFilePath);
		result = true;
		return result;
	}

	public boolean importCsv(String csvFilePath) throws IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, ParseException {
		boolean result = false;

		CsvParser.importFromCsv(Order.class, csvFilePath);

		result = true;
		return result;
	}

}

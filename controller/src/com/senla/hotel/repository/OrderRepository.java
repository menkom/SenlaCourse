package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.util.IdGenerator;
import com.senla.hotel.repository.api.IOrderRepository;

public class OrderRepository implements IOrderRepository {

	private static IOrderRepository orderRepository;

	private static Integer lastId;
	private List<Order> orders;

	private static Integer getLastId() {
		return OrderRepository.lastId;
	}

	private static void setLastId(Integer lastId) {
		OrderRepository.lastId = lastId;
	}

	public OrderRepository() {
		super();
		this.orders = new ArrayList<Order>();
	}

	public static IOrderRepository getInstance() {
		if (orderRepository == null) {
			orderRepository = DependencyInjection.getInstance().getInterfacePair(IOrderRepository.class);
		}
		return orderRepository;
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public boolean add(Order order) {
		boolean result = false;
		if (order.getId() != null) {
			result = orders.add(order);
		} else {
			Integer id = IdGenerator.generateId(getLastId());
			order.setId(id);
			result = getOrders().add(order);
			if (result) {
				setLastId(id);
			}
		}
		return result;
	}

	@Override
	public boolean addAll(List<Order> orders) {
		boolean result = getOrders().addAll(orders);
		if (result) {
			setLastId(IdGenerator.getLastId(getOrders()));
		}
		return result;
	}

	@Override
	public boolean delete(Integer orderNum) {
		boolean result = false;
		for (int i = 0; i < getOrders().size(); i++) {
			if (getOrders().get(i).getNum().equals(orderNum)) {
				getOrders().remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		for (int i = 0; i < orders.size() - 1; i++) {
			if (orders.get(i).getId().equals(id)) {
				orders.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Order getOrderByNum(Integer num) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getNum().equals(num))) {
				return order;
			}
		}
		return null;
	}

	@Override
	public Order getOrderById(Integer id) {
		for (Order order : getOrders()) {
			if ((order != null) && (order.getId().equals(id))) {
				return order;
			}
		}
		return null;
	}

	@Override
	public boolean addOrderService(int num, Service service) {
		return getOrderByNum(num).addService(service);
	}

	@Override
	public boolean update(Order order) {
		boolean result = false;
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

	@Override
	public boolean exportCsv(String csvFilePath) {
		return CsvParser.exportToCsv(getOrders(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) {
		return addAll(CsvParser.importFromCsv(Order.class, csvFilePath));
	}

}

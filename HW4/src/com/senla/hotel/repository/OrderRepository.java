package com.senla.hotel.repository;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;

public class OrderRepository implements IBaseRepository {

	private Order[] repository;

	public OrderRepository() {
		super();
		this.repository = new Order[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setRepository(BaseObject[] array) {
		this.repository = (Order[]) array;
	}

	public BaseObject[] getRepository() {
		return repository;
	}

	public void add(BaseObject element) {
		setRepository((BaseObject[]) ArrayOperator.add(getRepository(), element));
	}

	public void delete(BaseObject element) {
		for (Order order : (Order[]) getRepository()) {
			if (order == element) {
				order = null;
			}
		}
	}

	public Order getOrderByNum(int num) {
		for (Order order : (Order[]) getRepository()) {
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
		return ArrayOperator.toStringArray(getRepository());
	}

}

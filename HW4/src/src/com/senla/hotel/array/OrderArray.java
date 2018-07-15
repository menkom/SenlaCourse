package com.senla.hotel.array;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Service;
import com.senla.util.ArrayOperator;

public class OrderArray implements IBaseArray {

	private Order[] array;

	public OrderArray() {
		super();
		this.array = new Order[ArrayOperator.MINIMUM_ARRAY_LENGTH];
	}

	public void setArray(BaseObject[] array) {
		this.array = (Order[]) array;
	}

	public BaseObject[] getArray() {
		return array;
	}

	public void add(BaseObject element) {
		new ArrayOperator().add(getArray(), element);
	}

	public void delete(BaseObject element) {
		for (Order order : (Order[]) getArray()) {
			if (order == element) {
				order = null;
			}
		}
	}

	public Order getOrderByNum(int num) {
		for (Order order : (Order[]) getArray()) {
			if (order.getNum() == num) {
				return order;
			}
		}
		return null;
	}

	public void addOrderService(int num, Service service) {
		getOrderByNum(num).addService(service);
	}

	public String[] toStringArray() {
		return new ArrayOperator().toStringArray(getArray());
	}

}

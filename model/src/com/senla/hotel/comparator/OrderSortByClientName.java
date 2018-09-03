package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Order;

public class OrderSortByClientName implements Comparator<Order> {

	@Override
	public int compare(Order arg0, Order arg1) {
		if (arg0 == null) {
			return -1;
		}
		if (arg1 == null) {
			return 1;
		}
		if (arg0.getClient() == null) {
			return -1;
		} else if (arg1.getClient() == null) {
			return 1;
		} else {
			if (arg0.getClient().getName() == null) {
				return -1;
			} else if (arg1.getClient().getName() == null) {
				return 1;
			} else {
				return arg0.getClient().getName().compareToIgnoreCase(arg1.getClient().getName());
			}
		}
	}

}

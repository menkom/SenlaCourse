package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Order;

public class OrderSortByFinishDate implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
	
		if (o1.getFinishDate() == null) {
			return -1;
		} else if (o2.getFinishDate() == null) {
			return 1;
		} else {
			return o1.getFinishDate().compareTo(o2.getFinishDate());
		}
	}

}

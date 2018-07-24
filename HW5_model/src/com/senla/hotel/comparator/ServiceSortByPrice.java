package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Service;

public class ServiceSortByPrice implements Comparator<Service> {

	@Override
	public int compare(Service o1, Service o2) {
		if (o1 == null) {
			return 1;
		}
		if (o2 == null) {
			return -1;
		}
		return Integer.compare(o1.getPrice(), o2.getPrice());
	}

}

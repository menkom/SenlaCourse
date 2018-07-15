package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Service;

public class ServiceSortByPrice implements Comparator<Service> {

	@Override
	public int compare(Service o1, Service o2) {
		return Integer.compare(o1.getPrice(), o2.getPrice());
	}

}

package com.senla.hotel.comparator;

import java.util.Comparator;

import com.senla.hotel.model.Service;

public class ServiceSortByPrice implements Comparator<Service> {

	@Override
	public int compare(Service s1, Service s2) {
		if (s1 == null) {
			return -1;
		}
		if (s2 == null) {
			return 1;
		}
		if (s1.getPrice() == null) {
			return -1;
		} else if (s2.getPrice() == null) {
			return 1;
		} else {
			return Integer.compare(s1.getPrice(), s2.getPrice());
		}
	}

}

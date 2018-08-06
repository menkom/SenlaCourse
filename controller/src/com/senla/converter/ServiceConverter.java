package com.senla.converter;

import com.senla.hotel.model.Service;

public class ServiceConverter {

	public static final String SEPARATOR = ";";

	public static Service getServiceFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Service result = new Service(Integer.parseInt(array[1]), array[2], Integer.parseInt(array[3]));

		result.setId(Integer.parseInt(array[0]));

		return result;
	}

}

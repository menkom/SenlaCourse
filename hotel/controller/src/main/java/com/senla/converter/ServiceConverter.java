package com.senla.converter;

import com.senla.hotel.model.Service;

public class ServiceConverter {

	public static final String SEPARATOR = ";";

	public static Service getServiceFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Service result = new Service();

		result.setId(Integer.parseInt(array[0]));
		result.setCode(Integer.parseInt(array[1]));
		result.setName(array[2]);
		result.setPrice(Integer.parseInt(array[3]));

		return result;
	}

}

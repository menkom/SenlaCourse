package com.senla.converter;

import com.senla.hotel.model.Client;

public class ClientConverter {

	public static final String SEPARATOR = ";";

	public static Client getClientFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Client result = new Client(array[0]);

		return result;
	}
}

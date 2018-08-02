package com.senla.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.util.DisplayOperator;

public class OrderConverter {

	public static final String SEPARATOR = ";";

	public static Order getOrderFromString(String line) {
		String[] array = line.split(SEPARATOR);

		Client client = ClientRepository.getInstance().getClientByName(array[1]);

		Room room = RoomRepository.getInstance().getRoomByNum(Integer.parseInt(array[2]));

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date dateStart = null;
		Date dateFinish = null;

		if (!array[3].equals("null")) {
			try {
				dateStart = formatter.parse(array[3]);
			} catch (ParseException e) {
				DisplayOperator.printMessage("Error in Date format during loading the Order.");
			}
		}

		if (!array[4].equals("null")) {
			try {
				dateFinish = formatter.parse(array[4]);
			} catch (ParseException e) {
				DisplayOperator.printMessage("Error in Date format during loading the Order.");
			}
		}
		Order result = new Order(Integer.parseInt(array[0]), client, room, dateStart, dateFinish);

		for (int i = 5; i < array.length; i++) {
			Service service = ServiceRepository.getInstance().getServiceByCode(Integer.parseInt(array[i]));
			result.addService(service);
		}
		return result;
	}
}

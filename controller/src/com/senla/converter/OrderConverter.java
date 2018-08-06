package com.senla.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.util.DisplayOperator;

public class OrderConverter {

	private static final Logger logger = Logger.getLogger(OrderConverter.class);
	private static final String ERROR_DATE_FORMAT = "Error in Date format during loading the Order.";
	public static final String SEPARATOR = ";";

	public static Order getOrderFromString(String line) {

		String[] array = line.split(SEPARATOR);

		Client client = ClientRepository.getInstance().getClientByName(array[2]);

		Room room = RoomRepository.getInstance().getRoomByNum(Integer.parseInt(array[3]));

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date dateStart = null;
		Date dateFinish = null;

		if (!array[4].equals("null")) {
			try {
				dateStart = formatter.parse(array[4]);
			} catch (ParseException e) {
				DisplayOperator.printMessage(ERROR_DATE_FORMAT);
				logger.error(e);
			}
		}
		if (!array[5].equals("null")) {
			try {
				dateFinish = formatter.parse(array[5]);
			} catch (ParseException e) {
				DisplayOperator.printMessage(ERROR_DATE_FORMAT);
				logger.error(e);
			}
		}
		Order result = new Order(Integer.parseInt(array[1]), client, room, dateStart, dateFinish);

		result.setId(Integer.parseInt(array[0]));

		for (int i = 6; i < array.length; i++) {
			Service service = ServiceRepository.getInstance().getServiceByCode(Integer.parseInt(array[i]));
			result.addService(service);
		}
		return result;
	}
}

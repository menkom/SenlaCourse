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

public class OrderConverter {

	private static final Logger logger = Logger.getLogger(OrderConverter.class);
	public static final String SEPARATOR = ";";

	public static Order getOrderFromString(String line) {

		String[] array = line.split(SEPARATOR);

		Client client = ClientRepository.getInstance().getClientById(Integer.parseInt(array[2]));

		Room room = RoomRepository.getInstance().getRoomById(Integer.parseInt(array[3]));

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date dateStart = null;
		Date dateFinish = null;

		if (!array[4].equals("null")) {
			try {
				dateStart = formatter.parse(array[4]);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		if (!array[5].equals("null")) {
			try {
				dateFinish = formatter.parse(array[5]);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		Order result = new Order();

		result.setId(Integer.parseInt(array[0]));
		result.setNum(Integer.parseInt(array[1]));
		result.setClient(client);
		result.setRoom(room);
		result.setStartDate(dateStart);
		result.setFinishDate(dateFinish);

		for (int i = 6; i < array.length; i++) {
			Service service = ServiceRepository.getInstance().getServiceById(Integer.parseInt(array[i]));
			result.addService(service);
		}
		return result;
	}
}

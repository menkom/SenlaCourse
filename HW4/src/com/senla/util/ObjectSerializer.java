package com.senla.util;

import java.text.ParseException;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;

public class ObjectSerializer {

	public static final String SEPARATOR = ";";

	public String[] stringToArray(String aValue) {
		return aValue.split(SEPARATOR);
	}

	public String[] arrayToStringArray(BaseObject[] array) {
		String[] result = new String[array.length];
		// TODO Check how will it work without one element inside of array
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				result[i] = array[i].toString();
			}
		}
		return result;
	}

	public Client getClientFromArray(String[] array) {
		Client result = new Client(array[0]);

		return result;
	}

	public Client getClientFromString(String line) {
		return getClientFromArray(stringToArray(line));
	}

	public Client[] getClientsFromArray(String[] arg) {

		Client[] result = new Client[arg.length];

		for (int i = 0; i < arg.length; i++) {
			result[i] = getClientFromString(arg[i]);
		}

		return result;
	}

	public Service getServiceFromArray(String[] array) throws NumberFormatException, ParseException {
		Service result = new Service(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2]));

		return result;
	}

	public Service getServiceFromString(String line) throws NumberFormatException, ParseException {
		return getServiceFromArray(stringToArray(line));
	}

	public Service[] getServicesFromArray(String[] arg) throws NumberFormatException, ParseException {
		Service[] result = new Service[arg.length];

		for (int i = 0; i < arg.length; i++) {
			result[i] = getServiceFromString(arg[i]);
		}
		return result;
	}

	public Room getRoomFromArray(String[] array) {
		return new Room(Integer.parseInt(array[0]), Integer.parseInt(array[1]), RoomStar.valueOf(array[2]),
				RoomStatus.valueOf(array[3]), Integer.parseInt(array[4]));
	}

	public Room getRoomFromString(String line) {
		return getRoomFromArray(stringToArray(line));
	}

	public Room[] getRoomsFromArray(String[] arg) {

		Room[] result = new Room[arg.length];

		for (int i = 0; i < arg.length; i++) {
			result[i] = getRoomFromString(arg[i]);
		}

		return result;
	}

	public Order getOrderFromArray(String[] array, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository) throws NumberFormatException, ParseException {

		Client client = clientRepository.getClientByName(array[1]);

		Room room = roomRepository.getRoomByNum(Integer.parseInt(array[2]));

		Order result = new Order(Integer.parseInt(array[0]), client, room, new DateOperator().getStringToDate(array[3]),
				new DateOperator().getStringToDate(array[4]));

		for (int i = 5; i < array.length; i++) {
			Service service = serviceRepository.getServiceByCode(Integer.parseInt(array[i]));
			result.addService(service);
		}
		return result;
	}

	public Order getOrderFromString(String line, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository) throws NumberFormatException, ParseException {
		return getOrderFromArray(stringToArray(line), clientRepository, roomRepository, serviceRepository);
	}

	public Order[] getOrdersFromArray(String[] array, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository) throws NumberFormatException, ParseException {
		Order[] result = new Order[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = getOrderFromString(array[i], clientRepository, roomRepository, serviceRepository);
		}

		return result;
	}

}

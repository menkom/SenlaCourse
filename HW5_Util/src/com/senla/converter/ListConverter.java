package com.senla.converter;

import java.util.ArrayList;
import java.util.List;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class ListConverter {

	public static String[] getArrayFromList(List<? extends BaseObject> list) {
		List<String> result = new ArrayList<>();

		for (BaseObject element : list) {
			if (element != null) {
				result.add(element.toString());
			}
		}

		return result.toArray(new String[result.size()]);
	}

	public static List<Client> getClients(String[] array) {
		List<Client> clients = new ArrayList<>();

		for (String line : array) {
			Client client = ClientConverter.getClientFromString(line);

			clients.add(client);
		}

		return clients;
	}

	public static List<Order> getOrders(String[] array) {
		List<Order> orders = new ArrayList<>();

		for (String line : array) {
			Order order = OrderConverter.getOrderFromString(line);

			orders.add(order);
		}
		return orders;
	}

	public static List<Room> getRooms(String[] array) {
		List<Room> orders = new ArrayList<>();

		for (String line : array) {
			Room room = RoomConverter.getRoomFromString(line);

			orders.add(room);
		}
		return orders;
	}

	public static List<Service> getServices(String[] array) {
		List<Service> orders = new ArrayList<>();

		for (String line : array) {
			Service service = ServiceConverter.getServiceFromString(line);

			orders.add(service);
		}
		return orders;
	}

}

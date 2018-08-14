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
		List<String> result = new ArrayList<String>();
		for (BaseObject baseObject : list) {
			if (baseObject != null) {
				result.add(baseObject.toString());
			}
		}

		return result.toArray(new String[result.size()]);
	}

	public static List<Client> getClients(List<String> list) {
		List<Client> clients = new ArrayList<>();

		for (String line : list) {
			Client client = ClientConverter.getClientFromString(line);

			clients.add(client);
		}

		return clients;
	}

	public static List<Order> getOrders(List<String> list) {
		List<Order> orders = new ArrayList<>();

		for (String line : list) {
			Order order = OrderConverter.getOrderFromString(line);

			orders.add(order);
		}
		return orders;
	}

	public static List<Room> getRooms(List<String> list) {
		List<Room> orders = new ArrayList<>();

		for (String line : list) {
			Room room = RoomConverter.getRoomFromString(line);

			orders.add(room);
		}
		return orders;
	}

	public static List<Service> getServices(List<String> list) {
		List<Service> orders = new ArrayList<>();

		for (String line : list) {
			Service service = ServiceConverter.getServiceFromString(line);

			orders.add(service);
		}
		return orders;
	}

}

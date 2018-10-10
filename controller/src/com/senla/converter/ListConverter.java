package com.senla.converter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class ListConverter {

	private static final Logger logger = Logger.getLogger(ListConverter.class);

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

	public static List<Order> getOrders(List<String> list) throws ClassNotFoundException {
		List<Order> orders = new ArrayList<>();

		try {
			for (String line : list) {
				Order order = OrderConverter.getOrderFromString(line);

				orders.add(order);
			}
		} catch (SQLException e) {
			logger.error(e);
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

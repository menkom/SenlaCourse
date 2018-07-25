package com.senla.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.senla.base.BaseObject;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class DisplayOperator {

	public static String SEPARATOR_LINE = "=====================================";

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printArray(BaseObject[] array) {
		printMessage(Arrays.toString(array));
	}

	public static void printRoomInfo(Room room) {
		if (room == null) {
			printMessage("Room: Number= -, Capacity= -, Star= -, Status= -, Price= -");
		} else {
			printMessage("Room: Number=" + room.getNumber() + ", Capacity=" + room.getCapacity() + ", Star="
					+ room.getStar() + ", Status=" + room.getStatus() + ", Price=" + room.getPrice());
		}
	}

	public static void printRooms(List<Room> array) {
		printMessage("Number Capacity Star   Status    Price");
		for (Room room : array) {
			if (room != null) {
				printMessage(room.getNumber() + "-------" + room.getCapacity() + "------" + room.getStar() + "----"
						+ room.getStatus() + "---" + room.getPrice());
			}
		}
	}

	public static void printOrders(List<Order> array) {
		printMessage("Client  --  Room  --  Start Date -- Finish Date");
		for (Order order : array) {
			if (order != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				printMessage(order.getClient().getName() + "-------" + order.getRoom().getNumber() + "------"
						+ formatter.format(order.getStartDate()) + "----" + formatter.format(order.getFinishDate()));
			}
		}
	}

	public static void printServices(List<Service> array) {
		printMessage("Code --- Name  --------  Price");
		for (Service service : array) {
			if (service != null) {
				printMessage(service.getCode() + "-------" + service.getName() + "------" + service.getPrice());
			}
		}
	}

	public static void printClients(List<Client> array) {
		printMessage("Name");
		for (Client client : array) {
			if (client != null) {
				printMessage(client.getName());
			}
		}
	}
}

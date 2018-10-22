package com.senla.ui.util;

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
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		if (room == null) {
			printMessage("Room: Number= -, Capacity= -, Star= -, Status= -, Price= -");
		} else {
			printMessage("Room:id=" + room.getId() + ", Number=" + room.getNumber() + ", Capacity=" + room.getCapacity()
					+ ", Star=" + room.getStar() + ", Status=" + room.getStatus() + ", Price=" + room.getPrice());
		}
	}

	public static void printOrderInfo(Order order) {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		if (order == null) {
			printMessage("Order: id= -, Num= -, Client= -, Room= -, StartDate= -, FinishDate= -");
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateStart = "";
			String dateFinish = "";
			if (order.getStartDate() != null) {
				dateStart = formatter.format(order.getStartDate());
			}
			if (order.getFinishDate() != null) {
				dateStart = formatter.format(order.getFinishDate());
			}
			printMessage("Order:id= " + order.getId() + ", Num=" + order.getNum() + ", Client="
					+ order.getClient().getName() + ", Room=" + order.getRoom().getNumber() + ", StartDate=" + dateStart
					+ ", FinishDate=" + dateFinish);
		}
	}

	public static void printRooms(List<Room> array) {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		printMessage("id    Number Capacity Star   Status    Price");
		if (array != null) {
			for (Room room : array) {
				if (room != null) {
					printMessage(room.getId() + " --- " + room.getNumber() + "-------" + room.getCapacity() + "------"
							+ room.getStar() + "----" + room.getStatus() + "---" + room.getPrice());
				}
			}
		}
	}

	public static void printOrders(List<Order> array) {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		printMessage("id -- Num -- Client  --  Room  --  Start Date -- Finish Date");
		for (Order order : array) {
			if (order != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				String dateStart = "empty";
				String dateFinish = "empty";

				if (order.getStartDate() != null) {
					dateStart = formatter.format(order.getStartDate());
				}

				if (order.getFinishDate() != null) {
					dateFinish = formatter.format(order.getFinishDate());
				}

				printMessage(order.getId() + " --- " + order.getNum() + " --- " + order.getClient().getName()
						+ "-------" + order.getRoom().getNumber() + "------" + dateStart + "----" + dateFinish);
			}
		}
	}

	public static void printServices(List<Service> array) {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		printMessage("id --- Code --- Name  --------  Price");
		if (array != null) {
			for (Service service : array) {
				if (service != null) {
					printMessage(service.getId() + " ---- " + service.getCode() + "-------" + service.getName()
							+ "------" + service.getPrice());
				}
			}
		}
	}

	public static void printClients(List<Client> array) {
		DisplayOperator.printMessage(DisplayOperator.SEPARATOR_LINE);
		printMessage("id -- Name ---");
		for (Client client : array) {
			if (client != null) {
				printMessage(client.getId() + " --- " + client.getName());
			}
		}
	}
}

package com.senla.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.base.BaseObject;
import com.senla.converter.ListConverter;
import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class ExportCSV {

	public static Boolean saveCSV(String line, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			try (BufferedWriter br = new BufferedWriter(fileWriter);) {
				br.write(line);
			}
		}
		result = true;
		return result;
	}

	public static <T extends BaseObject> Boolean saveCSV(List<T> list, String path) throws IOException {
		Boolean result = false;
		try (FileWriter fileWriter = new FileWriter(path)) {
			try (BufferedWriter br = new BufferedWriter(fileWriter);) {
				for (T element : list) {
					br.write(element.toString());
				}
			}
		}
		result = true;
		return result;
	}

	private static List<String> loadCSV(String path) throws IOException {
		List<String> result = new ArrayList<>();
		String line;
		try (FileReader fileReader = new FileReader(path)) {
			try (BufferedReader br = new BufferedReader(fileReader);) {
				while ((line = br.readLine()) != null) {
					result.add(line);
				}
			}
		}
		return result;
	}

	public static List<Client> getClientsFromCSV(String file) throws NoEntryException, IOException {
		List<Client> result = new ArrayList<>();
		List<String> list = loadCSV(file);
		if (list != null) {
			result = ListConverter.getClients(list);
		}
		return result;
	}

	public static List<Order> getOrdersFromCSV(String file) throws NoEntryException, IOException {
		List<Order> result = new ArrayList<>();
		List<String> list = loadCSV(file);
		if (list != null) {
			result = ListConverter.getOrders(list);
		}
		return result;
	}

	public static List<Room> getRoomsFromCSV(String file) throws NoEntryException, IOException {
		List<Room> result = new ArrayList<>();
		List<String> list = loadCSV(file);
		if (list != null) {
			result = ListConverter.getRooms(list);
		}
		return result;
	}

	public static List<Service> getServicesFromCSV(String file) throws NoEntryException, IOException {
		List<Service> result = new ArrayList<>();
		List<String> list = loadCSV(file);
		if (list != null) {
			result = ListConverter.getServices(list);
		}
		return result;
	}
}

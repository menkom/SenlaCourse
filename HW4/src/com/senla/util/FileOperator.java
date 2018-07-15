package com.senla.util;

import java.text.ParseException;
import java.util.Arrays;

import com.danco.training.TextFileWorker;
import com.senla.hotel.array.ClientArray;
import com.senla.hotel.array.OrderArray;
import com.senla.hotel.array.RoomArray;
import com.senla.hotel.array.ServiceArray;
import com.senla.hotel.services.ClientService;
import com.senla.hotel.services.OrderService;
import com.senla.hotel.services.RoomService;
import com.senla.hotel.services.ServiceService;

public class FileOperator {

	// private static String FOLDER_PATH = "";

	public ClientService getClientService(String fileName, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository, OrderArray orderRepository) {
		ClientService result = new ClientService(clientRepository, roomRepository, serviceRepository, orderRepository);

		result.getRepository()
				.setArray(new ObjectSerializer().getClientsFromArray(new TextFileWorker(fileName).readFromFile()));

		return result;
	}

	public RoomService getRoomService(String fileName, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository, OrderArray orderRepository) {
		RoomService result = new RoomService(clientRepository, roomRepository, serviceRepository, orderRepository);

		result.getRepository()
				.setArray(new ObjectSerializer().getRoomsFromArray(new TextFileWorker(fileName).readFromFile()));

		return result;
	}

	public ServiceService getServiceService(String fileName, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository, OrderArray orderRepository) throws NumberFormatException, ParseException {

		ServiceService result = new ServiceService(clientRepository, roomRepository, serviceRepository,
				orderRepository);

		result.getRepository()
				.setArray(new ObjectSerializer().getServicesFromArray(new TextFileWorker(fileName).readFromFile()));

		return result;
	}

	public OrderService getOrderService(String fileName, ClientArray clientRepository, RoomArray roomRepository,
			ServiceArray serviceRepository, OrderArray orderRepository) throws NumberFormatException, ParseException {

		OrderService result = new OrderService(clientRepository, roomRepository, serviceRepository, orderRepository);

		String[] load = new TextFileWorker(fileName).readFromFile();

		result.setNextNum(Integer.parseInt(load[0]));

		result.getRepository().setArray(new ObjectSerializer().getOrdersFromArray(
				Arrays.copyOfRange(load, 1, load.length), clientRepository, roomRepository, serviceRepository));

		return result;
	}

	public void saveToDB(String fileName, String[] array) {
		new TextFileWorker(fileName).writeToFile(array);
	}
}

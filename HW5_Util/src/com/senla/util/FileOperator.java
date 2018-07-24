package com.senla.util;

import java.text.ParseException;

import com.danco.training.TextFileWorker;
import com.senla.hotel.services.ClientService;
import com.senla.hotel.services.OrderService;
import com.senla.hotel.services.RoomService;
import com.senla.hotel.services.ServiceService;

public class FileOperator {

	// private static String FOLDER_PATH = "";

	public ClientService getClientService(String fileName) {
		return null;
	}

	public RoomService getRoomService(String fileName) {
		return null;
	}

	public ServiceService getServiceService(String fileName) throws NumberFormatException, ParseException {
		return null;
	}

	public OrderService getOrderService(String fileName) throws NumberFormatException, ParseException {
		return null;
	}

	public void saveToDB(String fileName, String[] array) {
		new TextFileWorker(fileName).writeToFile(array);
	}
}

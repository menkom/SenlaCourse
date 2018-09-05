package com.senla.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class ModelSerializer {

	private static final Logger logger = Logger.getLogger(ModelSerializer.class);

	private static final String ERROR_MODEL_SERIALIZER_FILE_LOADING = "Error while model serializer file load.";
	private static final String ERROR_MODEL_SERIALIZER_READING = "Error while model serializer read.";
	private static final String ERROR_NO_FILE = "Error. Model serializer file not found.";

	private List<Client> clients;
	private List<Order> orders;
	private List<Room> rooms;
	private List<Service> services;

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Boolean serialize(String filePath) throws IOException {
		Boolean result = false;
		File file = new File(filePath);
		if (file.exists()) {
			try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
				outputStream.writeObject(getClients());
				outputStream.writeObject(getOrders());
				outputStream.writeObject(getRooms());
				outputStream.writeObject(getServices());
				result = true;
			}
		} else {
			logger.error(ERROR_NO_FILE);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Boolean deserialize(String filePath) {
		Boolean result = false;
		File file = new File(filePath);
		if (file.exists()) {
			try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(file))) {
				setClients((List<Client>) objectStream.readObject());
				setOrders((List<Order>) objectStream.readObject());
				setRooms((List<Room>) objectStream.readObject());
				setServices((List<Service>) objectStream.readObject());
				result = true;
			} catch (IOException e) {
				logger.error(ERROR_MODEL_SERIALIZER_FILE_LOADING, e);
			} catch (ClassNotFoundException e) {
				logger.error(ERROR_MODEL_SERIALIZER_READING, e);
			}
		} else {
			logger.error(ERROR_NO_FILE);
		}
		return result;
	}
}

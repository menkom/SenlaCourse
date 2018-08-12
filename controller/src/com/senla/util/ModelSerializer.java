package com.senla.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;

public class ModelSerializer {

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
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));) {
			outputStream.writeObject(getClients());
			outputStream.writeObject(getOrders());
			outputStream.writeObject(getRooms());
			outputStream.writeObject(getServices());
			result = true;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Boolean deserialize(String filePath) throws IOException, ClassNotFoundException {
		Boolean result = false;
		try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(filePath))) {
			setClients((List<Client>) objectStream.readObject());
			setOrders((List<Order>) objectStream.readObject());
			setRooms((List<Room>) objectStream.readObject());
			setServices((List<Service>) objectStream.readObject());
			result = true;
		}
		return result;
	}
}

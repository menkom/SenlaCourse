package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.senla.base.BaseObject;
import com.senla.hotel.repository.ClientRepository;
import com.senla.hotel.repository.IBaseRepository;
import com.senla.hotel.repository.OrderRepository;
import com.senla.hotel.repository.RoomRepository;
import com.senla.hotel.repository.ServiceRepository;
import com.senla.hotel.model.Client;
import com.senla.util.FileOperator;

public class ClientService implements IService {

	private ClientRepository clientRepository;
	private RoomRepository roomRepository;
	private ServiceRepository serviceRepository;
	private OrderRepository orderRepository;

	public ClientService(ClientRepository clientRepository, RoomRepository roomRepository,
			ServiceRepository serviceRepository, OrderRepository orderRepository) {
		super();
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.serviceRepository = serviceRepository;
		this.orderRepository = orderRepository;
	}

	public String getFileName() {
		return "client.db";
	}

	public void add(BaseObject element) {
		getRepository().add(element);
	}

	public Client getClientByName(String name) {
		return getClientRepository().getClientByName(name);
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public ServiceRepository getServiceRepository() {
		return serviceRepository;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		// We need to add information from file or replace all with new info from
		// file?
		// Answer: As we load and save only once at the beginning and at the end we
		// expect that OUR repository is empty

		ClientService service = new FileOperator().getClientService(dbPath + getFileName(), getClientRepository(),
				getRoomRepository(), getServiceRepository(), getOrderRepository());

		getRepository().setRepository(((ClientRepository) service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) throws IOException {
		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	@Override
	public IBaseRepository getRepository() {
		return getClientRepository();
	}

	public String[] getStringToSave() {
		return getRepository().toStringArray();
	}

	public int getNumberOfClients() {
		int result = 0;
		for (Client client : (Client[]) getClientRepository().getRepository()) {
			if (client != null) {
				result++;
			}
		}
		return result;

	}

}

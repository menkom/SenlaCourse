package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Client;
import com.senla.util.IdGenerator;

public class ClientRepository {

	private static ClientRepository clientRepository;

	private Integer lastId;
	private List<Client> clients;

	private ClientRepository() {
		super();
		this.clients = new ArrayList<Client>();
	}

	public static ClientRepository getInstance() {
		if (clientRepository == null) {
			clientRepository = new ClientRepository();
		}
		return clientRepository;
	}

	public Boolean add(Client client) {
		Integer id = IdGenerator.generateId(lastId);
		client.setId(id);
		Boolean result = clients.add(client);
		if (result) {
			lastId = id;
		}
		return result;
	}

	public void delete(String name) {
		for (int i = 0; i < clients.size() - 1; i++) {
			if (clients.get(i).getName().equals(name)) {
				clients.remove(i);
				break;
			}
		}
	}

	public Client getClientByName(String name) {
		for (Client client : clients) {
			if ((client != null) && (client.getName().toLowerCase().equals(name.toLowerCase()))) {
				return client;
			}
		}
		return null;
	}

	public Client getClientById(Integer id) {
		for (Client client : getClients()) {
			if ((client != null) && (client.getId().equals(id))) {
				return client;
			}
		}
		return null;
	}

	public List<Client> getClients() {
		return clients;
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

}

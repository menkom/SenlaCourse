package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Client;

public class ClientRepository {

	private static ClientRepository clientRepository;

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
		return clients.add(client);
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

	public List<Client> getClients() {
		return clients;
	}

}

package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.hotel.model.Client;

public class ClientRepository {

	private List<Client> clients;

	private static ClientRepository clientRepository;

	private ClientRepository() {
		super();
		clients = new ArrayList<>();
	}

	public void add(Client element) {
		clients.add(element);
	}

	public void delete(Client element) {
		for (Client client : clients) {
			if (client == element) {
				client = null;
			}
		}
	}

	public Client getClientByName(String name) {
		for (Client client : clients) {
			if ((client != null) && (client.getName().equals(name))) {
				return client;
			}
		}
		return null;
	}

	public String[] toStringArray() {
		String[] result = new String[clients.size()];

		int i = 0;
		for (Client element : clients) {
			if (element != null) {
				result[i] = element.toString();
				i++;
			}
		}
		return result;
	}

	public static ClientRepository getInstance() {
		if (clientRepository == null) {
			clientRepository = new ClientRepository();
		}
		return clientRepository;
	}

	public List<Client> getClients() {
		return clients;
	}

}

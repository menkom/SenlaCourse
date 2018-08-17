package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
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
		Boolean result = false;
		if (client.getId() != null) {
			result = clients.add(client);
		} else {
			Integer id = IdGenerator.generateId(lastId);
			client.setId(id);
			result = clients.add(client);
			if (result) {
				lastId = id;
			}
		}
		return result;
	}

	public Boolean delete(String name) {
		Boolean result = false;
		for (int i = 0; i < clients.size() - 1; i++) {
			if (clients.get(i).getName().equals(name)) {
				clients.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public Boolean deleteById(Integer id) {
		Boolean result = false;
		for (int i = 0; i < clients.size() - 1; i++) {
			if (clients.get(i).getId().equals(id)) {
				clients.remove(i);
				result = true;
				break;
			}
		}
		return result;
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

	public Boolean update(Client client) {
		Boolean result = false;
		if (client != null) {
			for (int i = 0; i < getClients().size(); i++) {
				if ((getClients().get(i) != null) && (getClients().get(i).getId().equals(client.getId()))) {
					getClients().set(i, client);
				}
			}
		}
		result = true;
		return result;
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

	public boolean exportCsv() throws IllegalArgumentException, IllegalAccessException {
		boolean result = false;

		CsvParser.exportItemCsv(getClients().get(0));
		result = true;
		return result;
	}

}

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

	public boolean add(Client client) {
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

	public boolean addAll(List<Client> clients) {
		boolean result = getClients().addAll(clients);
		getInstance().setLastId(IdGenerator.getLastId(getInstance().getClients()));
		return result;
	}

	public boolean delete(String name) {
		boolean result = false;
		for (int i = 0; i < clients.size() - 1; i++) {
			if (clients.get(i).getName().equals(name)) {
				clients.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean deleteById(Integer id) {
		boolean result = false;
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

	public boolean update(Client client) {
		boolean result = false;
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

	public boolean exportCsv(String csvFilePath) {
		return CsvParser.exportToCsv(getClients(), csvFilePath);
	}

	public boolean importCsv(String csvFilePath) {
		return addAll(CsvParser.importFromCsv(Client.class, csvFilePath));
	}
}

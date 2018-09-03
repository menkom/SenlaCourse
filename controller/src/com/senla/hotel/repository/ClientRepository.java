package com.senla.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.annotation.parser.CsvParser;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.api.IClientRepository;
import com.senla.util.IdGenerator;

public class ClientRepository implements IClientRepository {

	private static IClientRepository clientRepository;

	private static Integer lastId;
	private List<Client> clients;

	private static Integer getLastId() {
		return ClientRepository.lastId;
	}

	private static void setLastId(Integer lastId) {
		ClientRepository.lastId = lastId;
	}

	public ClientRepository() {
		super();
		clients = new ArrayList<Client>();
	}

	public static IClientRepository getInstance() {
		if (clientRepository == null) {
			clientRepository = DependencyInjection.getInstance().getInterfacePair(IClientRepository.class);
		}
		return clientRepository;
	}

	@Override
	public boolean add(Client client) {
		Boolean result = false;
		if (client.getId() != null) {
			result = clients.add(client);
		} else {
			Integer id = IdGenerator.generateId(getLastId());
			client.setId(id);
			result = clients.add(client);
			if (result) {
				setLastId(id);
			}
		}
		return result;
	}

	@Override
	public boolean addAll(List<Client> clients) {
		boolean result = getClients().addAll(clients);
		if (result) {
			setLastId(IdGenerator.getLastId(getClients()));
		}
		return result;
	}

	@Override
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

	@Override
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

	@Override
	public Client getClientByName(String name) {
		for (Client client : clients) {
			if ((client != null) && (client.getName().equalsIgnoreCase(name))) {
				return client;
			}
		}
		return null;
	}

	@Override
	public Client getClientById(Integer id) {
		for (Client client : getClients()) {
			if ((client != null) && (client.getId().equals(id))) {
				return client;
			}
		}
		return null;
	}

	@Override
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

	@Override
	public List<Client> getClients() {
		return clients;
	}

	@Override
	public boolean exportCsv(String csvFilePath) {
		return CsvParser.exportToCsv(getClients(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) {
		return addAll(CsvParser.importFromCsv(Client.class, csvFilePath));
	}
}

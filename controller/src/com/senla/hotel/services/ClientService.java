package com.senla.hotel.services;

import java.io.IOException;
import java.util.List;

import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.api.IClientRepository;
import com.senla.hotel.services.api.IClientService;
import com.senla.util.ExportCSV;

public class ClientService implements IClientService {

	private static IClientService clientService;

	private IClientRepository clientRepository;

	public ClientService() {
		super();
		this.clientRepository = (IClientRepository) DependencyInjection.getInstance()
				.getInterfacePair(IClientRepository.class);
	}

	private IClientRepository getClientRepository() {
		return clientRepository;
	}

	public static IClientService getInstance() {
		if (clientService == null) {
			clientService = (IClientService) DependencyInjection.getInstance().getInterfacePair(IClientService.class);
		}
		return clientService;
	}

	public boolean add(Client client) {
		return getClientRepository().add(client);
	}

	public boolean addAll(List<Client> clients) {
		return getClientRepository().addAll(clients);
	}

	public boolean update(Client client) {
		return getClientRepository().update(client);
	}

	public List<Client> getClients() {
		return getClientRepository().getClients();
	}

	public Client getClientByName(String name) {
		return getClientRepository().getClientByName(name);
	}

	public Client getClientById(int id) {
		return getClientRepository().getClientById(id);
	}

	public int getNumberOfClients() {
		return getClientRepository().getClients().size();
	}

	public boolean exportClientCSV(String name, String fileName) throws IOException {
		Client client = getClientByName(name);
		if (client == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(client.toString(), fileName);
		}
	}

	public boolean importClientsCSV(String file) throws IOException {
		boolean result = false;
		List<Client> clients = ExportCSV.getClientsFromCSV(file);
		for (Client client : clients) {
			if (getClientById(client.getId()) != null) {
				result = update(client);
			} else {
				result = add(client);
			}
			if (!result) {
				break;
			}

		}
		return result;
	}

	public boolean exportCsv(String csvFilePath) {
		return getClientRepository().exportCsv(csvFilePath);
	}

	public boolean importCsv(String csvFilePath) {
		return getClientRepository().importCsv(csvFilePath);
	}

}

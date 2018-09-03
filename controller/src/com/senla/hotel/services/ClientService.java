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
		this.clientRepository = DependencyInjection.getInstance().getInterfacePair(IClientRepository.class);
	}

	public static IClientService getInstance() {
		if (clientService == null) {
			clientService = DependencyInjection.getInstance().getInterfacePair(IClientService.class);
		}
		return clientService;
	}

	@Override
	public boolean add(Client client) {
		return clientRepository.add(client);
	}

	@Override
	public boolean addAll(List<Client> clients) {
		return clientRepository.addAll(clients);
	}

	@Override
	public boolean update(Client client) {
		return clientRepository.update(client);
	}

	@Override
	public List<Client> getClients() {
		return clientRepository.getClients();
	}

	@Override
	public Client getClientByName(String name) {
		return clientRepository.getClientByName(name);
	}

	@Override
	public Client getClientById(int id) {
		return clientRepository.getClientById(id);
	}

	@Override
	public int getNumberOfClients() {
		return clientRepository.getClients().size();
	}

	@Override
	public boolean exportClientCSV(String name, String fileName) throws IOException {
		Client client = getClientByName(name);
		if (client == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(client.toString(), fileName);
		}
	}

	@Override
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

	@Override
	public boolean exportCsv(String csvFilePath) {
		return clientRepository.exportCsv(csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) {
		return clientRepository.importCsv(csvFilePath);
	}

}

package com.senla.hotel.services;

import java.io.IOException;
import java.util.List;

import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.ClientRepository;
import com.senla.util.ExportCSV;

public class ClientService implements IService {

	private static ClientService clientService;

	private ClientRepository clientRepository;

	private ClientService() {
		super();
		this.clientRepository = ClientRepository.getInstance();
	}

	public static ClientService getInstance() {
		if (clientService == null) {
			clientService = new ClientService();
		}
		return clientService;
	}

	public Boolean add(Client client) {
		return getClientRepository().add(client);
	}

	public List<Client> getAllClients() {
		return getClientRepository().getClients();
	}

	public Client getClientByName(String name) throws NoEntryException {
		Client result = clientRepository.getClientByName(name);
		if (result == null)
			throw new NoEntryException(name);
		return result;
	}

	public int getNumberOfClients() {
		return getClientRepository().getClients().size();
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public Boolean exportClientCSV(String name) throws NoEntryException, IOException {
		Client client = getClientByName(name);
		if (client == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(client, "client_" + client.getId() + ".csv");
		}
	}

	public Boolean importClientCSV(String name) throws NoEntryException, IOException {
		return false;
	}

}

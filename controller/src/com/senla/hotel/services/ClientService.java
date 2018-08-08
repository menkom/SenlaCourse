package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.ClientRepository;
import com.senla.util.Serialization;

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

	public Boolean loadFromRaw(String filePath)
			throws IOException, NumberFormatException, ParseException, ClassNotFoundException {
		Boolean result = false;
		ClientRepository clients = Serialization.deserialize(filePath + "client.raw");

		if (clients != null) {
			clientRepository.setLastId(clients.getLastId());

			result = clientRepository.getClients().addAll(clients.getClients());
		}
		return result;
	}

	public Boolean saveToRaw(String filePath) throws IOException {
		Boolean result = false;
		result = Serialization.serialize(getClientRepository(), filePath + "client.raw");
		return result;
	}

	public int getNumberOfClients() {
		return getClientRepository().getClients().size();
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

}

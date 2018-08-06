package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
import com.senla.exception.NoEntryException;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.ClientRepository;

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

	public Boolean loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		Boolean result = false;
		String[] array = new TextFileWorker(filePath + "client.db").readFromFile();

		result = getClientRepository().getClients().addAll(ListConverter.getClients(array));
		return result;
	}

	public Boolean saveToFile(String filePath) {
		Boolean result = false;
		new TextFileWorker(filePath + "client.db")
				.writeToFile(ListConverter.getArrayFromList(clientRepository.getClients()));
		result = true;
		return result;
	}

	public int getNumberOfClients() {
		return getClientRepository().getClients().size();
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

}

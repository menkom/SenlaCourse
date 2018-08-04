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

	private ClientRepository clientRepository;

	private static ClientService clientService;

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

	public void add(Client client) {
		clientRepository.add(client);
	}

	public List<Client> getAllClients() {
		return clientRepository.getClients();
	}

	public Client getClientByName(String name) throws NoEntryException {
		Client result = clientRepository.getClientByName(name);
		if (result == null)
			throw new NoEntryException(name);
		return result;
	}

	public void loadFromFile(String filePath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(filePath + "client.db").readFromFile();

		clientRepository.getClients().addAll(ListConverter.getClients(array));
	}

	public void saveToFile(String filePath) {
		new TextFileWorker(filePath + "client.db")
				.writeToFile(ListConverter.getArrayFromList(clientRepository.getClients()));
	}

	public int getNumberOfClients() {
		return clientRepository.getClients().size();
	}

}

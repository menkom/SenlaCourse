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

	public String getFileName() {
		return "client.db";
	}

	public void add(Client element) {
		clientRepository.add(element);
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

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		String[] array = new TextFileWorker(dbPath + getFileName()).readFromFile();

		clientRepository.setClients(ListConverter.getClients(array));
	}

	public void saveToDB(String dbPath) {
		new TextFileWorker(dbPath + getFileName())
				.writeToFile(ListConverter.getArrayFromList(clientRepository.getClients()));
	}

	public int getNumberOfClients() {
		return clientRepository.getClients().size();
	}

}

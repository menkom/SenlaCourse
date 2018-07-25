package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.danco.training.TextFileWorker;
import com.senla.converter.ListConverter;
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

	public Client getClientByName(String name) {
		return clientRepository.getClientByName(name);
	}

	public void loadFromDB(String dbPath) throws IOException, NumberFormatException, ParseException {
		// ClientService service = new FileOperator().getClientService(dbPath +
		// getFileName());
		//
		// getRepository().setRepository(((ClientRepository)
		// service.getRepository()).getRepository());
	}

	public void saveToDB(String dbPath) {
		new TextFileWorker(dbPath + getFileName())
				.writeToFile(ListConverter.getArrayFromList(clientRepository.getClients()));
	}

	public int getNumberOfClients() {
		return clientRepository.getClients().size();
	}

}

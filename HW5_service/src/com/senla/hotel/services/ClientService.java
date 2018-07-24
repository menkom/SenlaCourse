package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;

import com.senla.hotel.model.Client;
import com.senla.hotel.repository.ClientRepository;
//import com.senla.util.FileOperator;

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

	public void saveToDB(String dbPath) throws IOException {
//		new FileOperator().saveToDB(dbPath + getFileName(), getStringToSave());
	}

	public String[] getStringToSave() {
		return clientRepository.toStringArray();
	}

	public int getNumberOfClients() {
		return clientRepository.getClients().size();
	}


}

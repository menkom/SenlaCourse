package com.senla.hotel.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
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

		clientRepository.setLastId(Integer.parseInt(array[0]));

		result = clientRepository.getClients()
				.addAll(ListConverter.getClients(Arrays.copyOfRange(array, 1, array.length)));
		return result;
	}

	public Boolean saveToFile(String filePath) {
		Boolean result = false;
		String[] repositoryToStr = ListConverter.getArrayFromList(clientRepository.getClients());
		String[] array = new String[repositoryToStr.length + 1];
		array[0] = Integer.toString(clientRepository.getLastId());
		System.arraycopy(repositoryToStr, 0, array, 1, repositoryToStr.length);

		new TextFileWorker(filePath + "client.db").writeToFile(array);
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

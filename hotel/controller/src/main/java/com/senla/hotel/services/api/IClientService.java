package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.List;

import com.senla.hotel.model.Client;

public interface IClientService {

	void add(Client client);

	void addAll(List<Client> clients);

	void update(Client client);

	List<Client> getClients();

	Client getClientById(int id);

	int getNumberOfClients();

	boolean exportClientCSV(int id, String fileName) throws IOException;

	boolean importClientsCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

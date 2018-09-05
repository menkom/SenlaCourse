package com.senla.hotel.services.api;

import java.io.IOException;
import java.util.List;

import com.senla.hotel.model.Client;

public interface IClientService {

	boolean add(Client client);

	boolean addAll(List<Client> clients);

	boolean update(Client client);

	List<Client> getClients();

	Client getClientByName(String name);

	Client getClientById(int id);

	int getNumberOfClients();

	boolean exportClientCSV(String name, String fileName) throws IOException;

	boolean importClientsCSV(String file) throws IOException;

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

package com.senla.hotel.repository.api;

import java.io.IOException;
import java.util.List;
import com.senla.hotel.model.Client;

public interface IClientRepository {

	boolean add(Client client);

	boolean addAll(List<Client> clients);

	boolean delete(String name);

	boolean deleteById(Integer id);

	Client getClientByName(String name);

	Client getClientById(Integer id);

	boolean update(Client client);

	List<Client> getClients();

	boolean exportCsv(String csvFilePath) throws IOException;

	boolean importCsv(String csvFilePath) throws IOException;

}

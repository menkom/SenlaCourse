package com.senla.hotel.services.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.senla.hotel.model.Client;

public interface IClientService {

	boolean add(Client client) throws SQLException;

	boolean addAll(List<Client> clients) throws SQLException;

	boolean update(Client client) throws SQLException;

	List<Client> getClients() throws SQLException;

	Client getClientById(int id) throws SQLException;

	int getNumberOfClients() throws SQLException;

	boolean exportClientCSV(int id, String fileName) throws IOException, SQLException;

	boolean importClientsCSV(String file) throws IOException, SQLException;

	boolean exportCsv(String csvFilePath) throws IOException, SQLException;

	boolean importCsv(String csvFilePath) throws IOException, SQLException;

}

package com.senla.hotel.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.model.Client;
import com.senla.hotel.services.api.IClientService;
import com.senla.util.ExportCSV;

public class ClientService implements IClientService {

	private static IClientService clientService;
	private DbConnector dbConnector;
	private IClientDao<Client> clientDao;

	@SuppressWarnings("unchecked")
	public ClientService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
		this.clientDao = DependencyInjection.getInstance().getInterfacePair(IClientDao.class);
	}

	public static IClientService getInstance() {
		if (clientService == null) {
			clientService = DependencyInjection.getInstance().getInterfacePair(IClientService.class);
		}
		return clientService;
	}

	@Override
	public boolean add(Client client) throws SQLException {
		return clientDao.add(dbConnector.getConnection(), client);
	}

	@Override
	public boolean addAll(List<Client> clients) throws SQLException {
		return clientDao.addAll(dbConnector.getConnection(), clients);
	}

	@Override
	public boolean update(Client client) throws SQLException {
		return clientDao.update(dbConnector.getConnection(), client);
	}

	@Override
	public List<Client> getClients() throws SQLException {
		return clientDao.getAll(dbConnector.getConnection(), "");
	}

	@Override
	public Client getClientById(int id) throws SQLException {
		return clientDao.getById(dbConnector.getConnection(), id);
	}

	@Override
	public int getNumberOfClients() throws SQLException {
		int result = 0;
		try (PreparedStatement ps = dbConnector.getConnection()
				.prepareStatement("SELECT count(client_id) count FROM `client`")) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		}
		return result;
	}

	@Override
	public boolean exportClientCSV(int id, String fileName) throws IOException, SQLException {
		Client client = getClientById(id);
		if (client == null) {
			return false;
		} else {
			return ExportCSV.saveCSV(client.toString(), fileName);
		}
	}

	@Override
	public boolean importClientsCSV(String file) throws IOException, SQLException {
		boolean result = false;
		List<Client> clients = ExportCSV.getClientsFromCSV(file);
		for (Client client : clients) {
			if (getClientById(client.getId()) != null) {
				result = update(client);
			} else {
				result = add(client);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException, SQLException {
		return clientDao.exportCsv(dbConnector.getConnection(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException, SQLException {
		return clientDao.importCsv(dbConnector.getConnection(), csvFilePath);
	}

}

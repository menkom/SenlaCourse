package com.senla.hotel.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.model.Client;
import com.senla.hotel.services.api.IClientService;
import com.senla.util.ExportCSV;

public class ClientService implements IClientService {

	private static final Logger logger = Logger.getLogger(ClientService.class);

	private static IClientService clientService;
	private DbConnector dbConnector;
	private IClientDao<Client> clientDao;

	@SuppressWarnings("unchecked")
	private ClientService() throws ClassNotFoundException {
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
		boolean result = false;
		Connection connection = dbConnector.getConnection();
		connection.setAutoCommit(false);
		try {
			result = clientDao.addAll(connection, clients);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e);
			connection.rollback();
			connection.setAutoCommit(true);
			throw e;
		}
		return result;
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
		return clientDao.getNumberOfClients(dbConnector.getConnection());
	}

	@Override
	public boolean exportClientCSV(int id, String fileName) throws IOException, SQLException {
		Client client = clientDao.getById(dbConnector.getConnection(), id);
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
			if (clientDao.getById(dbConnector.getConnection(), client.getId()) != null) {
				result = clientDao.update(dbConnector.getConnection(), client);
			} else {
				result = clientDao.add(dbConnector.getConnection(), client);
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

package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.annotation.parser.CsvParser;
import com.senla.dao.DaoHandler;
import com.senla.di.DependencyInjection;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.api.IClientRepository;

public class ClientDao implements IClientRepository {

	private static final Logger logger = Logger.getLogger(ClientDao.class);

	private static IClientRepository clientRepository;

	private ClientDao() {
		super();
	}

	public static IClientRepository getInstance() {
		if (clientRepository == null) {
			clientRepository = DependencyInjection.getInstance().getInterfacePair(IClientRepository.class);
		}
		return clientRepository;
	}

	@Override
	public boolean add(Client client) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("insert into client (client_id, client_name) values ("
					+ client.getId() + ",\'" + client.getName() + "\')");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean addAll(List<Client> clients) {
		int result = 0;
		if (clients.size() > 0) {
			StringBuilder query = new StringBuilder("insert into client (client_id, client_name) values ");
			for (Client client : clients) {
				query.append("(" + client.getId() + ",\'" + client.getName() + "\'),");
			}
			query.deleteCharAt(query.length() - 1);
			try {
				result = DaoHandler.getInstance().executeUpdate(query.toString());
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return result > 0;
	}

	@Override
	public boolean delete(String name) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from client where client_name=\'" + name + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from client  where client_id=\'" + id + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public Client getClientByName(String name) {
		Client client = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance()
					.executeQuery("select * from client where client_name=\'" + name + "\'");

			while (resultSet.next()) {
				client = parseResultSet(resultSet);
				return client;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return client;
	}

	@Override
	public Client getClientById(Integer id) {
		Client client = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from client where client_id=\'" + id + "\'");

			while (resultSet.next()) {
				client = parseResultSet(resultSet);
				return client;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return client;
	}

	@Override
	public boolean update(Client client) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("update client set client_name=\'" + client.getName()
					+ "\' where client_id=\'" + client.getId() + "\'");

		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public List<Client> getClients() {
		List<Client> resultList = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from client");

			while (resultSet.next()) {
				Client client = parseResultSet(resultSet);
				resultList.add(client);
			}

		} catch (SQLException e) {
			logger.error(e);
		}
		return resultList;
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		return CsvParser.exportToCsv(getClients(), csvFilePath);
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		return addAll(CsvParser.importFromCsv(Client.class, csvFilePath));
	}

	public Client parseResultSet(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getInt("client_id"));
		client.setName(resultSet.getString("client_name"));

		return client;
	}

}

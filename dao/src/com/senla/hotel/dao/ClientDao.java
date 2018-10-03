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
			result = DaoHandler.getInstance().executeUpdate(
					"insert into client (id, name) values (" + client.getId() + ",\'" + client.getName() + "\')");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean addAll(List<Client> clients) {
		int result = 0;
		if (clients.size() > 0) {
			StringBuilder query = new StringBuilder("insert into client (id, name) values ");
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
		System.out.println("addAll result:" + result);
		return result > 0;
	}

	@Override
	public boolean delete(String name) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from client where name=\'" + name + "\'");
		} catch (SQLException e) {
			logger.error(e);
		}
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = 0;
		try {
			result = DaoHandler.getInstance().executeUpdate("delete from client  where id=\'" + id + "\'");
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
			resultSet = DaoHandler.getInstance().executeQuery("select * from client where name=\'" + name + "\'");

			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));

				System.out.println("client by id : " + client);

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
			resultSet = DaoHandler.getInstance().executeQuery("select * from client where id=\'" + id + "\'");

			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));

				System.out.println("client by id : " + client);

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
			result = DaoHandler.getInstance().executeUpdate(
					"update client set name=\'" + client.getName() + "\' where id=\'" + client.getId() + "\'");

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
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));

				System.out.println("client : " + client);
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

}

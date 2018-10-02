package com.senla.hotel.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.dao.DaoHandler;
import com.senla.hotel.model.Client;
import com.senla.hotel.repository.api.IClientRepository;

public class ClientDao implements IClientRepository {

	private static final Logger logger = Logger.getLogger(ClientDao.class);

	@Override
	public boolean add(Client client) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(List<Client> clients) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client getClientByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClientById(Integer id) {
		Client client = null;
		ResultSet resultSet = null;
		try {
			resultSet = DaoHandler.getInstance().executeQuery("select * from client");

			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));

				System.out.println("client : " + client);

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}

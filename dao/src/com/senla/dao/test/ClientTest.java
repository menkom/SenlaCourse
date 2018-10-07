package com.senla.dao.test;

import java.sql.SQLException;
import java.util.List;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.hotel.dao.ClientDao;
import com.senla.hotel.model.Client;

public class ClientTest {

	public static void testClientDao() {

		ClientDao dao = new ClientDao();
		// getAll
		List<Client> clients;
		try {
			clients = dao.getAll(DbConnector.getInstance().getConnection(), "");
			for (Client client : clients) {
				System.out.println(client);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		// getById
		try {
			System.out.println("client[2] : " + dao.getById(DbConnector.getInstance().getConnection(), 3));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// add
//		try {
//			System.out.println(
//					"client add : " + dao.add(DbConnector.getInstance().getConnection(), new Client("simple name")));
//		} catch (SQLException e) {
//			System.out.println(e);
//		}

		// deleteById
		try {
			System.out.println("delete client.id=10 : " + dao.delete(DbConnector.getInstance().getConnection(), 10));
		} catch (SQLException e) {
			System.out.println(e);
		}

		// update
		try {
			Client client = dao.getById(DbConnector.getInstance().getConnection(), 11);
			client.setName(client.getName() + "1");
			System.out.println("client.id=11 update: " + dao.update(DbConnector.getInstance().getConnection(), client));
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}

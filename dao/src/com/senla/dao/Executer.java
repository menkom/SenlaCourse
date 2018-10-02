package com.senla.dao;

//import org.apache.log4j.Logger;

import com.senla.hotel.dao.ClientDao;
import com.senla.hotel.model.Client;

public class Executer {

//	private static final Logger logger = Logger.getLogger(Executer.class);

	public static void main(String[] args) {
		ClientDao clients = new ClientDao();

		clients.getClients();

		System.out.println("clients : " + clients.getClients().size());

		Client client = clients.getClients().get(0);

		System.out.println("client[0] : " + client.getName());

		client.setName(client.getName() + "1");

		clients.update(client);

		System.out.println("client[0] : " + client.getName());

		System.out.println("client[0] : " + clients.getClientById(1));

		DaoHandler.getInstance().closeConnection();
	}
}

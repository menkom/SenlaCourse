package com.senla.dao;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import com.senla.hotel.dao.ClientDao;
import com.senla.hotel.model.Client;

public class Executer {

//	private static final Logger logger = Logger.getLogger(Executer.class);

	public static void main(String[] args) {
		ClientDao clients = (ClientDao) ClientDao.getInstance();

		clients.getClients();

		System.out.println("clients : " + clients.getClients().size());

		Client client = clients.getClients().get(0);

		System.out.println("client[0] : " + client.getName());

		client.setName(client.getName() + "1");

		clients.update(client);

		System.out.println("client[0] : " + client.getName());

		System.out.println("client[2] : " + clients.getClientById(3));

		DaoHandler.getInstance().closeConnection();

//		client = new Client();
//		client.setName("Feel");
//		System.out.println("Client add:" + clients.add(client));
//
//		client.setId(14);
//		System.out.println("Client add:" + clients.add(client))

		List<Client> clientsToAdd = new ArrayList<>();

		clientsToAdd.add(new Client("Stranger 1"));
		clientsToAdd.add(new Client("Stranger 2"));
		clientsToAdd.add(new Client("Stranger 3"));

		System.out.println("Clients add all:" + clients.addAll(clientsToAdd));
	}
}

package com.senla.hotel.dao.api;

import org.hibernate.Session;

import com.senla.hotel.model.Client;

public interface IClientDao<T extends Client> extends IGenericDao<T> {

	int getNumberOfClients(Session session);

}
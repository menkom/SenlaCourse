package com.senla.hotel.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.model.Client;

public class ClientDao extends GenericDao<Client> implements IClientDao<Client> {

	@Override
	protected Class<Client> getTClass() {
		return Client.class;
	}

	@Override
	public int getNumberOfClients(Session session) {
		return (int) session.createCriteria(getTClass()).setProjection(Projections.rowCount()).uniqueResult();
	}

}

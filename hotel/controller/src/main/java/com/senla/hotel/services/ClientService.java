package com.senla.hotel.services;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.dao.api.IClientDao;
import com.senla.hotel.model.Client;
import com.senla.hotel.services.api.IClientService;
import com.senla.util.ExportCSV;

public class ClientService implements IClientService {

	private final static Logger logger = Logger.getLogger(ClientService.class);

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
	public void add(Client client) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			clientDao.add(session, client);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void addAll(List<Client> clients) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			clientDao.addAll(session, clients);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public void update(Client client) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			clientDao.update(session, client);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public List<Client> getClients() {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			List<Client> clients = clientDao.getAll(session, "");
			session.getTransaction().commit();
			return clients;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public Client getClientById(int id) {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Client client = clientDao.getById(session, id);
			session.getTransaction().commit();
			return client;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public int getNumberOfClients() {
		Session session = null;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();
			int result = clientDao.getNumberOfClients(session);
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean exportClientCSV(int id, String fileName) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			Client client = clientDao.getById(session, id);
			if (client != null) {
				result = ExportCSV.saveCSV(client.toString(), fileName);
			}

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importClientsCSV(String file) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			List<Client> clients = ExportCSV.getClientsFromCSV(file);
			for (Client client : clients) {
				if (clientDao.getById(session, client.getId()) != null) {
					clientDao.update(session, client);
				} else {
					clientDao.add(session, client);
				}
			}

			session.getTransaction().commit();
			result = true;
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean exportCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = clientDao.exportCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@Override
	public boolean importCsv(String csvFilePath) throws IOException {
		Session session = null;
		boolean result = false;
		try {
			session = dbConnector.getSession();
			session.beginTransaction();

			result = clientDao.importCsv(session, csvFilePath);

			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			throw e;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}

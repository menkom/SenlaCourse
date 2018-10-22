package com.senla.dao.dbconnector;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbConnector {

	private static final Logger logger = Logger.getLogger(DbConnector.class);

	private static DbConnector instance;

	private static SessionFactory sessionFactory;

	private DbConnector() {
		super();
		logger.info("DBConnector created.");
	}

	public static DbConnector getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new DbConnector();
		}
		return instance;
	}

	private SessionFactory getSessionFactory() {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			buildSessionFactory();
		}
		return sessionFactory;
	}

	private void buildSessionFactory() {
		Configuration configuration = new Configuration().configure();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		logger.info("SessionFactory built.");
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void closeSessionFactory() {
		if (sessionFactory != null && !(sessionFactory.isClosed())) {
			sessionFactory.close();
		}
	}
}
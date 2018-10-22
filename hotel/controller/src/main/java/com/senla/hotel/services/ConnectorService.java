package com.senla.hotel.services;

import com.senla.dao.dbconnector.DbConnector;
import com.senla.di.DependencyInjection;
import com.senla.hotel.services.api.IConnectorService;

public class ConnectorService implements IConnectorService {

	private static IConnectorService connectorService;

	private DbConnector dbConnector;

	public static IConnectorService getInstance() {
		if (connectorService == null) {
			connectorService = DependencyInjection.getInstance().getInterfacePair(IConnectorService.class);
		}
		return connectorService;
	}

	private ConnectorService() throws ClassNotFoundException {
		super();
		dbConnector = DbConnector.getInstance();
	}

	@Override
	public void CloseConnection() {
		dbConnector.closeSessionFactory();
	}

}

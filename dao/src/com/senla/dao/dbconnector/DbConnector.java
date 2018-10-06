package com.senla.dao.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DbConnector {

	private static final String ERROR_CLOSING_CONNECTION = "Error closing connection.";
	private static final String ERROR_LOADING_JDBC_DRIVER = "Error loading jdbc driver.";
	private static final String ERROR_TRYING_TO_CONNECT = "Error trying to connect to %s.";

	private static final Logger logger = Logger.getLogger(DbConnector.class);

	private static final String url = "jdbc:mysql://" + DbProperty.getInstance().getProp("dbServer") + ":3306/"
			+ DbProperty.getInstance().getProp("dbName") + "?serverTimezone=Europe/Moscow";

	private static DbConnector instance;

	private Connection connection;

	private DbConnector() {
		init();
	}

	public static DbConnector getInstance() {
		if (instance == null) {
			instance = new DbConnector();
		}
		return instance;
	}

	private void init() {
		try {
			Class.forName(DbProperty.getInstance().getProp("driverClass"));
		} catch (ClassNotFoundException e) {
			logger.error(ERROR_LOADING_JDBC_DRIVER, e);
		}
	}

	private void connect() {
		try {
			connection = DriverManager.getConnection(url, DbProperty.getInstance().getProp("dbUser"),
					DbProperty.getInstance().getProp("dbPass"));
		} catch (SQLException e) {
			logger.error(String.format(ERROR_TRYING_TO_CONNECT, url), e);
		}
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connect();
			}
		} catch (SQLException e) {
			logger.error(String.format(ERROR_TRYING_TO_CONNECT, url), e);
		}
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null && !(connection.isClosed())) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error(ERROR_CLOSING_CONNECTION, e);
		}
	}

}

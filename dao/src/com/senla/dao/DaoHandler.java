package com.senla.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DaoHandler {

	private static final String ERROR_CLOSING_CONNECTION = "Error closing connection.";
	private static final String ERROR_LOADING_JDBC_DRIVER = "Error loading jdbc driver.";
	private static final String ERROR_TRYING_TO_CONNECT = "Error trying to connect to %s.";

	private static final String driverClass = DaoProperty.getInstance().getDriverClass();
	private static final String url = "jdbc:mysql://" + DaoProperty.getInstance().getDbServer() + ":3306/"
			+ DaoProperty.getInstance().getDbName() + "?serverTimezone=Europe/Moscow";

	private static final Logger logger = Logger.getLogger(DaoHandler.class);

	private static DaoHandler instance;

	private Connection connection;

	private DaoHandler() {
		init();
	}

	public static DaoHandler getInstance() {
		if (instance == null) {
			instance = new DaoHandler();
		}
		return instance;
	}

	private void init() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			System.out.println(ERROR_LOADING_JDBC_DRIVER);
			logger.error(ERROR_LOADING_JDBC_DRIVER, e);
		}
	}

	private Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, DaoProperty.getInstance().getDbUser(),
						DaoProperty.getInstance().getDbPass());
			}
		} catch (SQLException e) {
			System.out.println(ERROR_TRYING_TO_CONNECT);
			logger.error(ERROR_TRYING_TO_CONNECT, e);
		}
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null && !(connection.isClosed())) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(ERROR_CLOSING_CONNECTION);
			logger.error(ERROR_CLOSING_CONNECTION, e);
		}
	}

	public ResultSet executeQuery(String query) throws SQLException {
		System.out.println("executeQuery:" + query);
		ResultSet result = null;
		Connection conn = DaoHandler.getInstance().getConnection();

		Statement statement = conn.createStatement();

		if (statement != null) {
			result = statement.executeQuery(query);
		}
		return result;
	}

	public int executeUpdate(String query) throws SQLException {
		System.out.println("executeUpdate:" + query);
		int result = 0;
		Connection conn = DaoHandler.getInstance().getConnection();

		Statement statement = conn.createStatement();

		if (statement != null) {
			result = statement.executeUpdate(query);
		}
		return result;
	}
}

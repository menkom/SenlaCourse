package com.senla.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DaoProperty {

	private static final Logger logger = Logger.getLogger(DaoProperty.class);
	private static DaoProperty instance;

	private String driverClass;
	private String dbServer;
	private String dbName;
	private String dbUser;
	private String dbPass;

	private Properties prop = new Properties();

	private DaoProperty() {
		load();
	}

	public static DaoProperty getInstance() {
		if (instance == null) {
			instance = new DaoProperty();
		}
		return instance;
	}

	private void loadProperties() {
		this.driverClass = prop.getProperty("driverClass");
		this.dbServer = prop.getProperty("dbServer");
		this.dbName = prop.getProperty("dbName");
		this.dbUser = prop.getProperty("dbUser");
		this.dbPass = prop.getProperty("dbPass");
	}

	private void load() {
		try (FileInputStream fileInputStream = new FileInputStream("dao.properties")) {

			prop.load(fileInputStream);
			loadProperties();

		} catch (IOException e) {
			System.out.println(e);
			logger.error(e);
		}
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getDbServer() {
		return dbServer;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

}

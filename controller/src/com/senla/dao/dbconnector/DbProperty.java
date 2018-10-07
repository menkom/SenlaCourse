package com.senla.dao.dbconnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbProperty {

	private static final Logger logger = Logger.getLogger(DbProperty.class);
	private static DbProperty instance;

	private Properties properties = new Properties();

	private DbProperty() {
		load();
	}

	public static DbProperty getInstance() {
		if (instance == null) {
			instance = new DbProperty();
		}
		return instance;
	}

	private void load() {
		try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
			properties.load(fileInputStream);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public String getProp(String key) {
		return properties.getProperty(key, "");
	}
}

package com.senla.dao.dbconnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbProperty {

	private static final String ERROR_DB_PROPERTY_FILE_NOT_FOUND = "Error. DB property file not found at address \"%s\".";
	private static final String ERROR_DB_PROPERTY_FILE_LOAD = "Error DB property file load.";
	private static final String INSTANCE_PATH = "./db.properties";
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
		File file = new File(INSTANCE_PATH);
		if (file.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				properties.load(fileInputStream);
			} catch (IOException e) {
				logger.error(ERROR_DB_PROPERTY_FILE_LOAD, e);
			}
		} else {
			logger.error(String.format(ERROR_DB_PROPERTY_FILE_NOT_FOUND, file.getAbsolutePath()));
		}
	}

	public String getProp(String key) {
		return properties.getProperty(key, "");
	}
}

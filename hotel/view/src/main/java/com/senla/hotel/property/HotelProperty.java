package com.senla.hotel.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HotelProperty {

	private static final String ERROR_HOTEL_PROPERTY_FILE_NOT_FOUND = "Error. Hotel property file not found at address \"%s\".";
	private static final String ERROR_HOTEL_PROPERTY_FILE_LOAD = "Error hotel property file load.";
	private static final String INSTANCE_PATH = "./hotel.properties";

	private static final Logger logger = Logger.getLogger(HotelProperty.class);
	private static HotelProperty instance;

	private String name;
	private boolean isAbleChangeRoomStatus;
	private int lastVisibleOrders;
	private String rawFilePath;
	private String csvFilePath;

	private HotelProperty() {
		loadProperties();
	}

	public static HotelProperty getInstance() {
		if (instance == null) {
			instance = new HotelProperty();
		}
		return instance;
	}

	private void loadProperties() {
		FileInputStream fileInputStream;
		Properties prop = new Properties();

		File file = new File(INSTANCE_PATH);
		if (file.exists()) {
			try {
				fileInputStream = new FileInputStream(file);
				prop.load(fileInputStream);

				this.name = prop.getProperty("name");
				this.isAbleChangeRoomStatus = Boolean.parseBoolean(prop.getProperty("isAbleChangeRoomStatus"));
				this.lastVisibleOrders = Integer.parseInt(prop.getProperty("lastVisibleOrders"));
				this.rawFilePath = prop.getProperty("rawFilePath");
				this.csvFilePath = prop.getProperty("csvFilePath");
			} catch (IOException e) {
				logger.error(ERROR_HOTEL_PROPERTY_FILE_LOAD, e);
			}
		} else {
			logger.error(String.format(ERROR_HOTEL_PROPERTY_FILE_NOT_FOUND, file.getAbsolutePath()));
		}
	}

	public String getName() {
		return name;
	}

	public boolean isAbleChangeRoomStatus() {
		return isAbleChangeRoomStatus;
	}

	public int getLastVisibleOrders() {
		return lastVisibleOrders;
	}

	public String getRawFilePath() {
		return rawFilePath;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

}

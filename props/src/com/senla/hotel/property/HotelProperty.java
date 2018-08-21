package com.senla.hotel.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HotelProperty {

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

		try {

			fileInputStream = new FileInputStream("hotel.properties");
			prop.load(fileInputStream);

			this.name = prop.getProperty("name");
			this.isAbleChangeRoomStatus = Boolean.parseBoolean(prop.getProperty("isAbleChangeRoomStatus"));
			this.lastVisibleOrders = Integer.parseInt(prop.getProperty("lastVisibleOrders"));
			this.rawFilePath = prop.getProperty("rawFilePath");
			this.csvFilePath = prop.getProperty("csvFilePath");

		} catch (IOException e) {
			logger.error(e);
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

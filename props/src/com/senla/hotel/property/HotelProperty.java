package com.senla.hotel.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.util.DisplayOperator;

public class HotelProperty {

	private static HotelProperty instance;
	private static final Logger logger = Logger.getLogger(HotelProperty.class);

	private String name;
	private boolean isAbleChangeRoomStatus;
	private int lastVisibleOrders;

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

		DisplayOperator.printMessage("Properties load started.");

		try {

			fileInputStream = new FileInputStream("hotel.properties");
			prop.load(fileInputStream);

			this.name = prop.getProperty("name");
			this.isAbleChangeRoomStatus = Boolean.parseBoolean(prop.getProperty("isAbleChangeRoomStatus"));
			this.lastVisibleOrders = Integer.parseInt(prop.getProperty("lastVisibleOrders"));

		} catch (IOException e) {
			DisplayOperator.printMessage("Properties file error. File hotel.propeties not found.");
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

}

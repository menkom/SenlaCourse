package com.senla.datahelp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.senla.hotel.facade.Hotel;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;

public class DataFiller {

	private Hotel hotel;

	public DataFiller() {
		super();
		this.hotel = Hotel.getInstance();
	}

	public void runDataIO() throws IOException, ParseException {

		addClients();

		addRooms();

		addServices();

		addOrders();

		addOrderServices();
	}

	public void addClients() {
		hotel.addClient("Customer One");
		hotel.addClient("John");
		hotel.addClient("Mike");
		hotel.addClient("Tim");
		hotel.addClient("Alex");
	}

	public void addRooms() throws ParseException {
		hotel.addRoom(11, 2, RoomStar.ONE, RoomStatus.AVAILABLE, 10);
		hotel.addRoom(22, 4, RoomStar.ONE, RoomStatus.AVAILABLE, 10);
		hotel.addRoom(33, 1, RoomStar.TWO, RoomStatus.AVAILABLE, 20);
		hotel.addRoom(44, 6, RoomStar.FOUR, RoomStatus.AVAILABLE, 40);
		hotel.addRoom(55, 2, RoomStar.FIVE, RoomStatus.AVAILABLE, 80);
		hotel.addRoom(66, 1, RoomStar.THREE, RoomStatus.AVAILABLE, 30);
		hotel.addRoom(77, 2, RoomStar.THREE, RoomStatus.AVAILABLE, 35);
	}

	public void addServices() {
		hotel.addService(1, "Cleaning", 5);
		hotel.addService(2, "Delivery", 10);
		hotel.addService(3, "Ironing", 3);
		hotel.addService(4, "Music", 15);
	}

	public void addOrders() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		hotel.addOrder(1, "Customer One", 11, formatter.parse("10/07/2018"), formatter.parse("14/07/2018"));

		hotel.addOrder(2, "John", 22, formatter.parse("11/07/2018"), formatter.parse("12/07/2018"));

		hotel.addOrder(3, "Mike", 33, formatter.parse("08/07/2018"), formatter.parse("10/07/2018"));

		hotel.orderRoom("Tim", 44, formatter.parse("14/07/2018"), formatter.parse("16/07/2018"));

		hotel.orderRoom("Customer One", 44, formatter.parse("10/07/2018"), formatter.parse("12/07/2018"));

		hotel.orderRoom("Alex", 55, new Date(), null);

		hotel.freeRoom(6);
	}

	public void addOrderServices() {

		hotel.addOrderService(1, 2);
		hotel.addOrderService(1, 3);
		hotel.addOrderService(1, 1);

		hotel.addOrderService(2, 1);
		hotel.addOrderService(3, 1);

	}
}

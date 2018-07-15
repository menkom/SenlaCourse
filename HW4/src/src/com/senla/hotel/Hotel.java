package com.senla.hotel;

import java.util.Date;

import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.util.DispayOperator;

public class Hotel extends AHotel {

	public Hotel() {
		super();

	}

	protected void finalize() throws Throwable {
		getClientService().saveToDB();
		getRoomService().saveToDB();
		getServiceService().saveToDB();
		getOrderService().saveToDB();

		super.finalize();
	}

	public void addClient(String name) {
		new DispayOperator().printMessage("Hotel.addClient. Client name>" + name);;
		getClientService().add(new Client(name));
		new DispayOperator().printMessage("Hotel.addClient. Clients added.");;
	}

	public void addRoom(int number, int capacity, RoomStar star, RoomStatus status, int price) {
		getRoomService().addRoom(number, capacity, star, status, price);
	}

	public void addService(int code, String name, int price) {
		getServiceService().addService(code, name, price);
	}

	public void addOrder(int num, String clientName, int roomNum, Date startDate, Date finishDate) {

		getOrderService().addOrder(num, clientName, roomNum, startDate, finishDate);
	}

	public void orderRoom(String clientName, int roomNum, Date dateStart, Date dateFinish) {
		getOrderService().orderRoom(roomNum, clientName, dateStart, dateFinish);
	}

}

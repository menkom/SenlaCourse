package com.senla.hotel;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import com.senla.hotel.comparator.OrderSortByClientName;
import com.senla.hotel.comparator.OrderSortByFinishDate;
import com.senla.hotel.comparator.RoomSortByCapacity;
import com.senla.hotel.comparator.RoomSortByPrice;
import com.senla.hotel.comparator.RoomSortByStar;
import com.senla.hotel.enums.EnumOrderSort;
import com.senla.hotel.enums.EnumRoomSort;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;

public class Hotel extends AHotel {

	public Hotel() {
		super();

	}

	public void load() throws NumberFormatException, IOException, ParseException {

		getClientService().loadFromDB();
		getRoomService().loadFromDB();
		getServiceService().loadFromDB();
		getOrderService().loadFromDB();

	}

	public void save() throws Throwable {
		getClientService().saveToDB();
		getRoomService().saveToDB();
		getServiceService().saveToDB();
		getOrderService().saveToDB();
	}

	public void addClient(String name) {
		getClientService().add(new Client(name));
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

	public void addOrderService(int orderNum, int serviceCode) {
		getOrderService().addOrderService(orderNum, serviceCode);
	}

	public void freeRoom(int orderNum) {
		getOrderService().freeRoom(orderNum);
	}

	public Room[] getAllRooms(EnumRoomSort sortType) {
		Room[] result = getRoomService().getAllRooms();
		switch (sortType) {
		case BY_PRICE:
			Arrays.sort(getRoomService().getAllRooms(), new RoomSortByPrice());
		case BY_CAPACITY:
			Arrays.sort(getRoomService().getAllRooms(), new RoomSortByCapacity());
		case BY_STAR:
			Arrays.sort(getRoomService().getAllRooms(), new RoomSortByStar());
		}
		return result;
	}

	public Room[] getFreeRooms(EnumRoomSort sortType) {
		Room[] result = getRoomService().getFreeRooms();
		switch (sortType) {
		case BY_PRICE:
			Arrays.sort(result, new RoomSortByPrice());
		case BY_CAPACITY:
			Arrays.sort(result, new RoomSortByCapacity());
		case BY_STAR:
			Arrays.sort(result, new RoomSortByStar());
		}
		return result;
	}

	public int getNumberOfFreeRooms() {
		return getRoomService().getNumberOfFreeRooms();
	}

	public int getNumberOfClients() {
		return getClientService().getNumberOfClients();
	}

	public Order[] getClientRoom(EnumOrderSort sortType) {
		Order[] result = getOrderService().getClientRoom();

		switch (sortType) {
		case BY_NAME:
			Arrays.sort(result, new OrderSortByClientName());
		case BY_FINISH_DATE:
			Arrays.sort(result, new OrderSortByFinishDate());
		}

		return result;
	}

	public void changeRoomStatus(int roomNum, RoomStatus roomStatus) {
		getRoomService().changeRoomStatus(roomNum, roomStatus);
	}

	public void changeRoomPrice(int roomNum, int newPrice) {
		getRoomService().changeRoomPrice(roomNum, newPrice);
	}

	public void changeServicePrice(int code, int price) {
		getRoomService().changeRoomPrice(code, price);
	}
}

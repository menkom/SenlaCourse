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
import com.senla.hotel.comparator.ServiceSortByPrice;
import com.senla.hotel.enums.RoomStar;
import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.model.Client;
import com.senla.hotel.model.Order;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.Service;
import com.senla.util.DisplayOperator;

public class Hotel extends AHotel {

	public Hotel() {
		super();

	}

	public void load(String dbPath) throws NumberFormatException, IOException, ParseException {
		getClientService().loadFromDB(dbPath);
		getRoomService().loadFromDB(dbPath);
		getServiceService().loadFromDB(dbPath);
		getOrderService().loadFromDB(dbPath);
	}

	public void save(String dbPath) throws Throwable {
		getClientService().saveToDB(dbPath);
		getRoomService().saveToDB(dbPath);
		getServiceService().saveToDB(dbPath);
		getOrderService().saveToDB(dbPath);
	}

	public Room[] getAllRoomsSortByPrice() {
		Room[] result = getRoomService().getAllRooms();
		Arrays.sort(result, new RoomSortByPrice());
		return result;
	}

	public void getAllRoomsSortByCapacity() {
		Room[] result = getRoomService().getAllRooms();
		Arrays.sort(result, new RoomSortByCapacity());
		DisplayOperator.printRooms(result);
	}

	public Room[] getAllRoomsSortByStar() {
		Room[] result = getRoomService().getAllRooms();
		Arrays.sort(result, new RoomSortByStar());
		return result;
	}

	public Room[] getFreeRoomsSortByPrice() {
		Room[] result = getRoomService().getFreeRooms();
		Arrays.sort(result, new RoomSortByPrice());
		return result;
	}

	public Room[] getFreeRoomsSortByCapacity() {
		Room[] result = getRoomService().getFreeRooms();
		Arrays.sort(result, new RoomSortByCapacity());
		return result;
	}

	public Room[] getFreeRoomsSortByStar() {
		Room[] result = getRoomService().getFreeRooms();
		Arrays.sort(result, new RoomSortByStar());
		return result;
	}

	public int getNumberOfFreeRooms() {
		return getRoomService().getNumberOfFreeRooms();
	}

	public int getNumberOfClients() {
		return getClientService().getNumberOfClients();
	}

	public Order[] getActiveOrdersSortByName() {
		Order[] result = getOrderService().getActiveOrders();
		Arrays.sort(result, new OrderSortByClientName());
		return result;
	}

	public Order[] getActiveOrdersSortByFinishDate() {
		Order[] result = getOrderService().getActiveOrders();
		Arrays.sort(result, new OrderSortByFinishDate());
		return result;
	}

	public Room[] getFreeRoomsByDateSortByPrice(Date date) {
		Room[] result = getRoomService().getFreeRooms(date);
		Arrays.sort(result, new RoomSortByPrice());
		return result;
	}

	public Room[] getFreeRoomsByDateSortByCapacity(Date date) {
		Room[] result = getRoomService().getFreeRooms(date);
		Arrays.sort(result, new RoomSortByCapacity());
		return result;
	}

	public Room[] getFreeRoomsByDateSortByStar(Date date) {
		Room[] result = getRoomService().getFreeRooms(date);
		Arrays.sort(result, new RoomSortByStar());
		return result;
	}

	public int getOrderPrice(int orderNum) {
		return getOrderService().getOrderPrice(orderNum);
	}

	public Room getRoomByNum(int roomNum) {
		// TODO Do I need to print here using Printer or send info and print after
		return getRoomService().getRoomByNum(roomNum);
	}

	public Order[] getLastThreeOrdersByRoom(int roomNum) {
		return getOrderService().getLastThreeOrdersByRoom(roomNum);
	}

	public Service[] getOrderServices(int orderNum) {
		Service[] result = getOrderService().getOrderByNum(orderNum).getServices();
		Arrays.sort(result, new ServiceSortByPrice());
		return result;
	}

	public Service[] getAllServicesSortByPrice() {
		Service[] result = getServiceService().getAllServices();
		Arrays.sort(result, new ServiceSortByPrice());
		return result;
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

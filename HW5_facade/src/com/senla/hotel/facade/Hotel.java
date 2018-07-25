package com.senla.hotel.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
import com.senla.hotel.services.ClientService;
import com.senla.hotel.services.OrderService;
import com.senla.hotel.services.RoomService;
import com.senla.hotel.services.ServiceService;

public class Hotel {

	private ClientService clientService;
	private RoomService roomService;
	private ServiceService serviceService;
	private OrderService orderService;

	private static Hotel hotel;

	private Hotel() {
		super();
		clientService = ClientService.getInstance();
		roomService = RoomService.getInstance();
		serviceService = ServiceService.getInstance();
		orderService = OrderService.getInstance();
	}

	public static Hotel getInstance() {
		if (hotel == null) {
			hotel = new Hotel();
		}
		return hotel;
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

	public List<Room> getAllRoomsSortByPrice() {
		List<Room> result = getRoomService().getAllRooms();
		result.sort(new RoomSortByPrice());
		return result;
	}

	public List<Room> getAllRoomsSortByCapacity() {
		List<Room> result = getRoomService().getAllRooms();
		result.sort(new RoomSortByCapacity());
		return result;
	}

	public List<Room> getAllRoomsSortByStar() {
		List<Room> result = getRoomService().getAllRooms();
		result.sort(new RoomSortByStar());
		return result;
	}

	public List<Room> getFreeRoomsSortByPrice() {
		List<Room> result = getRoomService().getFreeRooms();
		result.sort(new RoomSortByPrice());
		return result;
	}

	public List<Room> getFreeRoomsSortByCapacity() {
		List<Room> result = getRoomService().getFreeRooms();
		result.sort(new RoomSortByCapacity());
		return result;
	}

	public List<Room> getFreeRoomsSortByStar() {
		List<Room> result = getRoomService().getFreeRooms();
		result.sort(new RoomSortByStar());
		return result;
	}

	public int getNumberOfFreeRooms() {
		return getRoomService().getNumberOfFreeRooms();
	}

	public int getNumberOfClients() {
		return getClientService().getNumberOfClients();
	}

	public List<Order> getActiveOrdersSortByName() {
		List<Order> result = getOrderService().getActiveOrders();
		result.sort(new OrderSortByClientName());
		return result;
	}

	public List<Order> getActiveOrdersSortByFinishDate() {
		List<Order> result = getOrderService().getActiveOrders();
		result.sort(new OrderSortByFinishDate());
		return result;
	}

	public List<Room> getFreeRoomsByDateSortByPrice(Date date) {
		List<Room> result = getRoomService().getFreeRooms(date);
		result.sort(new RoomSortByPrice());
		return result;
	}

	public List<Room> getFreeRoomsByDateSortByCapacity(Date date) {
		List<Room> result = getRoomService().getFreeRooms(date);
		result.sort(new RoomSortByCapacity());
		return result;
	}

	public List<Room> getFreeRoomsByDateSortByStar(Date date) {
		List<Room> result = getRoomService().getFreeRooms(date);
		result.sort(new RoomSortByStar());
		return result;
	}

	public int getOrderPrice(int orderNum) {
		return getOrderService().getOrderPrice(orderNum);
	}

	public Room getRoomByNum(int roomNum) {
		// TODO Do I need to print here using Printer or send info and print after
		return getRoomService().getRoomByNum(roomNum);
	}

	public List<Order> getLastThreeOrdersByRoom(int roomNum) {
		List<Order> result = getOrderService().getLastThreeOrdersByRoom(roomNum);
		result.sort(new OrderSortByFinishDate());
		return result;
	}

	public List<Service> getOrderServices(int orderNum) {
		List<Service> result = getOrderService().getOrderByNum(orderNum).getServices();
		result.sort(new ServiceSortByPrice());
		return result;
	}

	public List<Service> getAllServicesSortByPrice() {
		List<Service> result = getServiceService().getAllServices();
		result.sort(new ServiceSortByPrice());
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

	public ClientService getClientService() {
		return clientService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public List<Client> getAllClients() {
		return getClientService().getAllClients();
	}
}
